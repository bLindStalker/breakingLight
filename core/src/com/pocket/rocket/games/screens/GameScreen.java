package com.pocket.rocket.games.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer.Task;
import com.pocket.rocket.games.AssetLoader;
import com.pocket.rocket.games.LightListener;
import com.pocket.rocket.games.Main;
import com.pocket.rocket.games.actors.AnimatedActor;
import com.pocket.rocket.games.actors.ImageActor;
import com.pocket.rocket.games.actors.LightBulb;
import com.pocket.rocket.games.actors.StarBuilder;
import com.pocket.rocket.games.actors.userData.ScoreActor;
import com.pocket.rocket.games.actors.userData.TimerActor;
import com.pocket.rocket.games.enums.LightBulbPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.games.AssetLoader.getButtonUp;
import static com.pocket.rocket.games.AssetLoader.getFont;
import static com.pocket.rocket.games.Constants.HEART_SIZE;
import static com.pocket.rocket.games.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.games.Constants.TIMER_HEIGHT;
import static com.pocket.rocket.games.Constants.TIMER_WIDTH;
import static com.pocket.rocket.games.Constants.WIDTH;
import static com.pocket.rocket.games.Constants.X_CENTER;
import static com.pocket.rocket.games.Constants.Y_HEART_POSITION;
import static com.pocket.rocket.games.Constants.Y_LAMP_POSITION;
import static com.pocket.rocket.games.Constants.Y_STATUS_POSITION;
import static com.pocket.rocket.games.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.games.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.games.enums.LightBulbPosition.RIGHT;

public class GameScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor;
    private final StarBuilder starBuilder;
    private final TimerActor timer;

    private final Queue<AnimatedActor> heartActors = new Queue<AnimatedActor>();
    private final List<LightBulb> allLamps = new ArrayList<LightBulb>();
    private final List<LightBulb> activeLamps = new ArrayList<LightBulb>();
    private boolean resultIsShow = true;

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

    private Group buildHeartGroup() {
        Group lifeGroup = new Group();
        lifeGroup.addActor(new ImageActor(X_CENTER - ((HEART_SIZE * 3 + 140) / 2), Y_HEART_POSITION - 20, HEART_SIZE * 3 + 140, HEART_SIZE + 40, getButtonUp()));

        Animation<TextureRegion> heartAnimation = new Animation<TextureRegion>(1f / 12f, AssetLoader.getHeart(), Animation.PlayMode.NORMAL);
        int width = heartAnimation.getKeyFrame(0).getRegionWidth();
        int height = heartAnimation.getKeyFrame(0).getRegionHeight();

        AnimatedActor heart1 = new AnimatedActor(X_CENTER - width - (width / 2) + 40, Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart1);
        lifeGroup.addActor(heart1);

        AnimatedActor heart2 = new AnimatedActor(X_CENTER - (width / 2), Y_HEART_POSITION, width, height, heartAnimation);
        heartActors.addLast(heart2);
        lifeGroup.addActor(heart2);

        AnimatedActor heart3 = new AnimatedActor(X_CENTER + (width / 2) - 40, Y_HEART_POSITION, width, height, heartAnimation);
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
