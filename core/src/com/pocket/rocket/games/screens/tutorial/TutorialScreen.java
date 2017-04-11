package com.pocket.rocket.games.screens.tutorial;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.pocket.rocket.games.LightListener;
import com.pocket.rocket.games.Main;
import com.pocket.rocket.games.actors.DialogBuilder;
import com.pocket.rocket.games.actors.LightBulb;
import com.pocket.rocket.games.actors.StarBuilder;
import com.pocket.rocket.games.actors.userData.ScoreActor;
import com.pocket.rocket.games.enums.LampLogicData;
import com.pocket.rocket.games.enums.LightBulbPosition;
import com.pocket.rocket.games.enums.LightBulbStatus;
import com.pocket.rocket.games.screens.BaseScreen;
import com.pocket.rocket.games.screens.ResultScreen;
import com.pocket.rocket.games.screens.tutorial.steps.StepManager;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static com.pocket.rocket.games.AssetLoader.getFont;
import static com.pocket.rocket.games.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.games.Constants.TIMER_HEIGHT;
import static com.pocket.rocket.games.Constants.TIMER_WIDTH;
import static com.pocket.rocket.games.Constants.WIDTH;
import static com.pocket.rocket.games.Constants.Y_LAMP_POSITION;
import static com.pocket.rocket.games.Constants.Y_STATUS_POSITION;
import static com.pocket.rocket.games.enums.LightBulbPosition.CENTER;
import static com.pocket.rocket.games.enums.LightBulbPosition.LEFT;
import static com.pocket.rocket.games.enums.LightBulbPosition.RIGHT;
import static java.lang.Integer.MAX_VALUE;

public class TutorialScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor;
    private final StarBuilder starBuilder;
    private final StepManager stepManager;
    private ArrayList<TutorialLamp> lamps = new ArrayList<TutorialLamp>();

    public TutorialScreen(Main main) {
        super(main);
        starBuilder = new StarBuilder(this);
        starBuilder.setClickToCreate(MAX_VALUE, MAX_VALUE);

        scoreActor = new ScoreActor(WIDTH - 20 - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, getFont());

        gameActors = new Group();
        Group lampGroup = createLamps();
        gameActors.addActor(lampGroup);
        addActor(gameActors);

        addActor(new DialogBuilder(50, 1050, 630, 150, "TUTORIAL").setFontScale(1.2f).build());
        stepManager = new StepManager(this, lamps, scoreActor, starBuilder, lampGroup);
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
        main.setScreen(new ResultScreen(main, 0, scoreActor.getStarCollected(), 0));
    }

    private Group createLamps() {
        Group lightBulbs = new Group();

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + 300, LightBulbStatus.TURN_OFF));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + 300, LightBulbStatus.TURN_ON));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + 300, LightBulbStatus.TURN_OFF));

        lightBulbs.addActor(initializeLight(LEFT, Y_LAMP_POSITION + LAMP_HEIGHT + 270, LightBulbStatus.TURN_ON));
        lightBulbs.addActor(initializeLight(CENTER, Y_LAMP_POSITION + LAMP_HEIGHT + 270, LightBulbStatus.TURN_OFF));
        lightBulbs.addActor(initializeLight(RIGHT, Y_LAMP_POSITION + LAMP_HEIGHT + 270, LightBulbStatus.TURN_ON));

        return lightBulbs;
    }

    private LightBulb initializeLight(LightBulbPosition position, int yPosition, LightBulbStatus status) {
        final TutorialLamp actor = new TutorialLamp(position, yPosition, status);

        actor.addListener(new LightListener(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return actor.justClicked(new LampLogicData());
            }
        }, new Array<Actor>(), scoreActor, starBuilder));

        lamps.add(actor);

        return actor;
    }
}
