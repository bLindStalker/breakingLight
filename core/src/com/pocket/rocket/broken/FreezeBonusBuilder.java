package com.pocket.rocket.broken;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pocket.rocket.broken.actors.FreezeBonusActor;

import static com.pocket.rocket.broken.Constants.FREEZE_CREATED_BONUS_INTERVAL;
import static com.pocket.rocket.broken.Constants.MAX_FREEZE_BONUS_CHANCE;
import static com.pocket.rocket.broken.Constants.MIN_FREEZE_BONUS_CHANCE;
import static com.pocket.rocket.broken.Constants.MIN_TIME_FOR_FREEZE_BONUS;
import static com.pocket.rocket.broken.Preference.bonusActivatedFreeze;

public class FreezeBonusBuilder {


    private final Stage stage;
    private int minTime = MIN_TIME_FOR_FREEZE_BONUS;
    private int chance = MIN_FREEZE_BONUS_CHANCE;

    public FreezeBonusBuilder(Stage stage) {
        this.stage = stage;
    }

    public void buildBonus(int time, Runnable setFreeze) {
        if (!bonusActivatedFreeze()) {
            return;
        }

        if (minTime == time) {
            int random = MathUtils.random(0, 100);
            if (random - chance <= 0) {
                stage.addActor(new FreezeBonusActor(setFreeze));
            } else {
                if (chance != MAX_FREEZE_BONUS_CHANCE) {
                    chance += 10;
                }
            }
            minTime += FREEZE_CREATED_BONUS_INTERVAL;
        }
    }
}
