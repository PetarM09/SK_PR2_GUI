package eventColection;

import lombok.Getter;
import lombok.Setter;
import scheduleManager.Event;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
@Getter
@Setter

public class Schedule {
    private Map<DayOfWeek, List<Event>> schedule;

}
