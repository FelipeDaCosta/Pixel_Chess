package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

/**
 * Created by felipecosta on 8/21/16.
 */

public class Bishop extends Piece {
    public Bishop(Position pos, Board board, Sprite pieceSprite, boolean color) {
        super(pos, board, pieceSprite, color);
    }

    // Working
    @Override
    public ArrayList<Position> possiblePositions() {
        ArrayList<Position> possiblePos = new ArrayList<Position>();
        // Creating local variables so we don't have to keep calling methods
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        Board board = this.getBoard();
        // This will hold the position we're analysing
        Position curPos;

        // Upper Right
        for(int i = x + 1,j = y + 1; i < 8 && j < 8 ; i++,j++){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece form a diff color
            if(board.getPieceinSquare(curPos) != null ){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        // Upper Left
        for(int i = x - 1, j = y + 1; i >= 0 && j < 8; i--,j++){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece
            if(board.getPieceinSquare(curPos) != null){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        // Lower Right
        for(int i = x + 1, j = y - 1; i < 8 && j >= 0; i++,j--){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece
            if(board.getPieceinSquare(curPos) != null){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--,j--){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece
            if(board.getPieceinSquare(curPos) != null ){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        return possiblePos;
    }
}
