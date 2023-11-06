package weeklyColection;

import scheduleManager.Event;
import scheduleManager.Room;
import scheduleManager.ScheduleManager;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScheduleManagerImp extends ScheduleManager {
    @Override
    public boolean addEvent(String input) {
        return false;
    }

    @Override
    public void removeEvent(String input) {

    }

    @Override
    public void updateEvent(String input) {

    }

    @Override
    public void initializeSchedule() {

    }

    @Override
    public List<Event> sreachSchedule(String criteria) {
        return null;
    }


    @Override
    public boolean isRoomOccupied(Room room, Date date, Time startTime, Time endTime) {
        return false;
    }

    @Override
    public boolean isEventOccupied(Event event) {
        return false;
    }


    @Override
    public boolean saveScheduleToFile(String filePath) {
        return false;
    }

    @Override
    public boolean saveScheduleToCSV(String filePath) {
        return false;
    }
}
