package com.example.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);
       // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });




        // Find the View that shows the numbers category
        TextView family = (TextView) findViewById(R.id.family);
        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(numbersIntent);
            }
        });




        // Find the View that shows the numbers category
        TextView phrases = (TextView) findViewById(R.id.phrases);
        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(numbersIntent);
            }
        });





        // Find the View that shows the numbers category
        TextView colors = (TextView) findViewById(R.id.colors);
        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(numbersIntent);
            }
        });



    }
/*  Other easier way to do this but I don't know why Udemy didn't use this
    public void openNums(View view){

        Intent i = new Intent(this, NumbersActivity.class);
        startActivity(i);

    }*/
}
