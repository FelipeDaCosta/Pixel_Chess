package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

/**
 * Created by felipecosta on 8/20/16.
 */
public class Knight extends Piece {
    public Knight(Position pos, Board board, Sprite pieceSprite, boolean color) {
        super(pos, board, pieceSprite, color);
    }


    @Override
    public ArrayList<Position> possiblePositions() {
        ArrayList<Position> possiblePos = new ArrayList<Position>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        // Forward right
        if(x < 6 && y < 7) {
            possiblePos.add(new Position(x + 2, y + 1));
        }

        if(x < 7 && y < 6){
            possiblePos.add(new Position(x + 1, y + 2));
        }

        // Forward left
        if(x > 1 && y < 7){
            possiblePos.add(new Position(x - 2, y + 1));
        }

        if(x > 0 && y < 6){
            possiblePos.add(new Position(x - 1, y + 2));
        }

        // Backward right
        if(x < 6 && y > 0){
            possiblePos.add(new Position(x + 2, y - 1));
        }

        if(x < 7 && y > 1){
            possiblePos.add(new Position(x + 1, y - 2));
        }

        // Backward left
        if(x > 1 && y > 0){
            possiblePos.add(new Position(x - 2, y - 1));
        }

        if ( x > 0 && y > 1){
            possiblePos.add(new Position(x - 1, y - 2));
        }

        return possiblePos;
    }
}
