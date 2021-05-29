package marsrover;

public class Rover {
    private int angle;
    private int x;
    private int y;

    public Rover(int angle, int x, int y) {
        setAngle(angle);
        this.x = x;
        this.y = y;
    }

    public void setAngle(int angle) {
        angle = angle % 360;
        boolean negativeAngle = (angle<0);
        if(negativeAngle){
            var positiveAngle = switch(angle){
                case -90 ->270;
                case -180 ->180;
                case -270 ->90;
                default -> throw new IllegalArgumentException("This angle is not supported");
            };
            angle = positiveAngle;
        }
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
