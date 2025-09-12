package com.olaz.coreforge.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class ResourcesFactory {
    private static final Map<String, Resource> resourceMap = new HashMap<>();
    private static final Map<String, Texture> textureCache = new HashMap<>();

    private ResourcesFactory() {
    }


    public static void loadDefinitions(String path) {
        Json json = new Json();
        JsonValue root = new JsonReader().parse(Gdx.files.internal(path));

        for (JsonValue entry : root) {
            ResourceData data = json.readValue(ResourceData.class, entry);

            Texture resourceTex = textureCache.computeIfAbsent(
                data.texturePath,
                texPath -> new Texture(Gdx.files.internal(texPath))
            );

            resourceMap.put(data.id, new Resource(
                data.id, data.name, data.description, resourceTex
            ));
        }
    }

    public static Resource getResource(String id) {
        Resource r = resourceMap.get(id);

        if (r == null) {
            throw new IllegalArgumentException("No resources found for id: " + id);
        }

        return r;
    }

    public static void dispose() {
        for (Texture tex : textureCache.values()) {
            tex.dispose();
        }
        textureCache.clear();
        resourceMap.clear();
    }

    public static class ResourceData {
        public String id;
        public String name;
        public String description;
        public String texturePath;
    }
}
