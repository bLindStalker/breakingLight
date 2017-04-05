package com.breaking.game.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.breaking.game.actors.userData.ScoreActor;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.Constants.MAX_CLICK_FOR_STAR;
import static com.breaking.game.Constants.MIN_CLICK_FOR_STAR;

public class StarBuilder {

    private final Stage stage;
    private int clickToCreate = random(MIN_CLICK_FOR_STAR, MAX_CLICK_FOR_STAR);
    private int clickCount = 0;

    public StarBuilder(Stage stage) {
        this.stage = stage;
    }

    public void buildStar(int xPosition, int yPosition, final ScoreActor scoreActor) {
        if (clickCount >= clickToCreate) {
            clickCount = 0;
            clickToCreate = random(MIN_CLICK_FOR_STAR, MAX_CLICK_FOR_STAR);
            stage.addActor(new StarActor(xPosition, yPosition, scoreActor));
        } else {
            clickCount++;
        }
    }

    public void setClickToCreate(int min, int max) {
        this.clickToCreate = random(min, max);
    }
}
