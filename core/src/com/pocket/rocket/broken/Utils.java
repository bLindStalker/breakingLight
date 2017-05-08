package com.pocket.rocket.broken;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.LabelActor;

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
        LabelActor actor = new LabelActor(0, 0, LOGO_SIZE, LOGO_SIZE, text);
        actor.addAction(Actions.rotateTo(360 * 100, 35 * 100));
        logoGroup.addActor(actor);
        return logoGroup;
    }
}
