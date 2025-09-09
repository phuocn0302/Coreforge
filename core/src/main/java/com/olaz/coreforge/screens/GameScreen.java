package com.olaz.coreforge.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.olaz.coreforge.ui.ChunkView;
import com.olaz.coreforge.utils.FontUtils;
import com.olaz.coreforge.world.chunks.BasicChunk;

public class GameScreen implements Screen {

    private final Game game;
    private Stage stage;
    private Skin skin;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(180, 320));
        Gdx.input.setInputProcessor(stage);

        setupSkin();
        setupUI();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.13f, 0.11f, 0.18f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }


    private void setupSkin() {
        skin = new Skin();
        BitmapFont font = FontUtils.loadTTF("fonts/monogram.ttf", 16, Color.WHITE);
        skin.add("default-font", font);
    }

    private void setupUI() {
        BasicChunk chunk = new BasicChunk();
        ChunkView chunkView = new ChunkView(chunk, 156, 156);

        Table root = new Table();
        root.setFillParent(true);
        root.top().pad(12);
        root.add(chunkView).size(156, 156).row();

        stage.addActor(root);
    }
}
