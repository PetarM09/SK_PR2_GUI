package scheduleManager;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter

public class Room {
    private int capacity;
    private int PCNumber;
    private boolean projector;
    private boolean graphicsTable;
    private Map<String, String> dodatniPodaci;

    public Room(int capacity, int PCNumber, boolean projector, boolean graphicsTable, Map<String, String> dodatniPodaci) {
        this.capacity = capacity;
        this.PCNumber = PCNumber;
        this.projector = projector;
        this.graphicsTable = graphicsTable;
        this.dodatniPodaci = dodatniPodaci;
    }

    public Room(int capacity, int PCNumber, boolean projector, boolean graphicsTable) {
        this.capacity = capacity;
        this.PCNumber = PCNumber;
        this.projector = projector;
        this.graphicsTable = graphicsTable;
    }
}
