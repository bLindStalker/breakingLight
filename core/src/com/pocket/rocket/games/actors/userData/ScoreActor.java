package com.pocket.rocket.games.actors.userData;

import static com.pocket.rocket.games.Constants.BASIC_SCORE;

public class ScoreActor extends LabelData {
    private int score = 0;
    private int starCollected = 0;

    public ScoreActor(int xPosition, int yPosition, int width, int height, LabelStyle font) {
        super(String.valueOf(0), xPosition, yPosition, width, height, font);
    }

    public void increaseScore() {
        score += BASIC_SCORE;
    }

    public void increaseStarScore() {
        starCollected++;
    }

    public int getScore() {
        return score;
    }

    public int getStarCollected() {
        return starCollected;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(String.valueOf(score));
    }
}
