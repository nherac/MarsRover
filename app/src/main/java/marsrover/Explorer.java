package marsrover;

import lombok.Getter;

import java.util.*;
import java.util.function.IntPredicate;

@Getter
public class Explorer {
    private List<Task> listOfTasks;
    private Area area;

    private Explorer(Area area, List<Task> listOfTasks){
        this.area = area;
        this.listOfTasks = Collections.unmodifiableList(listOfTasks);
    }

    public static Explorer getInstance(String... args){
        //Checking that we have at least the info for the area and the info for 1 rover

        boolean thereIsNotEnoughInfoForPlateauAndOneRover = args.length < 6;
        if(thereIsNotEnoughInfoForPlateauAndOneRover)
            throw new IllegalArgumentException("There are missing information, we need a plateau and a rover");
        boolean infoForOtherRoversIsNotEnough = (args.length - 6) % 4 != 0;

        if(infoForOtherRoversIsNotEnough)
            throw new IllegalArgumentException(("There are one o more rovers with missing information"));

        //arg[0] and arg[1] are the area upper right coordinates.
        //we set the predicates for a square starting at 0,0
        //if we want to use another shape, these predicates would be different
        IntPredicate rangeX = x -> x>0 && x<Integer.valueOf(args[0]);
        IntPredicate rangeY = y -> y>0 && y<Integer.valueOf(args[1]);
        //If the input is not a number, the system will throw a IllegalArgumentException.

        //Now we are going to read the info about the rovers

        //validate the info about the rovers

        //If the info is valid, it will be store in an ArrayList to identify the Rover order.
        List<Task> inputTasks = new LinkedList<>();

        for(int i= 2; i < args.length; i = i+4){
            //The  xCoordinate
            int xCoor = Integer.valueOf(args[i]);
            if(!rangeX.test(xCoor))
                throw new IllegalArgumentException("Value is not in the area range for x values");

            //The yCoordinate
            int yCoor = Integer.valueOf(args[i+1]);
            if(!rangeY.test(yCoor))
                throw new IllegalArgumentException("Value is not in the area range for y values");

            //The angle. If the letter is different of N,W,S,E, it will throws an exception. Lower case
            //are not allowed, but only adding args[i+3].toUpperCase() will add the feature.
            int angle = Cardinal.valueOf(args[i+2]).getAngle();

            //Now we have to validate the commands
            List<Commands> listOfCommands = new ArrayList<>();
            for(int j=0;j<args[i+3].length(); j++){
                char singleCommand = args[i+3].charAt(j);
                //In case we have a invalid command, the Enum will care to throw an exception.
                var command = Commands.valueOf(singleCommand);
                listOfCommands.add(command);
            }
            //At this point, we have a valid rover position with a valid command sequence. We can create the task
            Rover rover = new Rover(xCoor,yCoor,angle);
            Task task = new Task(rover,listOfCommands);
            inputTasks.add(task);
        }

        //now we can create the Explorer
        Area area = new Area(rangeX, rangeY);
        return new Explorer(area,inputTasks);
    }





}

