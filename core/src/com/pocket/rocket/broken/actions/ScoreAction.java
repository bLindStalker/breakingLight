package com.pocket.rocket.broken.actions;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.IntAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static java.lang.String.valueOf;

public class ScoreAction extends IntAction {
    private static final float DURATION = 1.3f;
    private Label label;

    public static ScoreAction scoreAction(int score, Label label) {
        ScoreAction action = Actions.action(ScoreAction.class);
        action.setStart(0);
        action.setEnd(score);
        action.setDuration(DURATION);
        action.label = label;
        return action;
    }

    @Override
    protected void update(float percent) {
        super.update(percent);
        if (label != null) {
            label.setText(valueOf(getValue()));
        }
    }
}
