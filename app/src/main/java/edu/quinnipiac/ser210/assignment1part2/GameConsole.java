/**
 * Made By: Emily Balboni
 * Professor Ruby
 * SER 210 Assignment 1 Part 2
 * 2/20/22
 */
package edu.quinnipiac.ser210.assignment1part2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GameConsole extends AppCompatActivity {
    private List<Button> computer_Move, person_Move,open_spots;
    private FourInARow fourInARow;
    private Context context;
    private int computer = -1, checkWinnerOne = -1, checkWinnerTwo=-1;
    private int currentState = 4;
    private static final int[] BUTTON_IDS ={
            R.id.buttonOne,
            R.id.buttonTwo,
            R.id.buttonThree,
            R.id.buttonFour,
            R.id.buttonFive,
            R.id.buttonSix,
            R.id.buttonSeven,
            R.id.buttonEight,
            R.id.buttonNine,
            R.id.buttonTen,
            R.id.buttonEleven,
            R.id.buttonTwelve,
            R.id.buttonThirteen,
            R.id.buttonFourteen,
            R.id.buttonFifteen,
            R.id.buttonSixteen,
            R.id.buttonSeventeen,
            R.id.buttonEighteen,
            R.id.buttonNineteen,
            R.id.buttonTwenty,
            R.id.buttonTwentyOne,
            R.id.buttonTwentyTwo,
            R.id.buttonTwentyThree,
            R.id.buttonTwentyFour,
            R.id.buttonTwentyFive,
            R.id.buttonTwentySix,
            R.id.buttonTwentySeven,
            R.id.buttonTwentyEight,
            R.id.buttonTwentyNine,
            R.id.buttonThirty,
            R.id.buttonThirtyOne,
            R.id.buttonThirtyTwo,
            R.id.buttonThirtyThree,
            R.id.buttonThirtyFour,
            R.id.buttonThirtyFive,
            R.id.buttonThirtyFive
    };
    FloatingActionButton reset;
    String name = "Default";

    /**
     * Saving state of computer and person moves
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Integer> savedPlayerMoves = new ArrayList<>();
        for(Button b: person_Move){
            savedPlayerMoves.add(b.getId());
        }
        outState.putIntegerArrayList("saved player moves", savedPlayerMoves);

        ArrayList<Integer> savecomputerMoves = new ArrayList<>();
        for(Button c: computer_Move){
            savecomputerMoves.add(c.getId());
        }
        outState.putIntegerArrayList("saved computer moves", savecomputerMoves);
    }

    /**
     * Initializes all needed variables and open_spots array
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        context = getApplicationContext();
        fourInARow = new FourInARow();
        person_Move = new ArrayList<Button>();
        computer_Move = new ArrayList<Button>();
        open_spots = new ArrayList<Button>();
        name = getIntent().getExtras().getString("name");

        /**
         * Creates an array of open spots on app
         */
        for(int id : BUTTON_IDS){
            Button button = (Button) findViewById(id);
            open_spots.add(button);
        }
        Toast player = Toast.makeText(context, name+ " turn",Toast.LENGTH_SHORT);
        player.show();


        /**
         * resets the game
         */
        //reset.setEnabled(false);
        reset = findViewById(R.id.floatingActionButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourInARow.clearBoard();
                resetButtons();
               while(person_Move.size()>0){
                   open_spots.add(person_Move.remove(0));
               }
               while(computer_Move.size() >0){
                   open_spots.add(computer_Move.remove(0));
               }
               currentState = -1;
               Toast toast = Toast.makeText(context,"Game has been reset",Toast.LENGTH_LONG);
               toast.show();
            }
        });

        /**
         * saving positions that player and computer have already marekd off
         */
        if(savedInstanceState != null){
            for(int i: savedInstanceState.getIntegerArrayList("saved player moves")){
                Button button = findViewById(i);
                int index = getIndexID(button);
                button.setBackgroundColor(Color.BLUE);
                button.setEnabled(false);
                open_spots.remove(button);
                person_Move.add(button);
                fourInARow.setMove(IGame.BLUE,index);
            };

            for(int k: savedInstanceState.getIntegerArrayList("saved computer moves")){
                Button buttonTwo = findViewById(k);
                int index2 = getIndexID(buttonTwo);
                buttonTwo.setBackgroundColor(Color.RED);
                buttonTwo.setEnabled(false);
                open_spots.remove(buttonTwo);
                computer_Move.add(buttonTwo);
                fourInARow.setMove(IGame.RED,index2);
            }
        }

    }

    /**
     * Generates valid computer move
     */
    public void computerMove(){
        do{
            computer = fourInARow.getComputerMove();
        }while(!open_spots.contains(findViewById(BUTTON_IDS[computer])));
        int id = BUTTON_IDS[computer];
        Button b = (Button) (findViewById(id));
        b.setBackgroundColor(Color.RED);
        fourInARow.setMove(IGame.RED, computer);
        computer_Move.add(b);
        open_spots.remove(b);
        checkWinnerTwo = fourInARow.checkForWinner();
        updatePlayer();
        Toast play = Toast.makeText(context, name+ " turn",Toast.LENGTH_SHORT);
        play.show();
    }

    /**
     * When user clicks on button, this method updates game state and makes block blue
     * @param view
     */
    public void buttonClicked(View view){
        if(open_spots.contains(view)){
           view.setBackgroundColor(Color.BLUE);
           int num = getIndexID(view);
          fourInARow.setMove(IGame.BLUE,num);
          person_Move.add((Button)view);
          open_spots.remove((Button)view);
        }
        checkWinnerOne = fourInARow.checkForWinner();
        updatePlayer();
        if(checkWinnerOne == IGame.PLAYING){
            computerMove();
            Toast t = Toast.makeText(context,"Computer move",Toast.LENGTH_SHORT);
            t.show();
        }
    }

    /**
     * Returns index of button ID
     * @param view
     * @return
     */
    public int getIndexID(View view){
        for(int i = 0; i < BUTTON_IDS.length; i++){
            if(view.getId()==(BUTTON_IDS[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * Disables all buttons
     */
    public void disableButtons(){
        for(int j : BUTTON_IDS){
            findViewById(j).setEnabled(false);
        }
        Toast t = Toast.makeText(context,"Game over!",Toast.LENGTH_SHORT);
        t.show();
    }

    public void resetButtons(){
        for(int k : BUTTON_IDS){
            findViewById(k).setEnabled(true);
            findViewById(k).setBackgroundColor(getResources().getColor(R.color.teal_700));
        }
    }

    /**
     * Checks to see who won game
     */
    public void updatePlayer(){
        if(checkWinnerTwo == IGame.RED){
            currentState = IGame.RED_WON;
            Toast t2 = Toast.makeText(context,"Computer Won",Toast.LENGTH_SHORT);
            t2.show();
            disableButtons();
            //reset.setEnabled(true);
        }else if(checkWinnerOne == IGame.BLUE){
            currentState = IGame.BLUE_WON;
            Toast t3 = Toast.makeText(context,name + "Won",Toast.LENGTH_SHORT);
            t3.show();
            disableButtons();
            //reset.setEnabled(true);
        }else if(checkWinnerOne == IGame.RED && checkWinnerTwo == IGame.BLUE){
            currentState = IGame.TIE;
            Toast t4 = Toast.makeText(context,"Tie",Toast.LENGTH_SHORT);
            t4.show();
            disableButtons();
            //reset.setEnabled(true);
        }else{
            currentState = IGame.PLAYING;
        }
    }



}
