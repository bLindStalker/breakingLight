package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.AssetLoader.getBonus;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.BASIC_BONUS_SCORE;
import static com.pocket.rocket.broken.Constants.BONUS_HEIGHT;
import static com.pocket.rocket.broken.Constants.BONUS_WIDTH;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Constants.Y_CENTER;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.Text.CATCH_THE_LIGHTNING;
import static com.pocket.rocket.broken.enums.Text.EARN_POINTS;
import static com.pocket.rocket.broken.enums.Text.GAIN;
import static com.pocket.rocket.broken.enums.Text.YOUR_POWER;
import static java.lang.String.format;

public class SecondStep extends Group implements TutorialStep {
    private final Group stage;
    private final Group infoGroup;

    private boolean showIU = true;
    private boolean complete = false;

    public SecondStep(Group stage) {
        this.stage = stage;

        infoGroup = new Group();

        infoGroup.setBounds(130, Y_CENTER + 80, WIDTH - 260, 240);
        Label label1 = new Label(GAIN.get(), getFont());
        label1.setAlignment(Align.center);
        label1.setFontScale(2.4f, 2.7f);
        label1.setPosition(infoGroup.getWidth() / 2 - label1.getWidth() / 2, infoGroup.getHeight() - label1.getHeight() - 20);
        infoGroup.addActor(label1);

        Label label2 = new Label(YOUR_POWER.get(), getFont());
        label2.setAlignment(Align.center);
        label2.setPosition(infoGroup.getWidth() / 2 - label2.getWidth() / 2, label1.getY() - 65);
        label2.setFontScale(1.2f, 1f);
        infoGroup.addActor(label2);

        Label label3 = new Label(format(EARN_POINTS.get(), BASIC_BONUS_SCORE), getFont());
        label3.setAlignment(Align.center);
        label3.setPosition(infoGroup.getWidth() / 2 - label3.getWidth() / 2, label2.getY() - 50);
        label3.setFontScale(0.8f, 0.8f);
        infoGroup.addActor(label3);

        Label label4 = new Label(CATCH_THE_LIGHTNING.get(), getFont(Color.GOLD));
        label4.setAlignment(Align.center);
        label4.setPosition(infoGroup.getWidth() / 2 - label4.getWidth() / 2, 0);
        label4.setFontScale(1.2f, 1.2f);
        infoGroup.addActor(label4);

        addActor(infoGroup);
        final Group _this = this;
        final ImageActor lamp = new ImageActor(X_CENTER - BONUS_WIDTH / 2, Y_CENTER - BONUS_HEIGHT / 2 - 50, BONUS_WIDTH, BONUS_HEIGHT, getBonus(false));
        final RepeatAction animation = pulseAnimation(lamp.getWidth(), lamp.getHeight(), 1.10f, 0.7f);
        lamp.addAction(animation);
        lamp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lamp.removeAction(animation);
                _this.addAction(Actions.alpha(0f, 0.55f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        complete = true;
                    }
                }, 0.55f);
            }
        });
        addActor(lamp);
        addAction(Actions.alpha(0f));
    }

    @Override
    public boolean run() {
        if (showIU) {
            stage.addActor(this);
            addAction(Actions.alpha(1f, 1f));
            showIU = false;
        }

        return complete;
    }

    @Override
    public void disappear() {
        remove();
    }
}
