package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.MAX_CLICK_FOR_BONUS;
import static com.pocket.rocket.broken.Constants.MIN_CLICK_FOR_BONUS;

public class BonusBuilder {

    private final Stage stage;
    private int clickToCreate = random(MIN_CLICK_FOR_BONUS, MAX_CLICK_FOR_BONUS);
    private int clickCount = 0;
    private float bonusVelocity = random(3f, 5.3f);

    public BonusBuilder(Stage stage) {
        this.stage = stage;
    }

    public void buildBonus(int xPosition, int yPosition, final ScoreActor scoreActor) {
        if (clickCount >= clickToCreate) {
            clickCount = 0;
            clickToCreate = random(MIN_CLICK_FOR_BONUS, MAX_CLICK_FOR_BONUS);
            BonusActor actor = new BonusActor(xPosition, yPosition, scoreActor, bonusVelocity);
            stage.addActor(actor);
        } else {
            clickCount++;
        }
    }

    public void setClickToCreate(int min, int max) {
        this.clickToCreate = random(min, max);
    }

    public void setBonusVelocity(float bonusVelocity) {
        this.bonusVelocity = bonusVelocity;
    }
}
