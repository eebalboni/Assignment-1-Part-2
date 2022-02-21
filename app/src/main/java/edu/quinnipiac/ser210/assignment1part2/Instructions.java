/**
 * Made By: Emily Balboni
 * Professor Ruby
 * SER 210 Assignment 1 Part 2
 * 2/20/22
 * Instructions class, displays how to play game
 */
package edu.quinnipiac.ser210.assignment1part2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Instructions extends AppCompatActivity {
    FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
