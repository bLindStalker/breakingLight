package com.pocket.rocket.broken.actors.userData.gallery;

import com.badlogic.gdx.graphics.Texture;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.AssetLoader.getLockedGalleryElement;
import static com.pocket.rocket.broken.Utils.buildDisplayLabel;

public class LockedGalleryElement extends GalleryElement {
    public LockedGalleryElement(String text) {
        Texture lockedItem = getLockedGalleryElement();
        addActor(new ImageActor((int) getWidth() / 2 - lockedItem.getWidth() / 2,
                (int) getHeight() / 2 - lockedItem.getHeight() / 2 + 60,
                lockedItem.getWidth(), lockedItem.getHeight(),
                lockedItem));
        addActor(buildDisplayLabel("to unlock you need: \n" + text));
    }
}
