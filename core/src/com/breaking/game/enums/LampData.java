package com.breaking.game.enums;

import static com.breaking.game.Constants.MAX_ACTIVE_LAMPS;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MAX_BROKEN_TIME;
import static com.breaking.game.Constants.MAX_TURN_OFF_TIME;
import static com.breaking.game.Constants.MIN_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MIN_BROKEN_TIME;
import static com.breaking.game.Constants.MIN_TURN_OFF_TIME;

public class LampData {
    public float minActiveTime = MIN_ACTIVE_LAMP_TIME;
    public float maxActiveTime = MAX_ACTIVE_LAMP_TIME;
    public float maxBrokenTime = MAX_BROKEN_TIME;
    public float minBrokenTime = MIN_BROKEN_TIME;
    public float maxTurnOffTime = MAX_TURN_OFF_TIME;
    public float minTurnOffTime = MIN_TURN_OFF_TIME;
    public float activeLamps = MAX_ACTIVE_LAMPS;
}
