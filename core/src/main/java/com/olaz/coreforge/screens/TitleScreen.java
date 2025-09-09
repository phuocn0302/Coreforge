package com.olaz.coreforge.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.olaz.coreforge.utils.FontUtils;
import com.olaz.coreforge.utils.TextureUtils;

public class TitleScreen implements Screen {

    private final Game game;
    private Stage stage;
    private Skin skin;
    private Texture logoTex, panelTex, indicatorTex;
    private Image logo, panel, indicator;
    private TextButton startBtn, optionsBtn, quitBtn;

    public TitleScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        setupStage();
        setupSkin();
        setupUI();
        setupListeners();
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
        logoTex.dispose();
        panelTex.dispose();
        indicatorTex.dispose();
    }

    private void setupStage() {
        stage = new Stage(new FitViewport(180, 320));
        Gdx.input.setInputProcessor(stage);
    }

    private void setupSkin() {
        skin = new Skin();
        BitmapFont font = FontUtils.loadTTF("fonts/monogram.ttf", 16, Color.WHITE);
        skin.add("default-font", font);

        TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();
        btnStyle.font = font;
        btnStyle.fontColor = Color.WHITE;
        skin.add("default", btnStyle);
    }

    private void setupUI() {
        // Textures
        logoTex = TextureUtils.loadTexture("ui/TitleScreen/logo.png");
        logo = new Image(logoTex);

        panelTex = TextureUtils.loadTexture("ui/TitleScreen/panel.png");
        panel = new Image(panelTex);

        indicatorTex = TextureUtils.loadTexture("ui/TitleScreen/indicator.png");
        indicator = new Image(indicatorTex);
        indicator.setVisible(false);
        stage.addActor(indicator);

        // Buttons
        startBtn = new TextButton("Start Game", skin);
        optionsBtn = new TextButton("Options", skin);
        quitBtn = new TextButton("Quit", skin);

        // Layouts
        Table menuTable = new Table();
        menuTable.add(startBtn).pad(5).row();
        menuTable.add(optionsBtn).pad(5).row();
        menuTable.add(quitBtn).pad(5).row();
        menuTable.center();

        Stack stack = new Stack();
        stack.add(panel);
        stack.add(menuTable);

        Table root = new Table();
        root.setFillParent(true);
        root.top().padTop(40);
        root.add(logo).padBottom(40).row();
        root.add(stack);

        stage.addActor(root);
    }

    private void setupListeners() {
        TextButton[] menuButtons = {startBtn, optionsBtn, quitBtn};
        for (TextButton btn : menuButtons) {
            btn.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    Vector2 pos = btn.localToStageCoordinates(new Vector2(0, btn.getHeight() / 2f));
                    indicator.setPosition(pos.x - 2, pos.y, Align.right);
                    indicator.setVisible(true);
                    indicator.toFront();
                }
            });
        }

        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: change to option screen
            }
        });

        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: quit confirmation dialog/screen
                Gdx.app.exit();
            }
        });
    }
}
