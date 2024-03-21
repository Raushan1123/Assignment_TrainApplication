package com.cloudbees.trainapplication.models;

public class Ticket {

    private static int lastAssignedId = 0;

    private int ticketId;
    private String from;
    private String to;
    private User user;
    private double price;
    private String seat;
    private TrainSection section;

    public Ticket(String from, String to, User user, double price, String seat) {
        this.ticketId = ++lastAssignedId;
        this.from = from;
        this.to = to;
        this.user = user;
        this.price = price;
        this.seat = seat;
        this.section = null;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public TrainSection getSection() {
        return section;
    }

    public void setSection(TrainSection section) {
        this.section = section;
    }
}
