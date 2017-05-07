package com.pocket.rocket.broken.enums;

import com.pocket.rocket.broken.Constants;

import static com.pocket.rocket.broken.Constants.LAMP_GROUP_WIDTH;

public enum LightBulbPosition {
    LEFT(0),
    CENTER(LAMP_GROUP_WIDTH / 2 - Constants.LAMP_WIDTH / 2),
    RIGHT(LAMP_GROUP_WIDTH - Constants.LAMP_WIDTH);

    private int position;

    LightBulbPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
