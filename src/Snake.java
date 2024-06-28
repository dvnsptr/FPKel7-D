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

public class Snake {
    private int tailPosition;
    private int headPosition;

    public Snake(int t, int h){
        this.tailPosition = t;
        this.headPosition = h;
    }

    public void setTailPosition(int t){
        this.tailPosition = t;
    }

    public void setHeadPosition(int h){
        this.headPosition = h;
    }

    public int getTailPosition(){
        return this.tailPosition;
    }

    public int getHeadPosition(){
        return this.headPosition;
    }


}
