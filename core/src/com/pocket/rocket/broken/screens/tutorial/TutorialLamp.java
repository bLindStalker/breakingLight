package com.pocket.rocket.broken.screens.tutorial;

import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.enums.LampLogic;
import com.pocket.rocket.broken.enums.LightBulbPosition;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import static com.pocket.rocket.broken.enums.LightBulbStatus.BROKEN;
import static com.pocket.rocket.broken.enums.LightBulbStatus.TURN_ON;

public class TutorialLamp extends LightBulb {
    private LightBulbStatus currentStatus;
    private LightBulbStatus previousStatus;
    private Runnable clickAction;

    public TutorialLamp(LightBulbPosition position, int yPosition, LightBulbStatus status) {
        super(position, yPosition);
        setStatus(status);
    }

    public void setStatus(LightBulbStatus status) {
        setImage(com.pocket.rocket.broken.AssetLoader.getLampImage(status));
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
    public boolean justClicked(LampLogic time) {
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
