package com.pocket.rocket.broken;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.LabelActor;
import com.pocket.rocket.broken.screens.menu.Gallery;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.LOGO_SIZE;

public class Utils {
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

    public static RepeatAction pulseAnimation(float width, float height, float scale, float time) {
        float delta = (scale - 1) * 100;
        return Actions.repeat(
                RepeatAction.FOREVER,
                Actions.sequence(
                        Actions.parallel(Actions.scaleTo(scale, scale, time), Actions.moveBy(-(width / 100 * delta / 2), -(height / 100 * delta / 2), time)),
                        Actions.parallel(Actions.scaleTo(1f, 1f, time), Actions.moveBy(width / 100 * delta / 2, height / 100 * delta / 2, time))
                ));
    }
}
