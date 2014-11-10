package com.example.chowdown.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by mattlauer on 2014-11-04.
 */
public class LunchEvent implements Parcelable {
    public String eventID;
    public String description;
    public DateTime startDate;
    public DateTime endDate;
    public DateTime votingDate;
    public ArrayList<String> eventAttendees;
    public String topRestaurant;


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eventID);
        dest.writeString(this.description);
        dest.writeSerializable(this.startDate);
        dest.writeSerializable(this.endDate);
        dest.writeSerializable(this.votingDate);
        dest.writeSerializable(this.eventAttendees);
        dest.writeString(this.topRestaurant);
    }

    private LunchEvent(Parcel in) {
        this.eventID = in.readString();
        this.description = in.readString();
        this.startDate = (DateTime) in.readSerializable();
        this.endDate = (DateTime) in.readSerializable();
        this.votingDate = (DateTime) in.readSerializable();
        this.eventAttendees = (ArrayList<String>) in.readSerializable();
        this.topRestaurant = in.readString();
    }

    public static final Parcelable.Creator<LunchEvent> CREATOR = new Parcelable.Creator<LunchEvent>() {
        public LunchEvent createFromParcel(Parcel source) {
            return new LunchEvent(source);
        }

        public LunchEvent[] newArray(int size) {
            return new LunchEvent[size];
        }
    };
}
