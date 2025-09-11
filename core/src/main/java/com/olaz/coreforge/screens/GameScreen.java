package com.olaz.coreforge.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.olaz.coreforge.ui.ChunkView;
import com.olaz.coreforge.ui.ChunkViewInputHandler;
import com.olaz.coreforge.utils.FontUtils;
import com.olaz.coreforge.world.chunks.BasicChunk;

public class GameScreen implements Screen {

    private final Game game;
    private Stage stage;
    private Skin skin;
    private ChunkView chunkView;
    private ChunkViewInputHandler chunkInputHandler;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(180, 320));

        setupSkin();
        setupUI();
        setupInput();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.13f, 0.11f, 0.18f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        updatePollingInput(delta);
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
        int chunkSize = 164;

        BasicChunk chunk = new BasicChunk();
        chunkView = new ChunkView(chunk, chunkSize, chunkSize);
        chunkView.setZoom(2f);

        Table chunkTable = new Table();
        chunkTable.setSize(chunkSize, chunkSize);
        chunkTable.setClip(true);
        chunkTable.add(chunkView);

        Table root = new Table();
        root.setFillParent(true);
        root.top().pad(12);
        root.add(chunkTable).row();

        stage.addActor(root);
    }

    private void setupInput() {
        chunkInputHandler = new ChunkViewInputHandler(chunkView);
        GestureDetector gestureDetector = new GestureDetector(chunkInputHandler);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(chunkInputHandler);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);
    }

    private void updatePollingInput(float delta) {
        if (chunkInputHandler != null)
            chunkInputHandler.pollingUpdate(delta);
    }
}
