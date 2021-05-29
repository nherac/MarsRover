package marsrover;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntPredicate;

public class Explorer {
    List<Task> listOfTasks;
    Area area;

    /* Weak telescopic constructor*/
    public Explorer(int areaX, int areaY,
                    int rover1PosX, int rover1PosY, String commandsForRover1,
                    int rover2PosX, int rover2PosY, String commandsForRover2){

        IntPredicate rangeX = x -> (x>=0 && x <= areaX);
        IntPredicate rangeY = y -> (y>=0 && y<= areaY);

        this.area = new Area(rangeX,rangeY);

    }

    public void createExplorerSystem(Area area, Task... infoAboutRovers){
        this.area = area;
        listOfTasks = Arrays.stream(infoAboutRovers).toList();
    }
    public void applyCommands(){

    }

}

