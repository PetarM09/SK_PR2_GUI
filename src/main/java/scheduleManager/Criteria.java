package scheduleManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Criteria {
    private String name;
    private String value;

    public Criteria(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
