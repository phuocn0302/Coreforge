package com.olaz.coreforge.world.tiles;

import java.util.List;

/**
 * Data model for tiles JSON file
 */
public class TileData {
    public String type;
    public List<TextureEntry> textures;

    public static class TextureEntry {
        public String path;
        public int weight = 1;
    }
}
