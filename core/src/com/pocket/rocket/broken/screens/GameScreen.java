package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer.Task;
import com.pocket.rocket.broken.LightListener;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.actors.HeartActor;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.StarBuilder;
import com.pocket.rocket.broken.actors.userData.ScoreActor;
import com.pocket.rocket.broken.actors.userData.TimerActor;
import com.pocket.rocket.broken.enums.LightBulbPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getButtonUp;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getHeart;
import static com.pocket.rocket.broken.Constants.HARD_CORE_TIME;
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
import static java.util.Collections.shuffle;

public class GameScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor;
    private final StarBuilder starBuilder;
    private final TimerActor timer;

    private final Queue<HeartActor> heartActors = new Queue<HeartActor>();
    private final List<LightBulb> allLamps = new ArrayList<LightBulb>();
    private final List<LightBulb> activeLamps = new ArrayList<LightBulb>();
    private boolean resultIsShow = true;

    private int previousScore;
    private int previousTime = 5;

    public GameScreen(Main main) {
        super(main);
        starBuilder = new StarBuilder(this);
        gameActors = new Group();

        Label.LabelStyle font = getFont();
        font.background = new TextureRegionDrawable(new TextureRegion(getButtonUp()));

        timer = new TimerActor(20, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, font);
        gameActors.addActor(timer);

        scoreActor = new ScoreActor(WIDTH - 20 - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, font);
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
            }, 0.8f);
            resultIsShow = false;
        }

        activateLamp();
        checkHearts();
    }

    private void checkHearts() {
        if (heartActors.size != 0 && previousTime < timer.getTime()) {

            if (previousScore == scoreActor.getScore()) {
                final HeartActor heart = heartActors.first();
                heart.animate();
                heartActors.removeValue(heart, false);
            }
            previousScore = scoreActor.getScore();
        }

        previousTime = previousTime < timer.getTime() ? timer.getTime() + HARD_CORE_TIME / timer.getTime() : previousTime;
    }

    private void showResult() {
        gameActors.addAction(Actions.alpha(0, 0.25f));
        main.setScreen(new ResultScreen(main, scoreActor.getScore(), scoreActor.getStarCollected(), timer.getTime() < 0 ? 0 : timer.getTime()));
    }

    private void activateLamp() {

        List<LightBulb> canBeActiveLamps = getCanBeActiveLamps();
        activeLamps.removeAll(canBeActiveLamps);
        if (activeLamps.size() < timer.lampData.activeLamps) {
            if (!canBeActiveLamps.isEmpty()) {
                shuffle(canBeActiveLamps);
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

    private Group buildHeartGroup() {
        Group lifeGroup = new Group();
        lifeGroup.addActor(new ImageActor(X_CENTER - ((HEART_SIZE * 3 + 140) / 2), Y_HEART_POSITION - 20, HEART_SIZE * 3 + 140, HEART_SIZE + 40, getButtonUp()));

        Animation<TextureRegion> heartAnimation = new Animation<TextureRegion>(1f / 12f, getHeart(), Animation.PlayMode.NORMAL);
        int width = heartAnimation.getKeyFrame(0).getRegionWidth();
        int height = heartAnimation.getKeyFrame(0).getRegionHeight();

        HeartActor heart1 = new HeartActor(X_CENTER - width - (width / 2) + 40, Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart1);
        lifeGroup.addActor(heart1);

        HeartActor heart2 = new HeartActor(X_CENTER - (width / 2), Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart2);
        lifeGroup.addActor(heart2);

        HeartActor heart3 = new HeartActor(X_CENTER + (width / 2) - 40, Y_HEART_POSITION, width, height, heartAnimation);
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

    private LightBulb initializeLight(LightBulbPosition position, int yPosition) {
        final LightBulb actor = new LightBulb(position, yPosition);

        actor.addListener(new LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(timer.lampData);
            }
        }, heartActors, scoreActor, starBuilder));

        allLamps.add(actor);

        return actor;
    }
}
