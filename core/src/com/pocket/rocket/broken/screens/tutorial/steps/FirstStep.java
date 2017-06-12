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

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getLampImage;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Constants.Y_CENTER;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ACTIVE;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ANGRY;
import static com.pocket.rocket.broken.enums.Text.ANGER_BREAK_IN;
import static com.pocket.rocket.broken.enums.Text.DONT_LET;
import static com.pocket.rocket.broken.enums.Text.TAP_RED;

public class FirstStep extends Group implements TutorialStep {
    private final Group stage;

    private boolean showUI = true;
    private boolean complete = false;

    public FirstStep(Group stage) {
        this.stage = stage;

        Group infoGroup = new Group();

        infoGroup.setBounds(130, Y_CENTER + 80, WIDTH - 260, 240);
        Label label1 = new Label(DONT_LET.get(), getFont());
        label1.setAlignment(Align.center);
        label1.setFontScale(2.4f, 2.7f);
        label1.setPosition(infoGroup.getWidth() / 2 - label1.getWidth() / 2, infoGroup.getHeight() - label1.getHeight() - 20);
        infoGroup.addActor(label1);

        Label label2 = new Label(ANGER_BREAK_IN.get(), getFont());
        label2.setAlignment(Align.center);
        label2.setPosition(infoGroup.getWidth() / 2 - label2.getWidth() / 2, label1.getY() - 70);
        label2.setFontScale(1.2f, 1f);
        infoGroup.addActor(label2);

        Label label3 = new Label(TAP_RED.get(), getFont(Color.GOLD));
        label3.setAlignment(Align.center);
        label3.setPosition(infoGroup.getWidth() / 2 - label3.getWidth() / 2, 0);
        label3.setFontScale(1.2f, 1.2f);
        infoGroup.addActor(label3);

        addActor(infoGroup);
        final ImageActor lamp = new ImageActor(X_CENTER - LAMP_WIDTH / 2, Y_CENTER - LAMP_HEIGHT / 2 - 80, LAMP_WIDTH, LAMP_HEIGHT, getLampImage(ANGRY));
        final RepeatAction animation = pulseAnimation(lamp.getWidth(), lamp.getHeight(), 1.15f, 0.7f);
        lamp.addAction(animation);
        lamp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lamp.removeAction(animation);
                lamp.setImage(getLampImage(ACTIVE));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        addAction(Actions.alpha(0f, 0.3f));
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                complete = true;
                            }
                        }, 0.3f);
                    }
                }, 0.25f);
            }
        });
        addActor(lamp);
        addAction(Actions.alpha(0f));
    }

    @Override
    public boolean run() {
        if (showUI) {
            stage.addActor(this);
            addAction(Actions.alpha(1f, 1f));
            showUI = false;
        }

        return complete;
    }

    @Override
    public void disappear() {
        remove();
    }
}
