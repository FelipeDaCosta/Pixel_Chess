package com.whatever.chess.pieces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.whatever.chess.board.Board;
import com.whatever.chess.board.Position;

import java.util.ArrayList;

import javafx.geometry.Pos;

/**
 * Created by felipecosta on 8/21/16.
 */
public class Queen extends Piece {

    public Queen(Position pos, Board board, Sprite pieceSprite, boolean color) {
        super(pos, board, pieceSprite, color);
    }

    @Override
    public ArrayList<Position> possiblePositions() {
        ArrayList<Position> possiblePos = new ArrayList<Position>();
        // Creating these variables locally so we dont have to keep calling methods
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        Board board = this.getBoard();
        // Position being currently analysed
        Position curPos;

        // --- Diagonal ---

        // Upper Right
        for(int i = x + 1,j = y + 1; i < 8 && j < 8 ; i++,j++){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece form a diff color
            if(board.getPieceinSquare(curPos) != null ){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        // Upper Left
        for(int i = x - 1, j = y + 1; i >= 0 && j < 8; i--,j++){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece
            if(board.getPieceinSquare(curPos) != null){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        // Lower Right
        for(int i = x + 1, j = y - 1; i < 8 && j >= 0; i++,j--){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece
            if(board.getPieceinSquare(curPos) != null){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }

        for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--,j--){
            curPos = new Position(i,j);
            possiblePos.add(curPos);
            // Found a piece
            if(board.getPieceinSquare(curPos) != null ){
                if(this.getColor() == board.getPieceinSquare(curPos).getColor()){
                    possiblePos.remove(curPos); // If the piece found is of the same color
                }
                break;
            }
        }


        // --- Horizontal & Vertical ---

        // Forward
        for(int i = y + 1; i < 8;i++){
            curPos = new Position(x,i);
            System.out.println("Forward");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){ // Found a piece
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        // Backward
        for(int i = 1; y - i  >= 0;i++){
            curPos = new Position(x,y - i);
            System.out.println("backward");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        // Horizontal moves

        // Right
        for(int i = x + 1; i < 8; i++) {
            curPos = new Position(i,y);
            System.out.println("Right");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        // Left
        for(int i = x - 1; i >= 0;i--) {
            curPos = new Position(i,y);
            System.out.println("left");
            possiblePos.add(curPos); // curPos was assigned to the tested position in the for condition
            if(board.getPieceinSquare(curPos) != null){
                if(board.getPieceinSquare(curPos).getColor() == this.getColor()){
                    possiblePos.remove(curPos);
                }
                break;
            }
        }

        return possiblePos;
    }
}
