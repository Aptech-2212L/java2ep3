package Entities;

public class Room {
    private String id;
    private RoomType roomType;
    private Double price_per_hour;
    public enum RoomType {
        Single, Double, Queen, Quad, Triple
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Double getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(Double price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public Room() {
    }

    public Room(String id, RoomType roomType, Double price_per_hour) {
        this.id = id;
        this.roomType = roomType;
        this.price_per_hour = price_per_hour;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", price_per_hour=" + price_per_hour +
                '}';
    }
}
