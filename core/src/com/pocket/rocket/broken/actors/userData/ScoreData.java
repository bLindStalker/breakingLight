package com.pocket.rocket.broken.actors.userData;

public class ScoreData {
    private String id;
    private String name;
    private String score;

    public ScoreData(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
