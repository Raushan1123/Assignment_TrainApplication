# Assignment_TrainApplication
This repository includes submission of machine coding question related to Train Application.

Approach
The Train Ticket Reservation System is designed to facilitate the booking, modification, and cancellation of train tickets. It provides a user-friendly interface for users to interact with the system, allowing them to purchase tickets, view ticket details, modify seat assignments, and remove tickets as necessary.

Tech Stack
Java: Core programming language
Spring Boot: Framework for building robust Java applications
Maven: Dependency management tool
H2 Database: In-memory database for storing ticket and user information
GitHub: Version control and collaboration platform
Endpoints
1. Purchase Ticket
Endpoint: POST /tickets/purchase
Request Payload:{
  "from": "London",
  "to": "France",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
Response: {
  "ticketId": 1,
  "from": "London",
  "to": "France",
  "user": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  "price": 20.0,
  "seat": "A-01"
}
2. View Users and their seat details by Section
Endpoint: GET /usersAndTheirSeatsBySectionType/{sectionName}
Response:{
  Users and their seats in Section A:
  Section: A
  Seat allocated: A-01, Name: John Doe
  Seat allocated: A-02, Name: Any Fun
}
3. Remove User
Endpoint: DELETE /Users/{userId}
Response:{
  "message": "User 3 removed successfully"
}
4. Modify Seat
Endpoint: PUT /tickets/{seatId}/modifyUserSeat
Request Payload:{
  {
  "userId": "2",
  "newSeat": "B-05"
  }
}
Response:{
  "message": "User 2 seat modified to B-02"
}

Setup
Clone the repository: git clone <repository_url>
Navigate to the project directory: cd trainapplication
Build the project: mvn clean install
Run the application: mvn spring-boot:run
Contributors
Raushan kumar
