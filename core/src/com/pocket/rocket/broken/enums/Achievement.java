package com.pocket.rocket.broken.enums;

public enum Achievement {

    Get500OrMoreAtOnce("CgkIvtLorYgJEAIQAQ"),
    Get1000OrMoreAtOnce("CgkIvtLorYgJEAIQAg"),
    Get3000OrMoreAtOnce("CgkIvtLorYgJEAIQAw"),
    TotalCount5000("CgkIvtLorYgJEAIQBA"),
    TotalCount10000("CgkIvtLorYgJEAIQBQ"),
    TotalCount20000("CgkIvtLorYgJEAIQCA"),
    Survive30Seconds("CgkIvtLorYgJEAIQCQ"),
    Survive45Seconds("CgkIvtLorYgJEAIQCg"),
    Lose100Lives("CgkIvtLorYgJEAIQCw"),
    Lose500Lives("CgkIvtLorYgJEAIQDA"),
    Lose2000Lives("CgkIvtLorYgJEAIQDQ"),
    Catch50Bonuses("CgkIvtLorYgJEAIQDg"),
    Catch100Bonuses("CgkIvtLorYgJEAIQDw"),
    Catch500Bonuses("CgkIvtLorYgJEAIQEA"),
    OpenAllAvailableElementsAtGallery("CgkIvtLorYgJEAIQEQ");

    private String hash;

    Achievement(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }
}
