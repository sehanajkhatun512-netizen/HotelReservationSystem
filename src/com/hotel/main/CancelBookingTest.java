package com.hotel.main;

import com.hotel.dao.BookingDAO;
import java.util.Scanner;

public class CancelBookingTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingDAO bookingDAO = new BookingDAO();

        System.out.print("Enter Booking ID to cancel: ");
        int bookingId = Integer.parseInt(sc.nextLine());

        boolean result = bookingDAO.cancelBooking(bookingId);

        if (result) {
            System.out.println("Booking cancelled successfully!");
        } else {
            System.out.println(
                "Cancellation failed! Check Booking ID or status."
            );
        }

        sc.close();
    }
}
