package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Player 1, place your ships on the game field\n");

        Board board1 = new Board();
        Ship2[] ships1 = new Ship2[5];
        ships1[0] = new Ship2("Aircraft Carrier", new Field[5] , false, false, false );
        ships1[1] = new Ship2("Battleship", new Field[4], false, false, false );
        ships1[2] = new Ship2("Submarine", new Field[3], false, false, false );
        ships1[3] = new Ship2("Cruiser", new Field[3], false, false, false );
        ships1[4] = new Ship2("Destroyer", new Field[2], false, false, false );

        Player player1 = new Player(board1, ships1);

        player1.board.printBoard(player1.board);

        board1.placeShips(ships1, board1);

        System.out.println("Player 2, place your ships to the game field\n");

        Board board2 = new Board();
        Ship2[] ships2 = new Ship2[5];
        ships2[0] = new Ship2("Aircraft Carrier", new Field[5] , false, false, false );
        ships2[1] = new Ship2("Battleship", new Field[4], false, false, false );
        ships2[2] = new Ship2("Submarine", new Field[3], false, false, false );
        ships2[3] = new Ship2("Cruiser", new Field[3], false, false, false );
        ships2[4] = new Ship2("Destroyer", new Field[2], false, false, false );

        Player player2 = new Player(board2, ships2);

        player2.board.printBoard(player2.board);

        board2.placeShips(ships2, board2);


//        System.out.println("\nPress Enter and pass the move to another player");
//        Scanner scanner2 = new Scanner(System.in);
//        System.out.println("...");
//        scanner2.nextLine();
//        scanner2.close();

        Game game = new Game();

        game.play(player1, player2);

        //board1.game(board1, ships1);

    }
}
