package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.MAX_CLICK_FOR_bonus;
import static com.pocket.rocket.broken.Constants.MIN_CLICK_FOR_bonus;

public class BonusBuilder {

    private final Stage stage;
    private int clickToCreate = random(MIN_CLICK_FOR_bonus, MAX_CLICK_FOR_bonus);
    private int clickCount = 0;
    private float bonusVelocity = random(3f, 5.3f);

    public BonusBuilder(Stage stage) {
        this.stage = stage;
    }

    public void buildbonus(int xPosition, int yPosition, final ScoreActor scoreActor) {
        if (clickCount >= clickToCreate) {
            clickCount = 0;
            clickToCreate = random(MIN_CLICK_FOR_bonus, MAX_CLICK_FOR_bonus);
            BonusActor actor = new BonusActor(xPosition, yPosition, scoreActor, bonusVelocity);
            stage.addActor(actor);
        } else {
            clickCount++;
        }
    }

    public void setClickToCreate(int min, int max) {
        this.clickToCreate = random(min, max);
    }

    public void setbonusVelocity(float bonusVelocity) {
        this.bonusVelocity = bonusVelocity;
    }
}
