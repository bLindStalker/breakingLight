package com.pocket.rocket.broken.enums;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.ACTIVE_INTERVAL;
import static com.pocket.rocket.broken.Constants.ANGRY_INTERVAL;
import static com.pocket.rocket.broken.Constants.MAX_ACTIVE_TIME;
import static com.pocket.rocket.broken.Constants.MAX_ANGRY_TIME;
import static com.pocket.rocket.broken.Constants.MAX_NEUTRAL_TIME;
import static com.pocket.rocket.broken.Constants.MIN_ACTIVE_TIME;
import static com.pocket.rocket.broken.Constants.MIN_ANGRY_TIME;
import static com.pocket.rocket.broken.Constants.MIN_NEUTRAL_TIME;
import static com.pocket.rocket.broken.Constants.NEUTRAL_INTERVAL;

public class LampLogic {
    public float maxAngryTime = MAX_ANGRY_TIME + ANGRY_INTERVAL + 0.7f;
    public float minAngryTime = MIN_ANGRY_TIME + ANGRY_INTERVAL;

    public float maxActiveTime = MAX_ACTIVE_TIME + ACTIVE_INTERVAL;
    public float minActiveTime = MIN_ACTIVE_TIME + ACTIVE_INTERVAL;

    public float maxNeutralTime = MAX_NEUTRAL_TIME + NEUTRAL_INTERVAL;
    public float minNeutralTime = MIN_NEUTRAL_TIME + NEUTRAL_INTERVAL;

    public int maxAngryLamps;

    public LampLogic() {
        maxAngryLamps = random(2, 4);
    }

    public void setActiveLampInterval(float delta) {
        if (delta > 0) {
            maxAngryTime = maxAngryTime - delta > MAX_ANGRY_TIME ? maxAngryTime - delta : MAX_ANGRY_TIME;
            minAngryTime = minAngryTime - delta > MIN_ANGRY_TIME ? minAngryTime - delta : MIN_ANGRY_TIME;
        } else {
            this.minAngryTime = MIN_ANGRY_TIME;
            this.maxAngryTime = MAX_ANGRY_TIME;
        }
    }

    public void setNeutralTimeInterval(float delta) {
        if (delta > 0) {
            maxActiveTime = maxActiveTime - delta > MAX_ACTIVE_TIME ? maxActiveTime - delta : MAX_ACTIVE_TIME;
            minActiveTime = minActiveTime - delta > MIN_ACTIVE_TIME ? minActiveTime - delta : MIN_ACTIVE_TIME;
        } else {
            maxActiveTime = MAX_ACTIVE_TIME;
            minActiveTime = MIN_ACTIVE_TIME;
        }
    }

    public void setTurnOffTimeInterval(float delta) {
        if (delta > 0) {
            maxNeutralTime = maxNeutralTime - delta > MAX_NEUTRAL_TIME ? maxNeutralTime - delta : MAX_NEUTRAL_TIME;
            minNeutralTime = minNeutralTime - delta > MIN_NEUTRAL_TIME ? minNeutralTime - delta : MIN_NEUTRAL_TIME;
        } else {
            maxNeutralTime = MAX_NEUTRAL_TIME;
            minNeutralTime = MIN_NEUTRAL_TIME;
        }
    }
}
