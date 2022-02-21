/**
 * Made By: Emily Balboni
 * Professor Ruby
 * SER 210 Assignment 1 Part 2
 * 2/20/22
 * MainActivity class, has information to switch to instructions and game
 */
package edu.quinnipiac.ser210.assignment1part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button switchToInstructions,switchToGame;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //stores player name
        name = findViewById(R.id.player_name);
        //button to go to instructions page
        switchToInstructions = findViewById(R.id.go_to_instructions);
        //button to go to game page
        switchToGame = findViewById(R.id.play_game);

       switchToGame.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onClickGame();
           }
       });
        switchToInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickInstructions();
            }
        });

    }
    //switches to instructions screen
    public void onClickInstructions(){
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
    }

    //switches to game screen
    public void onClickGame(){
        Intent in = new Intent(this, GameConsole.class);
        in.putExtra("name", name.getText().toString());
        startActivity(in);
    }
}