package battleship;

public class Ship2 {


    String type;
    Field[] fields;
    boolean isPlaced;

    boolean isSunk;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    boolean check;

    public String getType() {
        return type;
    }

    public Field[] getFields() {
        return fields;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public Ship2(String type, Field[] fields, boolean isPlaced, boolean isSunk, boolean check) {
        this.type = type;
        this.fields = fields;
        this.isPlaced = isPlaced;
        this.isSunk = isSunk;
        this.check = check;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public void isShipSunk() {
        int hits = 0;
        for (Field field: fields) {
            if (field.isHit() == true) hits++;
        }
        if (hits == fields.length) setSunk(true);
    }

}
