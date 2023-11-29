package com.paradigmas.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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

        loadFont();
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
		        setFontSize(50);
		        font.draw(batch, "MazeGame", screenWidth / 2 - (getTextWidth("MazeGame") / 2), screenHeight / 2 + 50);
		
		        if (showText) {
		            setFontSize(20);
		            font.draw(batch, "Press Enter to start!", screenWidth / 2 - (getTextWidth("Press Enter to start!") / 2), screenHeight / 2 - 10);
		        }
	        } else if (win) {
	        	setFontSize(50);
		        font.draw(batch, "Fim de jogo!", screenWidth / 2 - (getTextWidth("Fim de jogo!") / 2), screenHeight / 2 + font.getLineHeight()/2);
		        setFontSize(20);
	            font.draw(batch, "Pontos: " + points + "/" + possiblePoints, screenWidth / 2 - (getTextWidth("Pontos: " + points + "/" + possiblePoints) / 2), screenHeight / 2 - 30);
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

    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 50;
        parameter.borderWidth = 1;
        parameter.borderStraight = true;

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    private float getTextWidth(String text) {
        return font.draw(batch, text, 0, 0).width;
    }

    private void setFontSize(int newSize) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = newSize;
        parameter.borderWidth = 1;
        parameter.borderStraight = true;

        font = generator.generateFont(parameter);
        generator.dispose();
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
            System.out.println("Enter pressed - Start the game or perform some action.");
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
