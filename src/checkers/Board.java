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
public class Board {
    
    private Tile[] tiles;
    private int dimension;
    private int piecesPerTeam;
    
    public Board (int dimension, int piecesPerTeam) {
        this.dimension = dimension;
        this.piecesPerTeam = piecesPerTeam;
        
        tiles = new Tile[dimension * dimension];
        populateBoard();
    }
    
    private void populateBoard () {
        
        int size = dimension * dimension - 1;
        
        for (int i = 0; i < piecesPerTeam; i++) {
            int offset = 2 * i;
            if (i / (dimension / 2) % 2 == 0)
                offset++;
            
            tiles[offset] = new Tile(0, offset);
            tiles[size - offset] = new Tile(1, size - offset);
        }
        
    }
    
    public void printBoard () {
        
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == null)
                System.out.print("[ ]");
            else {
                if (tiles[i].getTeam() == 0) System.out.print("[O]");
                if (tiles[i].getTeam() == 1) System.out.print("[X]");
            }
            
            if ((i + 1) % dimension == 0)
                System.out.println("");
        }
        
    }
    
    public Tile getTile (int index) {
        return tiles[index];
    }
    
    public boolean makeMove (int teamID, int moveFrom, int moveTo) {
        if (getTile(moveFrom) == null)
            return false;
        if (getTile(moveFrom).getTeam() != teamID)
            return false;
        
        int arr[] = calculateMoves(getTile(moveFrom));
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == moveTo) {
                tiles[moveTo] = tiles[moveFrom];
                tiles[moveTo].setIndex(moveTo);
                tiles[moveFrom] = null;
                return true;
            }
        
        return false;
    }
    
    public int[] calculateMoves (Tile tile) {
        int[] moves = new int[4];
        
        if (tile.getTeam() == 0) {
            moves[0] = checkMove(tile.getIndex(), 9, tile.getTeam());
            moves[1] = checkMove(tile.getIndex(), 11, tile.getTeam());
        } else {
            moves[0] = checkMove(tile.getIndex(), -9, tile.getTeam());
            moves[1] = checkMove(tile.getIndex(), -11, tile.getTeam());
        }
        
        if (tile.getKing()) {
            if (tile.getTeam() == 1) {
                moves[2] = checkMove(tile.getIndex(), 9, tile.getTeam());
                moves[3] = checkMove(tile.getIndex(), 11, tile.getTeam());
            } else {
                moves[2] = checkMove(tile.getIndex(), -9, tile.getTeam());
                moves[3] = checkMove(tile.getIndex(), -11, tile.getTeam());
            }
        } else {
            moves[2] = -1;
            moves[3] = -1;
        }
        
        return moves;
        
    }
    
    public int checkMove (int index, int offset, int teamID) {        
        int size = dimension * dimension - 1;
        if ((index + offset) < 0 || (index + offset) > size)
            return -1;
        
        int row = index % 10;
        int rowOffset = (index + offset) % 10;
        if (Math.abs(row - rowOffset) != 1)
            return -1;
        
        int col = index / 10;
        int colOffset = (index + offset) / 10;
        if (Math.abs(col - colOffset) != 1)
            return -1;
        
        if (tiles[index + offset] == null) {
            return index + offset;
        } else if (tiles[index + offset].getTeam() == teamID) {
            return -1;
        } else {
            return checkMove(index + offset, offset, teamID);
        }
    }
    
    public boolean checkWin (int teamID) {
        int size = dimension * dimension - 1;
        int check = 1 - teamID;
        for (int i = 0; i < size; i++) {
            if (tiles[i].getTeam() == check)
                return false;
        }
        
        return true;
    }
    
}
