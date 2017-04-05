package com.breaking.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.breaking.game.AssetLoader;

import static com.badlogic.gdx.utils.Align.center;
import static com.breaking.game.AssetLoader.getDialog;

public class DialogBuilder extends Group {
    private final int xPosition, yPosition, width, height;
    private float fontScale = 0.7f;
    private String text;
    private Label label;

    public DialogBuilder(int xPosition, int yPosition, int width, int height, String text) {
        setBounds(xPosition, yPosition, width, height);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    private Label buildLabel(String text) {
        Label label = new Label(text, AssetLoader.getFont(Color.BLACK));
        label.setPosition(30, height / 2 - 30);
        label.setWidth(width - 60);
        label.setAlignment(center);
        label.setFontScale(fontScale);
        this.label = label;
        return label;
    }

    public void updateText(String text) {
        label.setText(text);
    }

    public DialogBuilder setFontScale(float fontScale) {
        this.fontScale = fontScale;
        return this;
    }

    public DialogBuilder build() {
        addActor(new DialogBg());
        addActor(buildLabel(text));
        return this;
    }

    private class DialogBg extends ImageActor {
        public DialogBg() {
            super(0, 0, width, height, getDialog());
        }
    }
}
