package com.example.foodjunkie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PantryFragment extends Fragment {
    Context context;
    PantryListAdapter adapter;
    DBHelper databasehelper;
    PantryModel newPantry;
    int intquantity;
    String strunit, stringredientName;
    private Parcelable mListState = null;
    private ListView listView;
    private View view;
    private EditText tv_quantity, tv_unit, tv_ingredient;
    private Button btn_add;
    private Button btn_cancel;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public PantryFragment() {
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
        listView =view.findViewById(R.id.myingredients);
        databasehelper = new DBHelper(getContext());

        List<PantryModel> pantryList = databasehelper.getAll();
        List<String> pantryStrings = new ArrayList<>(pantryList.size());
        for(int i = 0; i < pantryList.size(); i++){
            pantryStrings.add(pantryList.get(i).getIngredientName());
        }

        Collections.sort(pantryStrings, Collator.getInstance());
        for(int i = 0; i < pantryStrings.size(); i++){
            for(int j = 0; j < pantryList.size(); j++){
                if(pantryList.get(j).getIngredientName() == pantryStrings.get(i)){
                    PantryModel tempPantry = pantryList.get(i);
                    pantryList.set(i, pantryList.get(j));
                    pantryList.set(j, tempPantry);
                }
            }
        }

        adapter = new PantryListAdapter(getContext(), R.layout.pantryadapter, pantryList);
        if(databasehelper.checkEmpty()){
            listView.setAdapter(adapter);}

        Button deleteIng = view.findViewById(R.id.btn_deleteIng);



        //show message
        deleteIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Long click the ingredient you want to delete!", Toast.LENGTH_SHORT).show();
            }
        });

        //delete ingredient from lv and database if long clicked
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                PantryModel item = adapter.getItem(position);
                DBHelper dbHelper = new DBHelper(getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("PANTRY", "QUANTITY=? AND UNIT=? AND INGREDIENT=?", new String[]{String.valueOf(item.getQuantity()), item.getUnit(), item.getIngredientName()});
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        //button labeled "add ingredient" which takes u to pop up window
        Button btnShowPopUp = view.findViewById(R.id.btn_addIngredient);
        btnShowPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //define pop up window
                int width = 1100;
                int height = 600;
                View popUpView = LayoutInflater.from(getActivity()).inflate(R.layout.ingredientpopup, null);
                final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);

                //define edittexts and set focusable
                tv_quantity = popUpView.findViewById(R.id.tv_quantity);
                tv_unit = popUpView.findViewById(R.id.tv_unit);
                tv_ingredient = popUpView.findViewById(R.id.tv_ingredient);
                tv_quantity.setFocusable(true);
                tv_unit.setFocusable(true);
                tv_ingredient.setFocusable(true);
                tv_quantity.setFocusableInTouchMode(true);
                tv_unit.setFocusableInTouchMode(true);
                tv_ingredient.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, -300);

                //button to add ingredient inside popup
                btn_add = popUpView.findViewById(R.id.add);
                databasehelper = new DBHelper(getContext());
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intquantity = Integer.parseInt(tv_quantity.getText().toString().replace(" ", ""));
                        strunit = tv_unit.getText().toString();
                        stringredientName = tv_ingredient.getText().toString();
                        newPantry = new PantryModel(context,intquantity, strunit,stringredientName);
                        databasehelper.addOne(newPantry);

                        List<PantryModel> pantryList = databasehelper.getAll();
                        List<String> pantryStrings = new ArrayList<>(pantryList.size());
                        for(int i = 0; i < pantryList.size(); i++){
                            pantryStrings.add(pantryList.get(i).getIngredientName());
                        }

                        Collections.sort(pantryStrings, Collator.getInstance());
                        for(int i = 0; i < pantryStrings.size(); i++){
                            for(int j = 0; j < pantryList.size(); j++){
                                if(pantryList.get(j).getIngredientName() == pantryStrings.get(i)){
                                    PantryModel tempPantry = pantryList.get(i);
                                    pantryList.set(i, pantryList.get(j));
                                    pantryList.set(j, tempPantry);
                                }
                            }
                        }

                        adapter = new PantryListAdapter(getContext(), R.layout.pantryadapter, pantryList);
                        listView.setAdapter(adapter);

                        tv_unit.setText("");
                        tv_quantity.setText("");
                        tv_ingredient.setText("");
                        popupWindow.dismiss();
                    }});

                //button to leave popup window
                btn_cancel = popUpView.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});
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
