package com.whatever.chess.board;

/**
 * Created by felipecosta on 8/8/16.
 * Using Vector2 is not a good idea here coz it works with float values.
 * Decided to make my own class so I don't have to use casting that often
 */
public class Position {
    private int x,y;

    /*
    * Standard values are -1,-1 which is a position outside the board and outside the screen
    * */
    public Position(){
        x = -1;
        y = -1;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    // When the position is got coordinates from the screen, we use this method to convert it
    // to coordinates in the board
    public void toBoard(){
        this.x = this.x/Board.SQUARE_SIZE;
        this.y = (600 - this.y)/Board.SQUARE_SIZE;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
