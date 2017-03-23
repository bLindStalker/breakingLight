package com.breaking.game.enums;

import static com.breaking.game.Constants.ACTIVE_LAMP_INTERVAL;
import static com.breaking.game.Constants.BROKEN_TIME_INTERVAL;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MAX_BROKEN_TIME;
import static com.breaking.game.Constants.MAX_TURN_OFF_TIME;
import static com.breaking.game.Constants.MIN_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MIN_BROKEN_TIME;
import static com.breaking.game.Constants.MIN_TURN_OFF_TIME;
import static com.breaking.game.Constants.TURN_OFF_TIME_INTERVAL;

public class LampData {
    public float minActiveLampTime = MIN_ACTIVE_LAMP_TIME;
    public float maxActiveLampTime = MAX_ACTIVE_LAMP_TIME;
    public float activeLampInterval = ACTIVE_LAMP_INTERVAL;

    public float minBrokenTime = MIN_BROKEN_TIME;
    public float maxBrokenTime = MAX_BROKEN_TIME;
    public float brokenTimeInterval = BROKEN_TIME_INTERVAL;

    public float minTurnOffTime = MIN_TURN_OFF_TIME;
    public float maxTurnOffTime = MAX_TURN_OFF_TIME;
    public float turnOffTimeInterval = TURN_OFF_TIME_INTERVAL;
}
