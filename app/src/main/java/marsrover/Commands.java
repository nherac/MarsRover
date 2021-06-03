package marsrover;

public enum Commands {
    L, R, M;
    public static Commands valueOf(char c){
        return switch(c){
            case 'L' -> Commands.L;
            case 'R' -> Commands.R;
            case 'M' -> Commands.M;
            default -> throw new IllegalArgumentException("Not supported command");
        };
    }
}
