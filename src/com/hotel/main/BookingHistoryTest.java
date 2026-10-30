package com.hotel.main;

import com.hotel.dao.BookingDAO;

public class BookingHistoryTest {

    public static void main(String[] args) {

        BookingDAO bookingDAO = new BookingDAO();

        bookingDAO.displayAllBookings();
    }
}