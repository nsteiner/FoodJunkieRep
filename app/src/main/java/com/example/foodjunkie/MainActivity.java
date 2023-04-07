package com.example.foodjunkie;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.foodjunkie.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;

    DataBaseHelper dataBaseHelper;

    //need to keep track of what screen you're on so when
    // we switch back it doesn't go to the main recipe page
    int screen = 1;

    Button btnNewRecipe, LogOut, btn_signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        try {
            dataBaseHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MyRecipesFragment());


        //firebase
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        // gets current user
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        // can use else statment to display profile info

        //set buttons, listViews, etc.
        //  btnNewRecipe = findViewById(R.id.btn_newRecipe);
        // btn_signout = findViewById(R.id.Signout);

        LogOut = findViewById(R.id.LogOut);

        //changes the fragment when a button on the nav bar is clicked
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                //will put whatever you want to show up in these cases
                case R.id.myRecipes:
                    replaceFragment(new MyRecipesFragment());
                    break;
                case R.id.defaultRecipes:
                    replaceFragment(new DefaultRecipesFragment());
                    break;
                case R.id.tipsTricks:
                    replaceFragment(new TandTFragment());
                    break;
                case R.id.pantry:
                    replaceFragment(new PantryFragment());
                    break;
            }

            return true;
        });


        //database code




        //switch from "new recipe" button to newRecipe screen


        //   LogOut.setOnClickListener(new View.OnClickListener() {
        //         public void onClick(View view){
        //       FirebaseAuth.getInstance().signOut();
        //    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //     startActivity(intent);

        //   });




    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "TAG"+fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}