/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;

/**
 *
 * @author Kevin-Notebook
 */
public class Board {

    private Square[][] squares;
    private int row;
    private int col;
    private int mines;
    private int squaresLeft;

    public Board(int row, int col, int mines) {
        squares = new Square[row][col];
        this.row = row;
        this.col = col;
        this.mines = mines;
        this.squaresLeft = row * col;

        for (int i1 = 0; i1 < row; i1++) {
            for (int i2 = 0; i2 < col; i2++) {
                squares[i1][i2] = new Square();
            }
        }
    }

    public void setUp() {
        Random random = new Random();
        int x, y;
        for (int i = 0; i < mines; i++) {
            x = random.nextInt(col);
            y = random.nextInt(row);

            if (squares[y][x].checkMine()) {
                i--;
                continue;
            }

            squares[y][x].setMine(true);

            for (int i1 = y - 1; i1 <= y + 1; i1++) {
                for (int i2 = x - 1; i2 <= x + 1; i2++) {
                    if (checkValidPos(i2, i1)) {
                        squares[i1][i2].increment();
                    }
                }
            }
        }
    }

    public void display(boolean lose) {
        System.out.print("  ");
        if (col > 9) {
            if (row >= 10) {
                System.out.print(" ");
            }
            for (int i = 0; i < col; i++) {
                System.out.print((i / 10 == 0 ? " " : i / 10) + " ");
            }
            System.out.println();
        }
        if (row >= 10) {
            System.out.print("   ");
        }
        for (int i = 0; i < col; i++) {
            System.out.print(i % 10 + " ");
        }

        System.out.println();

        for (int i1 = 0; i1 < row; i1++) {
            System.out.print(i1 + " ");
            if (i1 < 10 && row >= 10) {
                System.out.print(" ");
            }
            for (int i2 = 0; i2 < col; i2++) {
                if (squares[i1][i2].checkIfOpen()) {
                    if (squares[i1][i2].checkMine()) {
                        System.out.print("*");
                    } else {
                        System.out.print(squares[i1][i2].getNumSurrMines());
                    }
                } else {
                    if (squares[i1][i2].checkFlag()) {
                        if (lose && !squares[i1][i2].checkMine()) {
                            System.out.print("X");
                        } else {
                            System.out.print("F");
                        }
                    } else {
                        System.out.print("_");
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("Squares Left: " + squaresLeft);
    }

    public boolean open(int x, int y) {
        if (squares[y][x].checkFlag() || squares[y][x].checkIfOpen()) {
            return true;
        }
        
        squares[y][x].openSquare();

        if (squares[y][x].checkMine()) {
            for (int i1 = 0; i1 < row; i1++) {
                for (int i2 = 0; i2 < col; i2++) {
                    if (squares[i1][i2].checkMine() && !squares[i1][i2].checkFlag()) {
                        squares[i1][i2].openSquare();
                    }
                }
            }
            return false;
        } else {
            squaresLeft--;
            if (squares[y][x].getNumSurrMines() == 0) {
                for (int i1 = y - 1; i1 <= y + 1; i1++) {
                    for (int i2 = x - 1; i2 <= x + 1; i2++) {
                        if (checkValidPos(i2, i1) && !squares[i1][i2].checkIfOpen() && !squares[i1][i2].checkFlag()) {
                            open(i2, i1);
                        }
                    }
                }
            }

            return true;
        }
    }
    
    public void flag(int x, int y) {
        if (squares[y][x].checkIfOpen()) {
            return;
        }
        
        squares[y][x].setFlag(!squares[y][x].checkFlag());
    }
    
    public boolean checkWin() {
        return squaresLeft == mines;
    }
    
    public void flagRest() {
        for (int i1 = 0; i1 < row; i1++) {
            for (int i2 = 0; i2 < col; i2++) {
                if (!squares[i1][i2].checkIfOpen()) {
                    squares[i1][i2].setFlag(true);
                }
            }
        }
    }

    public boolean checkValidPos(int x, int y) {
        return (y >= 0 && y < row) && (x >= 0 && x < col);
    }
}
