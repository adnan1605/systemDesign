package lld.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class PlayingBoard {
    public int size;
    public PlayingPiece[][]boards;
    public PlayingBoard(int size){
        this.size=size;
        this.boards=new PlayingPiece[size][size];
    }

    public List<Position> getFreeSpace(){
        List<Position>freeSpace=new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(boards[i][j]==null){
                    freeSpace.add(new Position(i,j));
                }
            }
        }
        return freeSpace;
    }

    public boolean addPiece(int row, int col,PlayingPiece playingPiece){
        if(row>=size || col>=size)
            return false;

        if(boards[row][col]!=null)
            return false;

        boards[row][col]=playingPiece;
        return true;
    }

    public void printBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boards[i][j] != null) {
                    System.out.print(boards[i][j].pieceType.name() +"   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }


}
