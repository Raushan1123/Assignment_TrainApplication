package com.cloudbees.trainapplication.controllers;

import com.cloudbees.trainapplication.dao.TicketDAO;
import com.cloudbees.trainapplication.models.Ticket;
import com.cloudbees.trainapplication.models.User;
import com.cloudbees.trainapplication.models.TrainSection;
import com.cloudbees.trainapplication.services.impl.TicketServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class TrainController {
    public static void purchaseTicket(String from, String to, String firstName, String lastName, String email) {
        TicketServiceImpl.purchaseTicket(from, to, firstName, lastName, email);
    }

    public static Ticket getTicket(int userId) {
        return TicketServiceImpl.getTicket(userId);
    }

    public static void removeUser(int userId) {
        TicketServiceImpl.removeUser(userId);
    }

    public static void modifySeat(int userId, String newSeat) {
        TicketServiceImpl.modifySeat(userId, newSeat);
    }

    // API to view details of the receipt for a user
    public static Ticket getReceiptDetails(int userId) {
        return TicketServiceImpl.getTicket(userId);
    }

    // API to view all users and seats allocated by the requested section
    public static Map<String, Map<User, String>> getUsersAndSeatsBySection(String sectionName) {
        Map<String, Map<User, String>> result = new HashMap<>();
        TrainSection section = TicketDAO.getTrainSection(sectionName);
        if (section != null) {
            result.put(sectionName, section.getSeats());
        }
        return result;
    }
}
