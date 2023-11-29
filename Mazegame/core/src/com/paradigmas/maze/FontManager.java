package com.paradigmas.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {

    private static BitmapFont font;

    public static void loadFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minecraft.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;
        parameter.borderWidth = 1;
        parameter.borderStraight = true;

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public static BitmapFont getFont() {
        if (font == null) {
            loadFont(12);
        }
        return font;
    }
    
    public static float getTextWidth(String text, SpriteBatch batch) {
		return font.draw(batch, text, -200, -200).width;
	}
    
}
