package com.pocket.rocket.broken;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pocket.rocket.broken.actors.HeartBonusActor;
import com.pocket.rocket.broken.actors.userData.HeartData;

import static com.pocket.rocket.broken.Constants.HEARD_CREATED_BONUS_INTERVAL;
import static com.pocket.rocket.broken.Constants.MAX_HEART_BONUS_CHANCE;
import static com.pocket.rocket.broken.Constants.MIN_HEART_BONUS_CHANCE;
import static com.pocket.rocket.broken.Preference.bonusActivatedHeart;

public class HeartBonusBuilder {

    private final Stage stage;
    private int minTime = Constants.MIN_TIME_FOR_HEART_BONUS;
    private int chance = MIN_HEART_BONUS_CHANCE;

    public HeartBonusBuilder(Stage stage) {
        this.stage = stage;
    }

    public void buildBonus(int time, final HeartData heartdata, boolean nowFreeze) {
        if (!bonusActivatedHeart()) {
            return;
        }

        if (minTime == time) {
            int random = MathUtils.random(0, 100);
            if (random - chance <= 0) {
                stage.addActor(new HeartBonusActor(heartdata, nowFreeze));
            } else {
                if (chance != MAX_HEART_BONUS_CHANCE) {
                    chance += 10;
                }
            }
            minTime += HEARD_CREATED_BONUS_INTERVAL;
        }
    }
}
