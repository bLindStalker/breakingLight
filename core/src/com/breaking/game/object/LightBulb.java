package com.breaking.game.object;

import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.enums.LightBulbStatus;

import static com.breaking.game.AssetLoader.getLampImage;
import static com.breaking.game.Constants.BROKEN_TIME;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.TURN_OFF_TIME;
import static com.breaking.game.enums.LightBulbStatus.BROKEN;
import static com.breaking.game.enums.LightBulbStatus.TURN_OFF;
import static com.breaking.game.enums.LightBulbStatus.TURN_ON;

public class LightBulb extends ImageActor {
    private static final LightBulbStatus DEFAULT_STATUS = TURN_OFF;

    private LightBulbStatus status = DEFAULT_STATUS;
    private float activationTime = 0;
    private float second = 0;
    private float turnOffTime = 0;

    public LightBulb(LightBulbPosition position, int yPosition) {
        super(position.getPosition(), yPosition, LIGHT_WIDTH, LIGHT_HEIGHT, getLampImage(DEFAULT_STATUS));
    }

    public boolean justClicked() {
        if (status == TURN_ON) {
            setStatus(BROKEN);
            activationTime = BROKEN_TIME;
            turnOffTime = TURN_OFF_TIME;

    /*        Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    addAction(Actions.color(new Color(1f,1f,1f, 0f), 0.5f));
                }
            }, 1);*/


            return true;
        }
        return false;
    }

    @Override
    public void act(float delta) {
        if (activationTime > 0 && (status == TURN_ON || status == BROKEN)) {
            if (activationTime <= second) {
                setStatus(TURN_OFF);
                //addAction(Actions.color(new Color(1f,1f,1f, 1f), 0.1f));
                second = 0;
                activationTime = 0;
            } else {
                second += delta;
            }
        } else {
            activationTime = 0;
        }

        if (turnOffTime > 0) {
            turnOffTime -= delta;
        }

        super.act(delta);
    }

    private void setStatus(LightBulbStatus status) {
        this.status = status;
        setImage(getLampImage(status));
    }

    public boolean isNonActive() {
        return status == TURN_OFF && turnOffTime <= 0;
    }

    public void activate() {
        setStatus(TURN_ON);
        activationTime = MAX_ACTIVE_LAMP_TIME;
    }
}