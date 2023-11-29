package com.paradigmas.maze;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.paradigmas.maze.MazeGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(800, 600);
		config.setResizable(false);

		config.setForegroundFPS(30);
		config.setTitle("Mazegame");
		config.setWindowIcon("SceneMaterials/chest.png");
		new Lwjgl3Application(new MainMenu(), config);
	}
}
