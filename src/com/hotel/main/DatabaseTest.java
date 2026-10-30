package com.hotel.main;

import com.hotel.dao.RoomDAO;
import com.hotel.model.Room;
import java.util.List;

public class DatabaseTest {

    public static void main(String[] args) {

        RoomDAO roomDAO = new RoomDAO();

        List<Room> rooms = roomDAO.getAllRooms();

        System.out.println("===== HOTEL ROOM LIST =====");

        for (Room room : rooms) {
            room.displayRoomInfo();
            System.out.println("---------------------------");
        }
    }
}
