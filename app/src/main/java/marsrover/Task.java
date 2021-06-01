package marsrover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Task {
    private final Rover rover;
    @Setter
    private List<Commands> commands;
}
