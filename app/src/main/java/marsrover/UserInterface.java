package marsrover;

public class UserInterface {
    public static void main(String[] args) {
        try{
            Explorer simulatorToValidateInput = ExplorerNasa.getInstance(args);
            simulatorToValidateInput.executeTasks();
            //If the input is fine, then, the real simulator can be create and all the tasks will be performed
            Explorer realExplorer = ExplorerNasa.getInstance(args);
            realExplorer.executeTasks();
            UserInterface.printStatus(realExplorer);
            System.out.println("Congratulations! You have send this explorers to Nasa");

        }catch (RuntimeException e){
            if (e instanceof IllegalArgumentException){
                String reason = e.getMessage();
                System.out.println("Oh, the input in the simulation was caused because " + reason +
                                   "Please, fix the input and don't worry, it wasn't the real Explorer");

            }else{
                throw new RuntimeException("OH, the logic is wrong. You have sent it to Mars...fix the code");
            }

        }
    }

    private static void printStatus(Explorer explorer) {
        explorer.getListOfRovers()
                .forEach(UserInterface::printSingleRover);

    }

    private static void printSingleRover(Rover r){
        Position p= r.getPosition();
        System.out.println(p.getCoordinates().getX() + " " +
                           p.getCoordinates().getY() + " " +
                           Cardinal.valueOf(p.getAngle()));
    }
}
