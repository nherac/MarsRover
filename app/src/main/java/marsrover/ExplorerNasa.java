package marsrover;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;

public class ExplorerNasa extends Explorer{
    public static int MINIMUM_ARG_LENGTH_FOR_ONE_SQUARE_GRID_AND_ONE_ROVER = 6;
    public static int MINIMUM_ARG_LENGTH_INFO_FOR_A_ROVER = 4;
    public static int LOWER_LEFT_X_COORDINATE_FOR_GRID = 0;
    public static int LOWER_LEFT_Y_COORDINATE_FOR_GRID = 0;

    private ExplorerNasa(Area area, List<Rover> inputRovers){
        super(area,inputRovers);
    }
    /*
    The logic for getInstance depends on the input.
    * */
    public  static Explorer getInstance(String... args){
        //Checking that we have at least the info for the area and the info for 1 rover

        boolean thereIsNotEnoughInfoForPlateauAndOneRover = args.length < MINIMUM_ARG_LENGTH_FOR_ONE_SQUARE_GRID_AND_ONE_ROVER;
        if(thereIsNotEnoughInfoForPlateauAndOneRover)
            throw new IllegalArgumentException("There are missing information, we need a plateau and a rover");
        boolean infoForOtherRoversIsNotEnough = (args.length - MINIMUM_ARG_LENGTH_FOR_ONE_SQUARE_GRID_AND_ONE_ROVER) % MINIMUM_ARG_LENGTH_INFO_FOR_A_ROVER != 0;

        if(infoForOtherRoversIsNotEnough)
            throw new IllegalArgumentException(("There are one o more rovers with missing information"));

        //arg[0] and arg[1] are the area upper right coordinates.
        //we set the predicates for a square starting at 0,0
        //if we want to use another shape, these predicates would be different
        IntPredicate rangeX = x -> x>=LOWER_LEFT_X_COORDINATE_FOR_GRID && x<=Integer.valueOf(args[0]);
        IntPredicate rangeY = y -> y>=LOWER_LEFT_Y_COORDINATE_FOR_GRID && y<=Integer.valueOf(args[1]);
        //If the input is not a number, the system will throw a IllegalArgumentException.
        //Now we can create the plateau
        Area area = new Area(rangeX, rangeY);

        //Now we are going to read the info about the rovers

        //validate the info about the rovers

        //If the info is valid, it will be store in an list
        List<Rover> inputRovers = new LinkedList<>();

        for(int i= 2; i < args.length; i = i+4){
            //The  xCoordinate
            int xCoor = Integer.valueOf(args[i]);
            //The yCoordinate
            int yCoor = Integer.valueOf(args[i+1]);

            var isUnavalableCoordinate = area.getValidX().negate().test(xCoor) ||
                    area.getValidY().negate().test(yCoor);

            if(isUnavalableCoordinate)
                throw new IllegalArgumentException("Coordinates values for the position are not available in this area");

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
            //At this point, we have a valid position position with a valid command sequence. We can create the rover
            Coordinates coordinates = new Coordinates(xCoor,yCoor);
            Position position = new Position(coordinates,angle);
            Rover rover = new Rover(position,listOfCommands);
            inputRovers.add(rover);
        }

        //now we can create the Explorer

        return new ExplorerNasa(area, inputRovers);
    }
}
