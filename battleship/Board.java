package battleship;

import java.lang.reflect.Array;
import java.util.*;

public class Board {
    private Field[][] co_ordinates;


    public Board() {
        this.co_ordinates = new Field[11][11];
        for (int y = 0; y < this.co_ordinates.length; y++) {
            if (y == 0) {
                for (int x = 0; x < this.co_ordinates[y].length; x++) {
                    if (x == 0) {
                        this.co_ordinates[y][x] = new Field(0, 0, false, " ", false, "");
                    } else {
                        this.co_ordinates[y][x] = new Field(0, 0, false, String.valueOf(x), false, "");
                    }
                    //System.out.println(this.co_ordinates[y][x].getValue());
                }

            } else {
                char[] letter = {'A','B','C','D','E','F','G','H','I','J'};
                for (int x = 0; x < this.co_ordinates[y].length; x++) {
                    if (x == 0) {
                        this.co_ordinates[y][x] = new Field(0, 0, false, String.valueOf(letter[y - 1]), false , "");
                    } else {
                        this.co_ordinates[y][x] = new Field(x, y, false, "~", false , "");
                    }
                    //System.out.println(this.co_ordinates[y][x].getValue());
                }

            }
        }
    }

    public void printBoard(Board board) {
        for (int i = 0; i < board.co_ordinates.length; i++) {
            for (int j = 0; j < board.co_ordinates[i].length; j++) {
                System.out.print(board.co_ordinates[i][j].getValue() + " ");

            }
            System.out.print("\n");
        }
    }

    public void placeShips(Ship2[] ships, Board board) {
        Integer[] coordinates;

        for (int shipIndex = 0; shipIndex < ships.length; shipIndex++) {


        System.out.println("\nEnter the coordinates of the " + ships[shipIndex].type + "(" + ships[shipIndex].fields.length + " cells):");

        do {
            //System.out.print("> ");

            coordinates = readPosition();

            boolean correctPosition = correctPosition(coordinates);
            if (!correctPosition) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }

            boolean correctLength = correctLength(ships[shipIndex], coordinates);
            if (!correctLength) {
                System.out.println("Error! Wrong length of the " + ships[shipIndex].type + "! Try again:");
                continue;
            }


            boolean hasNeighbor = hasNeighbor(board, ships[shipIndex],coordinates);
            if (hasNeighbor == true) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }


            if(correctLength == true && hasNeighbor == false && correctPosition == true) {
                ships[shipIndex].isPlaced = true;
                int y = Math.min(coordinates[0], coordinates[2]);
                int x = Math.min(coordinates[1], coordinates[3]);
                int length = ships[shipIndex].fields.length;
                if(coordinates[0] == coordinates[2]) { //jeśli ten sam Y
                    for (int i = y; i <= y; i++) {
                        int currentField = 0;
                        for (int j = x; j <= x + length - 1; j++) {
                            if (i < board.co_ordinates.length && j < board.co_ordinates[i].length) {
                                board.co_ordinates[i][j].setTaken(true);
                                board.co_ordinates[i][j].setValue("O");
                                board.co_ordinates[i][j].setShipName(ships[shipIndex].getType());
                                ships[shipIndex].fields[currentField] = board.co_ordinates[i][j];
                                currentField++;

                            }
                        }
                    }

                } else { // x ten sam
                    for (int i = x; i <= x; i++) {

                        int currentField = 0;
                        for (int j = y; j <= y + length - 1; j++) {
                            if (j < board.co_ordinates.length && i < board.co_ordinates[j].length) {
                                board.co_ordinates[j][i].setTaken(true);
                                board.co_ordinates[j][i].setValue("O");
                                board.co_ordinates[j][i].setShipName(ships[shipIndex].getType());
                                ships[shipIndex].fields[currentField] = board.co_ordinates[j][i];
                                currentField++;
                            }
                        }
                    }

                }


            };

        } while (ships[shipIndex].isPlaced == false);
            printBoard(board);
        }
        // aż nie wywali błędu ma pytać o koordynaty
        System.out.println("\nPress Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        System.out.println("...");
        scanner.nextLine();
        //scanner.close();
    }

    private boolean correctPosition(Integer[] coordinates) {
        boolean condition = false;
        if (coordinates[0] == coordinates[2] || coordinates[1] == coordinates[3]) condition = true;
        return condition;
    }

    private boolean correctLength(Ship2 ship, Integer[] coordinates) {
        int length = ship.fields.length;
        boolean condition;
        int y = Math.max(coordinates[0], coordinates[2]) - Math.min(coordinates[0], coordinates[2]) + 1;
        int x = Math.max(coordinates[1],coordinates[3]) - Math.min(coordinates[1], coordinates[3]) + 1;
        if (length == x || length == y) {
            condition = true;
        } else {
            condition = false;
        };

        return condition;
    }

    private Integer[] readPosition() {

        var counterparts = new HashMap<Character, Integer>();
        counterparts.put('A', 1);
        counterparts.put('B', 2);
        counterparts.put('C', 3);
        counterparts.put('D', 4);
        counterparts.put('E', 5);
        counterparts.put('F', 6);
        counterparts.put('G', 7);
        counterparts.put('H', 8);
        counterparts.put('I', 9);
        counterparts.put('J', 10);

        String positions2;
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.hasNext());
        positions2 = sc.nextLine().toUpperCase();

        String[] positions = positions2.split("[^[A-J][1-9]|10]", 2);
//        System.out.println(positions[0]);
//        System.out.println(positions[1]);

//
//        for (int i = 0; i < positions.length; i++) {
//            positions[i] = sc.next().toUpperCase();
//            System.out.println("w petli");
//            System.out.println("zebrany element");
//        }

        Integer[] positionsInt = new Integer[4];

                positionsInt[0] = counterparts.get(positions[0].charAt(0));
                positionsInt[1] = Integer.valueOf(positions[0].replaceAll("[^0-9]", ""));
                positionsInt[2] = counterparts.get(positions[1].charAt(0));
                positionsInt[3] = Integer.valueOf(positions[1].replaceAll("[^0-9]", ""));

        return positionsInt;
    }

    private boolean hasNeighbor(Board board, Ship2 ship, Integer[] coordinates) {
        boolean condition = false;
        int y = Math.min(coordinates[0], coordinates[2]);
        int x = Math.min(coordinates[1], coordinates[3]);
        int length = ship.fields.length;
        if(coordinates[0] == coordinates[2]) { //jeśli ten sam Y
            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + length; j++) {
                    if (i < board.co_ordinates.length && j < board.co_ordinates[i].length) {
                        if (board.co_ordinates[i][j].isTaken() == true) condition = true;
                    }
                }
            }
        } else {
            for (int i = x - 1; i <= x + 1; i++) { //jeśli ten sam x
                for (int j = y - 1; j <= y + length; j++) {
                    if (j < board.co_ordinates.length && i < board.co_ordinates[j].length) {
                        if (board.co_ordinates[j][i].isTaken() == true) condition = true;
                    }
                }
            }

        }

        return condition;
    }


    public Integer[] shot(Board board, Ship2[] ships) {
        var counterparts = new HashMap<Character, Integer>();
        counterparts.put('A', 1);
        counterparts.put('B', 2);
        counterparts.put('C', 3);
        counterparts.put('D', 4);
        counterparts.put('E', 5);
        counterparts.put('F', 6);
        counterparts.put('G', 7);
        counterparts.put('H', 8);
        counterparts.put('I', 9);
        counterparts.put('J', 10);

        String target;
        Integer[] positionsInt = new Integer[2];

        do { //pytaj az nie wpiszesz poprawnego

            Scanner sc = new Scanner(System.in);
            target = sc.next().toUpperCase();
            if (counterparts.containsKey(target.charAt(0)) == false ||
                    Integer.valueOf(target.substring(1)) > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }


            positionsInt[0] = counterparts.get(target.charAt(0));
            positionsInt[1] = Integer.valueOf(target.replaceAll("[^0-9]", ""));

        } while (counterparts.containsKey(target.charAt(0)) == false || Integer.valueOf(target.substring(1)) > 10);

        return positionsInt;








    }

    public void fogOfWar (Board board, boolean run) {
        if (run == true) {
            for (int i = 0; i < board.co_ordinates.length; i++) {
                for (int j = 0; j < board.co_ordinates[i].length; j++) {
                    if (board.co_ordinates[i][j].isTaken() == true && board.co_ordinates[i][j].isHit() == false)
                        board.co_ordinates[i][j].setValue("~");
                }
            }
        } else {
            for (int i = 0; i < board.co_ordinates.length; i++) {
                for (int j = 0; j < board.co_ordinates[i].length; j++) {
                    if (board.co_ordinates[i][j].isTaken() == true && board.co_ordinates[i][j].isHit() == false)
                        board.co_ordinates[i][j].setValue("O");
                }
            }
        }
    }


    public void turn (Board board, Ship2[] ship2s, Player player) {

        List<Ship2> toSunk = Arrays.asList(ship2s);


        //System.out.println("The game starts!");
        //fogOfWar(board, true);
        //printBoard(board);
        //System.out.println("Take a shot!");
        //System.out.print("> ");
        int numberOfSunkShips = 0;

        //do {
            //1. strzal i weryfikacja czy w cos trafil, jesli nie pyta ponownie
            Integer[] positionsInt = shot(board, ship2s);
            if (co_ordinates[positionsInt[0]][positionsInt[1]].isTaken() == true) {
                co_ordinates[positionsInt[0]][positionsInt[1]].setValue("X");
                co_ordinates[positionsInt[0]][positionsInt[1]].setHit(true);

                //2. jeśli trafil tutaj sprawdza czy zatopil
                String whichShip = co_ordinates[positionsInt[0]][positionsInt[1]].getShipName();
                for (Ship2 ship2: toSunk
                ) {
                    if (ship2.getType() == whichShip){
                        ship2.isShipSunk();
                        if (ship2.isSunk() == true && ship2.isCheck() == false) {
                            if (player.hasPlayerLost() == false) {
                                System.out.println("You sank a ship! Specify a new target:");
                                ship2.setCheck(true);
                            } else System.out.println("You sank the last ship. You won. Congratulations!");
                        } else System.out.println("You hit a ship!");
                    }
                }

                //lista dlugosci sttakow, skreslanie tych, ktore zostaly juz zatopione

            } else {
                System.out.println("You missed!");
                co_ordinates[positionsInt[0]][positionsInt[1]].setValue("M");
            }

        //} while (numberOfSunkShips != 5);
        System.out.println(numberOfSunkShips);

    }





}

