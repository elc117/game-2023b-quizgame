// TODOLIST
// Implementar câmera - FEITO
// Renderizar mapa - FEITO
// Colisão
// Tela menu
// Tela quiz
// Levels


package com.paradigmas.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class MazeGame3 extends Game {
    SpriteBatch batch;
    Texture spriteSheet;
    TextureRegion currentFrame;
    
    Texture wallTexture_rc;
    Texture wallTexture;
    Texture groundTexture;
    Texture chestTexture;
    
    private char[][] levelMatrix;
    private int tileSize = 64;

    public static int screenHeight, screenWidth;

    private int CharPosX, CharPosY;
    
    private float moveSpeed = 6f; //VELOCIDADE DE MOVIMENTO DO PERSONAGEM
    
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
        wallTexture_rc = new Texture(Gdx.files.internal("sceneMaterials/wall_rc.png"));
        chestTexture = new Texture(Gdx.files.internal("sceneMaterials/chest.png"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        
        loadLevel("level01.txt");
        
    }

    public void render() {
        inputHandle();
        animationHandle();
        ScreenUtils.clear(0, 0, 0, 0);
        
        camera.position.set(CharPosX + spriteWidth * scale / 2, CharPosY + spriteHeight * scale / 2, 0); // centralizada na tela
        camera.update();

        //controle de pos
        System.out.println(CharPosX);

        System.out.println(" ");

        System.out.println(CharPosY);
        
        
        batch.begin();
        
        renderLevel();
        
        batch.setProjectionMatrix(camera.combined);
        
        batch.draw(currentFrame, CharPosX - (spriteWidth/2), CharPosY - (spriteHeight/2), spriteWidth * scale, spriteHeight * scale);
        
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
    }

    private void inputHandle() {

    	
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            CharPosY += moveSpeed;
            lastKeyPressed = Input.Keys.W;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        	lastKeyPressed = Input.Keys.S;
            CharPosY -= moveSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        	lastKeyPressed = Input.Keys.A;
            CharPosX -= moveSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        	lastKeyPressed = Input.Keys.D;
        	CharPosX += moveSpeed;
        }
        
    }

    
    private void animationHandle () {
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
    
    private void loadLevel(String filename) {
        FileHandle file = Gdx.files.internal(filename);
        String levelString = file.readString();
        String[] rows = levelString.split("\n");

        int numRows = rows.length;
        int numCols = rows[0].length();
        levelMatrix = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                levelMatrix[i][j] = rows[i].charAt(j);
                if (levelMatrix[i][j] == '#') {
                	CharPosX = j * tileSize;
                	CharPosY = (levelMatrix.length - 1 - i) * tileSize;
                	levelMatrix[i][j] = 'G';
                }
            }
        }
    }
    
    private void renderLevel() {
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[0].length; j++) {
                float x = j * tileSize;
                float y = (levelMatrix.length - 1 - i) * tileSize;
                switch (levelMatrix[i][j]) {
                    case 'G':
                        batch.draw(groundTexture, x, y, tileSize, tileSize);
                        break;
                    case 'V':
                        break;
                    case ' ':
                    	break;
                    case 'C':
                        batch.draw(chestTexture, x, y, tileSize, tileSize);
                        break;
                    case 'R':
                    	batch.draw(wallTexture_rc, x, y, tileSize, tileSize);
                    	break;
                    case 'W':
                    	batch.draw(wallTexture, x, y, tileSize, tileSize);
                    	break;
                }
            }
        }
    }

}
