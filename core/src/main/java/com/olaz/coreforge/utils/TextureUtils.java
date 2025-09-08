package com.olaz.coreforge.utils;

import com.badlogic.gdx.graphics.Texture;

public class TextureUtils {
    static public Texture loadTexture(String path) {
        Texture tex = new Texture(path);
        tex.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        return tex;
    }
}
