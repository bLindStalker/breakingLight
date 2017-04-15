package com.pocket.rocket.broken.enums;

import com.pocket.rocket.broken.Constants;

public enum LightBulbPosition {
    LEFT(Constants.X_CENTER - Constants.LAMP_WIDTH - 80),
    CENTER(Constants.X_CENTER - Constants.LAMP_WIDTH / 2),
    RIGHT(Constants.X_CENTER + 80);

    private int position;

    LightBulbPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
