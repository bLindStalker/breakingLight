package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.AssetLoader.getBackGround;

public class BackGroundStage extends Stage {

    public BackGroundStage() {
        super();
        addActor(new ImageActor(0, 0, getWidth(), getHeight(), getBackGround()));
    }
}
