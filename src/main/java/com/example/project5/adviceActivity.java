package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * AdviceActivity class is the second window in the Android
 * application. This activity displays the proper advice based
 * on the user's calculated BMI.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */

public class adviceActivity extends AppCompatActivity {

    //App components
    private TextView results;
    private ImageView adviceImage;

    double resultBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        results = findViewById(R.id.results);
        adviceImage = findViewById(R.id.adviceImage);

        //Obtain the result BMI from the main activity
        resultBmi = getIntent().getExtras().getDouble("resultBmi");
        adviceImage.setImageResource(R.drawable.normal);

        //Different values for BMI results
        double underWeightValue = 18.5;
        double normalValue = 24.9;
        double overweightValue = 25;
        double obeseValue = 29.9;

        //Compare BMI results to the proper advice
        if(resultBmi < underWeightValue) {
            results.setText("UnderWeight");
            adviceImage.setImageResource(R.drawable.underweight);
        }
        else if (resultBmi >= underWeightValue && resultBmi <= normalValue) {
            results.setText("Normal");
            adviceImage.setImageResource(R.drawable.normal);
        }
        else if (resultBmi >= overweightValue && resultBmi <= obeseValue) {
            results.setText("Overweight");
            adviceImage.setImageResource(R.drawable.overweight);
        }
        else {
            results.setText("Obese");
            adviceImage.setImageResource(R.drawable.obese);
        }
    }
}