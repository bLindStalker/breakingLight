package com.pocket.rocket.broken.actors.userData;

import static com.pocket.rocket.broken.Constants.BASIC_BONUS_SCORE;
import static com.pocket.rocket.broken.Constants.BASIC_SCORE;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.Text.SCORE;

public class ScoreActor extends LabelData {
    private int score = 0;
    private int bonusCollected = 0;
    private int bonus2Collected = 0;
    private int collectedLamps = 0;
    private boolean animate;

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

    public void increaseBonusScore(boolean isDoubleBonus) {
        if (isDoubleBonus) {
            bonus2Collected++;
            score += BASIC_BONUS_SCORE * 2;
        } else {
            bonusCollected++;
            score += BASIC_BONUS_SCORE;
        }
        animate = true;
    }

    public int getScore() {
        return score;
    }

    public int getBonusCollected() {
        return bonusCollected;
    }

    public int getBonus2Collected() {
        return bonus2Collected;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (animate) {
            animate = false;
            addAction(pulseAnimation(.2f, 0.4f, 1));
        }
        data.setText(String.valueOf(score));
    }

    public int collectedLamps() {
        return collectedLamps;
    }
}
