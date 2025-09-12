package com.olaz.coreforge.world.tiles.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.world.tiles.Extractable;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.TileBorder;
import com.olaz.coreforge.world.tiles.TileType;

public class MineralTile extends Tile implements Extractable {
    private final Resource mineResource;

    public MineralTile(TileType type, Texture texture, Vector2 position, Resource mineResource) {
        super(type, texture, position);
        this.mineResource = mineResource;
    }

    public MineralTile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures, Resource mineResource) {
        super(type, texture, position, borderTextures);
        this.mineResource = mineResource;
    }

    @Override
    public void extract() {
        Gdx.app.log("MineralTile", "Extracted: " + this.mineResource);
    }
}
