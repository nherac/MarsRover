package marsrover.enumerated;

import lombok.Getter;

@Getter
public enum Cardinal {

    N(90,0,1, "North"),
    W(180,-1,0, "West"),
    S(270,0,-1, "South"),
    E(0,1,0,"East");

    private Cardinal(int angle, int x, int y, String name){
        this.angle = angle;
        this.x= x;
        this.y=y;
        this.name = name;
    }
    private final int angle;
    private final int x;
    private final int y;
    private final String name;

    public static Cardinal valueOf(int i) {
        var angleToConvert = i % 360;
        return switch (angleToConvert){
            case 90 -> Cardinal.N;
            case 180 -> Cardinal.W;
            case 270 -> Cardinal.S;
            case 0 -> Cardinal.E;
            default -> throw new IllegalArgumentException("Not supported cardinal");
        };
    }
}
