package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by felipecosta on 8/18/16.
 */
public class Rook extends Piece {

    private boolean canRook;
    public Rook(Position pos, Board board, Sprite pieceSprite, boolean color) {
        super(pos, board, pieceSprite, color);
        canRook = false;
    }

    // Works
    @Override
    public HashSet<Position> possiblePositions() {
        HashSet<Position> possiblePos = new HashSet<Position>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        Board board = this.getBoard();
        // Position being currently tested
        Position curPos;
        // Vertical moves

        // Forward
        for(int i = y + 1; i < 8;i++){
            curPos = new Position(x,i);
            System.out.println("Forward");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){ // Found a piece
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        // Backward
        for(int i = 1; y - i  >= 0;i++){
            curPos = new Position(x,y - i);
            System.out.println("backward");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        // Horizontal moves

        // Right
        for(int i = x + 1; i < 8; i++) {
            curPos = new Position(i,y);
            System.out.println("Right");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        // Left
        for(int i = x - 1; i >= 0;i--) {
            curPos = new Position(i,y);
            System.out.println("left");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        return possiblePos;
    }


}
