package scheduleManager;

import jdk.jfr.EventType;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
@Getter
@Setter

public abstract class ScheduleManager {
    protected List<Room>rooms = new ArrayList<>();
    protected String input;
    private Schedule schedule;

    public abstract void initializeSchedule();
    public abstract List<Event> sreachSchedule(String criteria);
    public abstract boolean isRoomOccupied(Room room, Date date, Time startTime, Time endTime);
    public abstract boolean isEventOccupied(Event event);
    public abstract boolean saveScheduleToFile(String filePath);
    public abstract boolean saveScheduleToCSV(String filePath);

    protected Event parser(String input){
        // Primer formata stringa: "Soba123,2023-10-15,08:00,10:00,Matematika,Profesor X,Predavanje,GrupaA,Dodatne informacije1:2, Dodatna informacija 2:5"
        // Primer formata stringa: "Soba123,2023-10-15,08:00,2,Matematika,Profesor X,Predavanje,GrupaA,Dodatne informacije1:2, Dodatna informacija 2:5"
        // Primer formata stringa: "Soba123,2023-10-15,08:00,10:00,Matematika,Profesor X,Predavanje,GrupaA"
        // Primer formata stringa: "Soba123,2023-10-15,08:00,2,Matematika,Profesor X,Predavanje,GrupaA"

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
            room = new Room(); //TODO srediti praznu sobu???
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(parts[1]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        LocalDate localDate = LocalDate.parse(parts[1]);

        Time startTime = Time.valueOf(parts[2].concat(":00"));
        Time endTime;
        if(parts[3].contains(":")){
            endTime = Time.valueOf(parts[3].concat(":00"));
        }else{
            double duration = Double.parseDouble(parts[3]);
            endTime = new Time((long) (startTime.getTime() + duration * 60 * 60 * 1000));
        }

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String subject = parts[4];
        String professor = parts[5];
        String type = parts[6];
        String group = parts[7];
        if(parts.length == 8){
            return new Event(date, room, startTime, endTime, dayOfWeek, professor, subject, type, group);
        }
        Map<String, String> additionalData = new HashMap<>();
        for(int i = 8; i < parts.length; i++){
            String [] token = parts[i].split(":");
            additionalData.put(token[0], token[1]);
        }
        return new Event(date, room, startTime, endTime, dayOfWeek, professor, subject, type, group, additionalData);
    }

    protected Event findEvent(String input){

        if (input.length() == 3){
            //Ime eventa:Vremepocetka:Datum
            Event found;
            String[] parts = input.split(":");
            LocalDate localDate = LocalDate.parse(parts[1]);
            DayOfWeek day = localDate.getDayOfWeek();
            String eventName = parts[1];
            Time time = Time.valueOf(parts[2].concat(":00"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = format.parse(parts[1]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            List<Event> lista = this.schedule.getSchedule().get(day);

            for (Event event : lista){
                if (event.getSubject().equals(eventName) && event.getStartTime().equals(time)){
                    return event;
                }
            }
        }else{
            //Soba123,2023-10-15,08:00,10:00,nedelja,Matematika,Profesor X,Predavanje,GrupaA,Dodatne informacije1:2, Dodatna informacija 2:5


        }
        return null;
    }

    public boolean addEvent(String input) {
        Event event = parser(input);
        List<Event> lista = this.schedule.getSchedule().get(event.getDayOfWeek());
        lista.add(event);
        this.schedule.getSchedule().put(event.getDayOfWeek(), lista);
        return true;
    }

    protected void removeEvent(String input) {
        //Ime eventa:Vremepocetka:Datum
        Event toRemove = findEvent(input);
        List<Event> lista = this.schedule.getSchedule().get(toRemove.getDayOfWeek());
        lista.remove(toRemove);
        this.schedule.getSchedule().put(toRemove.getDayOfWeek(), lista);
    }

    public void updateEvent(String input) {
        //Ime eventa:Vremepocetka:Datum
        //Soba123,2023-10-15,08:00,10:00,nedelja,Matematika,Profesor X,Predavanje,GrupaA,Dodatne informacije1:2, Dodatna informacija 2:5

        Event event = parser(input);
        String str = this.input;
        List<Event> lista = this.schedule.getSchedule().get(event.getDayOfWeek());
        /*if(lista.contains(event)){ //TODO
            if(this.addEvent(event))
                lista.remove(event);
        }*/
    }


    public void addRoom(String input){
        ///Soba123,capacity,PCNumber,projector,graphicsTable
        ///Soba123,capacity,PCNumber,projector,graphicsTable,Dodatne informacije1:2, Dodatna informacija 2:5

        String[] parts = input.split(",");
        String roomName = parts[0];
        int capacity = Integer.parseInt(parts[1]);
        int PCNumber = Integer.parseInt(parts[2]);
        boolean projector = Boolean.parseBoolean(parts[3]);
        boolean graphicsTable = Boolean.parseBoolean(parts[4]);
        Map<String, String> additionalData = new HashMap<>();

        for(int i = 5; i < parts.length; i++){
            String [] token = parts[i].split(":");
            additionalData.put(token[0], token[1]);
        }
        Room room = new Room(roomName, capacity, PCNumber, projector, graphicsTable, additionalData);
        this.rooms.add(room);
    }

    public void removeRoom(String input){
        for(var x : this.rooms)
            if(x.getName().equals(input)) {
                this.rooms.remove(x);
                return;
            }
    }

    public boolean loadScheduleFromFile(){
        Scanner scanner = new Scanner(System.in);
        List<Date> listaDatuma = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            listaDatuma.add(format.parse(scanner.nextLine()));
            listaDatuma.add(format.parse(scanner.nextLine()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String x;


        boolean flag = false;
        /*try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file)); //TODO

            String line;
            while((x = reader.readLine()) != null){
                if(!flag){
                    flag = true;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        return false;
    }
}
