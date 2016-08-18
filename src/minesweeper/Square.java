/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Kevin-Notebook
 */
public class Square {
    private int numSurrMines;
    private boolean mine;
    private boolean open;
    private boolean flag;
    
    public Square() {
        numSurrMines = 0;
        mine = false;
        open = false;
    } 
    
    /**
     * @return the number of mines surrounding the square
     */
    public int getNumSurrMines() {
        return numSurrMines;
    }
    
    /**
     * @return checks if the current square is a mine
     */
    public boolean checkMine() {
        return mine;
    }
    
    public void setMine(boolean mine) {
        this.mine = mine;
    }
    
    /**
     * @return checks if the current square is opened
     */
    public boolean checkIfOpen() {
        return open;
    }
    
    public void openSquare() {
        this.open = true;
    }
    
    public void increment() {
        numSurrMines++;
    }
    
    public boolean checkFlag() {
        return this.flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
