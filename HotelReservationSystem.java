import java.util.ArrayList;
import java.util.Scanner;

// Room class to represent individual rooms
class Room {
    private int roomNumber;
    private String roomType; // Example: Single, Double, Suite
    private boolean isAvailable;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - " + (isAvailable ? "Available" : "Booked");
    }
}

// Reservation class to store booking details
class Reservation {
    private String customerName;
    private Room room;

    public Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Reservation for " + customerName + " in " + room.getRoomType() + " room number " + room.getRoomNumber();
    }
}

// Hotel class to manage rooms and reservations
class Hotel {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public Hotel() {
        // Initializing hotel with different types of rooms
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
        rooms.add(new Room(104, "Single"));
        rooms.add(new Room(105, "Double"));
    }

    // Method to search for available rooms by type
    public void searchAvailableRooms(String roomType) {
        boolean found = false;
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms of type: " + roomType);
        }
    }

    // Method to make a reservation
    public void makeReservation(String customerName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(customerName, room);
                reservations.add(reservation);
                System.out.println("Reservation successful for " + customerName + " in room " + roomNumber);
                return;
            }
        }
        System.out.println("Room " + roomNumber + " is not available or doesn't exist.");
    }

    // Method to view all reservations
    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String roomType = scanner.nextLine();
                    hotel.searchAvailableRooms(roomType);
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    hotel.makeReservation(customerName, roomNumber);
                    break;
                case 3:
                    hotel.viewReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
