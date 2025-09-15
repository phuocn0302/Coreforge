package com.olaz.coreforge;

import com.badlogic.gdx.Game;
import com.olaz.coreforge.data.RecipeFactory;
import com.olaz.coreforge.data.ResourcesFactory;
import com.olaz.coreforge.screens.TitleScreen;
import com.olaz.coreforge.world.tiles.TileFactory;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game{
    @Override
    public void create() {
        TileFactory.loadDefinitions("data/tiles.json");
        ResourcesFactory.loadDefinitions("data/resources.json");
        RecipeFactory.loadDefinitions("data/recipes.json");

        setScreen(new TitleScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        TileFactory.dispose();
        ResourcesFactory.dispose();
    }
}
