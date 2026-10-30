package com.hotel.model;

import java.math.BigDecimal;

public class Room {

    private int roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricePerNight;
    private boolean available;

    public Room(int roomId, String roomNumber,
                String roomType, BigDecimal pricePerNight,
                boolean available) {

        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void displayRoomInfo() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price Per Night: " + pricePerNight);
        System.out.println("Available: " + (available ? "Yes" : "No"));
    }
}