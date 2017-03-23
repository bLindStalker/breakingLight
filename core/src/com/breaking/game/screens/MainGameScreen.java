package com.breaking.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.breaking.game.LightListener;
import com.breaking.game.Main;
import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.object.ImageActor;
import com.breaking.game.object.LightBulb;
import com.breaking.game.object.ScoreActor;
import com.breaking.game.object.TimerActor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.AssetLoader.getHeard;
import static com.breaking.game.Constants.HEARD_SIZE;
import static com.breaking.game.Constants.LAMPS_SPACE;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.TIMER_HEIGHT;
import static com.breaking.game.Constants.TIMER_WIDTH;
import static com.breaking.game.Constants.X_STATUS_POSITION;
import static com.breaking.game.Constants.Y_LAMP_POSITION;
import static com.breaking.game.Constants.Y_LIFE_POSITION;
import static com.breaking.game.Constants.Y_STATUS_POSITION;
import static com.breaking.game.enums.LightBulbPosition.CENTER;
import static com.breaking.game.enums.LightBulbPosition.LEFT;
import static com.breaking.game.enums.LightBulbPosition.RIGHT;

public class MainGameScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor;
    private TimerActor timer;
    private Array<Actor> lifeActors;
    private List<LightBulb> allLamps = new ArrayList<LightBulb>();
    private List<LightBulb> activeLamps = new ArrayList<LightBulb>();

    public MainGameScreen(Main main) {
        super(main);

        gameActors = new Group();

        timer = new TimerActor(X_STATUS_POSITION - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT);
        gameActors.addActor(timer);

        Group lifeGroup = getLifeGroup();
        gameActors.addActor(lifeGroup);
        lifeActors = new Array<Actor>(lifeGroup.getChildren());

        scoreActor = new ScoreActor(X_STATUS_POSITION * 3, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT);
        gameActors.addActor(scoreActor);

        gameActors.addActor(createLamps());
        addActor(gameActors);

        gameActors.addAction(Actions.alpha(0, 0f));
        gameActors.addAction(Actions.alpha(1, 0.5f));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (lifeActors.size == 0) {
            showResult("Game Over");
        }

        if (timer.getTime() <= -1) {
            showResult("Times Over");
        }

        activateLamp();
    }

    private void showResult(String header) {
        gameActors.addAction(Actions.alpha(0, 0.25f));
        main.setScreen(new ResultScreen(main, scoreActor.getScore(), scoreActor.getStarCollected(), header, timer.getTime() < 0 ? 0 : timer.getTime()));
    }

    private void activateLamp() {

        List<LightBulb> canBeActiveLamps = getCanBeActiveLamps();
        activeLamps.removeAll(canBeActiveLamps);
        if (activeLamps.size() < timer.lampData.activeLamps) {
            if (!canBeActiveLamps.isEmpty()) {
                LightBulb lamp = canBeActiveLamps.get(random(0, canBeActiveLamps.size() - 1));
                activeLamps.add(lamp);

                lamp.activate(timer.lampData);
            }
        }
    }

    private List<LightBulb> getCanBeActiveLamps() {
        List<LightBulb> canBeActive = new ArrayList<LightBulb>();

        for (LightBulb lamp : allLamps) {
            if (lamp.canBeActive()) {
                canBeActive.add(lamp);
            }
        }

        return canBeActive;
    }

    private Group getLifeGroup() {
        Group lifeGroup = new Group();

        lifeGroup.addActor(new ImageActor(X_STATUS_POSITION * 2 - HEARD_SIZE - (HEARD_SIZE / 2) - 40, Y_LIFE_POSITION, HEARD_SIZE, HEARD_SIZE, getHeard()));
        lifeGroup.addActor(new ImageActor(X_STATUS_POSITION * 2 - (HEARD_SIZE / 2), Y_LIFE_POSITION, HEARD_SIZE, HEARD_SIZE, getHeard()));
        lifeGroup.addActor(new ImageActor(X_STATUS_POSITION * 2 + (HEARD_SIZE / 2) + 40, Y_LIFE_POSITION, HEARD_SIZE, HEARD_SIZE, getHeard()));

        return lifeGroup;
    }

    private Group createLamps() {
        Group lightBulbs = new Group();

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_SPACE));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_SPACE));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + LIGHT_HEIGHT + LAMPS_SPACE));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_SPACE) * 2));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_SPACE) * 2));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + (LIGHT_HEIGHT + LAMPS_SPACE) * 2));

        return lightBulbs;
    }

    private LightBulb initializeLight(LightBulbPosition position, int yPosition) {
        final LightBulb actor = new LightBulb(position, yPosition);

        actor.addListener(new LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(timer.lampData);
            }
        }, lifeActors, scoreActor));

        allLamps.add(actor);

        return actor;
    }
}
