package marsrover;

import lombok.Getter;
import lombok.Setter;

import java.util.function.IntPredicate;

@Getter
@Setter
public class Area {
    IntPredicate validX;
    IntPredicate validY;
    public Area(IntPredicate rangeXValues, IntPredicate rangeYValues ){
        validX = rangeXValues;
        validY = rangeYValues;
    }
}
