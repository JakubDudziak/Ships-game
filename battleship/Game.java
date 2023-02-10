package battleship;

import java.util.Scanner;

public class Game {

    public void play (Player player1, Player player2) {
        int turn = 1;
        do {

            if (turn % 2 != 0) {
                player2.board.fogOfWar(player2.board, true);
                player2.board.printBoard(player2.board);

                System.out.println("---------------------");

                player1.board.fogOfWar(player1.board, false);
                player1.board.printBoard(player1.board);

                System.out.println("\nPlayer 1, it's your turn:");

                player2.board.turn(player2.board, player2.ships2, player2);



                if (player2.hasPlayerLost() == true) break;

                System.out.println("Press Enter and pass the move to another player");
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("...");
                scanner2.nextLine();

            } else {
                player1.board.fogOfWar(player1.board, true);
                player1.board.printBoard(player1.board);

                System.out.println("---------------------");

                player2.board.fogOfWar(player2.board, false);
                player2.board.printBoard(player2.board);

                System.out.println("\nPlayer 2, it's your turn:");

                player1.board.turn(player1.board, player1.ships2, player1);
                //System.out.println(player1.hasPlayerLost());

                if (player1.hasPlayerLost() == true) break;

                System.out.println("Press Enter and pass the move to another player");
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("...");
                scanner2.nextLine();
            }


            turn++;
        } while (player1.hasPlayerLost() != true && player2.hasPlayerLost() != true);
    }
}
