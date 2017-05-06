package com.pocket.rocket.broken.actors.userData;

import static com.pocket.rocket.broken.Constants.BASIC_SCORE;

public class ScoreActor extends LabelData {
    private int score = 0;
    private int bonusCollected = 0;

    public ScoreActor(int xPosition, int yPosition, int width, int height, LabelStyle font) {
        super(String.valueOf(0), xPosition, yPosition, width, height, font);
    }

    public void increaseScore() {
        score += BASIC_SCORE;
    }

    public void increasebonusScore() {
        bonusCollected++;
    }

    public int getScore() {
        return score;
    }

    public int getbonusCollected() {
        return bonusCollected;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(String.valueOf(score));
    }
}
