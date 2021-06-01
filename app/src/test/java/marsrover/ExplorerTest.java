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
            "'5,5,1,2,N,LMLMLMLMM,3,3,E,MMRMMRMRRM',2"
    })
    void getInstance(String input, int numberOfTasks) {

        String[] inputArgs = input.split(",");
        Explorer instanceToTest = Explorer.getInstance(inputArgs);
        assertThat(instanceToTest.getListOfTasks(),hasSize(numberOfTasks));

    }
}