package assignment1;

import java.util.Scanner;

public class MazeRunner {

    private static char[][] maze;
    private static int playerX, playerY;
    private static long startTime, endTime;
    private static int numberOfSteps;
    private static int score;
    private static int highScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char option;

        do {
            System.out.println("Main Menu:");
            System.out.println("a. Play Game");
            System.out.println("b. Instructions");
            System.out.println("c. Credits");
            System.out.println("d. High Score");
            System.out.println("e. Exit");
            System.out.print("Choose an option: ");
            option = scanner.next().charAt(0);

            // Check if the menu option input is valid (a, b, c, d, or e)
            if (isValidMenuOption(option)) {
                switch (option) {
                    case 'a':
                        startNewGame();
                        break;
                    case 'b':
                        showInstructions();
                        break;
                    case 'c':
                        showCredits();
                        break;
                    case 'd':
                        showHighScore();
                        break;
                    case 'e':
                        exitGame();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } while (option != 'e');
    }

    public static boolean isValidMenuOption(char option) {
        // Check if the menu option input is valid (a, b, c, d, or e)
        return option == 'a' || option == 'b' || option == 'c' || option == 'd' || option == 'e';
    }

    public static void initializeMaze() {
        maze = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'P', '.', '.', '.', '.', '#'},
                {'#', '.', '#', '#', '.', '#', '#'},
                {'.', '.', '.', '.', '.', '.', '#'},
                {'.', '#', '#', '.', '#', '#', '#'},
                {'#', '.', '.', '.', '.', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        playerX = 1;
        playerY = 1;
    }

    public static void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int newX, int newY) {
        return (newX >= 0 && newX < maze.length) && (newY >= 0 && newY < maze[0].length) && (maze[newX][newY] == '.' || maze[newX][newY] == 'E');
    }

    public static void movePlayer(char direction) {
        int newX = playerX, newY = playerY;

        switch (direction) {
            case 'w':
                newX--;
                break;
            case 'a':
                newY--;
                break;
            case 's':
                newX++;
                break;
            case 'd':
                newY++;
                break;
            default:
                System.out.println("Invalid command. Use w/a/s/d.");
                return;
        }

        if (isValidMove(newX, newY)) {
            maze[playerX][playerY] = '.';
            playerX = newX;
            playerY = newY;
            maze[playerX][playerY] = maze[newX][newY] == 'E' ? 'E' : 'P'; // Set to 'E' if it's an exit
            numberOfSteps++;
        } else {
            System.out.println("You hit a wall or went out of bounds. Try again.");
        }
    }

    
    public static boolean hasPlayerWon() {
        return playerX == 5 && playerY == 5;
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        startTime = System.currentTimeMillis();
        while (!hasPlayerWon()) {
            printMaze();
            System.out.print("Enter your move (W/A/S/D): ");
            char move = scanner.next().charAt(0);
            movePlayer(move);
        }
        endTime = System.currentTimeMillis();
        updateScore();
        displayResult();
    }

    public static void displayResult() {
        System.out.println("You won!");
        System.out.println("Steps taken: " + numberOfSteps);
        System.out.println("Time taken: " + (endTime - startTime) / 1000.0 + " seconds");
        System.out.println("Score: " + score);
        System.out.println("High Score: " + highScore);
    }

    public static void updateScore() {
        // Score calculation logic
        score = 1000 - numberOfSteps - (int) (endTime - startTime) / 100;
        if (score > highScore) {
            highScore = score;
        }
    }

    public static void startNewGame() {
        initializeMaze();
        numberOfSteps = 0;
        playGame();
    }

    public static void showInstructions() {
        System.out.println("Instructions:");
        System.out.println("Navigate through the maze from the starting position (P) to the exit (E).");
        System.out.println("Use W to move up, A to move left, S to move down, and D to move right.");
        System.out.println("Avoid walls (#) and reach the exit as quickly as possible!");
    }

    public static void showCredits() {
        System.out.println("Credits:");
        System.out.println("Developed by: Sarmad Sabir - 231523180");
    }

    public static void showHighScore() {
        System.out.println("High Score: " + highScore);
    }

    public static void exitGame() {
        System.out.println("Thanks for playing! Goodbye!");
    }
}
