package com.breaking.game.screens.tutorial;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
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
    private final ArrayList<TutorialLamp> lamps = new ArrayList<TutorialLamp>();
    private int timeForStars = 0;
    private DialogBuilder dialog;
    private int starCount = 3;

    public TutorialScreen(Main main) {
        super(main);
        starBuilder = new StarBuilder(this);
        starBuilder.setClickToCreate(MAX_VALUE, MAX_VALUE);
        gameActors = new Group();

        scoreActor = new ScoreActor(WIDTH - 20 - TIMER_WIDTH, Y_STATUS_POSITION, TIMER_WIDTH, TIMER_HEIGHT, getFont());

        gameActors.addActor(createLamps());
        addActor(gameActors);

        addActor(new DialogBuilder(50, 1050, 630, 150, "TUTORIAL").setFontScale(1).build());
        dialog = new DialogBuilder(50, 200, 630, 150, "BREAK ALL ACTIVE LAMPS!").build();
        addActor(dialog);
        gameActors.addAction(Actions.alpha(0, 0f));
        gameActors.addAction(Actions.alpha(1, 0.5f));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        activateLamp();
        if (scoreActor.getStarCollected() >= 3) {
            showResult();
        }
    }

    private void showResult() {
        gameActors.addAction(Actions.alpha(0, 0.25f));
        main.setScreen(new ResultScreen(main, 230, scoreActor.getStarCollected(), 0));
    }

    private void activateLamp() {
        if (timeForStars > 2) {
            starBuilder.setClickToCreate(1, 3);
            dialog.updateText(String.format("Try to catch %s star!", starCount - scoreActor.getStarCollected()));
        }

        if (timeForStars > 6) {
            starBuilder.setClickToCreate(0, 2);
        }

        if (timeForStars > 9) {
            starBuilder.setClickToCreate(0, 0);
        }

        for (TutorialLamp lamp : lamps) {
            if (lamp.getCurrentStatus() == LightBulbStatus.TURN_ON) {
                return;
            }
        }

        for (final TutorialLamp lamp : lamps) {
            if (lamp.getCurrentStatus() == LightBulbStatus.BROKEN) {
                Timer.schedule(new Timer.Task() {
                                   @Override
                                   public void run() {
                                       lamp.addAction(Actions.alpha(0, 0.4f));
                                       Timer.schedule(new Timer.Task() {
                                                          @Override
                                                          public void run() {
                                                              lamp.setStatus(lamp.getPreviousStatus());
                                                              lamp.addAction(Actions.alpha(1, 0.4f));
                                                          }
                                                      }, 0.5f
                                       );
                                   }
                               }, 0.5f
                );
            } else {
                lamp.setStatus(lamp.getPreviousStatus());
            }
        }
        timeForStars++;
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
