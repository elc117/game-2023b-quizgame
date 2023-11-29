package com.paradigmas.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class MazeGame extends Game {
	
	private QuizScreen quizScreen;
	Boolean inGame;
	
	private BitmapFont font;
    private boolean nearChestMessageVisible;

	
    SpriteBatch batch;
    Texture spriteSheet;
    TextureRegion currentFrame;
    
    Texture wallTexture;
    Texture groundTexture;
    Texture chestTexture;
    
    private char[][] levelMatrix;
    private int tileSize = 64;

    public static int screenHeight, screenWidth;

    private int CharPosX, CharPosY;
    private int chestPosX, chestPosY;
    
    private float moveSpeed = 12f; //VELOCIDADE DE MOVIMENTO DO PERSONAGEM
    
    private int spriteWidth = 16;
    private int spriteHeight = 20;
    private int currentFrameX = 1;
    private int currentFrameY = 0; //CONSTANTE EM 0 PQ NAO FAZ DIFERENÇA PARA SPRITE RETO
    private float scale = 4.0f; //Escala de aumento do personagem
    
    private int FrameTime = 0;
    
    private int lastKeyPressed; //importante para determinar qual direção o personagem ficou
    
    private OrthographicCamera camera;
    
    public void create() {
    	
    	screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();

        batch = new SpriteBatch();
        spriteSheet = new Texture(Gdx.files.internal("spriteTeste.png"));
        currentFrame = new TextureRegion(spriteSheet, currentFrameX * spriteWidth, currentFrameY * spriteHeight, spriteWidth, spriteHeight);
        
        //MATERIAIS USÁVEIS
        groundTexture = new Texture(Gdx.files.internal("sceneMaterials/ground.png"));
        wallTexture = new Texture(Gdx.files.internal("sceneMaterials/wall.png"));
                
        chestTexture = new Texture(Gdx.files.internal("sceneMaterials/chest.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        
        loadLevel("level"+ MainMenu.getCurrentLevel() +".txt");
        
        loadFont();
        
        nearChestMessageVisible = false;
        
        quizScreen = new QuizScreen();
        quizScreen.create();
        
        inGame = true;
        
    }

    public void render() {
        inputHandle();
        animationHandle();
        isNearChest();
        ScreenUtils.clear(0, 0, 0, 0);
        
        //camera.position.set(CharPosX + spriteWidth * scale / 2, CharPosY + spriteHeight * scale / 2, 0); // centralizada na tela
        camera.position.set(CharPosX, CharPosY, 0);

        camera.update();

        //controle de pos
        //System.out.println(CharPosX);

        //System.out.println(" ");

        //System.out.println(CharPosY);
        
        if (inGame) {
        	batch.begin();
            
            renderLevel();
            
            batch.setProjectionMatrix(camera.combined);
            
            batch.draw(currentFrame, CharPosX - (spriteWidth * scale /2), CharPosY - (spriteHeight * scale / 2), spriteWidth * scale, spriteHeight * scale);
            
            renderChestMessage();
            
            batch.end();
        
        }
        if (!inGame) {
        	quizScreen.render();
        }
    }

    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
        
    }

    private void inputHandle() {
    	if (inGame) {
	        int newCharPosX = CharPosX;
	        int newCharPosY = CharPosY;
	
	        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
	            newCharPosY += moveSpeed;
	            lastKeyPressed = Input.Keys.W;
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
	            newCharPosY -= moveSpeed;
	            lastKeyPressed = Input.Keys.S;
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
	            newCharPosX -= moveSpeed;
	            lastKeyPressed = Input.Keys.A;
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
	            newCharPosX += moveSpeed;
	            lastKeyPressed = Input.Keys.D;
	        }
	
	        if (canMoveTo(newCharPosX, newCharPosY)) {
	        	if (canMoveTo(newCharPosX, newCharPosY - spriteHeight - 6)) {
		            CharPosX = newCharPosX;
		            CharPosY = newCharPosY;
	        	}
	        }
    	}
    }

    private boolean canMoveTo(int x, int y) {
        int tileX = x / tileSize;
        int tileY = (levelMatrix.length - y / tileSize);

        if (tileX < 0 || tileX >= levelMatrix[0].length || tileY < 0 || tileY >= levelMatrix.length) {
            return false;
        }

        return levelMatrix[tileY][tileX] == 'G' || levelMatrix[tileY][tileX] == 'C';
    
    }

    
    private void animationHandle () {
    	if (inGame) {
	    	if (!(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D))) {
	    	    if (lastKeyPressed == Input.Keys.W) {
	    	        currentFrameX = 4;
	    	    }
	    	    if (lastKeyPressed == Input.Keys.A) {
	    	        currentFrameX = 7;
	    	    }
	    	    if (lastKeyPressed == Input.Keys.D) {
	    	        currentFrameX = 10;
	    	    }
	    	    if (lastKeyPressed == Input.Keys.S) {
	    	        currentFrameX = 1;
	    	    }
	    	    currentFrame.setRegion(currentFrameX * spriteWidth, currentFrameY * spriteHeight, spriteWidth, spriteHeight);
	    	    return;
	    	}
	
	    	FrameTime++;
	    	if (FrameTime == 3) {
		    	if (Gdx.input.isKeyPressed(Input.Keys.W)) {
		    		currentFrameX = 3;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.A)) {
		    		currentFrameX = 6;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.D)) {
		    		currentFrameX = 9;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.S)) {
		    		currentFrameX = 0;
		    	}
		    	currentFrame.setRegion(currentFrameX * spriteWidth, currentFrameY * spriteHeight, spriteWidth, spriteHeight);
		    	return;
	    	}else if (FrameTime == 6) {
		    	if (Gdx.input.isKeyPressed(Input.Keys.W)) {
		    		currentFrameX = 4;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.A)) {
		    		currentFrameX = 7;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.D)) {
		    		currentFrameX = 10;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.S)) {
		    		currentFrameX = 1;
		    	}
		    	currentFrame.setRegion(currentFrameX * spriteWidth, currentFrameY * spriteHeight, spriteWidth, spriteHeight);
		    	return;
	    	} else if (FrameTime == 9) {
	    		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
		    		currentFrameX = 5;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.A)) {
		    		currentFrameX = 8;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.D)) {
		    		currentFrameX = 11;
		    	}
		    	if (Gdx.input.isKeyPressed(Input.Keys.S)) {
		    		currentFrameX = 2;
		    	}
		    	currentFrame.setRegion(currentFrameX * spriteWidth, currentFrameY * spriteHeight, spriteWidth, spriteHeight);
		    	FrameTime = 0;
	    	}
    	}
    }
    
    private void loadLevel(String filename) {
        FileHandle file = Gdx.files.internal(filename);
        if (!file.exists()) {
        	MainMenu.setVictory();
        	return;
        }
        MainMenu.addPossiblePoint();
        String levelString = file.readString();
        String[] rows = levelString.split("\n");

        int numRows = rows.length;
        int numCols = rows[0].length();
        levelMatrix = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols && j < rows[i].length(); j++) {
            	if (j < rows[i].length()) {
                    levelMatrix[i][j] = rows[i].charAt(j);
                } else {
                    levelMatrix[i][j] = 'V';
                }
                if (levelMatrix[i][j] == '#') {
                    CharPosX = (j * tileSize) + tileSize/2;
                    CharPosY = ((levelMatrix.length - i) * tileSize) + tileSize/2;
                    levelMatrix[i][j] = 'G';
                }
                if (levelMatrix[i][j] == 'C') {
                    chestPosX = (j * tileSize) + tileSize/2;
                    chestPosY = ((levelMatrix.length - i) * tileSize) + tileSize/2;
                }
            }
        }
    }

    private void renderLevel() {
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[0].length; j++) {
                float x = j * tileSize;
                float y = (levelMatrix.length - i) * tileSize;
                switch (levelMatrix[i][j]) {
                    case 'G':
                        batch.draw(groundTexture, x, y, tileSize, tileSize);
                        break;
                    case 'V':
                        break;
                    case ' ':
                    	break;
                    case 'C':
                        batch.draw(groundTexture, x, y, tileSize, tileSize);
                        batch.draw(chestTexture, x, y, tileSize, tileSize);
                        break;
                    case 'W':
                    	batch.draw(wallTexture, x, y, tileSize, tileSize);
                    	break;
                }
            }
        }
    }
    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 16;

        parameter.borderWidth = 1;
        parameter.borderStraight = true;

        font = generator.generateFont(parameter);
        
        generator.dispose();
    }
    
    private void renderChestMessage() {
        if (nearChestMessageVisible) {
            String str = "Tesouro encontrado! Pressione Enter para prosseguir.";
            //String ponto = ".";
            font.setColor(1, 1, 1, 1);

            float textWidth = font.draw(batch, str, -200, -200).width;
            
            float fontX = CharPosX - (textWidth / 2);
            float fontY = CharPosY - 70;

            font.draw(batch, str, fontX, fontY);
            //font.draw(batch, ponto, chestPosX, chestPosY);
        }
    }



    private void isNearChest() {
    	if (inGame) {
	        float distance = calculateDistance(CharPosX, CharPosY, chestPosX, chestPosY);
	        float threshold = 120.0f;
	
	        if (distance <= threshold) {
	        	nearChestMessageVisible = true;
	            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ) {
	            	 System.out.println("Clicou");
	            	 inGame= false;
	            	 this.dispose();
	             }
	        } else if (distance > threshold) {
	        	nearChestMessageVisible = false;
	        }
    	}
    }


    private float calculateDistance(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        
        return (float) Math.sqrt(dx * dx + dy * dy);
    }


    
}
