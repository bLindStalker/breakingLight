package com.breaking.game.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.breaking.game.AssetLoader;

import static com.breaking.game.Constants.BASIC_SCORE;

public class ScoreActor extends Label {
    private int score = 0;
    private int starCollected = 0;

    public ScoreActor(int xPosition, int yPosition, int width, int height) {
        super(String.valueOf(0), AssetLoader.getFont());
        setBounds(xPosition, yPosition, width, height);
        setAlignment(Align.center);
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