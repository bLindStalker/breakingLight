package com.pocket.rocket.broken;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.LabelActor;
import com.pocket.rocket.broken.screens.menu.Gallery;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGalleryLight;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.LOGO_SIZE;

public class Utils {
    public static final int MAX_Y_POSITION = 800;
    public static final int MIN_Y_POSITION = 150;

    private Utils() {
    }

    public static Group buildLogo(Texture text, Texture logo) {
        Group logoGroup = new Group();
        logoGroup.setBounds((WIDTH - LOGO_SIZE) / 2, HEIGHT - LOGO_SIZE - 100, LOGO_SIZE, LOGO_SIZE);
        logoGroup.addActor(new ImageActor(0, 0, LOGO_SIZE, LOGO_SIZE, logo));
        logoGroup.addActor(new LabelActor(0, 0, LOGO_SIZE, LOGO_SIZE, text));
        return logoGroup;
    }

    public static Label buildDisplayLabel(String text) {
        final Label display = new Label(text, getFont());
        display.setAlignment(Align.center);
        display.setPosition(Gallery.GALLERY_WIDTH / 2 - display.getWidth() / 2, 60);
        display.setFontScale(0.7f, 0.7f);
        display.addAction(Actions.alpha(0.7f));

        return display;
    }

    @Deprecated
    public static RepeatAction pulseAnimation(float width, float height, float scale, float time) {
        float delta = (scale - 1) * 100;
        return Actions.repeat(
                RepeatAction.FOREVER,
                sequence(
                        Actions.parallel(scaleTo(scale, scale, time), Actions.moveBy(-(width / 100 * delta / 2), -(height / 100 * delta / 2), time)),
                        Actions.parallel(scaleTo(1f, 1f, time), Actions.moveBy(width / 100 * delta / 2, height / 100 * delta / 2, time))
                ));
    }

    public static RepeatAction pulseAnimation(float delta, float time) {
        return pulseAnimation(delta, time, RepeatAction.FOREVER);
    }

    public static RepeatAction pulseAnimation(float delta, float time, int count) {
        float scale = 1 + delta;
        return Actions.repeat(
                count,
                sequence(scaleTo(scale, scale, time), scaleTo(1f, 1f, time))
        );
    }

    public static void showWord(float x, float y, Stage stage, String text) {
        final Label label = new Label(text, new Label.LabelStyle(AssetLoader.getFont()));
        label.setPosition(x, y);
        label.addAction(Actions.alpha(0));
        label.addAction(Actions.alpha(0.7f, 0.4f));
        label.addAction(Actions.moveBy(0, 170, 1.7f));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                label.addAction(Actions.fadeOut(0.4f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        label.remove();
                    }
                }, 0.5f);
            }
        }, 1.2f);
        stage.addActor(label);
    }

    public static Action[] buildMoveActions(int xPosition, float velocity) {
        int steps = random(2, 6);

        int amountX = (xPosition > 0 ? -WIDTH - 200 : WIDTH + 200) / steps;
        int amountY = generateVector();

        MoveByAction[] actions = new MoveByAction[steps];
        int interpolation = 1;

        for (int i = 0; i < steps; i++){
            actions[i] = Actions.moveBy(amountX, amountY * interpolation, velocity / steps);
            interpolation *= -1;
        }

        return actions;
    }

    public static ImageActor buildShine(Color color, float width, float height) {
        final ImageActor shine = new ImageActor(width / 2 - 100, height / 2 - 100, 200, 200, getGalleryLight());
        shine.setOrigin(Align.center);
        shine.addAction(Actions.forever(Actions.rotateBy(360, 2f)));
        shine.setColor(color);
        return shine;
    }

    private static int generateVector() {
        int vector = random(-300, 300);
        return MIN_Y_POSITION + vector < MAX_Y_POSITION || MAX_Y_POSITION + vector > MAX_Y_POSITION
                ? -vector
                : vector;
    }
}
