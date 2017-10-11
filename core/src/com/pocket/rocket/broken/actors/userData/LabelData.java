package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.AssetLoader;

import static com.badlogic.gdx.utils.Align.center;
import static com.pocket.rocket.broken.Utils.pulseAnimation;

public class LabelData extends Group {
    protected final Label data;

    public LabelData(String text, int xPosition) {
        setBounds(xPosition, 0, 80, 100);
        setOrigin(center);

        Label scoreLabel = new Label(text, AssetLoader.getFont());
        scoreLabel.setFontScale(0.8f);
        scoreLabel.setPosition(0, 55);
        scoreLabel.setAlignment(Align.center);
        addActor(scoreLabel);

        data = new Label(String.valueOf(0), AssetLoader.getFont());
        data.setFontScale(1.4f);
        data.setBounds(0, 0, scoreLabel.getWidth(), 60);
        data.setAlignment(Align.center);
        addActor(data);
    }
}
