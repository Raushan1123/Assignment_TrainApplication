package com.cloudbees.trainapplication.dao;

import com.cloudbees.trainapplication.models.Ticket;
import com.cloudbees.trainapplication.models.TrainSection;
import com.cloudbees.trainapplication.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TicketDAO {
    private static Map<User, Ticket> ticketMap = new HashMap<>();
    private static Map<String, TrainSection> trainSections = new HashMap<>();

    public static void addTicket(Ticket ticket) {
        ticketMap.put(ticket.getUser(), ticket);
    }

    public static Ticket getTicket(User user) {
        return ticketMap.get(user);
    }

    public static void addTrainSection(TrainSection section) {
        trainSections.put(section.getSectionName(), section);
    }

    public static void removeTicket(User user) {
        ticketMap.remove(user);
    }

    public static Collection<Ticket> getAllTickets() {
        return ticketMap.values();
    }

    public static TrainSection getTrainSection(String sectionName) {
        return trainSections.get(sectionName);
    }

    public static Map<String, TrainSection> getAllTrainSections() {
        return trainSections;
    }
}
