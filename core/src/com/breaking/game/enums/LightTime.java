package com.breaking.game.enums;

import static com.breaking.game.Constants.ACTIVE_LAMP_INTERVAL;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MAX_BROKEN_TIME;

public class LightTime {
    public float minActiveTime = MAX_ACTIVE_LAMP_TIME;
    public float maxActiveTime = minActiveTime + ACTIVE_LAMP_INTERVAL;
    public float activationTime = MAX_BROKEN_TIME;
}
