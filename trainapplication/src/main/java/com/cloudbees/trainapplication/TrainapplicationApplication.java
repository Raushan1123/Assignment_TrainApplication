package com.cloudbees.trainapplication;

import com.cloudbees.trainapplication.dao.TicketDAO;
import com.cloudbees.trainapplication.models.Ticket;
import com.cloudbees.trainapplication.models.TrainSection;
import com.cloudbees.trainapplication.controllers.TrainController;
import com.cloudbees.trainapplication.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

//@SpringBootApplication
public class TrainapplicationApplication {

	public static void main(String[] args) {

		//SpringApplication.run(TrainapplicationApplication.class, args);
		// Create sections
		TrainSection sectionA = new TrainSection("A");
		TrainSection sectionB = new TrainSection("B");

		TicketDAO.addTrainSection(sectionA);
		TicketDAO.addTrainSection(sectionB);

		// Purchase tickets
		TrainController.purchaseTicket("London", "France", "John", "Doe", "john@example.com");
		TrainController.purchaseTicket("London", "France", "Alice", "Smith", "alice@example.com");
		TrainController.purchaseTicket("London", "France", "Any", "Fun", "any@example.com");

		// Get receipt details for a user
		Ticket johnsReceipt = TrainController.getReceiptDetails(1);
		if (johnsReceipt != null) {
			System.out.println("Receipt details for: " + johnsReceipt.getUser().getFirstName());
			System.out.println("From: " + johnsReceipt.getFrom());
			System.out.println("To: " + johnsReceipt.getTo());
			System.out.println("Price Paid: " + johnsReceipt.getPrice());
			System.out.println("Seat: " + johnsReceipt.getSeat());
			System.out.println("User FirstName: " + johnsReceipt.getUser().getFirstName());
			System.out.println("User LastName: " + johnsReceipt.getUser().getLastName());
			System.out.println("User Email: " + johnsReceipt.getUser().getEmail());
		} else {
			System.out.println("No receipt found");
		}
		final String sectionName = "A";

		Map<String, Map<User, String>> usersAndTheirSeatsBySectionType = TrainController.getUsersAndSeatsBySection(sectionName);

		System.out.println("Users and their seats in Section " + sectionName + ":");
		for (Map.Entry<String, Map<User, String>> entry : usersAndTheirSeatsBySectionType.entrySet()) {
			String section = entry.getKey();
			Map<User, String> usersInSeat = entry.getValue();
			System.out.println("Section: " + section);
			for (Map.Entry<User, String> userEntry : usersInSeat.entrySet()) {
				User user  = userEntry.getKey();
				String seat = userEntry.getValue();
				System.out.println("Seat allocated: " + seat + ", Name: " + user.getFirstName() + " " + user.getLastName());
			}
		}

		TrainController.removeUser(3);
		TrainController.modifySeat(2, "B-02");

	}
}

