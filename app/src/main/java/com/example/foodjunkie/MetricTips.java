package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.*;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MetricTips extends AppCompatActivity {
    private Button TspToTbs, TbsToTsp, CupToOz, OzToCup;
    private Button ButToTbs, ButToCup, home1;
    private TextView unit_Output, unit1, unit2;
    private EditText unit_Input;
    double unit_Output0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_tips);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }


        TspToTbs = findViewById(R.id.btn_TspToTbs2);
        TbsToTsp = findViewById(R.id.btn_TbsToTsp2);
        CupToOz = findViewById(R.id.btn_CupToOz2);
        OzToCup = findViewById(R.id.btn_OzToCup2);
        ButToTbs = findViewById(R.id.btn_ButToTbs2);
        ButToCup = findViewById(R.id.btn_ButToCup2);
        unit_Output = findViewById(R.id.unit_Output2);
        unit_Input = findViewById(R.id.unit_input2);
        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);


        unit_Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = unit_Input.getText().toString();

                // Check if inputText is empty or null
                if (TextUtils.isEmpty(inputText)) {
                    String emptyOutput = ("");
                    String Result = ("Result: ");
                    unit_Output.setText(Result);
                    unit1.setText(emptyOutput);
                    unit2.setText(emptyOutput);
                    return; // Exit the onClick method if inputText is empty
                }
            }
        });


        TspToTbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double unit = Double.parseDouble(unit_Input.getText().toString());

                    unit_Output0 = (unit / 3);
                    String formattedResult = String.format("%.2f", unit_Output0);
                    unit_Output.setText(formattedResult);
                    String Tsp = ("Tsp");
                    unit1.setText(Tsp);
                    String Tbs = ("Tbs");
                    unit2.setText(Tbs);

                    String textEmpty = unit_Input.getText().toString();
                    if (textEmpty.equals("")) {
                        String EmptyOutput = ("");
                        String Result = ("Result: ");
                        unit_Output.setText(Result);
                        unit1.setText(EmptyOutput);
                        unit2.setText(EmptyOutput);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception here
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TbsToTsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    double unit = Double.parseDouble(unit_Input.getText().toString());

                    unit_Output0 = (unit * 3);

                    String formattedResult = String.format("%.2f", unit_Output0);
                    unit_Output.setText(formattedResult);
                    String Tbs = ("Tbs");
                    unit1.setText(Tbs);
                    String Tsp = ("Tsp");
                    unit2.setText(Tsp);

                    String textEmpty = unit_Input.getText().toString();
                    if (textEmpty.equals("")) {
                        String EmptyOutput = ("");
                        String Result = ("Result: ");
                        unit_Output.setText(Result);
                        unit1.setText(EmptyOutput);
                        unit2.setText(EmptyOutput);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception here
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });
        CupToOz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double unit = Double.parseDouble(unit_Input.getText().toString());

                    unit_Output0 = (unit * 8);
                    String formattedResult = String.format("%.2f", unit_Output0);
                    unit_Output.setText(formattedResult);
                    String Cup = ("Cup");
                    unit1.setText(Cup);
                    String Oz = ("Oz");
                    unit2.setText(Oz);

                    String textEmpty = unit_Input.getText().toString();
                    if (textEmpty.equals("")) {
                        String EmptyOutput = ("");
                        String Result = ("Result: ");
                        unit_Output.setText(Result);
                        unit1.setText(EmptyOutput);
                        unit2.setText(EmptyOutput);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception here
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        OzToCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    double unit = Double.parseDouble(unit_Input.getText().toString());

                    unit_Output0 = (unit / 8);
                    String formattedResult = String.format("%.2f", unit_Output0);
                    unit_Output.setText(formattedResult);
                    String Oz = ("Oz");
                    unit1.setText(Oz);
                    String Cup = ("Cup");
                    unit2.setText(Cup);

                    String textEmpty = unit_Input.getText().toString();
                    if (textEmpty.equals("")) {
                        String EmptyOutput = ("");
                        String Result = ("Result: ");
                        unit_Output.setText(Result);
                        unit1.setText(EmptyOutput);
                        unit2.setText(EmptyOutput);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception here
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ButToTbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double unit = Double.parseDouble(unit_Input.getText().toString());

                    unit_Output0 = (unit * 8);
                    String formattedResult = String.format("%.2f", unit_Output0);
                    unit_Output.setText(formattedResult);
                    String Butter = ("Butter");
                    unit1.setText(Butter);
                    String Tbs = ("Tbs");
                    unit2.setText(Tbs);

                    String textEmpty = unit_Input.getText().toString();
                    if (textEmpty.equals("")) {
                        String EmptyOutput = ("");
                        String Result = ("Result: ");
                        unit_Output.setText(Result);
                        unit1.setText(EmptyOutput);
                        unit2.setText(EmptyOutput);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception here
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ButToCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double unit = Double.parseDouble(unit_Input.getText().toString());

                    unit_Output0 = (unit / 2);
                    String formattedResult = String.format("%.2f", unit_Output0);
                    unit_Output.setText(formattedResult);
                    String Butter = ("Butter");
                    unit1.setText(Butter);
                    String Cup = ("Cup");
                    unit2.setText(Cup);

                    String textEmpty = unit_Input.getText().toString();
                    if (textEmpty.equals("")) {
                        String EmptyOutput = ("");
                        String Result = ("Result: ");
                        unit_Output.setText(Result);
                        unit1.setText(EmptyOutput);
                        unit2.setText(EmptyOutput);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception here
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // navigate back to the previous fragment
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
