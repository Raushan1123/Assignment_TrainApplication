package com.cloudbees.trainapplication.services;

import com.cloudbees.trainapplication.models.Ticket;
import com.cloudbees.trainapplication.models.User;

import java.util.Map;

public interface TicketService {
    // Method to purchase a ticket
    void purchaseTicket(String from, String to, String firstName, String lastName, String email);

    // Method to retrieve a ticket by user ID
    Ticket getTicket(int userId);

    // Method to remove a user from the train by user ID
    void removeUser(int userId);

    // Method to modify a user's seat by user ID
    void modifySeat(int userId, String newSeat);

    // Method to get receipt details for a user by user ID
    Ticket getReceiptDetails(int userId);

    // Method to view all users and seats allocated by the requested section
    Map<String, Map<User, String>> getUsersAndSeatsBySection(String sectionName);
}
