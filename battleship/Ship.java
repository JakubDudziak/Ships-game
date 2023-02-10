package battleship;

public class Ship {


    String type;
    Integer length;
    boolean isPlaced;

    boolean isSunk;

    public Ship(String type, Integer length, boolean isPlaced, boolean isSunk) {
        this.type = type;
        this.length = length;
        this.isPlaced = isPlaced;
        this.isSunk = isSunk;
    }

    public String getType() {
        return type;
    }
}
