package marsrover;

public enum Commands {
    L(90),
    R(-90),
    M(0){
        @Override
        public void applyToVehicule(Rover rover) {
            var roverCardinalPosition = Cardinal.valueOf(rover.getAngle());
            switch(roverCardinalPosition){
                case N:{
                    var newRoverY = rover.getY() + 1;
                    rover.setY(newRoverY);
                }break;
                case S:{
                    var newRoverY = rover.getY() -1;
                    rover.setY(newRoverY);
                }break;
                case E:{
                    var newRoverX = rover.getX() + 1;
                    rover.setX(newRoverX);
                }break;
                case W:{
                    var newRoverX = rover.getX() - 1;
                    rover.setX(newRoverX);
                }break;
            }
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

}
