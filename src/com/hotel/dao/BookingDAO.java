package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hotel.model.Booking;

import java.sql.ResultSet;
import java.time.LocalDate;

public class BookingDAO {

    public boolean saveBooking(Booking booking) {

        String sql = "INSERT INTO bookings "
                + "(customer_name, customer_email, room_id, "
                + "check_in, check_out, total_amount, booking_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, booking.getCustomerName());
            ps.setString(2, booking.getCustomerEmail());
            ps.setInt(3, booking.getRoomId());
            ps.setDate(4, java.sql.Date.valueOf(booking.getCheckIn()));
            ps.setDate(5, java.sql.Date.valueOf(booking.getCheckOut()));
            ps.setBigDecimal(6, booking.getTotalAmount());
            ps.setString(7, booking.getBookingStatus());

            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error saving booking!");
            e.printStackTrace();
            return false;
        }
    }
    public boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {

        String sql = "SELECT COUNT(*) FROM bookings "
                + "WHERE room_id = ? "
                + "AND booking_status = 'CONFIRMED' "
                + "AND check_in < ? "
                + "AND check_out > ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, java.sql.Date.valueOf(checkOut));
            ps.setDate(3, java.sql.Date.valueOf(checkIn));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking room availability!");
            e.printStackTrace();
            return false;
        }

        return false;
    }
    
    public void displayAllBookings() {

        String sql = "SELECT * FROM bookings";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== ALL BOOKINGS =====");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("-------------------------");
                System.out.println("Booking ID: " + rs.getInt("booking_id"));
                System.out.println("Customer Name: " + rs.getString("customer_name"));
                System.out.println("Customer Email: " + rs.getString("customer_email"));
                System.out.println("Room ID: " + rs.getInt("room_id"));
                System.out.println("Check-in: " + rs.getDate("check_in"));
                System.out.println("Check-out: " + rs.getDate("check_out"));
                System.out.println("Total Amount: Rs. " + rs.getBigDecimal("total_amount"));
                System.out.println("Status: " + rs.getString("booking_status"));
            }

            if (!found) {
                System.out.println("No bookings found.");
            }

        } catch (SQLException e) {
            System.out.println("Error displaying bookings!");
            e.printStackTrace();
        }
    }
    public boolean cancelBooking(int bookingId) {

        String sql = "UPDATE bookings "
                   + "SET booking_status = 'CANCELLED' "
                   + "WHERE booking_id = ? "
                   + "AND booking_status = 'CONFIRMED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error cancelling booking!");
            e.printStackTrace();
            return false;
        }
    }
    public void displayBookingById(int bookingId) {

        String sql = "SELECT * FROM bookings WHERE booking_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("\n===== BOOKING DETAILS =====");
                    System.out.println("Booking ID: " + rs.getInt("booking_id"));
                    System.out.println("Customer Name: " + rs.getString("customer_name"));
                    System.out.println("Customer Email: " + rs.getString("customer_email"));
                    System.out.println("Room ID: " + rs.getInt("room_id"));
                    System.out.println("Check-in: " + rs.getDate("check_in"));
                    System.out.println("Check-out: " + rs.getDate("check_out"));
                    System.out.println("Total Amount: Rs. " + rs.getBigDecimal("total_amount"));
                    System.out.println("Booking Status: " + rs.getString("booking_status"));
                } else {
                    System.out.println("No booking found with ID: " + bookingId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving booking details!");
            e.printStackTrace();
        }
    }
    public void displayBookingsByEmail(String email) {
        String sql = "SELECT * FROM bookings WHERE customer_email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("\n===== BOOKING DETAILS =====");
                    System.out.println("Booking ID: " + rs.getInt("booking_id"));
                    System.out.println("Customer Name: " + rs.getString("customer_name"));
                    System.out.println("Customer Email: " + rs.getString("customer_email"));
                    System.out.println("Room ID: " + rs.getInt("room_id"));
                    System.out.println("Check-in: " + rs.getDate("check_in"));
                    System.out.println("Check-out: " + rs.getDate("check_out"));
                    System.out.println("Total Amount: " + rs.getBigDecimal("total_amount"));
                    System.out.println("Booking Status: " + rs.getString("booking_status"));
                }

                if (!found) {
                    System.out.println("No booking found for this email.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching booking: " + e.getMessage());
        }
    }
}