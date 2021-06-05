package marsrover.plainobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import marsrover.enumerated.Commands;

import java.util.List;

@Getter
@AllArgsConstructor
public class Rover {
    private final Position position;
    @Setter
    private List<Commands> commands;
}
