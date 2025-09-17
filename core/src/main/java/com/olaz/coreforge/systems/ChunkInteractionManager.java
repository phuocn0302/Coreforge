package com.olaz.coreforge.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.blocks.machines.TestMachine;
import com.olaz.coreforge.ui.ChunkViewInputHandler;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.types.MineralTile;

public class ChunkInteractionManager {
    public enum Mode {
        NORMAL,
        BUILD,
        DECONSTRUCT,
    }

    private Mode currentMode = Mode.NORMAL;
    private final Chunk chunk;
    private final ChunkViewInputHandler chunkViewInputHandler;
    private final MainInventory inventory;

    public ChunkInteractionManager(Chunk chunk, ChunkViewInputHandler chunkViewInputHandler, MainInventory inventory) {
        this.chunk = chunk;
        this.chunkViewInputHandler = chunkViewInputHandler;
        this.inventory = inventory;


        chunkViewInputHandler.onChunkTapped.connect(this::handleChunkTap);
    }

    private void handleChunkTap(Vector2 position) {
        switch (currentMode) {
            case NORMAL:
                normalModeHandler(position);
                break;
            case BUILD:
                // TODO: Place machine/block
                buildModeHandler(position);
                break;
            case DECONSTRUCT:
                // TODO: Remove machine/block
                deconstructModeHandler(position);
                break;
        }
    }

    private void normalModeHandler(Vector2 position) {
        if (chunk.getBlock(position) == null) {
            extract(position);
            return;
        }

        chunk.placeBlock(new TestMachine(
                "m_test",
                "dummy machine",
                new Texture(Gdx.files.internal("resources/ore/copper.png"))
            ),
            position
        );
    }

    private void buildModeHandler(Vector2 position) {
        if (chunk.getBlock(position) != null) {
            Gdx.app.log("BuildMode", position + " already has block!");
        }

        chunk.placeBlock(new TestMachine(
                "m_test",
                "dummy machine",
                new Texture(Gdx.files.internal("resources/ore/copper.png"))
            ),
            position
        );
    }

    private void deconstructModeHandler(Vector2 position) {

    }

    private void extract(Vector2 position) {
        Tile tile = chunk.getTile(position);

        if (tile == null) {
            throw new IllegalArgumentException("Tile is null!!");
        }

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
