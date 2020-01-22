/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Scanner;

/**
 *
 * @author Kevin-Notebook
 */
public class MineSweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int x, y, diff, row, col, mines;
        boolean playAgain = true;
        Board board;
        String action;
        Scanner scanner = new Scanner(System.in);

        while (playAgain) {
            System.out.print("Difficulty? (1-3) ");
            diff = scanner.nextInt();

            if (diff == 1) {
                row = 9;
                col = 9;
                mines = 10;
            } else if (diff == 2) {
                row = 16;
                col = 16;
                mines = 40;
            } else if (diff == 3) {
                row = 16;
                col = 30;
                mines = 99;
            } else {
                System.out.print("Custom: ");
                row = scanner.nextInt();
                col = scanner.nextInt();
                mines = scanner.nextInt();
            }

            board = new Board(row, col, mines);
            board.setUp();
            board.display(false);

            boolean lose = false, win = false;

            while (!lose && !win) {
                action = scanner.next();
                x = scanner.nextInt();
                y = scanner.nextInt();

                if (action.equals("open")) {
                    lose = !board.open(x, y);
                    win = board.checkWin();
                } else if (action.equals("flag")) {
                    board.flag(x, y);
                }

                if (!win) {
                    board.display(lose);
                }

                if (lose) {
                    System.out.println("You Lose!");
                } else if (win) {
                    board.flagRest();
                    board.display(lose);
                    System.out.println("You Win!");
                }
            }

            System.out.println();
            System.out.print("Play again? (y/n) ");
            action = scanner.next();

            if (action.toLowerCase().equals("n")) {
                playAgain = false;
            }
        }
    }
    
    /*public static void executeCommands(String[] commands) {
        for (String command : commands) {
            String[] items = command.split(" ");
            String action = items[0];
            int x = Integer.parseInt(items[1]);
            int y = Integer.parseInt(items[2]);
            
            if (action.equals("open")) {
                x.open;
            } else if (action.equals("flag")) {
                
            }
        }
    }*/
}
