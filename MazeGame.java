// TODOLIST
// Implementar câmera - FEITO
// Renderizar mapa
// Colisão
// Tela menu
// Tela quiz
// Levels


package com.paradigmas.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class MazeGame extends Game {
    SpriteBatch batch;
    Texture spriteSheet;
    TextureRegion currentFrame;

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
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        
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
    
}