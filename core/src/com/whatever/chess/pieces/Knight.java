package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by felipecosta on 8/20/16.
 */
public class Knight extends Piece {
    public Knight(Position pos, Board board, Sprite pieceSprite, boolean color) {
        super(pos, board, pieceSprite, color);
    }

    // Works
    @Override
    public HashSet<Position> possiblePositions() {
        HashSet<Position> possiblePos = new HashSet<Position>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        Board board = this.getBoard();
        Position curPos;

        // Forward right
        curPos = new Position(x + 2, y + 1);
        if(x + 2 < 6 && y + 1 < 7) {
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }


        curPos = new Position(x + 1, y + 2);
        if(x < 7 && y < 6){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        // Forward left
        curPos = new Position(x - 2, y + 1);
        if(x > 1 && y < 7){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        curPos = new Position(x - 1, y + 2);
        if(x > 0 && y < 6){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        // Backward right
        curPos = new Position(x + 2, y - 1);
        if(x < 6 && y > 0){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        curPos = new Position(x + 1, y - 2);
        if(x < 7 && y > 1){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        // Backward left
        curPos = new Position(x - 2, y - 1);
        if(x > 1 && y > 0){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        curPos = new Position(x - 1, y - 2);
        if ( x > 0 && y > 1){
            possiblePos.add(curPos);
            if(board.getPieceinSquare(curPos) != null
                    && board.getPieceinSquare(curPos).getColor() == this.getColor()){
                possiblePos.remove(curPos);
            }
        }

        return possiblePos;
    }
}
