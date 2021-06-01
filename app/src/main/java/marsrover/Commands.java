package marsrover;

public enum Commands {
    L(90),
    R(-90),
    M(0){
        @Override
        public void applyToVehicule(Rover rover) {
            var roverCardinalPosition = Cardinal.valueOf(rover.getAngle());
            var newXForRover = roverCardinalPosition.getX() + rover.getX();
            rover.setX(newXForRover);
            var newYForRover = roverCardinalPosition.getY() + rover.getY();
            rover.setY(newYForRover);

        }
    };
    Commands(int angle){
        this.angle = angle;
    }
    private final int angle;
    public void applyToVehicule(Rover rover){
        var newAngleForRover = rover.getAngle() + this.angle;
        rover.setAngle(newAngleForRover);
    }

    public static Commands valueOf(char c){
        return switch(c){
            case 'L' -> Commands.L;
            case 'R' -> Commands.R;
            case 'M' -> Commands.M;
            default -> throw new IllegalArgumentException("Not supported command");
        };
    }

}
