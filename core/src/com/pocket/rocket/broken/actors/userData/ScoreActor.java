package com.pocket.rocket.broken.actors.userData;

import static com.pocket.rocket.broken.Constants.BASIC_SCORE;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.enums.Text.SCORE;

public class ScoreActor extends LabelData {
    private int score = 0;
    private int bonusCollected = 0;
    private int bonus2Collected = 0;
    private int collectedLamps = 0;

    public ScoreActor() {
        super(SCORE.get(), WIDTH - 280);
    }

    public void increaseScore() {
        score += BASIC_SCORE;
    }

    public void increaseLamps() {
        collectedLamps++;
    }

    public void resetLamps() {
        collectedLamps = 0;
    }


    public void increasebonusScore(boolean isDoubleBonus) {
        if (isDoubleBonus) {
            bonus2Collected++;
        } else {
            bonusCollected++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getbonusCollected() {
        return bonusCollected;
    }

    public int getbonus2Collected() {
        return bonus2Collected;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        data.setText(String.valueOf(score));
    }

    public int collectedLamps() {
        return collectedLamps;
    }
}
