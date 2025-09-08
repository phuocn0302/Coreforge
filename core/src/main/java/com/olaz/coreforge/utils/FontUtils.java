package com.olaz.coreforge.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontUtils {
    public static BitmapFont loadTTF(String path, int size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = size;
        param.color = color;
        param.minFilter = Texture.TextureFilter.Nearest;
        param.magFilter = Texture.TextureFilter.Nearest;
        BitmapFont font = generator.generateFont(param);
        generator.dispose();
        return font;
    }

    public static BitmapFont loadTTF(String path, int size) {
        return loadTTF(path, size, Color.WHITE);
    }
}
