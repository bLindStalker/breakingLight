package com.breaking.game.screens.tutorial;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.breaking.game.LightListener;
import com.breaking.game.Main;
import com.breaking.game.actors.DialogBuilder;
import com.breaking.game.actors.LightBulb;
import com.breaking.game.actors.StarBuilder;
import com.breaking.game.actors.userData.ScoreActor;
import com.breaking.game.enums.LampLogicData;
import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.enums.LightBulbStatus;
import com.breaking.game.screens.BaseScreen;
import com.breaking.game.screens.ResultScreen;
import com.breaking.game.screens.tutorial.steps.StepManager;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static com.breaking.game.AssetLoader.getFont;
import static com.breaking.game.Constants.LAMP_HEIGHT;
import static com.breaking.game.Constants.TIMER_HEIGHT;
import static com.breaking.game.Constants.TIMER_WIDTH;
import static com.breaking.game.Constants.WIDTH;
import static com.breaking.game.Constants.Y_LAMP_POSITION;
import static com.breaking.game.Constants.Y_STATUS_POSITION;
import static com.breaking.game.enums.LightBulbPosition.CENTER;
import static com.breaking.game.enums.LightBulbPosition.LEFT;
import static com.breaking.game.enums.LightBulbPosition.RIGHT;
import static java.lang.Integer.MAX_VALUE;

public class TutorialScreen extends BaseScreen {
    private final Group gameActors;
    private final ScoreActor scoreActor;
    private final StarBuilder starBuilder;
    private final StepManager stepManager;
    private int timeToStars = 0;
    private int cachedStarsCount = 6;
    private boolean cachedFirstStar = false;
    private ArrayList<TutorialLamp> lamps = new ArrayList<TutorialLamp>();

    public TutorialScreen(Main main) {
        super(main);
        starBuilder = new StarBuilder(this);
        starBuilder.setClickToCreate(MAX_VALUE, MAX_VALUE);
        gameActors = new Group();

        scoreActor = new ScoreActor(WIDTH - 20 - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, getFont());

        gameActors.addActor(createLamps());
        addActor(gameActors);

        addActor(new DialogBuilder(50, 1050, 630, 150, "TUTORIAL").setFontScale(1.2f).build());
        stepManager = new StepManager(this, lamps, scoreActor, starBuilder);
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
        main.setScreen(new ResultScreen(main, 230, scoreActor.getStarCollected(), 0));
    }


    private void manageStar() {
        if (timeToStars > 3 && !cachedFirstStar) {
            starBuilder.setClickToCreate(0, 0);
            //dialog.setText("Try to catch star!");
            cachedFirstStar = scoreActor.getStarCollected() == 1;
            timeToStars--;
        }

        if (timeToStars > 4) {
            starBuilder.setClickToCreate(1, 3);
            // dialog.setText(format("Try to catch %s star!", cachedStarsCount - scoreActor.getStarCollected()));
        }

        if (timeToStars > 6) {
            starBuilder.setClickToCreate(0, 2);
        }

        if (timeToStars > 9) {
            starBuilder.setClickToCreate(0, 0);
        }
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
