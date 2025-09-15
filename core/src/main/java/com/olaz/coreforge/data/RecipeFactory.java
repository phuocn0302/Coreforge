package com.olaz.coreforge.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeFactory {
    private static final Array<Recipe> recipes = new Array<>();
    private static final Map<String, Array<Recipe>> recipesByMachine = new HashMap<>();

    public static void loadDefinitions(String path) {
        Json json = new Json();
        JsonValue root = new JsonReader().parse(Gdx.files.internal(path));

        for (JsonValue entry : root) {
            RecipeData data = json.readValue(RecipeData.class, entry);

            List<Resource> resolvedInputs = new ArrayList<>();
            for (String inputId : data.inputs) {
                resolvedInputs.add(ResourcesFactory.getResource(inputId));
            }

            Resource output = ResourcesFactory.getResource(data.output);
            Recipe recipe = new Recipe(data.machine, resolvedInputs, output, data.duration);

            recipes.add(recipe);

            recipesByMachine
                .computeIfAbsent(data.machine, k -> new Array<>())
                .add(recipe);
        }
    }

    public static Array<Recipe> getRecipesForType(String machineType) {
        return recipesByMachine.getOrDefault(machineType, new Array<>());
    }

    public static class RecipeData {
        public String machine;
        public Array<String> inputs;
        public String output;
        public int duration;
    }
}
