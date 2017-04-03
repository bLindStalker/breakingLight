package com.breaking.game.enums;

import static com.breaking.game.Constants.LAMPS_SPACE;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.Constants.X_CENTER;

public enum LightBulbPosition {
    LEFT(X_CENTER - LIGHT_WIDTH - 80),
    CENTER(X_CENTER - LIGHT_WIDTH / 2),
    RIGHT(X_CENTER + LAMPS_SPACE + 80);

    private int position;

    LightBulbPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
