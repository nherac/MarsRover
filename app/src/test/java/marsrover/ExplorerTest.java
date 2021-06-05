package marsrover;

import marsrover.enumerated.Cardinal;
import marsrover.logic.Explorer;
import marsrover.logic.ExplorerNasa;
import marsrover.plainobjects.Rover;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static  org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import static org.hamcrest.Matchers.in;
import static org.junit.jupiter.api.Assertions.*;

class ExplorerTest {

    @DisplayName("Happy path. Given an correct formated input, which valid options the system updated the Rovers coordinates")
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/001HappyPathInputs")
    void Test01(String input, String output) {
        //Arrange
        String[] inputArgs = input.split(",");
        Explorer instanceToTest = ExplorerNasa.getInstance(inputArgs);

        //Act
        instanceToTest.executeTasks();

        //Test
        int outputArgsIndex = 0;
        String[] outputArgs = output.split(",");
        for(Rover t: instanceToTest.getListOfRovers()){
            var endX = Integer.valueOf(outputArgs[outputArgsIndex]);
            var endY = Integer.valueOf(outputArgs[outputArgsIndex + 1]);
            int endAngelInDegrees = Cardinal.valueOf( outputArgs[outputArgsIndex + 2]).getAngle();
            var position = t.getPosition();
            assertEquals(endX, position.getCoordinates().getX());
            assertEquals(endY, position.getCoordinates().getY());
            assertEquals(endAngelInDegrees, position.getAngle());
            outputArgsIndex = outputArgsIndex + 3;
        }
    }

    @DisplayName("When getting an input with the intial speficication format, we get an explorer system")
    @ParameterizedTest
    @CsvSource({
            "'5,5,1,2,N,LMLMLMLMM,3,3,E,MMRMMRMRRM',2",
            "'5,5,1,2,N,LMLMLMLMM',1",
            "'5,5,1,2,N,LMLMLMLMM,3,3,E,MMRMMRMRRM,2,4,N,LMLMLMLMM',3"
    })
    void getInstance(String input, int numberOfRovers) {

        String[] inputArgs = input.split(",");
        Explorer instanceToTest = ExplorerNasa.getInstance(inputArgs);
        assertThat(instanceToTest.getListOfRovers(),hasSize(numberOfRovers));

    }

    @DisplayName("When two rovers ends in the same point, the system will throws an exception")
    @ParameterizedTest
    @CsvSource({
            "'5,5,3,3,E,MMRMMRMRRM,3,3,E,MMRMMRMRRM'",
            "'5,5,1,2,N,LMLMLMLMM,1,2,N,LMLMLMLMM'",
    })
    void TestRoverBumpsWithEachOther(String input) {

        String[] inputArgs = input.split(",");
        Explorer instanceToTest = ExplorerNasa.getInstance(inputArgs);
        Throwable t = assertThrows(IllegalArgumentException.class, ()->instanceToTest.executeTasks());
        assertTrue(t.getMessage().contains("This coordinate is busy, the rover cannot go here"));

    }

    @DisplayName("When introducing incorrect info, the system throws an exception")
    @ParameterizedTest
    @CsvSource(
            {
                    "'1,2,N,LMLMLMLMM',",

            }
    )
    void inputIsNotLongEnough(String input){

        String[] inputArgs = input.split(",");
        Throwable t = assertThrows(IllegalArgumentException.class, ()->ExplorerNasa.getInstance(inputArgs));

    }
}