package marsrover.logic;

import lombok.Getter;
import marsrover.enumerated.Cardinal;
import marsrover.enumerated.Commands;
import marsrover.plainobjects.Area;
import marsrover.plainobjects.Coordinates;
import marsrover.plainobjects.Position;
import marsrover.plainobjects.Rover;

import java.util.*;
import java.util.function.Consumer;

@Getter
public abstract class Explorer {
    private List<Rover> listOfRovers;
    private Area area;
    private Set<Coordinates> busyCoordinates;
    private Map<Commands,Consumer<Position>> commandsTranslation;


    protected Explorer(Area area, List<Rover> listOfRovers){
        this.area = area;
        this.listOfRovers = listOfRovers;
        this.busyCoordinates = new HashSet<>();
        CommandsTranslator translator = new CommandsTranslator();
        this.commandsTranslation = translator.map();
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
                   .forEach(c->commandsTranslation.get(c).accept(singleRover.getPosition()));
        singleRover.setCommands(Collections.emptyList());
    }

    private void areAvailableCoordinates(Coordinates coordinates){
        //Ad current rover position to busy coordinates. Rover lands

        var coordinateIsNotAvalible = !busyCoordinates.add(coordinates);
        if(coordinateIsNotAvalible)
            throw new IllegalArgumentException("This coordinate is busy, the rover cannot go here");
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

    protected class CommandsTranslator {

        Map<Commands, Consumer<Position>> map(){
            Map<Commands,Consumer<Position>> result = new HashMap<>();

            Consumer<Position> turnLeft = p -> p.setAngle(p.getAngle()+ 90);
            result. put(Commands.L,turnLeft);

            Consumer<Position> turnRight = p -> p.setAngle(p.getAngle()- 90);
            result.put(Commands.R,turnRight);

            Consumer<Position> moveForward = p-> moveForward(p);
            result.put(Commands.M, moveForward);

            return result;
        }

         void moveForward(Position position){
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
    }

}

