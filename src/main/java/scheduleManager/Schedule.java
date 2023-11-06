package scheduleManager;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class Schedule {
    private Map<DayOfWeek, List<Event>> schedule;


    public Map<DayOfWeek, List<Event>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, List<Event>> schedule) {
        this.schedule = schedule;
    }
}
