package com.hotel.main;

import com.hotel.dao.BookingDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.model.Booking;
import com.hotel.model.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class BookingTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== HOTEL RESERVATION SYSTEM =====");

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter customer email: ");
        String email = sc.nextLine();
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(sc.nextLine());

        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(sc.nextLine());

        System.out.println("\nAvailable rooms:");

        RoomDAO roomDAO = new RoomDAO();
        BookingDAO bookingDAO = new BookingDAO();

        List<Room> rooms = roomDAO.getAllRooms();

        boolean availableRoomFound = false;

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

                availableRoomFound = true;
            }
        }

        if (!availableRoomFound) {
            System.out.println("No rooms available for these dates.");
        }

        System.out.print("\nEnter room ID: ");
        int roomId = Integer.parseInt(sc.nextLine());

        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.getRoomId() == roomId
                    && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room ID or room unavailable!");
            sc.close();
            return;
        }

       
        if (!checkOut.isAfter(checkIn)) {
            System.out.println(
                "Check-out date must be after check-in date!"
            );
            sc.close();
            return;
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

        BigDecimal totalAmount =
            selectedRoom.getPricePerNight()
                .multiply(BigDecimal.valueOf(nights));

        Booking booking = new Booking(
            0,
            name,
            email,
            roomId,
            checkIn,
            checkOut,
            totalAmount,
            "CONFIRMED"
        );
        
        if (!bookingDAO.isRoomAvailable(roomId, checkIn, checkOut)) {
            System.out.println(
                "Sorry! This room is already booked for these dates."
            );
            sc.close();
            return;
        }
        boolean result = bookingDAO.saveBooking(booking);

        if (result) {
            System.out.println("\n===== BOOKING CONFIRMED =====");
            System.out.println("Customer: " + name);
            System.out.println("Room: " + selectedRoom.getRoomNumber());
            System.out.println("Nights: " + nights);
            System.out.println("Total Amount: Rs. " + totalAmount);
            System.out.println("Booking saved successfully!");
        } else {
            System.out.println("Booking failed!");
        }

        sc.close();
    }
}