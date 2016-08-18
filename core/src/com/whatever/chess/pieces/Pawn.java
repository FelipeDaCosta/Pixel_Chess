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

    public Pawn(Position pos, Board board, Sprite pieceSprite){
        super(pos, board, pieceSprite);
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
        if(board.getPieceinSquare(moveForward) == null)
            possiblePos.add(moveForward);
        if(firstMove)
            possiblePos.add(new Position(x,y+2));

        // Capture movement
        if(x != 0) {
            Position captureLeft = new Position(x - 1, y + 1);
            if(board.getPieceinSquare(captureLeft) != null)
                possiblePos.add(captureLeft);
        }

        if(x != 7){
            Position captureRight = new Position(x + 1, y + 1);
            if(board.getPieceinSquare(captureRight) != null){
                possiblePos.add(captureRight);
            }
        }

        // En passant will be implemented later
        return possiblePos;
    }


}
