package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class LabelData extends Label {
    static private final Color tempColor = new Color();

    public LabelData(String text, int xPosition, int yPosition, int width, int height, LabelStyle style) {
        super(text, style);
        setBounds(xPosition, yPosition, width, height);
        setAlignment(Align.center);
        setFontScale(1.3f, 1.2f);
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
