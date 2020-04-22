package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity class is the main activity in the application
 * which is first seen when initially launched. The activity
 * takes in the users credentials along with their selected unit of
 * measure. It also calculates their BMI and gets advice based on the
 * results.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */

public class MainActivity extends AppCompatActivity {

    //App components
    private Button advice;
    private Button calculate;
    private EditText enterWeight;
    private EditText enterHeight;
    private TextView resultBmi;

    private RadioGroup radioGroup;
    private RadioButton poundInch;
    private RadioButton kiloMeter;

    //Weight and Height as double values
    private double weight;
    private double height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advice = findViewById(R.id.advice);
        calculate = findViewById(R.id.calculate);
        enterWeight = findViewById(R.id.enterWeight);
        enterHeight = findViewById(R.id.enterHeight);
        resultBmi = findViewById(R.id.resultBmi);

//        enterWeight.setFocusable(false);
//        enterHeight.setFocusable(false);

        radioGroup = findViewById(R.id.radioGroup);
        poundInch = findViewById(R.id.poundInch);
        kiloMeter = findViewById(R.id.kiloMeter);

        //Listener for the button click
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate the entered inputs
                String enteredWeight = enterWeight.getText().toString();
                String enteredHeight = enterHeight.getText().toString();
                double minimum = 1.0;

                if(TextUtils.isEmpty(enteredWeight) || TextUtils.isEmpty(enteredHeight)) {
                    Toast.makeText(MainActivity.this, "Please fill empty fields", Toast.LENGTH_SHORT).show();
                }
                else if(radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please choose a metric unit", Toast.LENGTH_SHORT).show();
                }
                else if (enteredWeight.equals(".") || enteredHeight.equals(".")) {
                    Toast.makeText(MainActivity.this, ". is not valid", Toast.LENGTH_SHORT).show();
                }
                else if (Double.parseDouble(enteredWeight) < minimum) {
                    Toast.makeText(MainActivity.this, "Enter a valid weight", Toast.LENGTH_SHORT).show();
                }
                else if (Double.parseDouble(enteredHeight) < minimum) {
                    Toast.makeText(MainActivity.this, "Enter a valid height", Toast.LENGTH_SHORT).show();
                }
                else {
                    //If all cases passed then proceed
                    calculateBmi();
                }
            }
        });

        //Listener for the button click
        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(resultBmi.getText().toString())) {
                    Toast.makeText(MainActivity.this, "No calculated BMI", Toast.LENGTH_SHORT).show();
                }
                else {
                    //If all cases passed then proceed
                    openSecondActivity();
                }
            }
        });
    }

    /**
     * poundInch method changes the EditText hints and makes fields editable.
     *
     * @param v The object used to refer to a component
     */
    public void poundInch(View v) {
//        enterWeight.setFocusableInTouchMode(true);
//        enterHeight.setFocusableInTouchMode(true);
        enterWeight.setHint("Enter weight in pounds");
        enterHeight.setHint("Enter height in inches");
    }

    /**
     * kiloMeter method changes the EditText hints and makes fields editable.
     *
     * @param v The object used to refer to a component
     */
    public void kiloMeter(View v) {
//        enterWeight.setFocusableInTouchMode(true);
//        enterHeight.setFocusableInTouchMode(true);
        enterWeight.setHint("Enter weight in kilograms");
        enterHeight.setHint("Enter height in meters");
    }

    /**
     * The calculateBMI method calculates the BMI given
     * the user's credentials and the unit of measure
     * selected.
     */
    public void calculateBmi() {

        //Get the user input
        weight = Double.parseDouble(enterWeight.getText().toString());
        height = Double.parseDouble(enterHeight.getText().toString());

        //Calculated BMI
        double finalBmi = 0.0;

        //If pound/inches is selected
        if(poundInch.isChecked()) {
            finalBmi = weight * 703 / (height * height);
        }
        else if (kiloMeter.isChecked()){
            finalBmi = weight / (height * height);
        }
        //2 decimal places
        resultBmi.setText(String.format("%.2f", finalBmi));
    }

    /**
     * openSecondActivity method opens up the second activity. The second
     * activity is the advice activity. When the user returns
     * to the main activity the fields reset except the radioGroup.
     */
    public void openSecondActivity() {
        //Create a new intent to open up second activity
        Intent intent = new Intent(this, adviceActivity.class);
        double res = Double.parseDouble(resultBmi.getText().toString());
        intent.putExtra("resultBmi", res);
        startActivity(intent);

        //Clear fields for when we return to the main activity
        enterWeight.setText(null);
        enterHeight.setText(null);
        resultBmi.setText(null);
    }
}
