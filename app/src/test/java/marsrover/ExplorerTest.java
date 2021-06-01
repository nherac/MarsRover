package marsrover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static  org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import static org.junit.jupiter.api.Assertions.*;

class ExplorerTest {

    @DisplayName("When getting an input with the intial speficication format, we get an explorer system")
    @ParameterizedTest
    @CsvSource({
            "'5,5,1,2,N,LMLMLMLMM,3,3,E,MMRMMRMRRM',2",
            "'5,5,1,2,N,LMLMLMLMM',1",
            "'5,5,1,2,N,LMLMLMLMM,3,3,E,MMRMMRMRRM,2,4,N,LMLMLMLMM',3"
    })
    void getInstance(String input, int numberOfTasks) {

        String[] inputArgs = input.split(",");
        Explorer instanceToTest = Explorer.getInstance(inputArgs);
        assertThat(instanceToTest.getListOfTasks(),hasSize(numberOfTasks));

    }

    @DisplayName("When introducing incorrect info, the system throws an exception")
    @ParameterizedTest
    @CsvSource(
            {
                    "1,2,N,LMLMLMLMM",

            }
    )
    void inputIsNotLongEnough(String input){

        String[] inputArgs = input.split(",");
        Throwable t = assertThrows(IllegalArgumentException.class, ()->Explorer.getInstance(inputArgs));

    }
}