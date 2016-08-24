package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.geometry.Pos;

/**
 * Created by felipecosta on 8/9/16.
 */
public class Pawn extends Piece {

    boolean firstMove;

    public Pawn(Position pos, Board board, Sprite pieceSprite, boolean color){
        super(pos, board, pieceSprite, color);
        this.firstMove = true;
    }

    // So that we can set firstMove to false after first movement
    @Override
    public void move(Position newPos) {
        super.move(newPos);
        firstMove = false;
    }


    public HashSet<Position> possiblePositions(){
        Board board = this.getBoard();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        HashSet<Position> possiblePos = new HashSet<Position>();
        Position curPos; // Current position being analysed

        // If the piece is white, we're subtracting 1 to the cur Y position
        // If its black, we add 1
        int add;
        if(this.getColor())
            add = -1;
        else
            add = +1;

        // Basic movement
        curPos = new Position(x,y + add);
        if(board.getPieceinSquare(curPos) == null) {
            possiblePos.add(curPos);
            // Moving two cells
            if(firstMove)
                curPos = new Position(x, y + 2*add);
                if (board.getPieceinSquare(curPos) == null)
                    possiblePos.add(curPos);
        }

        // Capture movement
        if(x != 0) {
            Position captureLeft = new Position(x - 1, y + add);
            // If there's a piece in the newPos and its from a different color
            if(board.getPieceinSquare(captureLeft) != null &&
                    board.getPieceinSquare(captureLeft).getColor() != this.getColor())
                possiblePos.add(captureLeft);
        }

        if(x != 7){
            Position captureRight = new Position(x + 1, y + add);
            if(board.getPieceinSquare(captureRight) != null &&
                    board.getPieceinSquare(captureRight).getColor() != this.getColor()){
                possiblePos.add(captureRight);
            }
        }

        for(Position pos: possiblePos){
            System.out.println("X: " + pos.getX() + "Y: " + pos.getY());
        }

        // En passant will be implemented later

        return possiblePos;
    }


}
