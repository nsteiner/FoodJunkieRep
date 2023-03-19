package com.example.foodjunkie;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TandTFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TandTFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Button names here
    Button btn_example;
    Button btn_example2;

    public TandTFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TandTFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TandTFragment newInstance(String param1, String param2) {
        TandTFragment fragment = new TandTFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //btn_example = findViewById(R.id.btn_example);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    //Here on is how to switch from fragment to new activity with button
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tand_t, container, false);
        btn_example = view.findViewById(R.id.btn_example);
        btn_example2 = view.findViewById(R.id.btn_example2);
        btn_example.setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_example:
                //(getActivity(), [name of activity to be switched to].class);
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_example2:

        }
    }

}