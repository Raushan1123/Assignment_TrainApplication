package com.cloudbees.trainapplication.models;

import java.util.HashMap;
import java.util.Map;

public class TrainSection {
    private String sectionName;
    private Map<User, String> seats;
    private int lastAssignedSeatNumber; // New variable to track the last assigned seat number

    public TrainSection(String sectionName) {
        this.sectionName = sectionName;
        this.seats = new HashMap<>();
        this.lastAssignedSeatNumber = 0; // Initialize to 0
    }

    public String getSectionName() {
        return sectionName;
    }

    public Map<User, String> getSeats() {
        return seats;
    }

    public void addSeatAndUser(String seat, User user) {
        seats.put(user, seat);
    }

    public void removeUser(String seat) {
        seats.remove(seat);
    }

    public int getLastAssignedSeatNumber() {
        return lastAssignedSeatNumber;
    }

    public void setLastAssignedSeatNumber(int lastAssignedSeatNumber) {
        this.lastAssignedSeatNumber = lastAssignedSeatNumber;
    }

    public boolean isSeatOccupied(String seat) {
        return seats.containsValue(seat);
    }
}
