package com.example.chowdown.models;

/**
 * Created by Ken on 11/10/14.
 */
public class Vote {

    private String lunchId;
    private String userId;
    private String firstChoice;
    private String secondChoice;
    private String thirdChoice;

    public Vote(String lunchId, String firstChoice, String secondChoice, String thirdChoice) {
        this.lunchId = lunchId;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
    }

    public String getLunchId() {
        return lunchId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public String toString() {
        return lunchId + firstChoice + secondChoice + thirdChoice;
    }
}
