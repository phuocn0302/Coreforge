package com.olaz.coreforge.world.tiles.types;

import com.badlogic.gdx.graphics.Texture;
import com.olaz.coreforge.data.Position;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.TileType;

public class GroundTile extends Tile {
    public GroundTile(TileType type, Texture texture, Position position) {
        super(type, texture, position);
    }

    @Override
    public void onInteract() {
    }
}
