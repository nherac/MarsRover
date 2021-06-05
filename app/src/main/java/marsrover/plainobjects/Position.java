package marsrover.plainobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private int angle;
    private Coordinates coordinates;

    public Position(Coordinates coordinates, int angle ) {
        setAngle(angle);
        this.coordinates = coordinates;
    }

    public void setAngle(int angle) {
        angle = angle % 360;
        boolean negativeAngle = (angle<0);
        if(negativeAngle){
            var positiveAngle = switch(angle){
                case - 90 -> 270;
                case - 180 -> 180;
                case - 270 -> 90;
                default -> throw new IllegalArgumentException("This angle is not supported");
            };
            angle = positiveAngle;
        }
        this.angle = angle;
    }

}
