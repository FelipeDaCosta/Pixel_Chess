package com.whatever.chess;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whatever.chess.screen.PlayScreen;

public class Main extends com.badlogic.gdx.Game {
	SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new PlayScreen(batch));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
