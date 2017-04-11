package com.pocket.rocket.games.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pocket.rocket.games.actors.userData.ScoreActor;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.games.Constants.MAX_CLICK_FOR_STAR;
import static com.pocket.rocket.games.Constants.MIN_CLICK_FOR_STAR;

public class StarBuilder {

    private final Stage stage;
    private int clickToCreate = random(MIN_CLICK_FOR_STAR, MAX_CLICK_FOR_STAR);
    private int clickCount = 0;
    private float starVelocity = random(2.7f, 4.7f);

    public StarBuilder(Stage stage) {
        this.stage = stage;
    }

    public void buildStar(int xPosition, int yPosition, final ScoreActor scoreActor) {
        if (clickCount >= clickToCreate) {
            clickCount = 0;
            clickToCreate = random(MIN_CLICK_FOR_STAR, MAX_CLICK_FOR_STAR);
            StarActor actor = new StarActor(xPosition, yPosition, scoreActor, starVelocity);
            stage.addActor(actor);
        } else {
            clickCount++;
        }
    }

    public void setClickToCreate(int min, int max) {
        this.clickToCreate = random(min, max);
    }

    public void setStarVelocity(float starVelocity) {
        this.starVelocity = starVelocity;
    }
}
