package com.sciplay.connect4;

import java.util.Scanner;

public class ConnectFourGame {

    private enum Player {
        RED_PLAYER('R', "Red"),
        BLACK_PLAYER('B', "Black");

        public final char gameIcon;
        public final String name;

        Player(char gameIcon, String name) {
            this.gameIcon = gameIcon;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[][] board = new char[6][7];

        //initialize game board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                board[row][col] = ' ';
            }
        }

        int turn = 1;
        Player currentPlayer = Player.RED_PLAYER;
        boolean foundWinner = false;

        //player play a turn
        while (!foundWinner && turn <= 42){
            boolean validMove;
            int play;
            do {
                displayGameBoard(board);

                System.out.print(currentPlayer.name + ", player, choose column for your next move: ");
                play = in.nextInt();

                //validate move
                validMove = checkMove(play,board);

            } while (!validMove);

            //drop the checker onto selected column
            for (int row = board.length - 1; row >= 0; row--) {
                if(board[row][play] == ' '){
                    board[row][play] = currentPlayer.gameIcon;
                    break;
                }
            }

            //check if a player won
            foundWinner = checkWinner(currentPlayer.gameIcon, board);

            //switch turns
            if(!foundWinner) {
                currentPlayer = (currentPlayer == Player.RED_PLAYER ? Player.BLACK_PLAYER : Player.RED_PLAYER);
                turn++;
            }

        }
        displayGameBoard(board);

        if (foundWinner) {
            System.out.println(currentPlayer.name + " Player Wins!!");
            System.out.println("Game over, please play again...");
        } else {
            System.out.println("No Winner, please play again");
        }

    }

    private static void displayGameBoard(char[][] board){
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (char[] chars : board) {
            System.out.print("|");
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(chars[col]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println();
    }

    private static boolean checkMove(int column, char[][] board){
        //checks the move - if a valid column was selected
        if (column < 0 || column > board[0].length){
            return false;
        }

        //check if column already full
        return board[0][column] == ' ';
    }

    private static boolean checkWinner(char player, char[][] board){
        //check for 4 across
        for (char[] chars : board) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (chars[col] == player &&
                        chars[col + 1] == player &&
                        chars[col + 2] == player &&
                        chars[col + 3] == player) {
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length; col++){
                if (board[row][col] == player   &&
                        board[row + 1][col] == player &&
                        board[row + 2][col] == player &&
                        board[row + 3][col] == player){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < board.length; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row - 1][col + 1] == player &&
                        board[row - 2][col + 2] == player &&
                        board[row - 3][col + 3] == player){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row + 1][col + 1] == player &&
                        board[row + 2][col + 2] == player &&
                        board[row + 3][col + 3] == player){
                    return true;
                }
            }
        }
        return false;
    }
}
