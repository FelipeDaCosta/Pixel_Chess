package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

/**
 * Created by felipecosta on 8/18/16.
 */
public class Rook extends Piece {

    private boolean canRook;
    public Rook(Position pos, Board board, Sprite pieceSprite, boolean color) {
        super(pos, board, pieceSprite, color);
        canRook = false;
    }

    @Override
    public ArrayList<Position> possiblePositions() {
        ArrayList<Position> possiblePos = new ArrayList<Position>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        Position curPos; // Position being currently tested
        // Vertical moves

        // Forward
        for(int i = 1; i + y < 8 &&
                this.getBoard().getPieceinSquare(curPos = new Position(x,i+y)) == null;i++){
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
        }

        // Backward
        for(int i = 1; y - i  >= 0  &&
                this.getBoard().getPieceinSquare(curPos = new Position(x,y - i)) == null;i++){
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
        }

        // Horizontal moves

        // Right
        for(int i = 1; i + x < 8 &&
                this.getBoard().getPieceinSquare(curPos = new Position(x + i,y)) == null;i++) {
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
        }

        // Left
        for(int i = 1; x - i >= 0 &&
                this.getBoard().getPieceinSquare(curPos = new Position(x - i,y)) == null;i++) {
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
        }
        return possiblePos;
    }


}
