package com.pocket.rocket.broken.enums;

public enum GalleryElementsPosition {
    UNDEFINED(-1),
    LAMP(0),
    SUPPER_BONUS(1),
    MEGA_LAMP(2),
    HEARD(3),
    FREEZE_BONUS(4),
    ANGRY_LAMP(5);
    int position;

    GalleryElementsPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
