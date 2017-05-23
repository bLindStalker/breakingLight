package com.pocket.rocket.broken.actors.userData.gallery;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.screens.menu.Gallery;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Utils.buildDisplayLabel;
import static com.pocket.rocket.broken.enums.Text.UNLOCKED;

public class OpenGalleryElement extends GalleryElement {
    public OpenGalleryElement(String description, Texture texture, ClickListener clickListener) {
        addActor(buildElement(texture));
        addActor(buildDescriptionLabel(description));
        addActor(buildDisplayLabel(UNLOCKED.get()));
        addListener(clickListener);
    }

    private Label buildDescriptionLabel(String description) {
        final Label display = new Label(description, getFont());
        display.setAlignment(Align.center);
        display.setPosition(Gallery.GALLERY_WIDTH / 2 - display.getWidth() / 2, 120);
        return display;
    }
}
