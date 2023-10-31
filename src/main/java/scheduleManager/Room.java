package scheduleManager;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter

public class Room {
    private String name;
    private int capacity;
    private int PCNumber;
    private boolean projector;
    private boolean graphicsTable;
    private Map<String, String> additionalData;

    public Room(String name, int capacity, int PCNumber, boolean projector, boolean graphicsTable, Map<String, String> additionalData) {
        this.name = name;
        this.capacity = capacity;
        this.PCNumber = PCNumber;
        this.projector = projector;
        this.graphicsTable = graphicsTable;
        this.additionalData = additionalData;
    }

    public Room(String name, int capacity, int PCNumber, boolean projector, boolean graphicsTable) {
        this.name = name;
        this.capacity = capacity;
        this.PCNumber = PCNumber;
        this.projector = projector;
        this.graphicsTable = graphicsTable;
    }
}
