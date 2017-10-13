package com.pocket.rocket.broken.screens.tutorial;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.BonusBuilder;
import com.pocket.rocket.broken.GameLampListener;
import com.pocket.rocket.broken.GameLogicProcessor;
import com.pocket.rocket.broken.LampLogic;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.userData.HeartData;
import com.pocket.rocket.broken.actors.userData.ScoreActor;
import com.pocket.rocket.broken.enums.LightBulbPosition;
import com.pocket.rocket.broken.screens.BaseScreen;
import com.pocket.rocket.broken.screens.MainGameScreen;
import com.pocket.rocket.broken.screens.MenuScreen;
import com.pocket.rocket.broken.screens.tutorial.steps.StepManager;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.actors.BackScreen.HEADER_HEIGHT;
import static com.pocket.rocket.broken.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.broken.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.broken.enums.LightBulbPosition.RIGHT;
import static com.pocket.rocket.broken.enums.Text.TUTORIAL;
import static java.lang.Integer.MAX_VALUE;

public class TutorialScreen extends BaseScreen {
    private final ScoreActor scoreActor = new ScoreActor();
    private final BonusBuilder bonusBuilder;
    private final StepManager stepManager;
    private final LampLogic tutorialLampLogic;
    private final GameLogicProcessor logicProcessor;
    private final Group tutorialGroup;
    private ArrayList<LightBulb> allLamps = new ArrayList<LightBulb>();

    public TutorialScreen(final Main main) {
        super(main);
        bonusBuilder = new BonusBuilder(main, this);
        bonusBuilder.setClickToCreate(MAX_VALUE, MAX_VALUE);

        tutorialLampLogic = new LampLogic();
        tutorialLampLogic.maxAngryLamps = 3;
        tutorialLampLogic.maxAngryTime = 6f;
        tutorialLampLogic.minAngryTime = 3f;
        tutorialLampLogic.maxActiveTime = 3; // yellow
        tutorialLampLogic.minActiveTime = 5;
        tutorialLampLogic.maxNeutralTime = 1;
        tutorialLampLogic.minNeutralTime = 1;

        tutorialGroup = new Group();
        Group lamps = createLamps();
        tutorialGroup.addActor(lamps);
        if (Preference.getTotalScore() > 10) {
            tutorialGroup.addActor(buildHeader(main));
        }

        logicProcessor = new GameLogicProcessor(allLamps, tutorialLampLogic);

        stepManager = new StepManager(tutorialGroup, allLamps, scoreActor, bonusBuilder, lamps);
        addActor(tutorialGroup);
        tutorialGroup.addAction(Actions.alpha(0, 0f));
        tutorialGroup.addAction(Actions.alpha(1, 1f));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                tutorialLampLogic.maxAngryLamps = 5;
            }
        }, 6);
    }

    private Group buildHeader(final Main main) {
        Group headerGroup = new Group();
        headerGroup.addActor(new ImageActor(0, HEIGHT - HEADER_HEIGHT, WIDTH, HEADER_HEIGHT, AssetLoader.getHeader()));
        ImageActor back = new ImageActor(0, HEIGHT - 200 / 2 - 50, 150, 150, AssetLoader.getBack());
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tutorialGroup.addAction(Actions.moveTo(WIDTH, 0, 0.25f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new MenuScreen(main, false));
                    }
                }, 0.25f);
            }
        });
        headerGroup.addActor(back);

        Label headerLabel = new Label(TUTORIAL.get().toUpperCase(), new Label.LabelStyle(getFont()));
        headerLabel.setBounds(X_CENTER - 150, HEIGHT - 125, 300, 100);
        headerLabel.setAlignment(Align.center);
        headerGroup.addActor(headerLabel);
        addActor(headerGroup);

        return headerGroup;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (!stepManager.runStep()) {
            showResult();
        }

        logicProcessor.activateLamp();
    }

    private void showResult() {
        addAction(Actions.alpha(0, 0.25f));
        main.setScreen(new MainGameScreen(main));
    }

    private Group createLamps() {
        Group lightBulbs = new Group();
        lightBulbs.setBounds(30, 150, WIDTH - 60, HEIGHT - 270 - 150);

        lightBulbs.addActor(initializeLight(LEFT, LAMP_HEIGHT + 70));
        lightBulbs.addActor(initializeLight(CENTER, LAMP_HEIGHT + 70));
        lightBulbs.addActor(initializeLight(RIGHT, LAMP_HEIGHT + 70));

        lightBulbs.addActor(initializeLight(LEFT, (LAMP_HEIGHT + 70) * 2));
        lightBulbs.addActor(initializeLight(CENTER, (LAMP_HEIGHT + 70) * 2));
        lightBulbs.addActor(initializeLight(RIGHT, (LAMP_HEIGHT + 70) * 2));

        return lightBulbs;
    }

    private LightBulb initializeLight(LightBulbPosition position, int yPosition) {
        final LightBulb lamp = new LightBulb(position, yPosition);

        lamp.addListener(new GameLampListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return lamp.justClicked(tutorialLampLogic);
            }
        }, new HeartData(main), scoreActor, bonusBuilder, null));

        allLamps.add(lamp);

        return lamp;
    }
}
