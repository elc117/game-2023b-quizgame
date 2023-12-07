package com.paradigmas.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {

	public static void loadBackgroundMusic() {
    	AssetManager assetManager = new AssetManager();
		assetManager.load("sounds/Lost Woods - The Legend of Zelda_ Ocarina Of Time.mp3", Music.class);
		assetManager.finishLoading();
		
		Music backgroundMusic = assetManager.get("sounds/Lost Woods - The Legend of Zelda_ Ocarina Of Time.mp3", Music.class);
		backgroundMusic.play();
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.07f);
    }
}
