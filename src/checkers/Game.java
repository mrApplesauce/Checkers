/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.Random;

/**
 *
 * @author Taylor
 */
public class Game {
    
    private Board board;
    private int currentTeam;
    
    public Game () {
        
        //initialize the checkers board (default: 10*10 board, 20 pieces per team)
        board = new Board(10, 20);
        board.printBoard();
        
        //choose a starting team
        Random rnd = new Random();
        currentTeam = rnd.nextInt(2);
        
    }
    
    public int makeMove (int moveFrom, int moveTo) {
        int moveMade = board.makeMove(currentTeam, moveFrom, moveTo);
        if (moveMade == 1)
            currentTeam = 1 - currentTeam;
        
        board.printBoard();
        return moveMade;
    }
    
    public int getCurrentTeam () {
        return currentTeam;
    }
    
}
