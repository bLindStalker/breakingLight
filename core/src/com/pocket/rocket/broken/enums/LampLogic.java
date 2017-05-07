package com.pocket.rocket.broken.enums;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.ACTIVE_LAMP_INTERVAL;
import static com.pocket.rocket.broken.Constants.neutral_TIME_INTERVAL;
import static com.pocket.rocket.broken.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.pocket.rocket.broken.Constants.MAX_NEUTRAL_TIME;
import static com.pocket.rocket.broken.Constants.MAX_TURN_OFF_TIME;
import static com.pocket.rocket.broken.Constants.MIN_ACTIVE_LAMP_TIME;
import static com.pocket.rocket.broken.Constants.MIN_NEUTRAL_TIME;
import static com.pocket.rocket.broken.Constants.MIN_TURN_OFF_TIME;
import static com.pocket.rocket.broken.Constants.TURN_OFF_TIME_INTERVAL;

public class LampLogic {
    public float minActiveTime = MIN_ACTIVE_LAMP_TIME + ACTIVE_LAMP_INTERVAL;
    public float maxActiveTime = MAX_ACTIVE_LAMP_TIME + ACTIVE_LAMP_INTERVAL + 0.7f;

    public float maxNeutralTime = MAX_NEUTRAL_TIME + neutral_TIME_INTERVAL;
    public float minNeutralTime = MIN_NEUTRAL_TIME + neutral_TIME_INTERVAL;

    public float maxTurnOffTime = MAX_TURN_OFF_TIME + TURN_OFF_TIME_INTERVAL;
    public float minTurnOffTime = MIN_TURN_OFF_TIME + TURN_OFF_TIME_INTERVAL;

    public int activeLamps;

    public LampLogic() {
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

    public void setNeutralTimeInterval(float delta) {
        if (delta > 0) {
            maxNeutralTime = maxNeutralTime - delta > MAX_NEUTRAL_TIME ? maxNeutralTime - delta : MAX_NEUTRAL_TIME;
            minNeutralTime = minNeutralTime - delta > MIN_NEUTRAL_TIME ? minNeutralTime - delta : MIN_NEUTRAL_TIME;
        } else {
            maxNeutralTime = MAX_NEUTRAL_TIME;
            minNeutralTime = MIN_NEUTRAL_TIME;
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
