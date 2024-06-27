import Entities.Room;
import Services.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println("Hello Java");
        BookingService bookingService = new BookingService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Book a room");
            System.out.println("2. Searching booking");
            System.out.println("3. Sumerize revenue by rate per hours ");
            System.out.println("4. Show room type with highest revenue in 2023");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("We have 6 types of room: ");
                    bookingService.getRooms().values().stream().map(Room::toString).forEach(roomType -> System.out.println(roomType + " "));
                    System.out.print("Enter room ID for type of room you want: ");
                    String roomId = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String cus_name = scanner.nextLine();
                    System.out.print("Enter customer phone: ");
                    String cus_phone = scanner.nextLine();
                    System.out.print("Enter check-in date and time (yyyy-MM-dd HH:mm:ss): ");
                    LocalDateTime checkIn = LocalDateTime.parse(scanner.nextLine(), DATE_TIME_FORMATTER);
                    System.out.print("Enter check-out date and time (yyyy-MM-dd HH:mm:ss): ");
                    LocalDateTime checkOut = LocalDateTime.parse(scanner.nextLine(), DATE_TIME_FORMATTER);
                    bookingService.bookRoom(roomId, cus_name, cus_phone, checkIn, checkOut);
                    break;
                case 2:
                    System.out.print("Enter your ID or phone number or booking number: ");
                    String inputData = scanner.nextLine();
                    bookingService.showBooking(inputData);
                    break;
                case 3:
                    System.out.print("Sumerize revenue by rate per hours ");
                    bookingService.calculateRevenueByRoomType();
                    break;
                case 4:
                    bookingService.showRoomTypeWithHighestRevenueIn2023();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}