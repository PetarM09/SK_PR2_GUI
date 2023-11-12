package eventColection;

import lombok.Getter;
import scheduleManager.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Getter
public class ScheduleManagerImp extends ScheduleManager {

    /*@Override
    public boolean addEvent(String input) {
        Event event = parser(input);
        List<Event> lista = this.schedule.getSchedule().get(event.getDayOfWeek());
        lista.add(event);
        this.schedule.getSchedule().put(event.getDayOfWeek(), lista);
        return true;
    }

    private boolean addEvent(Event event){
        List<Event> lista = this.schedule.getSchedule().get(event.getDayOfWeek());
        lista.add(event);
        this.schedule.getSchedule().put(event.getDayOfWeek(), lista);
        return true;
    }

    @Override
    public void removeEvent(String input) {
        Event event = parser(input);
        List<Event> lista = this.schedule.getSchedule().get(event.getDayOfWeek());
        lista.remove(event);
        this.schedule.getSchedule().put(event.getDayOfWeek(), lista);
    }

    @Override
    public void updateEvent(String input) {
        Event event = parser(input);
        String str = this.input;
        List<Event> lista = this.schedule.getSchedule().get(event.getDayOfWeek());
        if(lista.contains(event)){
            if(this.addEvent(event))
                lista.remove(event);
        }

    }*/

    @Override
    public void initializeSchedule() {
        Schedule schedule = new Schedule();
        this.schedule = schedule;
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
