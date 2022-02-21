/**
 * Made By: Emily Balboni
 * Professor Ruby
 * SER 210 Assignment 1 Part 2
 * 2/20/22
 */
package edu.quinnipiac.ser210.assignment1part2;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @date 2/12/2022
 *
 * Modified by Emily Balboni
 * Professor Ruby
 * SER 210 Assignment 1 Part 2
 * February 20th 2021
 */
public class FourInARow implements IGame {

    // The game board and the game status
    private static final int ROWS = 6, COLS = 6; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array

    /**
     * clear board and set current player
     */
    @Override
    public void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

        /**
         * Sets move
         */
    @Override
    public void setMove(int player, int location) {
        // TODO Auto-generated method stub
        //fix later
        int col = location % 6;
        int row = location /6;
        board[row][col]=player;
        //conditional to check to see if spot is empty before placing player at location
        //calls get computer move until a valid spot is generated
        //notifies user when they choose a spot that's already been taken
        /*
          if(player == IGame.RED && board[row][col] != EMPTY){
            do{
                location = getComputerMove();
                row = location % 6;
                col = location / 6;
            }while(board[row][col] != EMPTY);
         */

    }

    /**
     * getComputerMove() generates a computer move and returns a location
     */

    @Override
    public int getComputerMove() {
        // TODO Auto-generated method stub
        int compRow = -1;
        int compCol = -1;

        //uses random
        int computerMove = (int)(Math.random() * 35) ;
        if(computerMove != 0){
            computerMove = computerMove - 1;
        }
        return computerMove;
    }


    //checkForWinner
	/**
		Method checks to see if there is four in a row. Checks the four across pattern of horizontally, vertically and diagnaly.
		Method returns the color of the player who has four in a row otherwise it returns PLAYING to let the player know the game is
		not over.
	 */
    @Override
    public int checkForWinner() {
        // TODO Auto-generated method stub
        int count = 0;
        boolean emptySpots = false;
        for (int k = 0; k < board.length; k++) {
            for (int j = 0; j < board[1].length; j++) {
                if (board[k][j] != EMPTY) {
                    for (int h = 0; h < 4; h++) {
                        if(((j+h) < board[k].length) && (board[k][j] == board[k][j+h])) {
                            count++;
                            if(count == 4){
                                return board[k][j];
                            }
                        }
                    }
                    count = 0;
                    for(int h = 0; h < 4; h++){
                        if( ((k+h) < board.length) && (board[k][j] == board[k+h][j])) {
                            count++;
                            if(count == 4){
                                return board[k][j];
                            }
                        }
                    }
                    count = 0;
                    for(int h = 0; h < 4; h++){
                        if(((k+h) < board.length ) && ((j+h) < board[k].length) && (board[k][j] == board[k+h][j+h])) {
                            count++;
                            if(count == 4){
                                return board[k][j];
                            }
                        }
                    }
                    count = 0;
                    for(int h = 0; h < 4; h++){
                        if(((k+h) < board.length) && ( ((j-h) >= 0) && (board[k][j] == board[k+h][j-h]))) {
                            count++;
                            if(count == 4){
                                return board[k][j];
                            }
                        }
                    }
                    count = 0;
                }else{
                    emptySpots = true;
                }
            }
        }
        //if there are empty spots left on the grid and no one has won
        if(emptySpots && count != 4){
            return PLAYING;
        }
        return PLAYING;
    }

}
