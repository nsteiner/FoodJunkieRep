package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import com.google.android.material.textfield.TextInputEditText;
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
                unit_Output.setText(String.valueOf(unit_Output0));
            }
        });
        TbsToTsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit * 3);

                unit_Output.setText(String.valueOf(unit_Output0));
            }
        });
        CupToOz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit * 8);
                unit_Output.setText(String.valueOf(unit_Output0));
            }
        });
        OzToCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit / 8);
                unit_Output.setText(String.valueOf(unit_Output0));
            }
        });
        ButToTbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit * 8);
                unit_Output.setText(String.valueOf(unit_Output0));
            }
        });
        ButToCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit =

                        Double.parseDouble(unit_Input.getText().toString());

                unit_Output0 = (unit / 2);
                unit_Output.setText(String.valueOf(unit_Output0));
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