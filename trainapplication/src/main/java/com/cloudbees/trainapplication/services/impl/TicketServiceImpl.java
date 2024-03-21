package com.cloudbees.trainapplication.services.impl;

import com.cloudbees.trainapplication.dao.TicketDAO;
import com.cloudbees.trainapplication.models.Ticket;
import com.cloudbees.trainapplication.models.User;
import com.cloudbees.trainapplication.models.TrainSection;

import java.util.Collection;
import java.util.Map;

public class TicketServiceImpl {
    public static void purchaseTicket(String from, String to, String firstName, String lastName, String email) {
        User user = new User(firstName, lastName, email);
        // Get all train sections
        Map<String, TrainSection> sections = TicketDAO.getAllTrainSections();
        // Allocate seat to the user
        TrainSection section = allocateSeat(sections);
        // Increment and assign seat number for the section
        int lastAssignedSeatNumber = section.getLastAssignedSeatNumber() + 1;
        if (lastAssignedSeatNumber > 99) {
            System.out.println("No available seats in section " + section.getSectionName());
            return;
        }
        String seatNumber = String.format("%02d", lastAssignedSeatNumber);
        Ticket ticket = new Ticket(from, to, user, 20.0, section.getSectionName() + "-" + seatNumber);

        section.addSeatAndUser(ticket.getSeat(), user);
        section.setLastAssignedSeatNumber(lastAssignedSeatNumber);
        ticket.setSection(section);
        TicketDAO.addTicket(ticket);
    }

    private static TrainSection allocateSeat(Map<String, TrainSection> sections) {
        Collection<Ticket> allTickets = TicketDAO.getAllTickets();
        Ticket lastCreatedTicket = null;
        int maxId = Integer.MIN_VALUE; // Initialize maxId to the smallest possible integer value
        for (Ticket ticket : allTickets) {
            int ticketId = ticket.getTicketId(); // Assuming getTicketId returns the ticket ID
            if (ticketId > maxId) {
                maxId = ticketId;
                lastCreatedTicket = ticket;
            }
        }
        if(lastCreatedTicket!=null) {
            if(lastCreatedTicket.getSection().getSectionName().equals("A")){
                return sections.get("B");
            }
        }
        return sections.get("A");
    }

    public static Ticket getTicket(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            return TicketDAO.getTicket(user);
        }
        return null;
    }

    public static void removeUser(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            Ticket ticket = TicketDAO.getTicket(user);
            if (ticket != null) {
                TrainSection section = TicketDAO.getTrainSection(ticket.getSeat().split("-")[0]);
                section.removeUser(ticket.getSeat());
                TicketDAO.removeTicket(user);
                System.out.println("User " + userId + " removed successfully");
                return;
            }
        }
        System.out.println("User " + userId + " not found");
    }

    public static void modifySeat(int userId, String newSeat) {
        User user = getUserById(userId);
        if (user != null) {
            Ticket ticket = TicketDAO.getTicket(user);
            if (ticket != null) {
                TrainSection oldSection = TicketDAO.getTrainSection(ticket.getSeat().split("-")[0]);
                oldSection.removeUser(ticket.getSeat());
                TrainSection newSection = TicketDAO.getTrainSection(newSeat.split("-")[0]);
                if (!newSection.isSeatOccupied(newSeat)) {
                    newSection.addSeatAndUser(newSeat, user);
                    ticket.setSeat(newSeat);
                    System.out.println("User " + userId + " seat modified to " + newSeat);
                }
                else {
                    System.out.println("Seat " + newSeat + " is already occupied");
                }
                return;
            }
        }
        System.out.println("User " + userId + " not found");
    }

    private static User getUserById(int userId) {
        for (Ticket ticket : TicketDAO.getAllTickets()) {
            if (ticket.getUser().getId() == userId) {
                return ticket.getUser();
            }
        }
        return null;
    }
}
