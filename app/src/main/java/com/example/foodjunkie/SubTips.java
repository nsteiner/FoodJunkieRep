package com.example.foodjunkie;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class SubTips extends AppCompatActivity {
    //private Button home2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_tips);
//home2 = findViewById(R.id.btn_home2);
/* home2.setOnClickListener(new View.OnClickListener() {
public void onClick(View view) {
Intent intent = new Intent(SubTips.this, TandTFragment.class);
startActivity(intent);
}
});*/
    }
}