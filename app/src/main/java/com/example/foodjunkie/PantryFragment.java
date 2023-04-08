package com.example.foodjunkie;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;

public class PantryFragment extends Fragment {

    //PantryListAdapter adapter;
    //DBHelper databasehelper;
   // PantryModel newPantry;
    Context context;
    int quantity;
    String unit, ingredientName;
    private Parcelable mListState = null;
    private ListView listView;

    private View view;
    private EditText tv_quantity, tv_unit, tv_ingredient;
    private Button btn_add;
    private Button btn_cancel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PantryFragment() {
        // Required empty public constructor
    }


    public static PantryFragment newInstance(String param1, String param2) {
        PantryFragment fragment = new PantryFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_pantry, container, false);

        //listView =view.findViewById(R.id.myingredients);

       // databasehelper = new DBHelper(getContext());

      //  List<PantryModel> pantryList = databasehelper.getAll();

       // if(databasehelper.checkEmpty()){
           // adapter = new PantryListAdapter(getContext(), R.layout.adapter_view_layout, pantryList);
     //       listView.setAdapter(adapter);}
        // Inflate the layout for this fragment


        Button btnShowPopUp = view.findViewById(R.id.btn_addIngredient);
        btnShowPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the pop-up window
                //final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);
                int width = 900;
                int height = 1200;

                View popUpView = LayoutInflater.from(getActivity()).inflate(R.layout.ingredientpopup, null);
                final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);
                tv_quantity = popUpView.findViewById(R.id.tv_quantity);
                tv_unit = popUpView.findViewById(R.id.tv_unit);
                tv_ingredient = popUpView.findViewById(R.id.tv_ingredient);
                tv_quantity.setFocusable(true);
                tv_unit.setFocusable(true);
                tv_ingredient.setFocusable(true);
                tv_quantity.setFocusableInTouchMode(true);
                tv_unit.setFocusableInTouchMode(true);
                tv_ingredient.setFocusableInTouchMode(true);

                // Show the soft keyboard for the EditText view
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                // Add the popup window to the PopupWindow instance
                // popupWindow = new PopupWindow(popUpView, width, height, true);
                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // MyContract.MyDbHelper mDbHelper = new MyContract.MyDbHelper(getContext());


                btn_add = popUpView.findViewById(R.id.add);
               // databasehelper = new DBHelper(getContext());

               /* btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        quantity = Integer.parseInt(tv_quantity.getText().toString());
                        unit = tv_unit.getText().toString();
                        ingredientName = tv_ingredient.getText().toString();

                        //newPantry = new PantryModel(context,quantity, unit,ingredientName );
                        //databasehelper.addOne(newPantry);

                        tv_unit.setText("");
                        tv_quantity.setText("");
                        tv_ingredient.setText("");

                    }



                });*/

                btn_cancel = popUpView.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the pop-up window when the close button is clicked
                        popupWindow.dismiss();
                    }
                });
            }
        });
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        if (mListState != null) {
            listView.onRestoreInstanceState(mListState);
        }
    }



}
