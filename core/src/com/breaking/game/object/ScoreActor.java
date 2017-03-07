package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.breaking.game.AssetLoader;
import com.breaking.game.Constants;

public class ScoreActor extends Label {
    private int score = 0;

    public ScoreActor(int xPosition, int yPosition, int width, int height) {
        super(String.valueOf(0), AssetLoader.getFont());
        setBounds(xPosition, yPosition, width, height);
        setAlignment(Align.center);
    }

    public void increaseScore() {
        score += Constants.BASIC_SCORE;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(String.valueOf(score));
    }
}
