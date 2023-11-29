//TITULO 44 CHAR POR LINHA

package com.paradigmas.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class QuizScreen extends Game{
	//ALTERNATIVES AND WORDING SPECS
	float wordingWidth, wordingHeight;
	float alt1Width, alt1Height;
	float alt2Width, alt2Height;
	float alt3Width, alt3Height;
	float underScoreHeight;
	
	private boolean delayInProgress = false;
    private float delayTime = 3.0f;
	
	String wording, alt1, alt2, alt3;
	int awnser, input;
	
	private BitmapFont font;
	SpriteBatch batch;
	
	Texture background;
	
	int screenWidth, screenHeight;
	
	public void create () {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		loadFont();
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("fundo02.png"));
		
		input = 0;
		
		setWording ("A");
		setAlt(1, "TESTE");
		setAlt(2, "TESTE2");
		setAlt(3, "TESTE3");
		
	}
	public void render () {
		
		batch.begin();
		
		batch.draw(background, 0, 0);
		
		setAwnser(1);
		
		checkInput();
		
		if (input != 0) processAwnser(input);
		
		renderQuiz();
		
		batch.end();

		
		if (delayInProgress) {
            delayTime -= Gdx.graphics.getDeltaTime();

            if (delayTime <= 0) {
                delayInProgress = false;
                delayTime = 5.0f;
                if (awnser == input) MainMenu.addPoint();
                MainMenu.nextLevel();
            }
        }
		
		
	}
	public void dispose () {
		batch.dispose();
		background.dispose();
		font.dispose();
	}
	
	private void setWording (String str) {
		wording = str;
	}
	private void setAlt (int N, String Str) {
		if (N == 1) alt1 = "A) " + Str;
		if (N == 2) alt2 = "B) " + Str;
		if (N == 3) alt3 = "C) " + Str;
	}
	private void checkInput() {
	    if (Gdx.input.isKeyJustPressed(Keys.A)) {
	        input = Keys.A;
	    } else if (Gdx.input.isKeyJustPressed(Keys.B)) {
	    	input = Keys.B;
	    } else if (Gdx.input.isKeyJustPressed(Keys.C)) {
	    	input = Keys.C;
	    }
	}

	private void processAwnser(int input) {
	    setFontSize(30);
	    if (awnser == input) {
	        font.draw(batch, "Correto! Voce ganhou um ponto!", screenWidth / 2 - getTextWidth("Correto! Voce ganhou um ponto!") / 2, 15 + font.getLineHeight());
	        

	        delayInProgress = true;
	    } else {
	        font.draw(batch, "Incorreto! Tente na proxima!", screenWidth / 2 - getTextWidth("Incorreto! Tente na proxima!") / 2, 15 + font.getLineHeight());

	        delayInProgress = true;
	    }
	}

	private void setAwnser (int input) {
		if (input == 1) awnser = Keys.A;
		if (input == 2) awnser = Keys.B;
		if (input == 3) awnser = Keys.C;
	}
	private void renderQuiz () {
		
		renderWording(wording);
		renderAlt(1, alt1);
		renderAlt(2, alt2);
		renderAlt(3, alt3);
	}
	private void loadFont() {
    	System.out.println("Fonte carregada");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 16;

        parameter.borderWidth = 1;
        parameter.borderStraight = true;


        font = generator.generateFont(parameter);
        
        generator.dispose();
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
	
	private void renderWording(String wording) {
	    font.setColor(1, 1, 1, 1);
	    setFontSize(20);

	    float maxLineWidth = 650.0f;

	    String[] lines = splitStringByWidth(wording, maxLineWidth);

	    float lineHeight = font.getLineHeight();
	    float totalHeight = lines.length * lineHeight;
	    wordingHeight = totalHeight;
	   
	    float fontX = screenWidth / 2;
	    float fontY = screenHeight - 100;

	    for (String line : lines) {
	        float textWidth = getTextWidth(line);
	        fontX -= textWidth / 2;
	        font.draw(batch, line, fontX, fontY);
	        fontX = screenWidth / 2;
	        fontY -= lineHeight;
	    }
	    String underscore = "--------------------------------------------------------------------------------";
		font.draw(batch, underscore, screenWidth/2 - (getTextWidth(underscore)/2), 600 - wordingHeight - 100 - 10);
		wordingHeight+= font.getLineHeight();
	}
	
	private void renderAlt(int N, String wording) {
	    font.setColor(1, 1, 1, 1);
	    setFontSize(16);

	    float maxLineWidth = 650.0f;

	    String[] lines = splitStringByWidth(wording, maxLineWidth);

	    float fontY = 0;
	    float fontX = screenWidth / 2;
	    
	    float lineHeight = font.getLineHeight();
	    float totalHeight = lines.length * lineHeight;
	    if (N==1) {
	    	alt1Height = totalHeight + underScoreHeight;
	    	verifyAltSize(N, alt1Height);
	    	fontY = (600 - wordingHeight) - 100 - 40;
	    } else if (N==2) {
	    	alt2Height = totalHeight + underScoreHeight;
	    	verifyAltSize(N, alt2Height);
	    	fontY = (600 - wordingHeight) - 100 - 20 - alt1Height - 20;
	    }
	    else if (N==3) {
	    	alt3Height = totalHeight;
	    	verifyAltSize(N, alt3Height);
	    	fontY = (600 - wordingHeight) - 100 - 20 - alt1Height - 20 - alt2Height - 20;
	    }


	    for (String line : lines) {
	        float textWidth = getTextWidth(line);
	        fontX -= textWidth / 2;
	        font.draw(batch, line, fontX, fontY);
	        fontX = screenWidth / 2;
	        fontY -= lineHeight;
	    }
	}


	private String[] splitStringByWidth(String input, float maxWidth) {
	    String[] words = input.split(" ");
	    StringBuilder currentLine = new StringBuilder();
	    java.util.List<String> lines = new java.util.ArrayList<>();

	    for (String word : words) {
	        if (getTextWidth(currentLine.toString() + " " + word) <= maxWidth) {
	            currentLine.append(word).append(" ");
	        } else {
	            lines.add(currentLine.toString().trim());
	            currentLine = new StringBuilder(word + " ");
	        }
	    }

	    if (!currentLine.toString().trim().isEmpty()) {
	        lines.add(currentLine.toString().trim());
	    }

	    return lines.toArray(new String[0]);
	}

	private float getTextWidth(String text) {
		return font.draw(batch, text, 0, 0).width;
	}
	private void verifyAltSize (int N, float height) {
		if (N==1) {
	    	if (alt1Height < 100) alt1Height = 100;
	    } else if (N==2) {
	    	if (alt2Height < 100) alt2Height = 100;
	    }
	    else if (N==3) {
	    	if (alt3Height < 100) alt3Height = 100;
	    }
	}
	
	
	
}
