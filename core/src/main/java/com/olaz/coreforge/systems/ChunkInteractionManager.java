package com.olaz.coreforge.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.blocks.Block;
import com.olaz.coreforge.blocks.Machine;
import com.olaz.coreforge.blocks.MachineFactory;
import com.olaz.coreforge.ui.ChunkViewInputHandler;
import com.olaz.coreforge.utils.observer.Event;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.types.MineralTile;

public class ChunkInteractionManager {
    public enum Mode {
        NORMAL,
        BUILD,
        DECONSTRUCT,
    }

    public Event<Machine> onMachineTapped = new Event<>();
    public Event<Machine> onMachinePlaced = new Event<>();
    public Event<Machine> onMachineRemoved = new Event<>();

    private Mode currentMode = Mode.NORMAL;
    private final Chunk chunk;
    private final ChunkViewInputHandler chunkViewInputHandler;
    private final TickSystem tickSystem;
    private final MainInventory inventory;

    public ChunkInteractionManager(Chunk chunk, ChunkViewInputHandler chunkViewInputHandler, TickSystem tickSystem, MainInventory inventory) {
        this.chunk = chunk;
        this.chunkViewInputHandler = chunkViewInputHandler;
        this.tickSystem = tickSystem;
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
        Block block = chunk.getBlock(position);

        if (block == null) {
            extract(position);
            return;
        }

        if (block instanceof Machine) {
            onMachineTapped.emit((Machine) block);
        }
    }

    private void buildModeHandler(Vector2 position) {
        Block block = chunk.getBlock(position);

        if (block instanceof Machine) {
            onMachineTapped.emit((Machine) block);
        }

        if (chunk.getBlock(position) != null) {
            Gdx.app.log("BuildMode", position + " already has block!");
            return;
        }

        // TODO: ref to current selected machine in inventory;
        Machine currentMachine = MachineFactory.createMachine("m_t0_furnace");

        chunk.placeBlock(currentMachine, position);
        onMachinePlaced.emit(currentMachine);
        tickSystem.addToSystem(currentMachine);
    }

    private void deconstructModeHandler(Vector2 position) {
        if (chunk.getBlock(position) == null) {
            Gdx.app.log("DeconstructMode", "No block found at " + position);
            return;
        }

        Block currentBlock = chunk.getBlock(position);

        if (currentBlock instanceof Machine) {
            tickSystem.removeFromSystem((Tickable) currentBlock);
            onMachineRemoved.emit((Machine) currentBlock);
        }

        chunk.removeBlock(position);
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
