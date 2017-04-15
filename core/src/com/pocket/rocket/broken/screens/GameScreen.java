package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer.Task;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.userData.TimerActor;
import com.pocket.rocket.broken.enums.LightBulbPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEART_SIZE;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.TIMER_HEIGHT;
import static com.pocket.rocket.broken.Constants.TIMER_WIDTH;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Constants.Y_HEART_POSITION;
import static com.pocket.rocket.broken.Constants.Y_LAMP_POSITION;
import static com.pocket.rocket.broken.Constants.Y_STATUS_POSITION;
import static com.pocket.rocket.broken.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.broken.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.broken.enums.LightBulbPosition.RIGHT;

public class GameScreen extends BaseScreen {
    private final Group gameActors;
    private final com.pocket.rocket.broken.actors.userData.ScoreActor scoreActor;
    private final com.pocket.rocket.broken.actors.StarBuilder starBuilder;
    private final TimerActor timer;

    private final Queue<com.pocket.rocket.broken.actors.AnimatedActor> heartActors = new Queue<com.pocket.rocket.broken.actors.AnimatedActor>();
    private final List<com.pocket.rocket.broken.actors.LightBulb> allLamps = new ArrayList<com.pocket.rocket.broken.actors.LightBulb>();
    private final List<com.pocket.rocket.broken.actors.LightBulb> activeLamps = new ArrayList<com.pocket.rocket.broken.actors.LightBulb>();
    private boolean resultIsShow = true;

    public GameScreen(com.pocket.rocket.broken.Main main) {
        super(main);
        starBuilder = new com.pocket.rocket.broken.actors.StarBuilder(this);
        gameActors = new Group();

        Label.LabelStyle font = com.pocket.rocket.broken.AssetLoader.getFont();
        font.background = new TextureRegionDrawable(new TextureRegion(com.pocket.rocket.broken.AssetLoader.getButtonUp()));

        timer = new TimerActor(20, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, font);
        gameActors.addActor(timer);

        scoreActor = new com.pocket.rocket.broken.actors.userData.ScoreActor(WIDTH - 20 - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, font);
        gameActors.addActor(scoreActor);

        gameActors.addActor(buildHeartGroup());
        gameActors.addActor(createLamps());

        addActor(gameActors);

        gameActors.addAction(Actions.alpha(0, 0f));
        gameActors.addAction(Actions.alpha(1, 0.5f));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if ((heartActors.size == 0 || timer.getTime() <= -1) && resultIsShow) {
            schedule(new Task() {
                         @Override
                         public void run() {
                             showResult();
                         }
                     }, 0.8f
            );
            resultIsShow = false;
        }

        activateLamp();
    }

    private void showResult() {
        gameActors.addAction(Actions.alpha(0, 0.25f));
        main.setScreen(new ResultScreen(main, scoreActor.getScore(), scoreActor.getStarCollected(), timer.getTime() < 0 ? 0 : timer.getTime()));
    }

    private void activateLamp() {

        List<com.pocket.rocket.broken.actors.LightBulb> canBeActiveLamps = getCanBeActiveLamps();
        activeLamps.removeAll(canBeActiveLamps);
        if (activeLamps.size() < timer.lampData.activeLamps) {
            if (!canBeActiveLamps.isEmpty()) {
                com.pocket.rocket.broken.actors.LightBulb lamp = canBeActiveLamps.get(random(0, canBeActiveLamps.size() - 1));
                activeLamps.add(lamp);

                lamp.activate(timer.lampData);
            }
        }
    }

    private List<com.pocket.rocket.broken.actors.LightBulb> getCanBeActiveLamps() {
        List<com.pocket.rocket.broken.actors.LightBulb> canBeActive = new ArrayList<com.pocket.rocket.broken.actors.LightBulb>();

        for (com.pocket.rocket.broken.actors.LightBulb lamp : allLamps) {
            if (lamp.canBeActive()) {
                canBeActive.add(lamp);
            }
        }

        return canBeActive;
    }

    private Group buildHeartGroup() {
        Group lifeGroup = new Group();
        lifeGroup.addActor(new ImageActor(X_CENTER - ((HEART_SIZE * 3 + 140) / 2), Y_HEART_POSITION - 20, HEART_SIZE * 3 + 140, HEART_SIZE + 40, com.pocket.rocket.broken.AssetLoader.getButtonUp()));

        Animation<TextureRegion> heartAnimation = new Animation<TextureRegion>(1f / 12f, com.pocket.rocket.broken.AssetLoader.getHeart(), Animation.PlayMode.NORMAL);
        int width = heartAnimation.getKeyFrame(0).getRegionWidth();
        int height = heartAnimation.getKeyFrame(0).getRegionHeight();

        com.pocket.rocket.broken.actors.AnimatedActor heart1 = new com.pocket.rocket.broken.actors.AnimatedActor(X_CENTER - width - (width / 2) + 40, Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart1);
        lifeGroup.addActor(heart1);

        com.pocket.rocket.broken.actors.AnimatedActor heart2 = new com.pocket.rocket.broken.actors.AnimatedActor(X_CENTER - (width / 2), Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart2);
        lifeGroup.addActor(heart2);

        com.pocket.rocket.broken.actors.AnimatedActor heart3 = new com.pocket.rocket.broken.actors.AnimatedActor(X_CENTER + (width / 2) - 40, Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart3);
        lifeGroup.addActor(heart3);

        return lifeGroup;
    }

    private Group createLamps() {
        Group lightBulbs = new Group();

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + 300));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + 300));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + 300));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + LAMP_HEIGHT + 270));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + LAMP_HEIGHT + 270));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + LAMP_HEIGHT + 270));

        return lightBulbs;
    }

    private com.pocket.rocket.broken.actors.LightBulb initializeLight(LightBulbPosition position, int yPosition) {
        final com.pocket.rocket.broken.actors.LightBulb actor = new com.pocket.rocket.broken.actors.LightBulb(position, yPosition);

        actor.addListener(new com.pocket.rocket.broken.LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(timer.lampData);
            }
        }, heartActors, scoreActor, starBuilder));

        allLamps.add(actor);

        return actor;
    }
}
