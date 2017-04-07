package com.breaking.game.screens.tutorial;

import com.breaking.game.AssetLoader;
import com.breaking.game.actors.LightBulb;
import com.breaking.game.enums.LampLogicData;
import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.enums.LightBulbStatus;

import static com.breaking.game.enums.LightBulbStatus.BROKEN;
import static com.breaking.game.enums.LightBulbStatus.TURN_ON;

public class TutorialLamp extends LightBulb {
    private LightBulbStatus currentStatus;
    private LightBulbStatus previousStatus;
    private Runnable clickAction;

    public TutorialLamp(LightBulbPosition position, int yPosition, LightBulbStatus status) {
        super(position, yPosition);
        setStatus(status);
    }

    public void setStatus(LightBulbStatus status) {
        setImage(AssetLoader.getLampImage(status));
        currentStatus = status;

        if (currentStatus == LightBulbStatus.TURN_ON || currentStatus == LightBulbStatus.BROKEN) {
            previousStatus = LightBulbStatus.TURN_OFF;
        } else {
            previousStatus = LightBulbStatus.TURN_ON;
        }
    }

    public LightBulbStatus getPreviousStatus() {
        return previousStatus;
    }

    public LightBulbStatus getCurrentStatus() {
        return currentStatus;
    }

    public void addClickAction(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    @Override
    public boolean justClicked(LampLogicData time) {
        if (currentStatus == TURN_ON) {
            setStatus(BROKEN);
            if (clickAction != null) {
                clickAction.run();
            }

            return true;
        }

        return false;
    }
}
