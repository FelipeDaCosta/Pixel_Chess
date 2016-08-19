package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

/**
 * Created by felipe on 7/31/2016.
 */
public class King extends Piece{




    // Enum used to organize pieceTypes;
    PieceType pieceType;

    public King(Position pos, Board board, Sprite pieceSprite, boolean color){
        super(pos, board, pieceSprite, color);
        this.setType(PieceType.KING);
    }

    /*
    * return true if piece can go to newPos
    * */
    @Override
    public boolean isValidMov(Position newPos) {
        // Checks if newPos is in range
        if((newPos.getX() < 8 && newPos.getX() > 0) &&
                (newPos.getY() < 8 && newPos.getY() > 0)){
            return false;
        }
        // The king can move only up to one square in any direction
        if(Math.abs(this.getPosition().getX() - newPos.getX()) > 1
                || Math.abs(this.getPosition().getY() - newPos.getY()) > 1) {
            return false;
        }
        return true;
    }

    public ArrayList<Position> possiblePositions(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        ArrayList<Position> pos = new ArrayList<Position>();
        for(int i = -1; i < 2;i++){
            for(int j = -1; j < 2;j++){
                pos.add(new Position(x + i, y + j));
            }
        }
        return pos;
    }
}
