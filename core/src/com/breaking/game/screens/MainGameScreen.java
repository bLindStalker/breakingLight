package com.breaking.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.breaking.game.LightListener;
import com.breaking.game.Main;
import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.object.GameObject;
import com.breaking.game.object.LightBulb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.AssetLoader.empty;
import static com.breaking.game.AssetLoader.getBackGround;
import static com.breaking.game.AssetLoader.getHeard;
import static com.breaking.game.Constants.HEARD_SIZE;
import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.LAMPS_WHITE_SPACE;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMPS;
import static com.breaking.game.Constants.TIMER_HEIGHT;
import static com.breaking.game.Constants.TIMER_WIDTH;
import static com.breaking.game.Constants.WIDTH;
import static com.breaking.game.Constants.X_STATUS_POSITION;
import static com.breaking.game.Constants.Y_LAMP_POSITION;
import static com.breaking.game.Constants.Y_LIFE_POSITION;
import static com.breaking.game.Constants.Y_STATUS_POSITION;
import static com.breaking.game.enums.LightBulbPosition.CENTER;
import static com.breaking.game.enums.LightBulbPosition.LEFT;
import static com.breaking.game.enums.LightBulbPosition.RIGHT;

public class MainGameScreen extends BaseGameScreen {
    private Group lightBulbs;
    private Group lifes;
    private GameObject timer;
    private GameObject scoreActor;
    private GameObject bg;
    private Array<Actor> lifeActors;
    private int score = 0;
    private List<LightBulb> allLamps = new ArrayList<LightBulb>();
    private List<LightBulb> activeLamps = new ArrayList<LightBulb>();

    public MainGameScreen(Main main) {
        super(new FitViewport(WIDTH, HEIGHT), main.getBatch());

        bg = new GameObject(0, 0, WIDTH, HEIGHT, getBackGround());
        addActor(bg);

        timer = new GameObject(X_STATUS_POSITION - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, empty());
        addActor(timer);

        Group lifeGroup = getLifeGroup();
        addActor(lifeGroup);
        lifeActors = new Array<Actor>(lifeGroup.getChildren());

        scoreActor = new GameObject(X_STATUS_POSITION * 3, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, empty());
        addActor(scoreActor);

        addActor(createLamps());
        setDebugAll(true);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        if (lifeActors.size == 0) {
            Gdx.app.exit();
        }

        activateLamp();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act(delta);
        draw();
    }

    private void activateLamp() {
        if (activeLamps.size() <= MAX_ACTIVE_LAMPS) {
            List<LightBulb> allNonActiveLamps = new ArrayList<LightBulb>();

            for (LightBulb lamp : allLamps) {
                if (lamp.isNonActive()) {
                    allNonActiveLamps.add(lamp);
                }
            }

            if (!allNonActiveLamps.isEmpty()) {
                LightBulb lamp = allNonActiveLamps.get(random(0, allNonActiveLamps.size() - 1));
                activeLamps.add(lamp);

                lamp.activate();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private Group getLifeGroup() {
        Group lifeGroup = new Group();

        lifeGroup.addActor(new GameObject(X_STATUS_POSITION * 2 - HEARD_SIZE - (HEARD_SIZE / 2) - 40, Y_LIFE_POSITION, HEARD_SIZE, HEARD_SIZE, getHeard()));
        lifeGroup.addActor(new GameObject(X_STATUS_POSITION * 2 - (HEARD_SIZE / 2), Y_LIFE_POSITION, HEARD_SIZE, HEARD_SIZE, getHeard()));
        lifeGroup.addActor(new GameObject(X_STATUS_POSITION * 2 + (HEARD_SIZE / 2) + 40, Y_LIFE_POSITION, HEARD_SIZE, HEARD_SIZE, getHeard()));

        return lifeGroup;
    }

    private Group createLamps() {
        Group lightBulbs = new Group();

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION));

        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_WHITE_SPACE));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_WHITE_SPACE));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_WHITE_SPACE));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_WHITE_SPACE) * 2));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_WHITE_SPACE) * 2));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_WHITE_SPACE) * 2));

        return lightBulbs;
    }

    private LightBulb initializeLight(LightBulbPosition position, int yPosition) {
        final LightBulb actor = new LightBulb(position, yPosition);

        actor.addListener(new LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked();
            }
        }, score, lifeActors));

        allLamps.add(actor);

        return actor;
    }
}
