package com.breaking.game.enums;

import com.breaking.game.Preference;
import com.google.gson.Gson;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MAX_BROKEN_TIME;
import static com.breaking.game.Constants.MAX_TURN_OFF_TIME;
import static com.breaking.game.Constants.MIN_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MIN_BROKEN_TIME;
import static com.breaking.game.Constants.MIN_TURN_OFF_TIME;

public class LampLogicData {
   /* public float minActiveTime = MIN_ACTIVE_LAMP_TIME + ACTIVE_LAMP_INTERVAL;
    public float maxActiveTime = MAX_ACTIVE_LAMP_TIME + ACTIVE_LAMP_INTERVAL;

    public float maxBrokenTime = MAX_BROKEN_TIME + BROKEN_TIME_INTERVAL;
    public float minBrokenTime = MIN_BROKEN_TIME + BROKEN_TIME_INTERVAL;

    public float maxTurnOffTime = MAX_TURN_OFF_TIME + TURN_OFF_TIME_INTERVAL;
    public float minTurnOffTime = MIN_TURN_OFF_TIME + TURN_OFF_TIME_INTERVAL;

    public int activeLamps = MAX_ACTIVE_LAMPS;*/

    public float minActiveTime;
    public float maxActiveTime;

    public float maxBrokenTime;
    public float minBrokenTime;

    public float maxTurnOffTime;
    public float minTurnOffTime;

    public int activeLamps;

    public LampLogicData() {
        LampData lampData = new Gson().fromJson(Preference.getConfigs(), LampData.class);

        minActiveTime = lampData.minActiveLampTime + lampData.activeLampInterval;
        maxActiveTime = lampData.maxActiveLampTime + lampData.activeLampInterval;
        maxBrokenTime = lampData.maxBrokenTime + lampData.brokenTimeInterval;
        minBrokenTime = lampData.minBrokenTime + lampData.brokenTimeInterval;
        maxTurnOffTime = lampData.maxTurnOffTime + lampData.turnOffTimeInterval;
        minTurnOffTime = lampData.minTurnOffTime + lampData.turnOffTimeInterval;
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
