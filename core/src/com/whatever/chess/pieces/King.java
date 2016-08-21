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

    // Works
    public ArrayList<Position> possiblePositions(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        Board board = this.getBoard();
        Position curPos;
        ArrayList<Position> pos = new ArrayList<Position>();
        for(int i = -1; i < 2;i++){
            for(int j = -1; j < 2;j++){
                curPos = new Position(x + i, y + j);
                if((x + i < 8 && x + i >= 0) && (y + j < 8 && y + j >= 0)) {
                    pos.add(curPos);
                    if(board.getPieceinSquare(curPos) != null &&
                            board.getPieceinSquare(curPos).getColor() == this.getColor()){
                        pos.remove(curPos);
                    }
                }
            }
        }
        return pos;
    }
}
