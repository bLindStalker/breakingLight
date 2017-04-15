package com.pocket.rocket.broken.screens.tutorial;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Queue;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.enums.LightBulbPosition;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.broken.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.broken.enums.LightBulbPosition.RIGHT;
import static java.lang.Integer.MAX_VALUE;

public class TutorialScreen extends com.pocket.rocket.broken.screens.BaseScreen {
    private final Group gameActors;
    private final com.pocket.rocket.broken.actors.userData.ScoreActor scoreActor;
    private final com.pocket.rocket.broken.actors.StarBuilder starBuilder;
    private final com.pocket.rocket.broken.screens.tutorial.steps.StepManager stepManager;
    private ArrayList<TutorialLamp> lamps = new ArrayList<TutorialLamp>();

    public TutorialScreen(com.pocket.rocket.broken.Main main) {
        super(main);
        starBuilder = new com.pocket.rocket.broken.actors.StarBuilder(this);
        starBuilder.setClickToCreate(MAX_VALUE, MAX_VALUE);

        scoreActor = new com.pocket.rocket.broken.actors.userData.ScoreActor(Constants.WIDTH - 20 - Constants.TIMER_WIDTH, Constants.Y_STATUS_POSITION, Constants.TIMER_WIDTH, Constants.TIMER_HEIGHT, AssetLoader.getFont());

        gameActors = new Group();
        Group lampGroup = createLamps();
        gameActors.addActor(lampGroup);
        addActor(gameActors);

        addActor(new ImageActor(30, 1000, Constants.WIDTH - 60, 250, AssetLoader.getTutorial()));
        stepManager = new com.pocket.rocket.broken.screens.tutorial.steps.StepManager(this, lamps, scoreActor, starBuilder, lampGroup);
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
        main.setScreen(new com.pocket.rocket.broken.screens.ResultScreen(main, 0, scoreActor.getStarCollected(), 0));
    }

    private Group createLamps() {
        Group lightBulbs = new Group();

        lightBulbs.addActor(initializeLight(LEFT, Constants.Y_LAMP_POSITION + 300, com.pocket.rocket.broken.enums.LightBulbStatus.TURN_OFF));
        lightBulbs.addActor(initializeLight(CENTER, Constants.Y_LAMP_POSITION + 300, com.pocket.rocket.broken.enums.LightBulbStatus.TURN_ON));
        lightBulbs.addActor(initializeLight(RIGHT, Constants.Y_LAMP_POSITION + 300, com.pocket.rocket.broken.enums.LightBulbStatus.TURN_OFF));

        lightBulbs.addActor(initializeLight(LEFT, Constants.Y_LAMP_POSITION + Constants.LAMP_HEIGHT + 270, com.pocket.rocket.broken.enums.LightBulbStatus.TURN_ON));
        lightBulbs.addActor(initializeLight(CENTER, Constants.Y_LAMP_POSITION + Constants.LAMP_HEIGHT + 270, com.pocket.rocket.broken.enums.LightBulbStatus.TURN_OFF));
        lightBulbs.addActor(initializeLight(RIGHT, Constants.Y_LAMP_POSITION + Constants.LAMP_HEIGHT + 270, com.pocket.rocket.broken.enums.LightBulbStatus.TURN_ON));

        return lightBulbs;
    }

    private com.pocket.rocket.broken.actors.LightBulb initializeLight(LightBulbPosition position, int yPosition, com.pocket.rocket.broken.enums.LightBulbStatus status) {
        final TutorialLamp actor = new TutorialLamp(position, yPosition, status);

        actor.addListener(new com.pocket.rocket.broken.LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(new com.pocket.rocket.broken.enums.LampLogicData());
            }
        }, new Queue<com.pocket.rocket.broken.actors.AnimatedActor>(), scoreActor, starBuilder));

        lamps.add(actor);

        return actor;
    }
}
