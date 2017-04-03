package com.breaking.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.breaking.game.Constants.BASIC_SCORE;

public class ScoreActor extends Label {
    static private final Color tempColor = new Color();
    private int score = 0;
    private int starCollected = 0;

    public ScoreActor(int xPosition, int yPosition, int width, int height, LabelStyle font) {
        super(String.valueOf(0), font);
        setBounds(xPosition, yPosition, width, height);
        setAlignment(Align.center);
        setFontScale(1.3f, 1.2f);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();
        Color color = tempColor.set(getColor());
        if (getStyle().background != null) {
            batch.setColor(color.r, color.g, color.b, 0.7f);
            getStyle().background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (getStyle().fontColor != null) color.mul(getStyle().fontColor);
        getBitmapFontCache().tint(color);
        getBitmapFontCache().setPosition(getX(), getY());
        getBitmapFontCache().draw(batch);
    }
}
