package course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Day, List<Lesson>> lessonsByDay;

    public Schedule() {
        this.lessonsByDay = new HashMap<>();
        for (Day day : Day.values()) {
            lessonsByDay.put(day, new ArrayList<>());
        }
    }

    public void addLesson(Day day, Lesson lesson) {
        lessonsByDay.get(day).add(lesson);
    }

    public List<Lesson> getLessons(Day day) {
        return lessonsByDay.get(day);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== Schedule ===\n");
        for (Day day : Day.values()) {
            List<Lesson> lessons = lessonsByDay.get(day);
            if (!lessons.isEmpty()) {
                sb.append(day).append(":\n");
                for (Lesson lesson : lessons) {
                    sb.append("  ").append(lesson).append("\n");
                }
            }
        }
        return sb.toString();
    }
}
