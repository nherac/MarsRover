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
    private Set<Integer> busyX;
    private Set<Integer> busyY;

    public Area(IntPredicate rangeXValues, IntPredicate rangeYValues ){
        busyX = new HashSet<Integer>();
        busyY = new HashSet<Integer>();
        validX = rangeXValues;
        validY = rangeYValues;
    }

    public boolean addBusyCoordinate(int x, int y){
        var coordinatesAreAvailable = busyX.add(x) && busyY.add(y);
        return coordinatesAreAvailable;

    }
    public void removeBusyCoordinate(int x, int y){
        busyX.remove(x);
        busyY.remove(y);
    }
}
