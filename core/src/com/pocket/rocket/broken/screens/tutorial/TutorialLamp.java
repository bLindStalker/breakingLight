package com.pocket.rocket.broken.screens.tutorial;

import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.enums.LampLogic;
import com.pocket.rocket.broken.enums.LightBulbPosition;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import static com.pocket.rocket.broken.enums.LightBulbStatus.ACTIVE;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ANGRY;

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

        if (currentStatus == LightBulbStatus.ANGRY || currentStatus == LightBulbStatus.ACTIVE) {
            previousStatus = LightBulbStatus.NEUTRAL;
        } else {
            previousStatus = LightBulbStatus.ANGRY;
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
        if (currentStatus == ANGRY) {
            setStatus(ACTIVE);
            if (clickAction != null) {
                clickAction.run();
            }

            return true;
        }

        return false;
    }
}
