package tictactoe;

import java.util.Scanner;

public class Main {
    private static char[][] field;
    private static Scanner scan;
    private static int x;
    private static int y;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        //System.out.print("Enter cells: ");
        //String s = scan.next();
        //char[] c = s.toCharArray();
        field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //field[i][j] = c[i * 3 + j];
                field[i][j] = ' ';
            }
        }
        int step = 1;
        char signXO;
        while (true) {
            if (step % 2 == 1) {
                signXO = 'X';
            } else {
                signXO = 'O';
            }

            printField();
            inputXY(signXO);
            printField();
            if (checkImpossiblity()) {
                System.out.println("Impossible");
                break;
            } else if (checkXWins()) {
                System.out.println("X wins");
                break;
            } else if (checkOWins()) {
                System.out.println("O wins");
                break;
            } else if (checkDraw()) {
                System.out.println("Draw");
                break;
            } else {
                System.out.println("Game not finished");
            }

            step++;
        }

    }

    private static void inputXY(char signXO) {
        do {
            System.out.print("Enter the coordinates: ");
            x = scan.nextInt();
            y = scan.nextInt();
        } while (!checkCorrectness(x, y));

        field[Math.abs(y - 3)][x - 1] = signXO;
        //printField();
    }

    private static void printField() {
        System.out.println("---------");
        for (char[] line : field) {
            System.out.print("| ");
            for (char sign : line) {
                System.out.print(sign + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean checkCorrectness(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (field[Math.abs(y - 3)][x - 1] == 'X' || field[Math.abs(y - 3)][x - 1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }
    private static boolean checkDraw() {
        int countX = 0;
        int countO = 0;
        for (char[] line : field) {
            for (char cell : line) {
                if (cell == 'X') {
                    countX++;
                } else if (cell == 'O') {
                    countO++;
                }
            }
        }
        return countO + countX == 9;
    }

    private static boolean checkOWins() {
        for (int x = 0; x < 3; x++) {
            if (field[x][0] == 'O' && field[x][1] == 'O' && field[x][2] == 'O') {
                return true;
            }
            if (field[0][x] == 'O' && field[1][x] == 'O' && field[2][x] == 'O') {
                return true;
            }
        }
        return field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O' ||
                field[2][0] == 'O' && field[1][1] == 'O' && field[0][2] == 'O';
    }

    private static boolean checkXWins() {
        for (int x = 0; x < 3; x++) {
            if (field[x][0] == 'X' && field[x][1] == 'X' && field[x][2] == 'X') {
                return true;
            }
            if (field[0][x] == 'X' && field[1][x] == 'X' && field[2][x] == 'X') {
                return true;
            }
        }
        if (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X' ||
                field[2][0] == 'X' && field[1][1] == 'X' && field[0][2] == 'X') {
            return true;
        }
        return false;
    }

    private static boolean checkImpossiblity() {
        int countX = 0;
        int countO = 0;
        for (char[] line : field) {
            for (char cell : line) {
                if (cell == 'X') {
                    countX++;
                } else if (cell == 'O') {
                    countO++;
                }
            }
        }
        if (checkOWins() && checkXWins() || Math.abs(countO - countX) > 1) {
            return true;
        }
        return false;
    }
}
