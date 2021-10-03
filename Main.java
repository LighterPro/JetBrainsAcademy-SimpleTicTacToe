package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printGreeting();
        char[][] field = {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
        char currentSymbol = 'X'; //The current symbol, which shows whose turn it is now.
                                // Maybe 'X' or '0'. 'X' starts the game, so goes first.
        printField(field);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Gamer '%c' move. ", currentSymbol);
            System.out.print("Enter the coordinates (two numbers separated by a space): ");

            String[] inputCoordinates = scanner.nextLine().split("\\s+");
            if (inputCoordinates.length != 2) {
                System.out.println("You should enter two numbers separated by a space!");
                continue;
            }
            String yCoordString = inputCoordinates[0];
            String xCoordString = inputCoordinates[1];

            if (!isInteger(yCoordString) || !isInteger(xCoordString)) {
                System.out.println("You should enter two numbers separated by a space!");
                continue;
            }

            int yCoordinate = Integer.parseInt(yCoordString);
            int xCoordinate = Integer.parseInt(xCoordString);

            if (yCoordinate < 1 | yCoordinate > 3 | xCoordinate < 1 | xCoordinate > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (field[yCoordinate - 1][xCoordinate - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            field[yCoordinate - 1][xCoordinate - 1] = currentSymbol;
            printField(field);
            currentSymbol = currentSymbol == 'X' ? '0' : 'X';
            if (checkEndGame(field)) {
                break;
            }
        }
    }

    static boolean horizontalWin(char[][] array, char c) {
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            if (array[i][0] == c & array[i][0] == array[i][1] & array[i][1] == array[i][2]) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    static boolean verticalWin(char[][] array, char c) {
        boolean flag = false;
        for (int j = 0; j < 3; j++) {
            if (array[0][j] == c & array[0][j] == array[1][j] & array[1][j] == array[2][j]) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    static boolean diagonalWin(char[][] array, char c) {
        boolean flag = false;
        if (array[0][0] == c & array[0][0] == array[1][1] & array[1][1] == array[2][2]) {
            flag = true;
        }
        if (array[0][2] == c & array[0][2] == array[1][1] & array[1][1] == array[2][0]) {
            flag = true;
        }
        return flag;
    }

    static boolean win(char[][] array, char c) {
        return verticalWin(array, c) | horizontalWin(array, c) | diagonalWin(array, c);
    }

    static void printField(char[][] field) {  //Print game field

        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf("%c ", field[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static boolean checkEndGame(char[][] field) {
        if (win(field, 'X') & !win(field, '0')) {
            System.out.println("X wins");
            return true;
        }
        if (!win(field, 'X') & win(field, '0')) {
            System.out.println("0 wins");
            return true;
        }
        boolean existEmptyCells = false;
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == '_') {
                    existEmptyCells = true;
                }
            }
        }
        if (existEmptyCells) {
            return false;
        } else {
            System.out.println("Draw");
            return true;
        }
    }

    static boolean isInteger(String s) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static void printGreeting() {
        System.out.println("\n=====================================================================================");
        System.out.println("Hello, my friend! " +
                "It's Tic-Tac-Toe game.\n" +
                "The players take turns putting signs on the empty cells of the 3 × 3 field.\n" +
                "One player always puts crosses 'X', the other is always zeroes '0'.\n" +
                "The first player to line up his 3 signs vertically, horizontally or diagonally wins.\n" +
                "The first move is made by the player placing crosses 'X'.\n\n" +

                "In order to make a move, enter the coordinates.\n" +
                "Coordinates - TWO NUMBERS SEPARATED BY A SPACE.\n" +
                "Coordinates start at the top left corner.\n" +
                "The first digit is the Y-axis, the second digit is the X-axis.\n" +
                "Take a look at an example:\n" +
                "---------\t\t\t\t\t\t\t\t\t\t \t\t---------\n" +
                "| _ _ _ |\t\t\t\t\t\t\t\t\t\t \t\t| _ _ _ |\n" +
                "| _ _ _ |\t=>\t  Enter the coordinates: 3 2  \t=> \t\t| _ _ _ |\n" +
                "| _ _ _ |\t\t\t\t\t\t\t\t\t\t\t\t| _ X _ |\n" +
                "---------\t\t\t\t\t\t\t\t\t\t\t\t---------");
        System.out.println("=====================================================================================\n");
        System.out.println("Let's start the game! Good luck!\n");
    }
}


