package marsrover;

import lombok.Getter;

import java.util.*;
import java.util.function.IntPredicate;

@Getter
public class Explorer {
    private List<Rover> listOfRovers;
    private Area area;
    private Set<Coordinates> busyCoordinates;


    private Explorer(Area area, List<Rover> listOfRovers){
        this.area = area;
        this.listOfRovers = listOfRovers;
        this.busyCoordinates = new HashSet<>();
    }

    public void executeTasks(){
        listOfRovers.stream().forEach(this::executeSingleTask);
    }

    private void executeSingleTask(Rover singleRover){
        Coordinates landingCoordinates = singleRover.getPosition().getCoordinates();
        //Is the coordinate is available, it will be added. A busy coordinate generate IllegalArgumentException
        areInAreaRange(landingCoordinates);
        areAvailableCoordinates(landingCoordinates);
        singleRover.getCommands()
                   .stream()
                   .forEach(c -> applyCommandToSpecificPosition(singleRover.getPosition(), c));
        singleRover.setCommands(Collections.emptyList());
    }

    private void areAvailableCoordinates(Coordinates coordinates){
        //Ad current rover position to busy coordinates. Rover lands

        var coordinateIsNotAvalible = !busyCoordinates.add(coordinates);
        if(coordinateIsNotAvalible)
            throw new IllegalArgumentException("This coordinate is busy, the rover cannot land");
    }

    private void areInAreaRange(Coordinates coordinates){
        //These are the values to test
        var newX = coordinates.getX();
        var newY = coordinates.getY();

        var outOfXRanges = area.getValidX().negate().test(newX);
        var outOfYRange = area.getValidY().negate().test(newY);
        var outOfAreaRange = outOfXRanges || outOfYRange;

        if(outOfAreaRange)
            throw new IllegalArgumentException("This coordinate is out of the range of the Area");
    }

    private void applyCommandToSpecificPosition(Position r, Commands c) {

        switch (c){
            case L: r.setAngle(r.getAngle()+ 90);break;
            case R: r.setAngle(r.getAngle() -90);break;
            case M: executeMCommand(r);break;
            default: throw new IllegalArgumentException("Unsupported command");
        }

    }

    private void updateBusyCoordinates(Coordinates old, Position r){

    }
    private void executeMCommand(Position position){

        var currentCoordinates = position.getCoordinates();
        var currentOrientation = Cardinal.valueOf(position.getAngle());

        var provisionalNewXForRover = currentOrientation.getX() + currentCoordinates.getX();
        var provisionalNewYForRover = currentOrientation.getY() + currentCoordinates.getY();
        var provisionalCoordinate = new Coordinates(provisionalNewXForRover,provisionalNewYForRover);

        getBusyCoordinates().remove(currentCoordinates);
        areInAreaRange(provisionalCoordinate);
        areAvailableCoordinates(provisionalCoordinate);
        //If the coordinate is not available, it will throw an exception.
        //Everything is fine, then, I update the rover position
        position.setCoordinates(provisionalCoordinate);

    }

    public void printStatus(){
        listOfRovers.forEach(t->{
            var rover = t.getPosition();
            //System.out.println(rover.getX() + " " + rover.getY() + " " + Cardinal.valueOf(rover.getAngle()));
        });
    }

    public static void main(String[] args) {
        Explorer explorer = getInstance(args);
        explorer.executeTasks();
        explorer.printStatus();

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
        IntPredicate rangeX = x -> x>=0 && x<=Integer.valueOf(args[0]);
        IntPredicate rangeY = y -> y>=0 && y<=Integer.valueOf(args[1]);
        //If the input is not a number, the system will throw a IllegalArgumentException.
        //Now we can create the plateau
        Area area = new Area(rangeX, rangeY);

        //Now we are going to read the info about the rovers

        //validate the info about the rovers

        //If the info is valid, it will be store in an ArrayList to identify the Position order.
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

        return new Explorer(area, inputRovers);
    }

}

