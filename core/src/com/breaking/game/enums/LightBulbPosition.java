package com.breaking.game.enums;

import static com.breaking.game.Constants.LAMP_WIDTH;
import static com.breaking.game.Constants.X_CENTER;

public enum LightBulbPosition {
    LEFT(X_CENTER - LAMP_WIDTH - 80),
    CENTER(X_CENTER - LAMP_WIDTH / 2),
    RIGHT(X_CENTER + 80);

    private int position;

    LightBulbPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
