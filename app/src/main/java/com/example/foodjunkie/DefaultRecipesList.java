package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.*;

public class DefaultRecipesList extends AppCompatActivity {

    Button btn_addingRecipe;

        //when you click on a category in default cookbook, this will display recipes of that list
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_default_recipes_list);
            //sets title depending on button clicked in default recipes fragment
        TextView title = findViewById(R.id.tv_recipesName);
      title.setText(getIntent().getStringExtra("title"));
        }
    }