package com.example.foodjunkie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DefaultRecipesFragment extends Fragment implements View.OnClickListener {

    public DefaultRecipesFragment() {
        // Required empty public constructor
    }
Button btn_defaultBreakfast, btn_defaultLunch, btn_defaultDinner, btn_defaultSnacks, btn_defaultDessert;

    View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

       btn_defaultBreakfast = view.findViewById(R.id.btn_defaultBreakfast);
        btn_defaultLunch = view.findViewById(R.id.btn_defaultLunch);
        btn_defaultDinner = view.findViewById(R.id.btn_defaultDinner);
        btn_defaultSnacks = view.findViewById(R.id.btn_defaultSnacks);
        btn_defaultDessert = view.findViewById(R.id.btn_defaultDessert);

        btn_defaultBreakfast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), DefaultRecipesList.class);
                startActivity(intent);
            }
        });

        /*btn_defaultBreakfast.setOnClickListener(this);
        btn_defaultLunch.setOnClickListener(this) ;
        btn_defaultDinner.setOnClickListener(this);
        btn_defaultSnacks.setOnClickListener(this);
        btn_defaultDessert.setOnClickListener(this); */

        return view;

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


        //    ListView mListView = ((ListView) view.findViewById(R.id.listView));

       // RecipeListAdapter adapter = new RecipeListAdapter(getContext(), R.layout.adapter_view_layout, peopleList);

        //   mListView.setAdapter(adapter);

    public void onClick(View v){
        Intent intent = new Intent(getActivity(), DefaultRecipesList.class);


        //switch case that changes title of DefaultRecipeList activity depending on button
        /*switch (v.getId()) {
            case R.id.btn_defaultBreakfast:
                intent.putExtra("title","Breakfast");
                break;
            case R.id.btn_defaultLunch:
                intent.putExtra("title","Lunch");
                break;
            case R.id.btn_defaultDinner:
                intent.putExtra("title","Dinner");
                break;
            case R.id.btn_defaultSnacks:
                intent.putExtra("title","Snacks");
                break;
            case R.id.btn_defaultDessert:
                intent.putExtra("title","Dessert");
                break;
            default:
                break;
        } */
        startActivity(intent);
    } }



//method for switching to DefaultRecipeList activity


