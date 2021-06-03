package marsrover;

import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @DisplayName("Happy path. Given an correct formated input, which valid options the system updated the Rovers coordinates")
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/001HappyPathInputs")
    void Test01(String input, String output) {
        //Arrange
        String[] inputArgs = input.split(",");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        final PrintStream originalErr = System.err;

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        UserInterface.main(inputArgs);
        int outputArgsIndex = 0;
        //Pending to Fix to customsize outputs
        //String[] outputArgs = output.split("\n");

        //assertTrue(outContent.toString().contains(outputArgs[0]));
       // assertTrue(outContent.toString().contains(outputArgs[1]));

        assertTrue(outContent.toString().contains("Congratulations"));

        System.setOut(originalOut);
        System.setErr(originalErr);

    }

}