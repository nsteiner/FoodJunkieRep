package com.example.foodjunkie;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.foodjunkie.databinding.ActivityMainBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        auth = FirebaseAuth.getInstance();

        ListView mListView = ((ListView) findViewById(R.id.listView));
        String i1 = "info 1";
        String i2 = "info 2";

        Food john = new Food("Chicken a la mode", "info 1", "Info 2", "drawable://" + R.drawable.food);
        Food jerry = new Food("Applesauce", i1, i2, "drawable://" + R.drawable.food);
        Food jason = new Food("Mac and Cheese", i1, i2, "drawable://" + R.drawable.food);
        Food jimbo = new Food("Ceasar Salad", i1, i2, "drawable://" + R.drawable.food);
        Food james = new Food("Pasta Carbinara", i1, "Male", "drawable://" + R.drawable.food);





        ArrayList<Food> peopleList = new ArrayList<>();

        peopleList.add(john);
        peopleList.add(jerry);
        peopleList.add(jason);
        peopleList.add(jimbo);
        peopleList.add(james);

        PersonListAdapter adapter = new PersonListAdapter(this, R.layout.adapter_view_layout, peopleList);

        mListView.setAdapter(adapter);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                //will put whatever you want to show up in these cases
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    mListView.setVisibility(View.VISIBLE);
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    mListView.setVisibility(View.GONE);
                    break;
                case R.id.tipsTricks:
                    replaceFragment(new TandTFragment());
                    mListView.setVisibility(View.GONE);
                    break;
            }

            return true;
        });

        FirebaseDatabase.getInstance().getReference().child("Food Junkie Database").child("RecipeName").setValue("Apple Pie");

        //Log.d(TAG, "onCreate: Started");


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}