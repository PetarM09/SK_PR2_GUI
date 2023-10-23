package scheduleManager;

public abstract class ScheduleManager {
    protected String input;
    public abstract boolean addEvent(String input);
    public abstract void removeEvent(String input);
    public abstract void updateEvent(String input);
    public abstract void addRoom(Room room);
    public abstract void removeRoom(Room room);
    public abstract void createSchedule();
    public abstract void sreachSchedule(String criteria);

    protected Event parser(String input){
        this.input = input;
        String[] arrays = this.input.split(",");
        for(String string : arrays){
            String[] token = string.split(":");
            //criteria
            //event
        }
        return null;
    }




}
