package com.whatever.chess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.whatever.chess.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 512;
		config.height = 600;
		config.title = "Pixel Chess";
		config.forceExit = false; // Found this on stackOverflow, change it later
		new LwjglApplication(new Main(), config);
	}
}
