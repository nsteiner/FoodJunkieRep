package com.example.foodjunkie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyRecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRecipesFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Button Logout, newRecipe, btn_myBreakfast, btn_myLunch, btn_myDinner, btn_mySnacks, btn_myDessert;;



    public MyRecipesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRecipesFragment newInstance(String param1, String param2) {
        MyRecipesFragment fragment = new MyRecipesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_myrecipes, container, false);
        Logout = view.findViewById(R.id.LogOut);
        Logout.setOnClickListener(this);
        newRecipe = view.findViewById(R.id.btn_newRecipe);
        newRecipe.setOnClickListener(this);

        btn_myBreakfast = view.findViewById(R.id.btn_myBreakfast);
        btn_myLunch = view.findViewById(R.id.btn_myLunch);
        btn_myDinner = view.findViewById(R.id.btn_myDinner);
        btn_mySnacks = view.findViewById(R.id.btn_mySnacks);
        btn_myDessert = view.findViewById(R.id.btn_myDessert);
        btn_myBreakfast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MyRecipeList.class);
                intent.putExtra("title","Breakfast");
                startActivity(intent);
            }
        });
        btn_myLunch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MyRecipeList.class);
                intent.putExtra("title","Lunch");
                startActivity(intent);
            }
        });
        btn_myDinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MyRecipeList.class);
                intent.putExtra("title","Dinner");
                startActivity(intent);
            }
        });
        btn_mySnacks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MyRecipeList.class);
                intent.putExtra("title","Snacks");
                startActivity(intent);
            }
        });
        btn_myDessert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MyRecipeList.class);
                intent.putExtra("title","Dessert");
                startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        newRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent1 = new Intent(getActivity(), NewRecipe.class);
                startActivity(intent1);
            }
        });


        return view;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.LogOut:
                //(getActivity(), [name of activity to be switched to].class);
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_newRecipe:
                Intent intent1 = new Intent(getActivity(), NewRecipe.class);
                startActivity(intent1);
                break;


        }
    }




    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


    }

}