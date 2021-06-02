package marsrover;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

@Getter
public class Area {
    private IntPredicate validX;
    private IntPredicate validY;


    public Area(IntPredicate rangeXValues, IntPredicate rangeYValues ){
        validX = rangeXValues;
        validY = rangeYValues;
    }

}
