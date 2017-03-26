package com.breaking.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.breaking.game.Main;
import com.breaking.game.object.ImageActor;

import static com.breaking.game.AssetLoader.getBackGround;
import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.WIDTH;

public class BaseScreen extends Stage implements Screen {
    public final Main main;

    public BaseScreen(Main main) {
        super(new FitViewport(WIDTH, HEIGHT));
        this.main = main;
        addActor(new ImageActor(0, 0, WIDTH, HEIGHT, getBackGround()));
        //setDebugAll(true);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        act(delta);
        draw();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height);
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
}
