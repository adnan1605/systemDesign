package lld.tictactoe;

import lld.tictactoe.models.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    PlayingBoard board;

    public TicTacToeGame() {
        initializeGame();
    }

    public void initializeGame() {
        players = new LinkedList<>();

        PlayingPieceX playingPieceX = new PlayingPieceX();
        Player player1 = new Player("Player1", playingPieceX);

        PlayingPieceO playingPieceO = new PlayingPieceO();
        Player player2 = new Player("Player2", playingPieceO);

        players.add(player1);
        players.add(player2);

        board = new PlayingBoard(3);
    }


    public String startGame() {
        boolean noWinner = true;

        while (noWinner) {

            board.printBoard();
            List<Position> freeSpace = board.getFreeSpace();
            Player playerTurn = players.removeFirst();

            if (freeSpace.isEmpty()) {
                noWinner = false;
                continue;
            }
            // input
            System.out.println("Player : " + playerTurn.getName() + " Enter Row , column : ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String value[] = input.split(",");
            int row = Integer.parseInt(value[0]);
            int col = Integer.parseInt(value[1]);

            boolean pieceAddedSuccessFully = board.addPiece(row, col, playerTurn.getPlayingPiece());

            if (!pieceAddedSuccessFully) {
                System.out.println("Incorrect position, enter valid position");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isThereWinner(row, col, playerTurn.getPlayingPiece().pieceType);
            if(winner)
                return "Game won by "+playerTurn.getName();


        }
        return "Tied";
    }

    boolean isThereWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // check row
        for (int j = 0; j < board.size; j++) {
            if (board.boards[row][j] == null || board.boards[row][j].pieceType != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // colmn check

        for (int i = 0; i < board.size; i++) {
            if (board.boards[i][col] == null || board.boards[i][col].pieceType != pieceType) {
                colMatch = false;
                break;
            }
        }
        //diagonal
        for (int i = 0, j = 0; i < board.size; i++, j++) {
            if (board.boards[i][j] == null || board.boards[i][j].pieceType != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        //antidiagonal
        for (int i = 0, j = board.size - 1; i < board.size; i++, j--) {
            if (board.boards[i][j] == null || board.boards[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }
        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;

    }


}
