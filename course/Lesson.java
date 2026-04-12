package course;

import java.io.Serializable;

public class Lesson implements Serializable {
	private static final long serialVersionUID = 1L;

	private LessonType type;
	private int roomNumber;
	private Day day;
	private Course course;

	public Lesson() {
	}

	public Lesson(LessonType type, int roomNumber, Day day, Course course) {
		this.type = type;
		this.roomNumber = roomNumber;
		this.day = day;
		this.course = course;
	}

	public LessonType getType() {
		return type;
	}

	public void setType(LessonType type) {
		this.type = type;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Lesson [type=" + type + ", room=" + roomNumber + ", day=" + day + ", course=" + course.getCourseName() + "]";
	}
}
