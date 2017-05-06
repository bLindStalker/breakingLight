package com.pocket.rocket.broken.screens.tutorial;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Queue;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.LightListener;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.actors.BonusBuilder;
import com.pocket.rocket.broken.actors.HeartActor;
import com.pocket.rocket.broken.actors.userData.ScoreActor;
import com.pocket.rocket.broken.enums.LampLogicData;
import com.pocket.rocket.broken.enums.LightBulbPosition;
import com.pocket.rocket.broken.enums.LightBulbStatus;
import com.pocket.rocket.broken.screens.BaseScreen;
import com.pocket.rocket.broken.screens.ResultScreen;
import com.pocket.rocket.broken.screens.tutorial.steps.StepManager;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.TIMER_HEIGHT;
import static com.pocket.rocket.broken.Constants.TIMER_WIDTH;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.Y_LAMP_POSITION;
import static com.pocket.rocket.broken.Constants.Y_STATUS_POSITION;
import static com.pocket.rocket.broken.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.broken.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.broken.enums.LightBulbPosition.RIGHT;
import static com.pocket.rocket.broken.enums.LightBulbStatus.TURN_OFF;
import static com.pocket.rocket.broken.enums.LightBulbStatus.TURN_ON;
import static java.lang.Integer.MAX_VALUE;

public class TutorialScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor = new ScoreActor(WIDTH - 20 - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, AssetLoader.getFont());
    private final BonusBuilder bonusBuilder;
    private final StepManager stepManager;
    private ArrayList<TutorialLamp> lamps = new ArrayList<TutorialLamp>();

    public TutorialScreen(Main main) {
        super(main);
        bonusBuilder = new BonusBuilder(this);
        bonusBuilder.setClickToCreate(MAX_VALUE, MAX_VALUE);

        gameActors = new Group();
        Group lampGroup = createLamps();
        gameActors.addActor(lampGroup);
        addActor(gameActors);

        stepManager = new StepManager(this, lamps, scoreActor, bonusBuilder, lampGroup);
        gameActors.addAction(Actions.alpha(0, 0f));
        gameActors.addAction(Actions.alpha(1, 0.5f));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (stepManager.runStep()) {
            showResult();
        }
    }

    private void showResult() {
        gameActors.addAction(Actions.alpha(0, 0.25f));
        main.setScreen(new ResultScreen(main, 0, scoreActor.getbonusCollected(), 0));
    }

    private Group createLamps() {
        Group lightBulbs = new Group();

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + 300, TURN_OFF));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + 300, TURN_ON));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + 300, TURN_OFF));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + LAMP_HEIGHT + 270, TURN_ON));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + LAMP_HEIGHT + 270, TURN_OFF));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + LAMP_HEIGHT + 270, TURN_ON));

        return lightBulbs;
    }

    private com.pocket.rocket.broken.actors.LightBulb initializeLight(LightBulbPosition position, int yPosition, LightBulbStatus status) {
        final TutorialLamp actor = new TutorialLamp(position, yPosition, status);

        actor.addListener(new LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(new LampLogicData());
            }
        }, new Queue<HeartActor>(), scoreActor, bonusBuilder));

        lamps.add(actor);

        return actor;
    }
}
