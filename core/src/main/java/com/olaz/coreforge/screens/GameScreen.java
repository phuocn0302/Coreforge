package com.olaz.coreforge.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.olaz.coreforge.systems.MainInventory;
import com.olaz.coreforge.systems.TickSystem;
import com.olaz.coreforge.systems.ChunkInteractionManager;
import com.olaz.coreforge.ui.ChunkView;
import com.olaz.coreforge.ui.ChunkViewInputHandler;
import com.olaz.coreforge.ui.InventoryView;
import com.olaz.coreforge.utils.FontUtils;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.chunks.BasicChunk;

public class GameScreen implements Screen {
    private static final int chunkSize = 164;
    private final Game game;
    private Stage stage;
    private Skin skin;
    private Chunk chunk;
    private ChunkView chunkView;
    private InventoryView inventoryView;
    private ChunkViewInputHandler chunkViewInputHandler;
    private ChunkInteractionManager chunkInteractionManager;
    private MainInventory mainInventory;
    private TickSystem tickSystem;
    private DragAndDrop dragAndDrop;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(180, 320));

        dragAndDrop = new DragAndDrop();
        tickSystem = new TickSystem();
        mainInventory = new MainInventory();
        chunk = new BasicChunk();

        skin = new Skin();
        inventoryView = new InventoryView(mainInventory, skin, dragAndDrop);
        chunkView = new ChunkView(chunk, chunkSize, chunkSize);

        chunkViewInputHandler = new ChunkViewInputHandler(chunkView, dragAndDrop);
        chunkInteractionManager = new ChunkInteractionManager(chunk, chunkViewInputHandler, tickSystem, mainInventory);

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

        tickSystem.update(delta);
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
        BitmapFont font = FontUtils.loadTTF("fonts/monogram.ttf", 16, Color.WHITE);
        font.setUseIntegerPositions(false);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        skin.add("default-font", font);
        skin.add("default", labelStyle);
    }

    private void setupUI() {
        Table chunkTable = new Table();
        chunkTable.setSize(chunkSize, chunkSize);
        chunkTable.setClip(true);
        chunkTable.add(chunkView);

        Texture borderTex = new Texture(Gdx.files.internal("ui/frame.png"));
        NinePatch borderPatch = new NinePatch(borderTex, 3, 3, 3, 3);
        NinePatchDrawable borderDrawable = new NinePatchDrawable(borderPatch);

        chunkTable.setBackground(borderDrawable);
        ScrollPane inventoryScrollPane = new ScrollPane(inventoryView);

        Table bottomView = new Table();
        bottomView.add(inventoryScrollPane).left().expand();

        // Manually setting layout, cuz table sucks (or me just being dumb)

        // Chunk anchored at 3/5 screen
        chunkTable.setPosition(
            stage.getWidth() / 2f - chunkTable.getWidth() / 2f,
            stage.getHeight() * 3f / 5f - chunkTable.getHeight() / 2f
        );

        // Bottom view take all the space at the bottom
        // Padding: Top, Left, Bottom, Right
        Vector4 bottomViewPadding = new Vector4(1, 8, 5, 8);

        bottomView.setPosition(bottomViewPadding.y, bottomViewPadding.z);

        bottomView.setSize(
            stage.getWidth() - bottomViewPadding.y - bottomViewPadding.w,
            chunkTable.getY() - bottomViewPadding.x - bottomViewPadding.z
        );

        stage.addActor(chunkTable);
        stage.addActor(bottomView);
    }

    private void setupInput() {
        GestureDetector gestureDetector = new GestureDetector(chunkViewInputHandler);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(chunkViewInputHandler);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);
    }

    private void updatePollingInput(float delta) {
        if (chunkViewInputHandler != null)
            chunkViewInputHandler.pollingUpdate(delta);
    }
}
