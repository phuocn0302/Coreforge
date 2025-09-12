package com.olaz.coreforge.world.tiles.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.TileBorder;
import com.olaz.coreforge.world.tiles.TileType;

public class GroundTile extends Tile {
    public GroundTile(TileType type, Texture texture, Vector2 position) {
        super(type, texture, position);
    }

    public GroundTile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures) {
        super(type, texture, position, borderTextures);
    }
}
