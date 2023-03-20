package com.example.foodjunkie;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodjunkie.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;


    Button btnNewRecipe, btn_example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MyRecipesFragment());

        //set buttons, listViews, etc.
        btnNewRecipe = findViewById(R.id.btn_newRecipe);

        btn_example = findViewById(R.id.btn_example);

        //changes the fragment when a button on the nav bar is clicked
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                //will put whatever you want to show up in these cases
                case R.id.home:
                    replaceFragment(new MyRecipesFragment());
                    btnNewRecipe.setVisibility(View.VISIBLE);
                    break;
                case R.id.profile:
                    replaceFragment(new DefaultRecipesFragment());
                    btnNewRecipe.setVisibility(View.GONE);
                    break;
                case R.id.tipsTricks:
                    replaceFragment(new TandTFragment());
                    btnNewRecipe.setVisibility(View.GONE);
                    break;
            }

            return true;
        });





        //switch from "new recipe" button to newRecipe screen
        btnNewRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, NewRecipe.class);
                startActivity(intent);
            }
                                        });

        /*btn_example.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, RecipeDisplay.class);
                startActivity(intent);
            }
        });

         */


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}