package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.breaking.game.Constants;

public class ScoreActor extends Actor {
    private static int score = 0;

    public ScoreActor(int xPosition, int yPosition, int width, int height) {
        setBounds(xPosition, yPosition, width, height);
    }

    public static void increaseScore() {
        score += Constants.BASIC_SCORE;
    }

    public static int getScore() {
        return score;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
