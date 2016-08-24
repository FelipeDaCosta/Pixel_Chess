package com.whatever.chess.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whatever.chess.pieces.Bishop;
import com.whatever.chess.pieces.King;
import com.whatever.chess.pieces.Knight;
import com.whatever.chess.pieces.Pawn;
import com.whatever.chess.pieces.Piece;
import com.whatever.chess.pieces.PieceType;
import com.whatever.chess.pieces.Queen;
import com.whatever.chess.pieces.Rook;
import com.whatever.chess.util.Util;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.geometry.Pos;

/**
 * Created by felipecosta on 8/6/16.
 */


/*
* Board class. We're representing the board as a pieceMatrix array where null represents
* an empty square.
* */
public class Board{
    public static final int SQUARE_SIZE = 64; // Size of the square
    public static final int BOARD_SIZE = 8;

    // File names for the squares
    private final String blackSquareFileName = "BlackSquare.png";
    private final String whiteSquareFileName = "WhiteSquare.png";
    private final String greenSquareFileName = "GreenSquare.png";
    private final String redSquareFileName = "RedSquare.png";

    private Piece pieceMatrix[][]; // Matrix used to store where the pieces are. Empty squares are null

    private Sprite boardColor[][]; // Matrix used to define the color of the square

    // Sprites for the squares
    public Sprite blackSquare, whiteSquare, redSquare, greenSquare;


    // Sprite batch is gonna be passed in this method constructor
    // Creating several spritebatchs is a bad habit because it's [pretty heavy
    private SpriteBatch spriteBatch;

    // Will turn all positions in this array red or green
    private HashSet<Position> specialSquares;

    // The piece that is currently selected
    private Piece selectedPiece;


    // Pieces
    private Pawn[] whitePawns, blackPawns;
    private King whiteKing, blackKing;
    private Queen whiteQueen, blackQueen;
    private Bishop[] whiteBishop, blackBishop;
    private Knight[] whiteKnight, blackKnight;
    private Rook[] whiteRook, blackRook;

    // CONSTRUCTOR
    public Board(SpriteBatch sb){
        spriteBatch = sb;

        // Loading Square sprites
        blackSquare = Util.loadSprite(blackSquareFileName);
        whiteSquare = Util.loadSprite(whiteSquareFileName);
        greenSquare = Util.loadSprite(greenSquareFileName);
        redSquare = Util.loadSprite(redSquareFileName);

        pieceMatrix = new Piece[BOARD_SIZE][BOARD_SIZE]; // Empty squares are null
        boardColor = new Sprite[BOARD_SIZE][BOARD_SIZE];

        // Creating pawns
        whitePawns = new Pawn[8];
        blackPawns = new Pawn[8];
        for(int i = 0; i < 8; i++){
            whitePawns[i] = new Pawn(new Position(i,1), this, Util.loadSprite(Util.whitePawn), false);
            blackPawns[i] = new Pawn(new Position(i,6), this, Util.loadSprite(Util.blackPawn), true);
            pieceMatrix[i][1] = whitePawns[i];
            pieceMatrix[i][6] = blackPawns[i];
        }

        // Creating rooks
        blackRook = new Rook[2];
        whiteRook = new Rook[2];
        for(int i = 0; i < 2; i++){
            blackRook[i] = new Rook(new Position(i*7,7), this, Util.loadSprite(Util.blackRook), true);
            whiteRook[i] = new Rook(new Position(i*7,0), this, Util.loadSprite(Util.whiteRook), false);
            pieceMatrix[i*7][7] = blackRook[i];
            pieceMatrix[i*7][0] = whiteRook[i];
        }

        // Creating knights
        blackKnight = new Knight[2];
        whiteKnight = new Knight[2];
        for(int i = 0; i < 2; i++){
            blackKnight[i] = new Knight(new Position((i*5) + 1, 7), this,
                    Util.loadSprite(Util.blackKnight), true);
            whiteKnight[i] = new Knight(new Position((i*5) + 1, 0), this,
                    Util.loadSprite(Util.whiteKnight), false);

            pieceMatrix[(i*5)+1][7] = blackKnight[i];
            pieceMatrix[(i*5)+1][0] = whiteKnight[i];
        }

        // Creating bishops
        blackBishop = new Bishop[2];
        whiteBishop = new Bishop[2];
        for(int i = 0; i < 2; i++){
            blackBishop[i] = new Bishop(new Position((i*3) + 2, 7), this,
                    Util.loadSprite(Util.blackBishop), true);
            whiteBishop[i] = new Bishop(new Position(i*3 + 2, 0), this,
                    Util.loadSprite(Util.whiteBishop), false);
            pieceMatrix[(i*3) + 2][7] = blackBishop[i];
            pieceMatrix[(i*3) + 2][0] = whiteBishop[i];
        }

        // Creating queen
        blackQueen = new Queen(new Position(4,7), this, Util.loadSprite(Util.blackQueen), true);
        whiteQueen = new Queen(new Position(3,0), this, Util.loadSprite(Util.whiteQueen), false);
        pieceMatrix[4][7] = blackQueen;
        pieceMatrix[3][0] = whiteQueen;

        // Creatin king
        blackKing = new King(new Position(3,7), this, Util.loadSprite(Util.blackKing), true);
        whiteKing = new King(new Position(4,0), this, Util.loadSprite(Util.whiteKing), false);
        blackKing.setType(PieceType.KING);
        whiteKing.setType(PieceType.KING);
        pieceMatrix[3][7] = blackKing;
        pieceMatrix[4][0] = whiteKing;

        specialSquares = null;
        selectedPiece = null;

        // Fills the board with the squares
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8;j++){
                if((i + j)%2 == 0){ // This way we can switch between white and black squares
                    boardColor[i][j] = blackSquare;
                }
                else{
                    boardColor[i][j] = whiteSquare;
                }
            }
        }
    }

    /* --- END CONSTRUCTOR ---*/

    public void update(){
        if(specialSquares != null){
            for(Position square : specialSquares){
                if(this.getPieceinSquare(square) == null){
                    this.setBoardSquare(square, 'g');
                }
                else{
                    this.setBoardSquare(square, 'r');
                }
            }
        }
    }

    /*
    * Loads all pieces
    * */
    public void setBoardSquare(Position pos, char color){
        switch(color){
            case 'r':
                boardColor[pos.getX()][pos.getY()] = redSquare;
                break;
            case 'g':
                boardColor[pos.getX()][pos.getY()] = greenSquare;
                break;
            case 'w':
                boardColor[pos.getX()][pos.getY()] = whiteSquare;
                break;
            case 'b':
                boardColor[pos.getX()][pos.getY()] = blackSquare;
                break;

        }
    }

    /*
    * Paint all the squares in the positions arraylist with the color chosen.
    * If color == 'n', uses the natural color of the square
    * */
    public void paintSquares(ArrayList<Position> positions, char color){
        Sprite newSquare;
        if(color == 'r'){
            newSquare = redSquare;
        }
        else if(color == 'g'){
            newSquare = greenSquare;
        }
        else if(color == 'n'){
            for(Position pos : positions){
                if((pos.getX() + pos.getY())%2 == 0){
                    newSquare = blackSquare;
                }
                else{
                    newSquare = whiteSquare;
                }
                boardColor[pos.getX()][pos.getY()] = newSquare;
            }
            return;
        }
        else // case something wrong was assigned to color
            return;

        for(Position pos : positions){
            boardColor[pos.getX()][pos.getY()] = newSquare;
        }
    }


    /*
    * Moves a piece
    * */
    public boolean movePiece(Position newPos){
        if(specialSquares != null && specialSquares.contains(newPos)){
            if(pieceMatrix[newPos.getX()][newPos.getY()] != null){ // Capturing an enemy piece
                if(pieceMatrix[newPos.getX()][newPos.getY()].getType() == PieceType.KING){
                    // If the king is captured (game over)
                    Gdx.app.exit();
                }
                // The piece is gone
                // There's still a reference to the piece so the object cannot
                // be disposed. I'm not sure what to do with it
                pieceMatrix[newPos.getX()][newPos.getY()] = null;
            }
            // Moving piece
            pieceMatrix[selectedPiece.getPosition().getX()][selectedPiece.getPosition().getY()] = null;
            pieceMatrix[newPos.getX()][newPos.getY()] = selectedPiece;
            selectedPiece.move(newPos);
            return true;
        }
        return false;
    }

    /*
    * Returns coordinates in the screen for the position we pass
    * */
    public int coordToScreen(int pos){
        return pos*SQUARE_SIZE;
    }

    /*
    * Returns a new Position with the screen coordinates
    * */
    public Position positionToScreen(Position pos){
        return new Position(pos.getX()*SQUARE_SIZE, pos.getY()*SQUARE_SIZE);
    }

    public void setPieceinSquare(Position newPos, Piece piece) {
        pieceMatrix[newPos.getX()][newPos.getY()] = piece;
    }

    public Piece getPieceinSquare(Position position){
        return pieceMatrix[position.getX()][position.getY()];
    }

    /*
    * Returns position of the square clicked in the matrix
    * This is just so I can see if it works, I'm pretty sure there's a better way to do it
    * */
    public Position getPositionFromClick(int x, int y){

        int final_x = x/SQUARE_SIZE, final_y = x/SQUARE_SIZE;
        /*for(; x > SQUARE_SIZE;x -= SQUARE_SIZE){
            final_x++;
        }
        for(;y > SQUARE_SIZE;y -= SQUARE_SIZE){
            final_y++;
        }*/
        Position posClicked = new Position(final_x, final_y);
        return posClicked;

    }



    /* ----- DRAWING FUNCTIONS -----*/

    /*
    *  Printing the whole board
    * */
    public void drawBoard(){
        spriteBatch.begin();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8;j++){
                boardColor[i][j].setPosition(coordToScreen(i), coordToScreen(j));
                boardColor[i][j].draw(spriteBatch);
            }
        }
        spriteBatch.end();
    }


    /*
    * Draw All the pieces to the screen based on their position in the pieceMatrix
    * */
    public void drawAllPieces(){
        spriteBatch.begin();
        for(int i = 0; i < 8; i ++){
            for(int j = 0 ; j < 8 ; j++){
                if(pieceMatrix[i][j] != null) {
                    pieceMatrix[i][j].getPieceSprite().setPosition(coordToScreen(i),
                            coordToScreen(j));
                    pieceMatrix[i][j].getPieceSprite().draw(spriteBatch);
                }
            }
        }
        spriteBatch.end();
    }

    public HashSet<Position> getSpecialSquares() {
        return specialSquares;
    }

    public void setSpecialSquares(HashSet<Position> specialSquares) {
        if(this.specialSquares != null){
            for(Position square : this.specialSquares){
                if((square.getX() + square.getY()) % 2 == 0){
                    this.setBoardSquare(square, 'b');
                }
                else{
                    this.setBoardSquare(square, 'w');
                }
            }
        }

        if(specialSquares != null){
            for(Position square : specialSquares){
                if(this.getPieceinSquare(square) != null){
                    this.setBoardSquare(square, 'r');
                }
                else{
                    this.setBoardSquare(square, 'g');
                }
            }
        }

        this.specialSquares = specialSquares;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void dispose(){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                if(pieceMatrix[i][j] != null){
                    pieceMatrix[i][j].dispose();
                }
            }
        }

        if(blackSquare != null)
            blackSquare.getTexture().dispose();
        if(whiteSquare != null)
            whiteSquare.getTexture().dispose();
        if(greenSquare != null)
            greenSquare.getTexture().dispose();
        if(redSquare != null)
            redSquare.getTexture().dispose();

    }
}
