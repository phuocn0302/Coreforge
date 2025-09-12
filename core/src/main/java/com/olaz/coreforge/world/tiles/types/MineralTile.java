package com.olaz.coreforge.world.tiles.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.TileBorder;
import com.olaz.coreforge.world.tiles.TileType;

public class MineralTile extends Tile {
    public MineralTile(TileType type, Texture texture, Vector2 position) {
        super(type, texture, position);
    }

    public MineralTile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures) {
        super(type, texture, position, borderTextures);
    }

    public MineralTile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures, Resource harvestResource) {
        super(type, texture, position, borderTextures, harvestResource);
    }

    @Override
    public void onInteract() {
        Gdx.app.log("MineralTile", "Harvest: " + this.getHarvestResource());
    }
}
