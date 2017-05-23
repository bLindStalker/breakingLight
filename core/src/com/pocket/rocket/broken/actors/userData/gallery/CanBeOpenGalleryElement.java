package com.pocket.rocket.broken.actors.userData.gallery;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.pocket.rocket.broken.Utils.buildDisplayLabel;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.Text.TAP_TO_OPEN;

public class CanBeOpenGalleryElement extends GalleryElement {
    public CanBeOpenGalleryElement(String description, Texture texture, final ClickListener clickListener) {
        final OpenGalleryElement item = new OpenGalleryElement(description, texture, clickListener);
        item.addAction(alpha(0));
        item.setVisible(false);

        addActor(item);

        final Group group = new Group();
        group.setBounds(getX(), getY(), getWidth(), getHeight());

        group.addActor(buildElement(AssetLoader.getLightUnlock()));
        ImageActor lockedItem = buildElement(texture);
        lockedItem.setColor(new Color(102 / 255f, 21 / 255f, 193 / 255f, 1));
        lockedItem.addAction(pulseAnimation(texture.getWidth(), texture.getHeight(), 1.15f, 0.7f));
        lockedItem.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                item.setVisible(true);
                group.addAction(fadeOut(0.5f));
                clickListener.clicked(event, x, y);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        item.addAction(fadeIn(0.7f));
                        group.remove();
                    }
                }, 0.5f);
            }
        });

        group.addActor(lockedItem);
        group.addActor(buildDisplayLabel(TAP_TO_OPEN.get()));

        addActor(group);
    }
}
