package marsrover;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rover {
    private int angle;
    private int x;
    private int y;

    public Rover(int x, int y, int angle) {
        setAngle(angle);
        this.x = x;
        this.y = y;
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
