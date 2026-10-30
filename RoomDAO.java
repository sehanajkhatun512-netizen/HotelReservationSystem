package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotel.model.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void displayAllRooms() {

        String sql = "SELECT * FROM rooms";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("===== HOTEL ROOM LIST =====");

            while (rs.next()) {
                System.out.println("Room ID: " + rs.getInt("room_id"));
                System.out.println("Room Number: " + rs.getString("room_number"));
                System.out.println("Room Type: " + rs.getString("room_type"));
                System.out.println("Price Per Night: " + rs.getBigDecimal("price_per_night"));
                System.out.println("Available: " + (rs.getInt("is_available") == 1 ? "Yes" : "No"));
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving rooms!");
            e.printStackTrace();
        }
    }
    public List<Room> getAllRooms() {

        List<Room> roomList = new ArrayList<>();

        String sql = "SELECT * FROM rooms";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Room room = new Room(
                    rs.getInt("room_id"),
                    rs.getString("room_number"),
                    rs.getString("room_type"),
                    rs.getBigDecimal("price_per_night"),
                    rs.getInt("is_available") == 1
                );

                roomList.add(room);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving room objects!");
            e.printStackTrace();
        }

        return roomList;
    }
}
