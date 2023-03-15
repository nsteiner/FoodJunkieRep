package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MyRecipeDisplay extends AppCompatActivity {

    //page for displaying recipe from my the list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
    }
}