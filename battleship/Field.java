package battleship;

public class Field {
    Integer col;
    Integer row;
    boolean isTaken;
    String value;

    boolean isHit;

    String shipName;

    public Field(Integer col, Integer row, boolean isTaken, String value, boolean isHit, String shipName) {
        this.col = col;
        this.row = row;
        this.isTaken = isTaken;
        this.value = value;
        this.isHit = isHit;
        this.shipName = shipName;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}

