package com.pocket.rocket.broken.enums;

public enum Achievement {

    Get500OrMoreAtOnce("CgkIvtLorYgJEAIQAQ", 500),
    Get1000OrMoreAtOnce("CgkIvtLorYgJEAIQAg", 1000),
    Get3000OrMoreAtOnce("CgkIvtLorYgJEAIQAw", 3000),
    TotalCount5000("CgkIvtLorYgJEAIQBA", 5000),
    TotalCount10000("CgkIvtLorYgJEAIQBQ", 10000),
    TotalCount20000("CgkIvtLorYgJEAIQCA", 20000),
    Survive30Seconds("CgkIvtLorYgJEAIQCQ", 30),
    Survive45Seconds("CgkIvtLorYgJEAIQCg", 45),
    Lose100Lives("CgkIvtLorYgJEAIQCw", 100),
    Lose500Lives("CgkIvtLorYgJEAIQDA", 500),
    Lose2000Lives("CgkIvtLorYgJEAIQDQ", 2000),
    Catch50Bonuses("CgkIvtLorYgJEAIQDg", 50),
    Catch100Bonuses("CgkIvtLorYgJEAIQDw", 100),
    Catch500Bonuses("CgkIvtLorYgJEAIQEA", 500),
    OpenAllAvailableElementsAtGallery("CgkIvtLorYgJEAIQEQ", 0);

    private String hash;
    private int data;

    Achievement(String hash, int data) {
        this.hash = hash;
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public int getData() {
        return data;
    }
}
