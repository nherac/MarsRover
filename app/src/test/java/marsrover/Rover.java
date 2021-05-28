package marsrover;

import lombok.Data;

import java.util.Map;

@Data
public class Rover {
    private int x;
    private int y;
    private String angle;

    public void applyCommands(String commands) {
    }
}
