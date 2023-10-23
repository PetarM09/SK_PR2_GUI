package scheduleManager;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Map;

@Setter
@Getter

public class Event {
    private Date date;
    private Room room;
    private Time startTime;
    private Time endTime;
    private DayOfWeek dayOfWeek;


    Map<String, String> getAdditionalData() {
        return null;
    }
}
