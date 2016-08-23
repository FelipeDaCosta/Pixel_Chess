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
    // We need a camera to see stuff
    OrthographicCamera camera;
    // Sprite batch.
    SpriteBatch batch;
    //  Color background
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
      //  batch.begin();
       // playbtn.draw(batch);
      //  batch.end();
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
        System.out.println("Clicked");
        if(screenY < 600){
                Position clicked = new Position(screenX, screenY);
                clicked.toBoard();
                Piece clickedPiece = board.getPieceinSquare(clicked);
                // if there's a piece selected && the piece color is playing this turn
                if (clickedPiece != null && clickedPiece.getColor() == turn) {
                    board.setSpecialSquares(clickedPiece.possiblePositions());
                    board.setSelectedPiece(clickedPiece);
                }
                else if(clickedPiece == null){
                    System.out.println("Moving");
                    board.movePiece(clicked);
                    board.setSpecialSquares(null);
                    board.setSelectedPiece(null);
                }
                else {
                    board.setSpecialSquares(null);
                    board.setSelectedPiece(null);
                }
        }

            //board.setBoardSquare(new Position(screenX/Board.SQUARE_SIZE,(600 - screenY)/Board.SQUARE_SIZE),'g');
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
