package com.olaz.coreforge.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.olaz.coreforge.blocks.machines.Furnace;
import com.olaz.coreforge.blocks.machines.MachineType;

import java.util.HashMap;
import java.util.Map;

public class MachineFactory {
    private static final Map<String, MachineData> machineDefinitions = new HashMap<>();
    private static final Map<String, Texture> textureCache = new HashMap<>();

    private MachineFactory() {

    }

    public static void loadDefinitions(String path) {
        Json json = new Json();
        JsonValue root = new JsonReader().parse(Gdx.files.internal(path));

        for (JsonValue entry : root) {
            MachineData data = json.readValue(MachineData.class, entry);
            machineDefinitions.put(data.id, data);
        }
    }

    public static Machine createMachine(String id) {
        MachineData data = machineDefinitions.get(id);
        if (data == null) {
            throw new IllegalArgumentException("No machine definition found for id: " + id);
        }

        Texture texture = textureCache.computeIfAbsent(
            data.texturePath,
            path -> new Texture(Gdx.files.internal(path))
        );

        MachineType type = MachineType.valueOf(data.type);

        Machine machine;
        switch (type) {
            case FURNACE:
                machine = new Furnace(data.id, data.description, texture);
                break;
            default:
                throw new IllegalArgumentException("Unknown machine type: " + type);
        }

        return machine;
    }

    public static class MachineData {
        public String id;
        public String type;
        public String description;
        public String texturePath;
    }
}
