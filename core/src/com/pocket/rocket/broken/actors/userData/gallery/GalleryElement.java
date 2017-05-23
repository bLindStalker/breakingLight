package com.pocket.rocket.broken.actors.userData.gallery;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.AssetLoader.getChecked;
import static com.pocket.rocket.broken.AssetLoader.getGallery;
import static com.pocket.rocket.broken.screens.menu.Gallery.GALLERY_HEIGHT;
import static com.pocket.rocket.broken.screens.menu.Gallery.GALLERY_WIDTH;
import static com.pocket.rocket.broken.screens.menu.Gallery.X_ROUND_POSITION;
import static com.pocket.rocket.broken.screens.menu.Gallery.Y_ROUND_POSITION;

public class GalleryElement extends Group {
    private Actor select = new ImageActor(X_ROUND_POSITION, Y_ROUND_POSITION, 45, 45, getChecked());

    public GalleryElement() {
        setBounds(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT);
        addActor(new ImageActor(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT, getGallery()));
        select.setVisible(false);
        addActor(select);
    }

    protected ImageActor buildElement(Texture texture) {
        return new ImageActor((int) getWidth() / 2 - texture.getWidth() / 2,
                (int) getHeight() / 2 - texture.getHeight() / 2 + 60,
                texture.getWidth(), texture.getHeight(),
                texture);
    }

    public Actor getSelect() {
        return select;
    }
}
