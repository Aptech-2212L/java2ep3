package Entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Booking {
    private String id;
    private Room room;
    private Customer customer;
    private LocalDateTime check_in_datetime;
    private LocalDateTime check_out_datetime;

    public Booking(String id, Room room, Customer customer, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.check_in_datetime = checkInDateTime;
        this.check_out_datetime = checkOutDateTime;
    }

    public Booking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCheck_in_datetime() {
        return check_in_datetime;
    }

    public void setCheck_in_datetime(LocalDateTime checkInDateTime) {
        this.check_in_datetime = checkInDateTime;
    }

    public LocalDateTime getCheck_out_datetime() {
        return check_out_datetime;
    }

    public void setCheck_out_datetime(LocalDateTime checkOutDateTime) {
        this.check_out_datetime = checkOutDateTime;
    }

    @Override
    public String toString() {
        return "Roomtype{" +
                "id='" + id + '\'' +
                ", room=" + room +
                ", customer=" + customer +
                ", checkInDateTime=" + check_in_datetime +
                ", checkOutDateTime=" + check_out_datetime +
                '}';
    }
}
