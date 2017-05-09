package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.DialogBuilder;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.AssetLoader.getBonus;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getLampImage;
import static com.pocket.rocket.broken.Constants.BASIC_BONUS_SCORE;
import static com.pocket.rocket.broken.Constants.BASIC_SCORE;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_BONUS_SIZE;
import static com.pocket.rocket.broken.Constants.Y_BONUS_SIZE;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ANGRY;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;

public class FinalStep implements TutorialStep {
    private final Stage stage;
    private final Group infoGroup;
    private final Group lampGroup;
    private final Label completeButton;

    private boolean showInfoDialog = true;
    private boolean complete = false;

    public FinalStep(Stage stage, Group lampGroup) {
        this.stage = stage;
        this.lampGroup = lampGroup;
        infoGroup = new Group();
        infoGroup.setBounds((WIDTH - 630) / 2, (Constants.HEIGHT / 2) - 100, 630, 400);
        infoGroup.addActor(new DialogBuilder(0, 0, 630, 400, "GOOD JOB!").textPosition(350).build());

        infoGroup.addActor(new ImageActor(50, 170, LAMP_WIDTH / 2, LAMP_HEIGHT / 2, getLampImage(ANGRY)));
        Label score = new Label("= " + BASIC_SCORE, new Label.LabelStyle(getFont()));
        score.setPosition(150, 190);
        infoGroup.addActor(score);

        infoGroup.addActor(new ImageActor(425, 170, (int) (X_BONUS_SIZE / 1.5f), (int) (Y_BONUS_SIZE / 1.5f), getBonus()));
        Label bonus = new Label("= " + BASIC_BONUS_SCORE, new Label.LabelStyle(getFont()));
        bonus.setPosition(475, 190);
        infoGroup.addActor(bonus);

        completeButton = new Label("COMPLETE", new Label.LabelStyle(getFont()));
        completeButton.setBounds(630 / 2 - MENU_BUTTON_WIDTH / 2, 20, MENU_BUTTON_WIDTH, 100);
        completeButton.setAlignment(Align.center);
        completeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoGroup.addAction(Actions.alpha(0, 0.3f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        complete = true;
                    }
                }, 0.35f);
            }
        });
        infoGroup.addActor(completeButton);
        completeButton.addAction(Actions.alpha(0, 0));
    }

    @Override
    public boolean run() {
        if (showInfoDialog) {
            lampGroup.addAction(Actions.alpha(0f, 0.35f));
            stage.addActor(infoGroup);
            infoGroup.addAction(Actions.alpha(0f, 0f));
            infoGroup.addAction(Actions.alpha(1f, 1f));

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    completeButton.addAction(Actions.alpha(1, 0.5f));
                }
            }, 2f);
            showInfoDialog = false;

        } else {
            if (complete) {
                return true;
            }
        }

        return false;
    }
}
