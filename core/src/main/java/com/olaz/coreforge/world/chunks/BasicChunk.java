package com.olaz.coreforge.world.chunks;

import com.olaz.coreforge.data.Position;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.tiles.TileFactory;

public class BasicChunk extends Chunk {
    // TODO: Perhaps using 32x32 image to define chunk
    @Override
    public void setupChunkLayout() {
        int width = getSize().getWidth();
        int height = getSize().getHeight();
        int triangleSize = 8; // Size of the triangular patches

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String tileId;

                // Top left
                if (x + y < triangleSize) {
                    tileId = "ORE_IRON";
                }
                // Top right
                else if ((width - 1 - x) + y < triangleSize) {
                    tileId = "ORE_COAL";
                }
                // Bottom left
                else if (x + (height - 1 - y) < triangleSize) {
                    tileId = "ORE_COPPER";
                }
                // Bottom right
                else if ((width - 1 - x) + (height - 1 - y) < triangleSize) {
                    tileId = "WATER";
                } else {
                    tileId = "GRASS";
                }

                getTiles().add(TileFactory.createTile(tileId, new Position(x, y)));
            }
        }
    }
}
