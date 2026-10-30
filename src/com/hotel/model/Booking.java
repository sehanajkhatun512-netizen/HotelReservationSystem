package com.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {

    private int bookingId;
    private String customerName;
    private String customerEmail;
    private int roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalAmount;
    private String bookingStatus;

    public Booking(int bookingId, String customerName,
                   String customerEmail, int roomId,
                   LocalDate checkIn, LocalDate checkOut,
                   BigDecimal totalAmount, String bookingStatus) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
}
