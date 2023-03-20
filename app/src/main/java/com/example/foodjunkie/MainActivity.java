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

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;

    DataBaseHelper dataBaseHelper;

    Button btnNewRecipe, LogOut, btn_signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                case R.id.home:
                    replaceFragment(new MyRecipesFragment());
              //   LogOut.setVisibility(View.VISIBLE);
                    break;
                case R.id.profile:
                    replaceFragment(new DefaultRecipesFragment());
              //      LogOut.setVisibility(View.GONE);
                    break;
                case R.id.tipsTricks:
                    replaceFragment(new TandTFragment());
             //     LogOut.setVisibility(View.GONE);
                    break;
            }

            return true;
        });


        //database code
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        dataBaseHelper.generateRecipes(dataBaseHelper);


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
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}