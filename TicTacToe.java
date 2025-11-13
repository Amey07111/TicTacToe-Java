import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {

    public static void main(String[] args) {
        // 1. Initialize the 3x3 board with empty spaces
        char[][] board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        char currentPlayer = 'X';
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("To play, enter coordinates like: 0 1");

        // 2. The Main Game Loop
        while (!gameOver) {
            printBoard(board);
            System.out.println("Player " + currentPlayer + ", enter row (0-2) and column (0-2): ");
            
            int row = -1;
            int col = -1;

            // --- ERROR HANDLING START ---
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Invalid Input! Please enter numbers only (e.g., 0 1).");
                scanner.nextLine(); // Clear the "bad" input from memory
                continue; // Restart the loop to ask again
            }
            // --- ERROR HANDLING END ---

            // 3. Validating the move
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                // Place the token
                board[row][col] = currentPlayer;
                
                // Check if this move won the game
                gameOver = haveWon(board, currentPlayer);
                
                if (gameOver) {
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " has won! 🎉");
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("It's a draw! 🤝");
                    gameOver = true;
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("❌ Invalid move (space taken or out of bounds). Try again!");
            }
        }
        scanner.close();
    }

    // Helper method to print the board
    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Helper method to check win conditions
    public static boolean haveWon(char[][] board, char player) {
        // Check Rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        // Check Columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // Check Diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Helper method to check for draw
    public static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}