/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author Taylor
 */
public class Tile {
    
    private int teamID;
    private int index;
    private boolean king = false;
    
    public Tile (int teamID, int index) {
        this.teamID = teamID;
        this.index = index;
    }
    
    public Tile (int teamID, int index, boolean king) {
        this.teamID = teamID;
        this.index = index;
        this.king = king;
    }
    
    public void setKing () {
        king = true;
    }
    
    public boolean getKing () {
        return king;
    }
    
    public int getTeam () {
        return teamID;
    }
    
    public int getIndex () {
        return index;
    }
    
    public void setIndex (int index) {
        this.index = index;
    }
    
}
