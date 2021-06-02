package marsrover;

public enum Commands {

    L(90),
    R(-90),
    M(0);

    Commands(int angle){
        this.angle = angle;
    }

    private final int angle;

    public static Commands valueOf(char c){
        return switch(c){
            case 'L' -> Commands.L;
            case 'R' -> Commands.R;
            case 'M' -> Commands.M;
            default -> throw new IllegalArgumentException("Not supported command");
        };
    }
}
