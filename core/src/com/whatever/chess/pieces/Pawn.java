package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

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
    /*
    * Returns true if a movement is valid
    * */
    public boolean isValidMov(Position newPos){
        if(this.getPosition().getX() == newPos.getX())
            if(this.getPosition().getY() + 1 == newPos.getY() ||
                    (firstMove && this.getPosition().getY() + 2 == newPos.getY()))
                        return true;
        return false;
    }

    public ArrayList<Position> possiblePositions(){
        Board board = this.getBoard();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        ArrayList<Position> possiblePos = new ArrayList<Position>();
        // Basic movement
        Position moveForward = new Position(x, y+1);
        if(board.getPieceinSquare(moveForward) == null) {
            possiblePos.add(moveForward);
            // Moving two cells
            moveForward = new Position(x,y+2);
            if (firstMove && board.getPieceinSquare(moveForward) == null)
                possiblePos.add(new Position(x, y + 2));
        }

        // Capture movement
        if(x != 0) {
            Position captureLeft = new Position(x - 1, y + 1);
            // If there's a piece in the newPos and its from a different color
            if(board.getPieceinSquare(captureLeft) != null &&
                    board.getPieceinSquare(captureLeft).getColor() != this.getColor())
                possiblePos.add(captureLeft);
        }

        if(x != 7){
            Position captureRight = new Position(x + 1, y + 1);
            if(board.getPieceinSquare(captureRight) != null &&
                    board.getPieceinSquare(captureRight).getColor() != this.getColor()){
                possiblePos.add(captureRight);
            }
        }

        // En passant will be implemented later
        return possiblePos;
    }


}
