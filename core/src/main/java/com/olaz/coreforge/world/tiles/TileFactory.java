package com.olaz.coreforge.world.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.math.MathUtils;
import com.olaz.coreforge.world.tiles.types.FluidTile;
import com.olaz.coreforge.world.tiles.types.GroundTile;
import com.olaz.coreforge.world.tiles.types.MineralTile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileFactory {
    private static final Map<String, TileData> tileDefinitions = new HashMap<>();
    private static final Map<String, Texture> textureCache = new HashMap<>();

    public static void loadDefinitions(String path) {
        Json json = new Json();
        JsonValue root = new JsonReader().parse(Gdx.files.internal(path));

        for (JsonValue entry : root) {
            String key = entry.name();
            TileData data = json.readValue(TileData.class, entry);
            tileDefinitions.put(key, data);
        }
    }

    public static Tile createTile(String id, Vector2 position) {
        TileData data = tileDefinitions.get(id);
        if (data == null) {
            throw new IllegalArgumentException("No tile definition found for id: " + id);
        }

        List<TileData.TextureEntry> textures = data.textures;

        int totalWeight = 0;
        for (TileData.TextureEntry t : textures) {
            totalWeight += t.weight;
        }

        int r = MathUtils.random(totalWeight - 1);
        String texturePath = null;

        for (TileData.TextureEntry t : textures) {
            if (r < t.weight) {
                texturePath = t.path;
                break;
            }
            r -= t.weight;
        }

        Texture texture = textureCache.computeIfAbsent(
            texturePath,
            path -> new Texture(Gdx.files.internal(path))
        );



        TileBorder tileBorder = null;
        if (data.borders != null) {
            Texture borderTilemap = textureCache.computeIfAbsent(
                data.borders,
                path -> new Texture(Gdx.files.internal(path))
            );

            tileBorder = new TileBorder(borderTilemap);
        }

        TileType type = TileType.valueOf(data.type);

        Tile tile;
        switch (type) {
            case GROUND:
                tile = new GroundTile(type, texture, position, tileBorder);
                break;
            case MINERAL:
                tile = new MineralTile(type, texture, position, tileBorder);
                break;
            case FLUID:
                tile = new FluidTile(type, texture, position, tileBorder);
                break;
            default:
                throw new IllegalArgumentException("Unknown TileType: " + type);
        }
        return tile;
    }

    public static void dispose() {
        for (Texture tex : textureCache.values()) {
            tex.dispose();
        }
        textureCache.clear();
    }
}
