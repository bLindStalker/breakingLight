package com.breaking.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.breaking.game.Main;
import com.breaking.game.object.GameObject;
import com.breaking.game.object.LightBulb;
import com.breaking.game.object.LightBulb.POSITION;

import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.LAMPS_WHITE_SPACE;
import static com.breaking.game.Constants.LIFE_SIZE;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.TIMER_HEIGHT;
import static com.breaking.game.Constants.TIMER_WIDTH;
import static com.breaking.game.Constants.WIDTH;
import static com.breaking.game.Constants.X_STATUS_POSITION;
import static com.breaking.game.Constants.Y_LAMP_POSITION;
import static com.breaking.game.Constants.Y_LIFE_POSITION;
import static com.breaking.game.Constants.Y_STATUS_POSITION;

public class MainGameScreen extends BaseGameScreen {
    public MainGameScreen(Main main) {
        super(new FitViewport(WIDTH, HEIGHT), main.batch);

        //statuses
        addActor(new GameObject(X_STATUS_POSITION - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT));

        addActor(new GameObject(X_STATUS_POSITION * 2 - (LIFE_SIZE / 2), Y_LIFE_POSITION, LIFE_SIZE, LIFE_SIZE));
        addActor(new GameObject(X_STATUS_POSITION * 2 - LIFE_SIZE - (LIFE_SIZE / 2) - 40, Y_LIFE_POSITION, LIFE_SIZE, LIFE_SIZE));
        addActor(new GameObject(X_STATUS_POSITION * 2 + (LIFE_SIZE / 2) + 40, Y_LIFE_POSITION, LIFE_SIZE, LIFE_SIZE));

        addActor(new GameObject(X_STATUS_POSITION * 3, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT));

        // lamps
        addActor(new LightBulb(POSITION.LEFT, Y_LAMP_POSITION));
        addActor(new LightBulb(POSITION.CENTER, Y_LAMP_POSITION));
        addActor(new LightBulb(POSITION.RIGHT, Y_LAMP_POSITION));

        addActor(new LightBulb(POSITION.LEFT, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_WHITE_SPACE));
        addActor(new LightBulb(POSITION.CENTER, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_WHITE_SPACE));
        addActor(new LightBulb(POSITION.RIGHT, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_WHITE_SPACE));

        addActor(new LightBulb(POSITION.LEFT, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_WHITE_SPACE) * 2));
        addActor(new LightBulb(POSITION.CENTER, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_WHITE_SPACE) * 2));
        addActor(new LightBulb(POSITION.RIGHT, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_WHITE_SPACE) * 2));

        setDebugAll(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act(delta);
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
