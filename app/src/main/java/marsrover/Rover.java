package marsrover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Rover {
    private final Position position;
    @Setter
    private List<Commands> commands;
}
