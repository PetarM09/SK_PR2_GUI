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
    private String professor;
    private String subject;
    private String type;
    private String group;
    private Map<String, String > additionalData;

    public Event(Date date, Room room, Time startTime, Time endTime, DayOfWeek dayOfWeek, String professor, String subject, String type, String group, Map<String, String > additionalData) {
        this.date = date;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.professor = professor;
        this.subject = subject;
        this.type = type;
        this.group = group;
        this.additionalData = additionalData;
    }

    public Event(Date date, Room room, Time startTime, Time endTime, DayOfWeek dayOfWeek, String professor, String subject, String type, String group) {
        this.date = date;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.professor = professor;
        this.subject = subject;
        this.type = type;
        this.group = group;
    }
}
