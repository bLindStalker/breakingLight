package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.utils.Align.center;
import static com.pocket.rocket.broken.AssetLoader.getDialog;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.Y_CENTER;

public class DialogBuilder extends Group {
    private final int width, height;
    private float fontScale = 1.1f;
    private float labelHeight = 50 * fontScale;
    private String text;
    private Label label;
    private int yText;

    public DialogBuilder(int xPosition, int yPosition, int width, int height, String text) {
        setBounds(xPosition, yPosition, width, height);
        this.width = width;
        this.height = height;
        this.text = text;
        this.yText = Y_CENTER;
    }

    private Label buildLabel(String text) {
        Label label = new Label(text, getFont());
        label.setBounds(0, yText - labelHeight / 2, getWidth(), labelHeight);
        label.setAlignment(center);
        label.setFontScale(fontScale);
        this.label = label;
        return label;
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

    public DialogBuilder textPosition(int y) {
        yText = y;
        return this;
    }

    public void removeDialog() {
        addAction(Actions.alpha(0f, 0.35f));
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               remove();
                           }
                       }, 0.35f
        );
    }

    private class DialogBg extends ImageActor {
        public DialogBg() {
            super(0, 0, width, height, getDialog());
        }
    }
}
