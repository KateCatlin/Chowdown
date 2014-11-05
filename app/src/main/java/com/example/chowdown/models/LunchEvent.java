package com.example.chowdown.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mattlauer on 2014-11-04.
 */
public class LunchEvent {
    String eventID;
    Date startDate;
    Date endDate;
    Date votingDate;
    ArrayList<String> eventAttendees;
    String topRestaurant;

    public LunchEvent(String eventID, Date startDate, Date endDate, Date votingDate) {
        this.eventID = eventID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.votingDate = votingDate;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(Date votingDate) {
        this.votingDate = votingDate;
    }

    public ArrayList<String> getEventAttendees() {
        return eventAttendees;
    }

    public void setEventAttendees(ArrayList<String> eventAttendees) {
        this.eventAttendees = eventAttendees;
    }

    public String getTopRestaurant() {
        return topRestaurant;
    }

    public void setTopRestaurant(String topRestaurant) {
        this.topRestaurant = topRestaurant;
    }
}
