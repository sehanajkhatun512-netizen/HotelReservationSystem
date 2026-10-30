package com.hotel.main;

import java.util.Scanner;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.BookingDAO;
import java.time.LocalDate;
import java.util.List;
import com.hotel.model.Room;

public class HotelReservationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RoomDAO roomDAO = new RoomDAO();
        BookingDAO bookingDAO = new BookingDAO();

        int choice;

        do {
        	System.out.println();
        	System.out.println("1. View All Rooms");
        	System.out.println("2. Check Available Rooms");
        	System.out.println("3. Book a Room");
        	System.out.println("4. View All Bookings");
        	System.out.println("5. Cancel Booking");
        	System.out.println("6. View Booking Details");
        	System.out.println("7. Exit");
        	
        	System.out.print("Enter your choice:");

            choice = Integer.parseInt(sc.nextLine());
            System.out.println();

            switch (choice) {

            case 1:
                roomDAO.displayAllRooms();
                break;

            case 2: {
                System.out.print("Enter check-in date (YYYY-MM-DD): ");
                LocalDate checkIn = LocalDate.parse(sc.nextLine());

                System.out.print("Enter check-out date (YYYY-MM-DD): ");
                LocalDate checkOut = LocalDate.parse(sc.nextLine());

                if (!checkOut.isAfter(checkIn)) {
                    System.out.println("Invalid dates! Check-out must be after check-in.");
                    break;
                }

                List<Room> rooms = roomDAO.getAllRooms();
                boolean found = false;

                for (Room room : rooms) {
                    if (room.isAvailable()
                            && bookingDAO.isRoomAvailable(
                                    room.getRoomId(), checkIn, checkOut)) {

                        System.out.println(
                            "Room ID: " + room.getRoomId()
                            + " | Room No: " + room.getRoomNumber()
                            + " | Type: " + room.getRoomType()
                            + " | Price: Rs. " + room.getPricePerNight()
                        );

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No rooms available for these dates.");
                }
                break;
            }

            case 3: {
                System.out.println("\n===== BOOK A ROOM =====");

                System.out.print("Enter customer name: ");
                String name = sc.nextLine();
                
                if (name.trim().isEmpty()) {
                    System.out.println("Customer name cannot be empty!");
                    break;
                }

                System.out.print("Enter customer email: ");
                String email = sc.nextLine();
                
                if (email.trim().isEmpty()) {
                    System.out.println("Customer email cannot be empty!");
                    break;
                }
                
                LocalDate checkIn;
                LocalDate checkOut;

                try {
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    checkIn = LocalDate.parse(sc.nextLine());

                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    checkOut = LocalDate.parse(sc.nextLine());

                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("Invalid date format!");
                    System.out.println("Please enter date in YYYY-MM-DD format.");
                    break;
                }

                if (!checkOut.isAfter(checkIn)) {
                    System.out.println("Invalid dates! Check-out must be after check-in.");
                    break;
                }

                List<Room> rooms = roomDAO.getAllRooms();
                boolean found = false;

                for (Room room : rooms) {
                    if (room.isAvailable()
                            && bookingDAO.isRoomAvailable(
                                    room.getRoomId(), checkIn, checkOut)) {
                        System.out.println(
                            "Room ID: " + room.getRoomId()
                            + " | Room No: " + room.getRoomNumber()
                            + " | Type: " + room.getRoomType()
                            + " | Price: Rs. " + room.getPricePerNight()
                        );
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No rooms available for these dates.");
                    break;
                }

                System.out.print("Enter room ID to book: ");
                int roomId = Integer.parseInt(sc.nextLine());

                Room selectedRoom = null;

                for (Room room : rooms) {
                    if (room.getRoomId() == roomId && room.isAvailable()) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom == null
                        || !bookingDAO.isRoomAvailable(roomId, checkIn, checkOut)) {
                    System.out.println("Invalid room ID or room unavailable for these dates!");
                    break;
                }

                long nights = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);

                java.math.BigDecimal totalAmount =
                    selectedRoom.getPricePerNight()
                        .multiply(java.math.BigDecimal.valueOf(nights));
                System.out.println("\n===== PAYMENT SIMULATION =====");
                System.out.println("Total Amount: Rs. " + totalAmount);

                System.out.println("1. Cash");
                System.out.println("2. Card");
                System.out.println("3. UPI");

                System.out.print("Select payment method: ");
                int paymentChoice = Integer.parseInt(sc.nextLine());

                String paymentMethod;

                if (paymentChoice == 1) {
                    paymentMethod = "Cash";
                } else if (paymentChoice == 2) {
                    paymentMethod = "Card";
                } else if (paymentChoice == 3) {
                    paymentMethod = "UPI";
                } else {
                    System.out.println("Invalid payment method!");
                    break;
                }

                System.out.println("\nProcessing payment...");
                System.out.println("Payment Successful!");
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("Amount Paid: Rs. " + totalAmount);
                System.out.println("Demo only - no real money charged.");

                com.hotel.model.Booking booking = new com.hotel.model.Booking(
                    0, name, email, roomId, checkIn, checkOut, totalAmount, "CONFIRMED"
                );

                if (bookingDAO.saveBooking(booking)) {
                    System.out.println("\n===== BOOKING CONFIRMED =====");
                    System.out.println("Customer: " + name);
                    System.out.println("Room: " + selectedRoom.getRoomNumber());
                    System.out.println("Nights: " + nights);
                    System.out.println("Total Amount: Rs. " + totalAmount);
                    System.out.println("Booking saved successfully!");
                } else {
                    System.out.println("Booking failed!");
                }

                break;
            }

            case 4:
                bookingDAO.displayAllBookings();
                break;

            case 5:
                System.out.print("Enter Booking ID to cancel: ");
                int bookingId = Integer.parseInt(sc.nextLine());

                if (bookingDAO.cancelBooking(bookingId)) {
                    System.out.println("Booking cancelled successfully!");
                } else {
                    System.out.println("Cancellation failed! Check Booking ID or status.");
                }
                break;

            case 6: {
                System.out.println("\n===== VIEW BOOKING DETAILS =====");
                System.out.println("1. Search by Booking ID");
                System.out.println("2. Search by Email");
                System.out.print("Enter your choice: ");

                int searchChoice = sc.nextInt();
                sc.nextLine();

                if (searchChoice == 1) {
                    System.out.print("Enter booking ID: ");
                    int searchBookingId = sc.nextInt();
                    sc.nextLine();

                    bookingDAO.displayBookingById(searchBookingId);

                } else if (searchChoice == 2) {
                    System.out.print("Enter customer email: ");
                    String searchEmail = sc.nextLine().trim();

                    if (searchEmail.isEmpty()) {
                        System.out.println("Email cannot be empty!");
                    } else {
                        bookingDAO.displayBookingsByEmail(searchEmail);
                    }

                } else {
                    System.out.println("Invalid choice!");
                }

                break;
            }

            case 7:
                System.out.println("Thank you for using our Hotel Reservation System!");
                break;

            default:
                System.out.println("Invalid choice! Try again."); 
                break;
            }

        } while (choice != 7);

        sc.close();
    }
}