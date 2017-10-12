package com.pocket.rocket.broken;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.ACTIVE_INTERVAL;
import static com.pocket.rocket.broken.Constants.ANGRY_INTERVAL;
import static com.pocket.rocket.broken.Constants.FREEZE_POWER;
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
    public float maxNeutralTime = MAX_NEUTRAL_TIME + NEUTRAL_INTERVAL + 0.5f;
    public float minNeutralTime = MIN_NEUTRAL_TIME + NEUTRAL_INTERVAL;
    public int maxAngryLamps;
    private float maxDifficult = MAX_ANGRY_TIME;
    private float minDifficult = MIN_ANGRY_TIME;
    private int time = 60;
    private boolean difficultUpdate = false;

    private float freezeMinAngryTime;
    private float freezeMaxActiveTime;

    public LampLogic() {
        maxAngryLamps = random(2, 4);
    }

    public void difficultUpdate(int currentTime) {
        if (time < currentTime) {
            difficultUpdate = true;
            time += 3;
        }
    }

    public void setActiveLampInterval(float delta) {

        if (freezeMinAngryTime != 0 || freezeMaxActiveTime != 0) {
            minAngryTime = freezeMinAngryTime;
            maxAngryTime = freezeMaxActiveTime;
        }

        if (delta > 0) {
            maxAngryTime = maxAngryTime - delta > maxDifficult ? maxAngryTime - delta : maxDifficult;
            minAngryTime = minAngryTime - delta > minDifficult ? minAngryTime - delta : minDifficult;
        } else {
            minAngryTime = minDifficult;
            maxAngryTime = maxDifficult;
        }

        if (difficultUpdate) {
            maxDifficult -= 0.1f;
            minDifficult -= 0.1f;
            difficultUpdate = false;
        }

        freezeMaxActiveTime = 0;
        freezeMinAngryTime = 0;
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

    public void setFreeze() {
        freezeMaxActiveTime = minAngryTime;
        freezeMinAngryTime = maxAngryTime;

        minAngryTime *= FREEZE_POWER;
        maxAngryTime *= FREEZE_POWER;
    }
}
