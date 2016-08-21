package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

/**
 * Created by felipe on 7/31/2016.
 * Abstract class with all pieces attributes and methods.
 * Must be extended by all piecer in the game
 */
public abstract class Piece {
    // Piece position in the board
    private Position position;
    // Piece type (knight, bishop etc)
    private PieceType type;

    private Sprite pieceSprite;


    private  String spritePath;

    // Black = true, White = false
    private boolean color;

    // Game board so we can check if moves are valid
    private Board board;

    /*
    * Receives the piece initial pos, the board the piece is in and the piece sprite
    * we pass the board so the piece knows where the other pieces are when it's calculating
    * the squares it can move to
    * */
    public Piece(Position pos, Board board, Sprite pieceSprite, boolean color) {
        this.position = new Position(pos.getX(), pos.getY());
        this.board = board;
        this.pieceSprite = pieceSprite;
        this.color = color;
    }



    /*
    * Move the piece to newPos (only if it's a valid move)
    * */
    public void move(Position newPos){
            this.position.setPosition(newPos.getX(), newPos.getY());
    }

    /*
    *  Move the piece to new position no matter what (As long as it's in the board range)
    * */
    public void forceMove(Position newPos){
        if((newPos.getX() < 8 && newPos.getX() > 0) &&
                (newPos.getY() < 8 && newPos.getY() > 0)) {
            position.setPosition(newPos.getX(), newPos.getY());
        }
    }

    // Returns a list with all the possible positions the piece can go to
    public abstract ArrayList<Position> possiblePositions();


    /*
    * Getters & Setters
    * */
    public Position getPosition() {
        return position;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Sprite getPieceSprite() {
        return pieceSprite;
    }

    public void setPieceSprite(Sprite pieceSprite) {
        this.pieceSprite = pieceSprite;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
