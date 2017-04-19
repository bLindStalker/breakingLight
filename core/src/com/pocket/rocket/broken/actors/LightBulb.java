package com.pocket.rocket.broken.actors;

import com.pocket.rocket.broken.enums.LampLogicData;
import com.pocket.rocket.broken.enums.LightBulbPosition;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.AssetLoader.getLampImage;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.enums.LightBulbStatus.BROKEN;
import static com.pocket.rocket.broken.enums.LightBulbStatus.TURN_OFF;
import static com.pocket.rocket.broken.enums.LightBulbStatus.TURN_ON;

public class LightBulb extends ImageActor {
    private static final LightBulbStatus DEFAULT_STATUS = TURN_OFF;

    private LightBulbStatus status = DEFAULT_STATUS;
    private float activationTime = 0;
    private float second = 0;
    private float turnOffTime = 0;

    public LightBulb(LightBulbPosition position, int yPosition) {
        super(position.getPosition(), yPosition, LAMP_WIDTH, LAMP_HEIGHT, getLampImage(DEFAULT_STATUS));
    }

    public boolean justClicked(LampLogicData time) {
        if (status == TURN_ON) {
            setStatus(BROKEN);
            activationTime = random(time.maxBrokenTime, time.minBrokenTime);
            turnOffTime = getTurnOffTime(time);

            return true;
        }

        return false;
    }

    @Override
    public void act(float delta) {
        if (activationTime > 0 && (status == TURN_ON || status == BROKEN)) {

            if (activationTime <= second) {
                setStatus(TURN_OFF);
                second = 0;
                activationTime = 0;
            } else {
                second += delta;
            }
        } else {
            activationTime = 0;
        }

        if (turnOffTime > 0 && status == TURN_OFF) {
            turnOffTime -= delta;
        }

        super.act(delta);
    }

    private void setStatus(LightBulbStatus status) {
        this.status = status;
        setImage(getLampImage(status));
    }

    public boolean canBeActive() {
        return status == TURN_OFF && turnOffTime <= 0;
    }

    public void activate(LampLogicData time) {
        setStatus(TURN_ON);
        activationTime = random(time.minActiveTime, time.maxActiveTime);
        turnOffTime = getTurnOffTime(time);
    }

    private float getTurnOffTime(LampLogicData time) {
        return random(time.minTurnOffTime, time.maxTurnOffTime);
    }
}