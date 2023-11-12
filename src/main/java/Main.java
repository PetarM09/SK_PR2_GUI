import eventColection.ScheduleManagerImp;
import scheduleManager.ScheduleManager;

public class Main {
    public static void main(String[] args) {
        ScheduleManagerImp scheduleManagerImp = new ScheduleManagerImp();
        scheduleManagerImp.initializeSchedule();
        scheduleManagerImp.loadScheduleFromFile();
        /*for(var x : scheduleManagerImp.getSchedule().getSchedule().entrySet()){
            System.out.println(x.getKey());
            for(var y : x.getValue()){
                System.out.println(y);
            }
        }*/
        scheduleManagerImp.addEvent("Soba123,2023-10-10,08:00,10:00, Dodatne informacije1:2, Dodatna informacija 2:5");
        for(var x : scheduleManagerImp.getSchedule().getSchedule().entrySet()){
            for(var y : x.getValue()){
                System.out.println(y);
            }
        }
        System.out.println("Nakon dodavanja\n\n\n\n\n\n\n");

        scheduleManagerImp.removeEvent("Soba123,2023-10-10,08:00,10:00, Dodatne informacije1:2, Dodatna informacija 2:5");
        for(var x : scheduleManagerImp.getSchedule().getSchedule().entrySet()){
            for(var y : x.getValue()){
                System.out.println(y);
            }
        }
    }
}