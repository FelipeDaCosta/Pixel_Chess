package com.whatever.chess.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;
import com.whatever.chess.pieces.Piece;

/**
 * Created by felipe on 7/31/2016.
 * This is the menu screen of the game
 * It implements the screen interface
 */
public class PlayScreen implements Screen, InputProcessor {
    OrthographicCamera camera;

    SpriteBatch batch;

    Color background;

    Board board;

    // false = white's turn
    boolean turn;


    /*
    *   Receives spriteBatch so we don't have to create a new one
    * */
    public PlayScreen(SpriteBatch batch) {
        turn = false;
        this.batch = batch;
        background = new Color(0, 0, 0, 1);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);

        board = new Board(batch);
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f,.5f,.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        board.drawBoard();
        board.drawAllPieces();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        board.dispose();
    }


    // Input processor methods
    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenY < 600){ // There's no board above 600
                Position clicked = new Position(screenX, screenY);
                clicked.toBoard(); // Converting screen coordinates to board coordinates
                Piece clickedPiece = board.getPieceinSquare(clicked); // Gets the piece that was clicked

                if(clickedPiece != null){ // Clicked on a piece
                    // If its the turn of the piece we clicked
                    if(clickedPiece.getColor() == turn){
                        // Selects the piece we clicked
                        board.setSelectedPiece(clickedPiece);
                        // Show green/red squares for the piece we clicked
                        board.setSpecialSquares(clickedPiece.possiblePositions());
                    }
                    // If it's not the clicked piece turn and there's a selected piece
                    // (Checking if there's a selected piece is just a safety measure)
                    else if(board.getSelectedPiece() != null){ // Capturing some piece
                        // We don't need to check if the piece can move to clicked because
                        // the movePiece method will take care of that and return true
                        // if the piece moves
                        if(board.movePiece(clicked)){ // If the piece moves, we have to pass the turn
                            turn = !turn;
                        }
                        // We clear special squares and selected pieces no matter if the movement was
                        // valid or not
                        board.setSelectedPiece(null);
                        board.setSpecialSquares(null);
                    }
                }
                else{ // Clicked on an empty cell
                    if(board.movePiece(clicked)){ // If the movement was valid, we pass the turn
                        turn = !turn;
                    }
                    // We clear special squares and selected pieces no matter if the movement was
                    // valid or not
                    board.setSelectedPiece(null);
                    board.setSpecialSquares(null);
                }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
