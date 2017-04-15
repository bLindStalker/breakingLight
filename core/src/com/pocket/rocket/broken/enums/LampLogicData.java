package com.pocket.rocket.broken.enums;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.ACTIVE_LAMP_INTERVAL;
import static com.pocket.rocket.broken.Constants.BROKEN_TIME_INTERVAL;
import static com.pocket.rocket.broken.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.pocket.rocket.broken.Constants.MAX_BROKEN_TIME;
import static com.pocket.rocket.broken.Constants.MAX_TURN_OFF_TIME;
import static com.pocket.rocket.broken.Constants.MIN_ACTIVE_LAMP_TIME;
import static com.pocket.rocket.broken.Constants.MIN_BROKEN_TIME;
import static com.pocket.rocket.broken.Constants.MIN_TURN_OFF_TIME;
import static com.pocket.rocket.broken.Constants.TURN_OFF_TIME_INTERVAL;

public class LampLogicData {
    public float minActiveTime = MIN_ACTIVE_LAMP_TIME + ACTIVE_LAMP_INTERVAL;
    public float maxActiveTime = MAX_ACTIVE_LAMP_TIME + ACTIVE_LAMP_INTERVAL;

    public float maxBrokenTime = MAX_BROKEN_TIME + BROKEN_TIME_INTERVAL;
    public float minBrokenTime = MIN_BROKEN_TIME + BROKEN_TIME_INTERVAL;

    public float maxTurnOffTime = MAX_TURN_OFF_TIME + TURN_OFF_TIME_INTERVAL;
    public float minTurnOffTime = MIN_TURN_OFF_TIME + TURN_OFF_TIME_INTERVAL;

    public int activeLamps;

    public LampLogicData() {
        activeLamps = random(2, 4);
    }

    public void setActiveLampInterval(float delta) {
        if (delta > 0) {
            maxActiveTime = maxActiveTime - delta > MAX_ACTIVE_LAMP_TIME ? maxActiveTime - delta : MAX_ACTIVE_LAMP_TIME;
            minActiveTime = minActiveTime - delta > MIN_ACTIVE_LAMP_TIME ? minActiveTime - delta : MIN_ACTIVE_LAMP_TIME;
        } else {
            this.minActiveTime = MIN_ACTIVE_LAMP_TIME;
            this.maxActiveTime = MAX_ACTIVE_LAMP_TIME;
        }
    }

    public void setBrokenTimeInterval(float delta) {
        if (delta > 0) {
            maxBrokenTime = maxBrokenTime - delta > MAX_BROKEN_TIME ? maxBrokenTime - delta : MAX_BROKEN_TIME;
            minBrokenTime = minBrokenTime - delta > MIN_BROKEN_TIME ? minBrokenTime - delta : MIN_BROKEN_TIME;
        } else {
            maxBrokenTime = MAX_BROKEN_TIME;
            minBrokenTime = MIN_BROKEN_TIME;
        }
    }

    public void setTurnOffTimeInterval(float delta) {
        if (delta > 0) {
            maxTurnOffTime = maxTurnOffTime - delta > MAX_TURN_OFF_TIME ? maxTurnOffTime - delta : MAX_TURN_OFF_TIME;
            minTurnOffTime = minTurnOffTime - delta > MIN_TURN_OFF_TIME ? minTurnOffTime - delta : MIN_TURN_OFF_TIME;
        } else {
            maxTurnOffTime = MAX_TURN_OFF_TIME;
            minTurnOffTime = MIN_TURN_OFF_TIME;
        }
    }
}
