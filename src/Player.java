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

public class Player {
    private String name;
    private int position;
    private int score;


    Player(String name){
        this.name=name;
        this.position=0;
        this.score=0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(int position){
        this.position = position;
    }
    public void setScore(int score) {this.score = score;}
    public String getName(){
        return this.name;
    }
    public int getPosition(){
        return this.position;
    }
    public int getScore() {return this.score;}

    public int rollDice() {
        return (int)(Math.random()*6+1);
    }

    public void moveAround(int y, int boardSize) {
        if(this.position + y>boardSize) {
            this.position = boardSize-(this.position + y)%boardSize;
        } else {
            this.position = this.position + y;
        }


    }
}
