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

    Sprite playbtn;

    Board board;

    /*
    *   Receives spriteBatch so we don't have to create a new one
    * */
    public PlayScreen(SpriteBatch batch) {
        this.batch = batch;
        background = new Color(0, 0, 0, 1);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);

        playbtn = new Sprite(new Texture(Gdx.files.internal("PlayButton.png")));
        if (playbtn == null){
            System.exit(1);
        }
        playbtn.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

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
        playbtn.getTexture().dispose();

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
        if(screenY < 600){
            Position clicked = new Position(screenX/Board.SQUARE_SIZE,(600 - screenY)/Board.SQUARE_SIZE);
            if(board.getPieceinSquare(clicked) != null){
                board.paintSquares(board.getPieceinSquare(clicked).possiblePositions(),'g');
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
