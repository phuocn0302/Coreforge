package com.olaz.coreforge.systems;

import com.olaz.coreforge.ui.ChunkViewInputHandler;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.types.MineralTile;

public class TileInteractionManager {
    public enum Mode {
        EXTRACT,
        BUILD,
        DECONSTRUCT,
    }
    private Mode currentMode = Mode.EXTRACT;
    private final MainInventory inventory;

    public TileInteractionManager(MainInventory inventory) {
        this.inventory = inventory;

        ChunkViewInputHandler.onTileTapped.connect(this::handleTileTap);
    }

    private void handleTileTap(Tile tile) {
        switch (currentMode) {
            case EXTRACT:
                extract(tile);
                break;
            case BUILD:
                // TODO: Place machine/block
                break;
            case DECONSTRUCT:
                // TODO: Remove machine/block
                break;
        }
    }

    private void extract(Tile tile) {
        if (tile instanceof MineralTile) {
            MineralTile mineral = (MineralTile) tile;
            mineral.extract();
            inventory.addToInventory(mineral.getMineResource(), 1);
        }
    }

    public void setMode(Mode mode) {
        this.currentMode = mode;
    }
}
