package com.example.chowdown.models;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mattlauer on 2014-11-04.
 */
public class LunchEvent {
    String eventID;
    String description;
    DateTime startDate;
    DateTime endDate;
    DateTime votingDate;
    ArrayList<String> eventAttendees;
    String topRestaurant;


    public LunchEvent(String eventID, String description, DateTime startDate, DateTime endDate, DateTime votingDate,
                      ArrayList<String> eventAttendees, String topRestaurant) {
        this.eventID = eventID;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.votingDate = votingDate;
        this.eventAttendees = eventAttendees;
        this.topRestaurant = topRestaurant;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getDescription() { return description; }

    public void setDescription(String title) { this.description = description; }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(DateTime votingDate) {
        this.votingDate = votingDate;
    }

    public ArrayList<String> getEventAttendees() {
        return eventAttendees;
    }

    public void setEventAttendees(ArrayList<String> eventAttendees) {
        this.eventAttendees = eventAttendees;
    }

    public String getEventStarter() {return eventAttendees.get(0); }

    public String getTopRestaurant() {
        return topRestaurant;
    }

    public void setTopRestaurant(String topRestaurant) {
        this.topRestaurant = topRestaurant;
    }
}
