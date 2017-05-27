package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.enums.Text;

import static com.pocket.rocket.broken.AssetLoader.getCoolLabel;
import static com.pocket.rocket.broken.AssetLoader.getCoolText;
import static com.pocket.rocket.broken.Utils.buildLogo;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.Text.ADVENTURE;
import static com.pocket.rocket.broken.enums.Text.LETS_START;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;

public class FinalStep extends Group implements TutorialStep {
    private final Group stage;

    private boolean showUI = true;
    private boolean complete = false;

    public FinalStep(Group stage) {
        this.stage = stage;

        final Group group = new Group();
        Group logo = buildLogo(getCoolText(), getCoolLabel());
        logo.setPosition(logo.getX(), logo.getY() - 100);
        group.addActor(logo);

        Label label1 = new Label(LETS_START.get(), AssetLoader.getFont(Color.GOLD));
        label1.setAlignment(Align.center);
        label1.setFontScale(.7f);
        label1.setPosition(Constants.X_CENTER - label1.getWidth() / 2, 525);
        group.addActor(label1);

        Label label2 = new Label(ADVENTURE.get(), AssetLoader.getFont(Color.GOLD));
        label2.setAlignment(Align.center);
        label2.setFontScale(1.3f);

        Group adventure = new Group();
        adventure.setBounds(Constants.X_CENTER - label2.getWidth() / 2, 475, label2.getWidth(), label2.getHeight());
        adventure.addActor(label2);
        adventure.addAction(pulseAnimation(adventure.getWidth(), adventure.getHeight(), 1.1f, 0.7f));
        group.addActor(adventure);

        TextButton startButton = new TextButton(Text.START.get(), AssetLoader.getButtonStyle());
        startButton.setBounds(X_MENU_BUTTON_POSITION, 170, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addAction(Actions.fadeOut(.3f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        complete = true;
                    }
                }, 0.3f);
            }
        });
        group.addActor(startButton);
        addActor(group);
        addAction(Actions.alpha(0));
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
