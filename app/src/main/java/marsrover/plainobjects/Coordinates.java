package marsrover.plainobjects;

import lombok.*;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Coordinates {
    private final int x;
    private final int y;
}
