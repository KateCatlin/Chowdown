package com.example.chowdown.models;

/**
 * Created by Ken on 11/10/14.
 */
public class Vote {

    private String lunchId;
    private String userId;
    private String restaurantChoice;
    private int rank;

    public Vote(String lunchId, String restaurantChoice, int rank) {
        this.lunchId = lunchId;
        this.restaurantChoice = restaurantChoice;
        this.rank = rank;
    }

    public String getLunchId() {
        return lunchId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantChoice() {
        return restaurantChoice;
    }

    public int getRank() { return rank; }

//    public String toString() {
//        return lunchId + firstChoice + secondChoice + thirdChoice;
//    }
}
