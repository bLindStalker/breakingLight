package com.pocket.rocket.broken.actors.userData;

import static com.pocket.rocket.broken.Constants.BASIC_SCORE;
import static com.pocket.rocket.broken.Constants.WIDTH;

public class ScoreActor extends LabelData {
    private int score = 0;
    private int bonusCollected = 0;
    private int bonus2Collected = 0;

    public ScoreActor() {
        super("Score", WIDTH - 280);
    }

    public void increaseScore() {
        score += BASIC_SCORE;
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
}
