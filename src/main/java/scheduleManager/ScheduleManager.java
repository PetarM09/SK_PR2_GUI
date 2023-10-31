package scheduleManager;

import jdk.jfr.EventType;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public abstract class ScheduleManager {
    protected List<Room>rooms = new ArrayList<>();
    protected String input;
    public abstract boolean addEvent(String input);
    public abstract void removeEvent(String input);
    public abstract void updateEvent(String input);
    public abstract void addRoom(Room room);
    public abstract void removeRoom(Room room);
    public abstract void initializeSchedule();
    public abstract List<Event> sreachSchedule(String criteria);
    public abstract boolean isRoomOccupied(Room room, Date date, Time startTime, Time endTime);
    public abstract boolean isEventOccupied(Event event);
    public abstract boolean loadScheduleFromFile(String filePath);
    public abstract boolean saveScheduleToFile(String filePath);
    public abstract boolean saveScheduleToCSV(String filePath);

    protected Event parser(String input){
        // Primer formata stringa: "Soba123,2023-10-15,08:00,10:00,nedelja,Matematika,Profesor X,Predavanje,GrupaA,Dodatne informacije1:2, Dodatna informacija 2:5"
        // Primer formata stringa: "Soba123,2023-10-15,08:00,2,nedelja,Matematika,Profesor X,Predavanje,GrupaA,Dodatne informacije1:2, Dodatna informacija 2:5"
        // Primer formata stringa: "Soba123,2023-10-15,08:00,10:00,nedelja,Matematika,Profesor X,Predavanje,GrupaA"
        // Primer formata stringa: "Soba123,2023-10-15,08:00,2,nedelja,Matematika,Profesor X,Predavanje,GrupaA"

        this.input = input;
        String[] parts = input.split(",");
        String roomName = parts[0];
        Room room = null;
        for(Room r : rooms){
            if(r.getName().equals(roomName)){
                 room = r;
                 break;
            }
        }
        if(room == null){
            throw new RuntimeException("Room not found");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(parts[1]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Time startTime = Time.valueOf(parts[2]);
        Time endTime;
        if(parts[3].contains(":")){
            endTime = Time.valueOf(parts[3]);
        }else{
            int duration = Integer.parseInt(parts[3]);
            endTime = new Time(startTime.getTime() + duration * 60 * 60 * 1000);
        }
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(parts[4].toUpperCase());
        String subject = parts[5];
        String professor = parts[6];
        String type = parts[7];
        String group = parts[8];
        if(parts.length == 9){
            return new Event(date, room, startTime, endTime, dayOfWeek, professor, subject, type, group);
        }
        Map<String, String> additionalData = new HashMap<>();
        for(int i = 9; i < parts.length; i++){
            String [] token = parts[i].split(":");
            additionalData.put(token[0], token[1]);
        }
        return new Event(date, room, startTime, endTime, dayOfWeek, professor, subject, type, group, additionalData);
    }
}
