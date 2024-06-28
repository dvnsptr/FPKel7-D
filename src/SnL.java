/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 7
 * Members  :
 * 1. 5026231020 - Diva Nesia Putri
 * 2. 5026231162 - I Nyoman Mahadyana Bhaskara
 * 3. 5026231211 - Hafidz Putra Dermawan
 * ------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SnL {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;
    private int level;
    private HashMap<Player, Integer> scores;

    public SnL(int s) {
        this.boardSize = s;
        this.players = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.gameStatus = 0;
        this.nowPlaying = -1;
        this.level = 1;
        this.scores = new HashMap<>();
    }

    public void setBoardSize(int s) {
        this.boardSize = s;
    }

    public void setGameStatus(int s) {
        this.gameStatus = s;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        Scanner read = new Scanner(System.in);

        int playerCount;
        while (true) {
            try {
                System.out.println("Please enter number of players (2-4): ");
                playerCount = read.nextInt();
                read.nextLine();
                if (playerCount < 2 || playerCount > 4) {
                    System.out.println("Invalid number of player! Please enter number between 2 and 4!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter number between 2 and 4!");
                read.nextLine();
            }
        }

        for (int i = 1; i <= playerCount; i++) {
            System.out.println("Please enter Player " + i + " name: ");
            String playerName = read.nextLine();
            Player player = new Player(playerName);
            addPlayer(player);
        }

        int gameMode = getGameModeChoice(read);

        if (gameMode == 1) {
            playSingleGame(read);
        } else {
            playAllLevels(read);
        }

        System.out.println("Game Over! Here is the Final Scores!");
        for (Player player : players) {
            System.out.println(player.getName() + " with score: " + player.getScore());
        }
    }

    private int getGameModeChoice(Scanner read) {
        int choice;
        do {
            System.out.println("Choose game mode:");
            System.out.println("1. Play single game");
            System.out.println("2. Play all 3 levels");
            choice = read.nextInt();
            read.nextLine();
        } while (choice < 1 || choice > 2);
        return choice;
    }

    private void playSingleGame(Scanner read) {
        System.out.println();
        System.out.println("Starting single game");
        initiateLevel(1);
        resetPlayerPositions();
        this.gameStatus = 0;
        playLevel(read);
    }

    private void playAllLevels(Scanner read) {
        while (this.level <= 3) {
            System.out.println();
            System.out.println("Starting level " + this.level);
            initiateLevel(this.level);
            resetPlayerPositions();
            this.gameStatus = 0;
            playLevel(read);
            this.level++;
        }
    }

    private void playLevel(Scanner read) {
        Player playerInTurn;

        do {
            playerInTurn = getWhoseTurn();
            boolean extraTurn;
            do {
                extraTurn = false;
                System.out.println("Now playing: " + playerInTurn.getName());
                System.out.println(playerInTurn.getName() + ", please press ENTER to roll the dice!");
                System.out.println();
                String enter = read.nextLine();
                int diceRoll = 0;
                if (enter.isEmpty()) {
                    diceRoll = playerInTurn.rollDice();
                }
                System.out.println("Dice number: " + diceRoll);
                movePlayerAround(playerInTurn, diceRoll);
                System.out.println("New position: " + playerInTurn.getPosition());
                System.out.println("============================================");

                if (diceRoll == 6) {
                    extraTurn = true;
                    System.out.println(playerInTurn.getName() + " rolled a 6 and gets an extra turn!");
                }
            } while (extraTurn && getGameStatus() != 2);

        } while (getGameStatus() != 2);

        playerInTurn.setScore(playerInTurn.getScore() + 1);
        System.out.println("Congrats! The Winner is: " + playerInTurn.getName() + " with Score: " + playerInTurn.getScore());
        System.out.println();
    }

    public void addPlayer(Player s) {
        this.players.add(s);
        this.scores.put(s, 0);
    }

    public void addSnake(Snake s) {
        this.snakes.add(s);
    }

    public void addSnakes(int[][] s) {
        for (int[] snake : s) {
            Snake newSnake = new Snake(snake[0], snake[1]);
            this.snakes.add(newSnake);
        }
    }

    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }

    public void addLadders(int[][] l) {
        for (int[] ladder : l) {
            Ladder newLadder = new Ladder(ladder[1], ladder[0]);
            this.ladders.add(newLadder);
        }
    }

    public void initiateGame() {
        int[][] l = {
                {2, 23}, {8, 34}, {20, 77}, {32, 68},
                {41, 79}, {74, 88}, {82, 100}, {85, 95}
        };
        addLadders(l);

        int[][] s = {
                {5, 47}, {9, 29}, {15, 38}, {25, 97},
                {33, 53}, {37, 62}, {54, 86}, {70, 92}
        };
        addSnakes(s);
    }

    public void initiateLevel(int level) {
        this.snakes.clear();
        this.ladders.clear();
        if (level == 1) {
            initiateGame();
        } else {
            int laddersCount = Math.max(10 - level, 1);
            int snakesCount = Math.min(level + 5, boardSize / 2);

            int[][] ladders = new int[laddersCount][2];
            int[][] snakes = new int[snakesCount][2];

            for (int i = 0; i < laddersCount; i++) {
                ladders[i][0] = (int) (Math.random() * (boardSize - 1)) + 1;
                ladders[i][1] = (int) (Math.random() * (boardSize - ladders[i][0])) + ladders[i][0] + 1;
            }

            for (int i = 0; i < snakesCount; i++) {
                snakes[i][0] = (int) (Math.random() * (boardSize - 1)) + 1;
                snakes[i][1] = (int) (Math.random() * snakes[i][0]) + 1;
            }

            addLadders(ladders);
            addSnakes(snakes);
        }
    }

    public void movePlayerAround(Player p, int x) {
        p.moveAround(x, this.boardSize);
        boolean opponentMoved = false;

        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getBottomPosition()) {
                System.out.println(p.getName() + ", you got Ladder from " + l.getBottomPosition() + " to " + l.getTopPosition());
                p.setPosition(l.getTopPosition());
            }
        }
        for (Snake s : this.snakes) {
            if (p.getPosition() == s.getHeadPosition()) {
                p.setPosition(s.getTailPosition());
                System.out.println(p.getName() + " you get snake head from " + s.getHeadPosition() + " slide down to " + s.getTailPosition());
            }
        }
        if (!opponentMoved && (p.getPosition() == 15 || p.getPosition() == 53 || p.getPosition() == 75 || p.getPosition() == 92)) {
            moveOpponentBack(p);
            opponentMoved = true;
        }
        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    public Player getWhoseTurn() {
        if (this.gameStatus == 0) {
            this.gameStatus = 1;
            this.nowPlaying = 0;
            return this.players.get(this.nowPlaying);
        } else {
            this.nowPlaying = (this.nowPlaying + 1) % this.players.size();
            return this.players.get(this.nowPlaying);
        }
    }

    private void moveOpponentBack(Player currentPlayer) {
        for (Player player : players) {
            if (!player.equals(currentPlayer)) {
                player.setPosition(Math.max(0, player.getPosition() - 5));
                System.out.println(player.getName() + " has been moved back 5 spaces.");
            }
        }
    }

    private void resetPlayerPositions() {
        for (Player player : players) {
            player.setPosition(0);

        }
    }
}
