package com.paradigmas.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu extends Game {

    private BitmapFont font;
    private SpriteBatch batch;
    private Texture background;

    private int screenWidth, screenHeight;

    private float textBlinkTimer = 0;
    private boolean showText = true;
    
    private static Boolean startGame;
	private static Boolean win;
    static MazeGame game;
    private static int currentLevel;
    private static int points, possiblePoints;

    @Override
    public void create() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        FontManager.loadFont(12);
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("fundo02.png"));
        
        game = new MazeGame();
        startGame = false;
        startGameAssets();
    }

    public void render() {
        if (!startGame) {
        	handleInput();
            updateTextBlink(Gdx.graphics.getDeltaTime());
	        batch.begin();
	        batch.draw(background, 0, 0);
	        if (!win) {
		        FontManager.loadFont(50);
		        font = FontManager.getFont();
		        font.draw(batch, "MazeGame", screenWidth / 2 - (FontManager.getTextWidth("MazeGame", batch) / 2), screenHeight / 2 + 50);
		
		        if (showText) {
		        	FontManager.loadFont(20);
		        	font = FontManager.getFont();
		            font.draw(batch, "Press Enter to start!", screenWidth / 2 - (FontManager.getTextWidth("Press Enter to start!", batch) / 2), screenHeight / 2 - 10);
		        }
	        } else if (win) {
	        	FontManager.loadFont(50);
	        	font = FontManager.getFont();
		        font.draw(batch, "Fim de jogo!", screenWidth / 2 - (FontManager.getTextWidth("Fim de jogo!", batch) / 2), screenHeight / 2 + font.getLineHeight()/2);
		        FontManager.loadFont(20);
	            font.draw(batch, "Pontos: " + points + "/" + possiblePoints, screenWidth / 2 - (FontManager.getTextWidth("Pontos: " + points + "/" + possiblePoints, batch) / 2), screenHeight / 2 - 30);
	        }
	
	        batch.end();
        }
        
        if (startGame) {
        	startGame();
        }
    }

    public void dispose() {
        batch.dispose();
        background.dispose();
        font.dispose();
    }
    
    public void startGameAssets() {
    	win = false;
    	currentLevel = 1;
    	points = 0;
    	possiblePoints = 0;
    	game.create(currentLevel);
    }
    public void startGame () {
    	game.render();
    }
    public static void nextLevel() {
    	currentLevel++;
    	game.create(currentLevel);
    }
    
    public static void setVictory () {
    	startGame = false;
    	win = true;
    }
    
    public static void addPoint () {
    	points++;
    }
    
    public static void addPossiblePoint() {
    	possiblePoints++;
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            startGame = true;
        }
    }

    private void updateTextBlink(float deltaTime) {
        textBlinkTimer += deltaTime;

        if (textBlinkTimer >= 0.5f) {
            showText = !showText;
            textBlinkTimer = 0;
        }
    }
}
