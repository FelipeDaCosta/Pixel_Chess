package com.whatever.chess.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whatever.chess.pieces.Pawn;
import com.whatever.chess.pieces.Piece;

import java.util.ArrayList;

/**
 * Created by felipecosta on 8/6/16.
 */


/*
* Board class. We're representing the board as a pieceMatrix array where null represents
* an empty square.
* */
public class Board{
    public static final int SQUARE_SIZE = 64; // Size of the square

    // File names for the squares
    private final String blackSquareFileName = "BlackSquare.png";
    private final String whiteSquareFileName = "WhiteSquare.png";
    private final String greenSquareFileName = "GreenSquare.png";
    private final String redSquareFileName = "RedSquare.png";

    private Piece pieceMatrix[][]; // Matrix used to store where the pieces are. Empty squares are null

    private Sprite boardColor[][]; // Matrix used to define the color of the square

    // Sprites for the squares
    public Sprite blackSquare, whiteSquare, redSquare, greenSquare;

    public Sprite pawnSprite;

    // Sprite batch is gonna be passed in this method constructor
    // Creating several spritebatchs is a bad habit because it's [pretty heavy
    private SpriteBatch spriteBatch;

    // Holds all black blackPawn
    private ArrayList<Pawn> blackPawn;

    // CONSTRUCTOR
    public Board(SpriteBatch sb){
        // Check if files exists
        if(!Gdx.files.internal(blackSquareFileName).exists()){
            System.out.println("BlackSquare file missing");
            System.exit(1);
        }
        if(!Gdx.files.internal(whiteSquareFileName).exists()){
            System.out.println("BlackSquare file missing");
            System.exit(1);
        }
        if(!Gdx.files.internal(greenSquareFileName).exists()){
            System.out.println("BlackSquare file missing");
            System.exit(1);
        }
        if(!Gdx.files.internal(redSquareFileName).exists()){
            System.out.println("BlackSquare file missing");
            System.exit(1);
        }

        spriteBatch = sb;

        // Loading sprites
        blackSquare = new Sprite(new Texture(blackSquareFileName));
        whiteSquare = new Sprite(new Texture(whiteSquareFileName));
        greenSquare = new Sprite(new Texture(greenSquareFileName));
        redSquare = new Sprite(new Texture(redSquareFileName));

        pieceMatrix = new Piece[8][8]; // Empty squares are null
        boardColor = new Sprite[8][8];


        // We only need one pawn sprite. Printing the same sprite several times won't use that much memory
        pawnSprite = new Sprite(new Texture(Gdx.files.internal("Pawn.png")));
        blackPawn = new ArrayList<Pawn>(8);

        for(int i = 0; i < 8; i ++){
            blackPawn.add(i, new Pawn(new Position(i, 1), this, pawnSprite) );
            pieceMatrix[i][1] = blackPawn.get(i);
        }

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

    public boolean isNaturalColor(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        if(boardColor[x][y].equals(redSquare) || boardColor[x][y].equals(greenSquare))
            return false;
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
    * Draw an specific square
    * */
    public void drawSquare(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        spriteBatch.begin();
        // Aparently the sprite does not save the position when we first set it so we have
        // to set it again
        boardColor[x][y].setPosition(coordToScreen(x),coordToScreen(y));
        boardColor[x][y].draw(spriteBatch);
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
}
