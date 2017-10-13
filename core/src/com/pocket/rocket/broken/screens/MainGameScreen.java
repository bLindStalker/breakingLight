package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.BonusBuilder;
import com.pocket.rocket.broken.FreezeBonusBuilder;
import com.pocket.rocket.broken.GameLampListener;
import com.pocket.rocket.broken.GameLogicProcessor;
import com.pocket.rocket.broken.HeartBonusBuilder;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.userData.HeartData;
import com.pocket.rocket.broken.actors.userData.ScoreActor;
import com.pocket.rocket.broken.actors.userData.TimerActor;
import com.pocket.rocket.broken.enums.LightBulbPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.pocket.rocket.broken.Constants.FREEZE_TIME;
import static com.pocket.rocket.broken.Constants.HARD_CORE_TIME;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.RATE_US_SHOW_TIMES;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Preference.getRateCount;
import static com.pocket.rocket.broken.Preference.isRated;
import static com.pocket.rocket.broken.Preference.updateRateCount;
import static com.pocket.rocket.broken.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.broken.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.broken.enums.LightBulbPosition.RIGHT;

public class MainGameScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor;
    private final BonusBuilder bonusBuilder;
    private final HeartBonusBuilder heartBonusBuilder;
    private final FreezeBonusBuilder freezeBonusBuilder;
    private final TimerActor timer;

    private final HeartData heartData;
    private final List<LightBulb> allLamps = new ArrayList<LightBulb>();
    private final GameLogicProcessor gameLogicProcessor;
    private final ImageActor ice;
    private boolean resultIsShow = true;
    private int previousScore;
    private int previousTime = 5;
    private boolean nowFreeze = false;

    public MainGameScreen(Main main) {
        super(main);
        bonusBuilder = new BonusBuilder(main, this);
        heartBonusBuilder = new HeartBonusBuilder(this);
        freezeBonusBuilder = new FreezeBonusBuilder(this);
        gameActors = new Group();

        timer = new TimerActor();
        scoreActor = new ScoreActor();
        heartData = new HeartData(main);

        Group gameObject = new Group();
        gameObject.setBounds(90, HEIGHT - 52 - 100, WIDTH - 180, 100);
        gameObject.addActor(timer);
        gameObject.addActor(scoreActor);
        gameObject.addActor(heartData);
        addActor(gameObject);

        gameActors.addActor(createLamps());

        addActor(gameActors);
        gameLogicProcessor = new GameLogicProcessor(allLamps, timer.lampLogicData);

        gameActors.addAction(Actions.alpha(0, 0f));
        gameActors.addAction(Actions.alpha(1, 0.5f));

        ice = new ImageActor(0, 0, WIDTH, HEIGHT, AssetLoader.getIce());
        ice.addAction(Actions.alpha(0));
        addActor(ice);

        ice.setZIndex(1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if ((heartData.getActiveHearts() == 0 || timer.getTime() <= -1) && resultIsShow) {
            showResult();
            resultIsShow = false;
        }

        gameLogicProcessor.activateLamp();
        checkHearts();
        heartBonusBuilder.buildBonus(timer.getTime(), heartData, timer.freeze);
        freezeBonusBuilder.buildBonus(timer.getTime(), new Runnable() {
            @Override
            public void run() {
                timer.setFreeze();
            }
        });

        freeze();
    }

    private void freeze() {
        if (timer.freeze && !nowFreeze) {
            nowFreeze = true;
            ice.addAction(Actions.sequence(Actions.alpha(.4f, .3f), Actions.alpha(0f, FREEZE_TIME)));
        }

        if (!timer.freeze && nowFreeze) {
            ice.addAction(Actions.alpha(0f, .2f));
            nowFreeze = false;
        }
    }

    private void checkHearts() {
        if (heartData.getActiveHearts() != 0 && previousTime < timer.getTime()) {

            if (previousScore == scoreActor.getScore()) {
                heartData.removeHeart();
            }
            previousScore = scoreActor.getScore();
        }

        previousTime = previousTime < timer.getTime() ? timer.getTime() + HARD_CORE_TIME / timer.getTime() : previousTime;
    }

    private void showResult() {
        gameActors.addAction(Actions.alpha(0, 0.25f));
        Runnable nextScreen = new Runnable() {
            @Override
            public void run() {
                main.setScreen(new GameOverScreen(main, scoreActor, timer.getTime() < 0 ? 0 : timer.getTime()));
            }
        };

        if (!isRated() && getRateCount() == RATE_US_SHOW_TIMES) {
            main.setScreen(new RateUsScreen(main, nextScreen));
        } else {
            updateRateCount();
            nextScreen.run();
        }
    }

    private Group createLamps() {
        Group lightBulbs = new Group();
        lightBulbs.setBounds(30, 150, WIDTH - 60, HEIGHT - 270 - 150);

        lightBulbs.addActor(initializeLight(LEFT, 0));
        lightBulbs.addActor(initializeLight(CENTER, 0));
        lightBulbs.addActor(initializeLight(RIGHT, 0));

        lightBulbs.addActor(initializeLight(LEFT, LAMP_HEIGHT + 70));
        lightBulbs.addActor(initializeLight(CENTER, LAMP_HEIGHT + 70));
        lightBulbs.addActor(initializeLight(RIGHT, LAMP_HEIGHT + 70));

        lightBulbs.addActor(initializeLight(LEFT, (LAMP_HEIGHT + 70) * 2));
        lightBulbs.addActor(initializeLight(CENTER, (LAMP_HEIGHT + 70) * 2));
        lightBulbs.addActor(initializeLight(RIGHT, (LAMP_HEIGHT + 70) * 2));

        return lightBulbs;
    }

    private LightBulb initializeLight(LightBulbPosition position, int yPosition) {
        final LightBulb actor = new LightBulb(position, yPosition);

        actor.addListener(new GameLampListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(timer.lampLogicData);
            }
        }, heartData, scoreActor, bonusBuilder, timer));

        allLamps.add(actor);

        return actor;
    }
}
