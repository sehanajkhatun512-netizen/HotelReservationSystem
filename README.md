🏨 Hotel Reservation System

✨ A Java & MySQL Based Hotel Booking Application

<p align="center">
  <img src="https://img.shields.io/badge/Java-Programming-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/JDBC-Connectivity-green?style=for-the-badge" alt="JDBC"/>
</p>---

🌟 About the Project

The Hotel Reservation System is a Java console-based application designed to manage hotel rooms and customer reservations using MySQL and JDBC.

It helps users check room availability, book rooms, calculate booking costs, search reservation details, and cancel bookings.

🚀 Key Features

- 🏨 View All Rooms
- 📅 Check Room Availability by Date
- 📝 Book a Room
- 💰 Calculate Total Booking Cost
- 💳 Simulated Payment Selection
- 🔍 Search Booking by ID or Email
- ❌ Cancel Reservations
- 🛡️ Check Overlapping Bookings
- ✅ Validate User Input
- 🗄️ Store Data in MySQL

🛠️ Technologies Used

Technology| Purpose
☕ Java| Application logic and OOP
🗄️ MySQL| Database management
🔌 JDBC| Database connectivity
💻 Eclipse IDE| Development
📊 MySQL Workbench| Database testing

##📂 Project Structure
```text

HotelReservationSystem/
├── src/
│   ├── com.hotel.dao/
│   │   ├── BookingDAO.java
│   │   ├── DBConnection.java
│   │   └── RoomDAO.java
│   │
│   ├── com.hotel.main/
│   │   ├── BookingHistoryTest.java
│   │   ├── BookingTest.java
│   │   ├── CancelBookingTest.java
│   │   ├── DatabaseTest.java
│   │   └── HotelReservationApp.java
│   │
│   ├── com.hotel.model/
│   │   ├── Booking.java
│   │   └── Room.java
│   │
│   └── module-info.java
│
├── database/
│   └── hotel_reservation_db.sql
│
└── README.md
```

Adjust the structure above to match your actual Eclipse project.

⚙️ Database Setup

1. Open MySQL Workbench.
2. Create or select the database "hotel_reservation_db".
3. Create the "rooms" and "bookings" tables using your project’s SQL schema.
4. Configure your database connection in "DBConnection.java".
5. Add MySQL Connector/J to the Java project.

▶️ How to Run

1. Open the project in Eclipse IDE.
2. Ensure MySQL is running.
3. Verify your database connection settings.
4. Run "HotelReservationApp.java".
5. Follow the console menu to manage reservations.

🎓 Learning Outcomes

Through this project, I practised:

- Java OOP concepts: classes, objects, constructors, and methods
- Organizing Java code using packages
- Connecting Java applications to MySQL using JDBC
- Writing SQL queries and using "PreparedStatement"
- Validating dates and calculating booking costs
- Checking overlapping confirmed reservations
- Managing booking status and cancellations
- Handling exceptions and invalid input
- Testing, debugging, and troubleshooting a database-driven application

🔮 Future Enhancements

- 🔐 Stronger booking-conflict prevention for concurrent requests
- 🖥️ A graphical user interface or web interface
- 📈 Booking reports and analytics
- 🧪 Automated testing
- 🔒 Improved database security

These are planned enhancements, not features claimed as completed.

👩‍💻 Author

Sahnaj Khatun

Aspiring Data Analyst | Software Developer | Web Developer

---

⭐ Thank you for visiting my project!