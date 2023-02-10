package battleship;

import java.util.*;

public class Player {
    Board board;

    Ship2[] ships2;

    public Player(Board board, Ship2[] ship2) {
        this.board = board;
        this.ships2 = ship2;
    }

    public Ship2[] getShips2() {
        return ships2;
    }

    public void setShips2(Ship2[] ships2) {
        this.ships2 = ships2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean hasPlayerLost() {
        List<Integer> lostShips = Arrays.asList(0, 0, 0, 0, 0);
        boolean lost = false;
        for (int i = 0; i < this.ships2.length; i++) {
            if (this.ships2[i].isSunk() == true) {
                lostShips.set(i, 1);
            }
        }
        if (lostShips.stream().mapToInt(Integer::intValue).sum() == 5) lost = true;

        return lost;
    }

}

