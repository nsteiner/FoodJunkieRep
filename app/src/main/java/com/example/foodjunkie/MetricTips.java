package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MetricTips extends AppCompatActivity {
    private Button TspToTbs, TbsToTsp, CupToOz, OzToCup;
    private Button ButToTbs, ButToCup, home1;
    private TextView unit_Output;
    private EditText unit_Input;
    double unit_Output0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_tips);


        TspToTbs = findViewById(R.id.btn_TspToTbs);
        TbsToTsp = findViewById(R.id.btn_TbsToTsp);
        CupToOz = findViewById(R.id.btn_CupToOz);
        OzToCup = findViewById(R.id.btn_OzToCup);
        ButToTbs = findViewById(R.id.btn_ButToTbs);
        ButToCup = findViewById(R.id.btn_ButToCup);
        unit_Output = findViewById(R.id.unit_Output);
        unit_Input = findViewById(R.id.unit_input);
//home1 = findViewById(R.id.btn_home1);
        TspToTbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit / 3);
                String formattedResult = String.format("%.2f", unit_Output0);
                unit_Output.setText(formattedResult);
            }
        });
        TbsToTsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit * 3);

                String formattedResult = String.format("%.2f", unit_Output0);
                unit_Output.setText(formattedResult);
            }
        });
        CupToOz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit * 8);
                String formattedResult = String.format("%.2f", unit_Output0);
                unit_Output.setText(formattedResult);
            }
        });
        OzToCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit / 8);
                String formattedResult = String.format("%.2f", unit_Output0);
                unit_Output.setText(formattedResult);
            }
        });
        ButToTbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit * 8);
                String formattedResult = String.format("%.2f", unit_Output0);
                unit_Output.setText(formattedResult);
            }
        });
        ButToCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit / 2);
                String formattedResult = String.format("%.2f", unit_Output0);
                unit_Output.setText(formattedResult);
            }
        });
/*home1.setOnClickListener(new View.OnClickListener() {
public void onClick(View view) {
Intent intent = new Intent(MetricTips.this,

TandTFragment.class);

startActivity(intent);

}
}); */
    }
}