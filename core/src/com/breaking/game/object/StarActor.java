package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.breaking.game.AssetLoader;

import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;

public class StarActor extends ImageActor {

    private static final int STAR_SIZE = 60;

    public StarActor(int xPosition, int yPosition, final ScoreActor scoreActor) {
        super(xPosition + (LIGHT_WIDTH / 2) - STAR_SIZE / 2, yPosition + (LIGHT_HEIGHT / 2) - STAR_SIZE / 2, STAR_SIZE, STAR_SIZE, AssetLoader.getStar());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scoreActor.increaseStarScore();
                //remove();
                addAction(Actions.moveBy(100, 800, 5));
            }
        });
    }
}
