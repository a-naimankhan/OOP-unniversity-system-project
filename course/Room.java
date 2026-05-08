package course;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String number;
    private int capacity;
    private String type; // e.g., "Lecture Hall", "Lab", "Classroom"

    public Room(String number, int capacity, String type) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;
    }

    public String getNumber() { return number; }
    public int getCapacity() { return capacity; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return "Room " + number + " (" + type + ", capacity: " + capacity + ")";
    }
}
