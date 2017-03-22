package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.breaking.game.AssetLoader;

import static com.breaking.game.Constants.BASIC_SCORE;
import static com.breaking.game.Constants.BASIC_STAR_SCORE;

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
        score += BASIC_STAR_SCORE;
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
