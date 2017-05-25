package com.pocket.rocket.broken.enums;

public enum Achievement {

    Get1000OrMoreAtOnce("CgkI-L-JwqYVEAIQAw", 1000),
    Get2000OrMoreAtOnce("CgkI-L-JwqYVEAIQBA", 2000),
    Get3000OrMoreAtOnce("CgkI-L-JwqYVEAIQBQ", 3000),
    TotalCount10000("CgkI-L-JwqYVEAIQBg", 10000),
    TotalCount20000("CgkI-L-JwqYVEAIQBw", 20000),
    TotalCount40000("CgkI-L-JwqYVEAIQCA", 40000),
    Survive30Seconds("CgkI-L-JwqYVEAIQCQ", 30),
    Survive60Seconds("CgkI-L-JwqYVEAIQCg", 60),
    Lose250Lives("CgkI-L-JwqYVEAIQCw", 250),
    Lose500Lives("CgkI-L-JwqYVEAIQDA", 500),
    Lose1000Lives("CgkI-L-JwqYVEAIQDQ", 1000),
    Catch100Bonuses("CgkI-L-JwqYVEAIQDg", 100),
    Catch500Bonuses("CgkI-L-JwqYVEAIQDw", 500),
    Catch1000Bonuses("CgkI-L-JwqYVEAIQEA", 1000),
    OpenAllAvailableElementsAtGallery("CgkI-L-JwqYVEAIQEQ", 0);

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
