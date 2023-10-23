package eventColection;

import scheduleManager.Criteria;
import scheduleManager.Event;
import scheduleManager.Room;
import scheduleManager.ScheduleManager;

import java.util.List;

public class ScheduleManagerImp extends ScheduleManager {
    Schedule schedule;
    @Override
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

    }

    @Override
    public void addRoom(Room room) {

    }

    @Override
    public void removeRoom(Room room) {

    }

    @Override
    public void createSchedule() {
        this.schedule = new Schedule();
    }

    @Override
    public void sreachSchedule(String criteria) {

    }
}
