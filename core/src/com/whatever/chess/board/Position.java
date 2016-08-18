package com.whatever.chess.board;

/**
 * Created by felipecosta on 8/8/16.
 * Using Vector2 is not a good idea here coz it works with float values.
 * Decided to make my own class so I don't have to use casting that often
 */
public class Position {
    private int x,y;
    private boolean isSelected;

    /*
    * Standard values are -1,-1 which is a position outside the board and outside the screen
    * */
    public Position(){
        x = -1;
        y = -1;
        isSelected = false;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        isSelected = false;
    }

    public void select(){
        isSelected = true;
    }

    public void setSelected(boolean selected){
        this.isSelected = selected;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
