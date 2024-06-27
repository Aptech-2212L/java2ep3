package Services;

import Entities.Booking;
import Entities.Customer;
import Entities.Room;
import Entities.Room.RoomType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {
    private Map<String, Room> rooms = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Booking> bookings = new HashMap<>();

    public BookingService() {
        rooms.put("RS001", new Room("RS001", RoomType.Single, 8.0));
        rooms.put("RD001", new Room("RD001", RoomType.Double, 12.0));
        rooms.put("RQ002", new Room("RQ002", RoomType.Queen, 35.0));
        rooms.put("RT001", new Room("RT001", RoomType.Triple, 12.5));
        rooms.put("RQ001", new Room("RQ001", RoomType.Quad, 20.5));

        customers.put("001", new Customer("001", "Mr.Linus Tovaldo", "84125325346457"));
        customers.put("002", new Customer("002", "Mr.Bill", "91124235346467"));
        customers.put("003", new Customer("003", "Mr.Turing", "911423534646"));

        bookings.put("1", new Booking("1", rooms.get("RS001"), customers.get("001"), LocalDateTime.parse("2023-03-15T09:30:15"), LocalDateTime.parse("2023-03-16T12:30:45")));
        bookings.put("2", new Booking("2", rooms.get("RS001"), customers.get("002"), LocalDateTime.parse("2023-06-09T19:30:25"), LocalDateTime.parse("2023-06-10T11:25:15")));
        bookings.put("3", new Booking("3", rooms.get("RD001"), customers.get("002"), LocalDateTime.parse("2023-03-11T10:10:05"), LocalDateTime.parse("2023-03-13T11:05:10")));
        bookings.put("4", new Booking("4", rooms.get("RT001"), customers.get("003"), LocalDateTime.parse("2023-11-11T11:11:15"), LocalDateTime.parse("2023-11-13T11:15:15")));
        bookings.put("5", new Booking("5", rooms.get("RT001"), customers.get("001"), LocalDateTime.parse("2023-10-25T09:20:25"), LocalDateTime.parse("2023-10-26T12:25:30")));
        bookings.put("6", new Booking("6", rooms.get("RQ001"), customers.get("001"), LocalDateTime.parse("2023-08-18T18:25:35"), LocalDateTime.parse("2023-08-19T11:35:20")));
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, Room> rooms) {
        this.rooms = rooms;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers;
    }

    public Map<String, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Map<String, Booking> bookings) {
        this.bookings = bookings;
    }

    public void bookRoom(String roomId, String cus_name, String cus_phone, LocalDateTime checkIn, LocalDateTime checkOut) {
        Room room = rooms.get(roomId);

        if (room == null) {
            System.out.println("Invalid room ID ");
            return ;
        }

        String cus_id = UUID.randomUUID().toString();
        Customer customer = new Customer(cus_id, cus_name, cus_phone);
        customers.put(cus_id,customer);

        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, room, customer, checkIn, checkOut);
        bookings.put(bookingId, booking);
        System.out.println("Room booked successfully with booking: " + booking);
    }

    public void showBooking(String input) {
//        if (bookings.containsKey(input)) {
//            System.out.println("Booking found: " + bookings.get(input));
//            return;
//        }
//        for (Booking booking : bookings.values()) {
//            if (booking.getCustomer().getCus_name().equals(input) || booking.getCustomer().getCus_phone().equals(input)) {
//                System.out.println("Booking found: " + booking);
//            }
//        }

        Iterator<Map.Entry<String, Booking>> it = bookings.entrySet().iterator();
        boolean bookingFound = false;

        while (it.hasNext()) {
            Map.Entry<String, Booking> entry = it.next();
            Booking booking = entry.getValue();
            Customer customer = booking.getCustomer();

            if (customer.getCus_name().equals(input) || customer.getCus_phone().equals(input)) {
                System.out.println("Booking found: " + booking);
                bookingFound = true;
            }
        }

        if (!bookingFound) {
            System.out.println("No booking found for the provided input.");
        }
    }

//    public String showBooking(String input) {
//        String result = bookings.values().stream()
//                .filter(booking -> booking.getCustomer().getCus_name().equals(input) || booking.getCustomer().getCus_phone().equals(input))
//                .map(Booking::toString)
//                .collect(Collectors.collectingAndThen(Collectors.joining("\n"), collected -> {
//                    if (collected.isEmpty()) {
//                        return "No booking found for the provided input.";
//                    } else {
//                        return collected;
//                    }
//                }));
//
//        if (bookings.containsKey(input)) {
//            result = "Booking found: " + bookings.get(input).toString() + "\n" + result;
//        }
//
//        return result;
//    }

    public void calculateRevenueByRoomType() {
        Map<Room.RoomType, Double> revenueByRoomType = bookings.values().stream()
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(booking -> {
                            long hours = java.time.Duration.between(booking.getCheck_in_datetime(), booking.getCheck_out_datetime()).toHours();
                            return hours * booking.getRoom().getPrice_per_hour();
                        })
                ));

        revenueByRoomType.forEach((roomType, revenue) ->
                System.out.println(roomType + ": $" + revenue)
        );
    }

//    public void calculateRevenueByRoomType() {
//        Map<RoomType, Double> revenueByRoomType = new HashMap<>();
//
//        for (Booking booking : bookings.values()) {
//            Room room = booking.getRoom();
//            long hours = java.time.Duration.between(booking.getCheck_in_datetime(), booking.getCheck_out_datetime()).toHours();
//            double revenue = hours * room.getPrice_per_hour();
//
//            revenueByRoomType.merge(room.getRoomType(), revenue, Double::sum);
//        }
//
//        revenueByRoomType.forEach((roomType, revenue) ->
//                System.out.println(roomType + ": $" + revenue)
//        );
//    }

    public void showRoomTypeWithHighestRevenueIn2023() {
    }
}
