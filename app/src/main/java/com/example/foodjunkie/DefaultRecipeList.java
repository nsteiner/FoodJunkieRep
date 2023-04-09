package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.text.Collator;
import java.util.Collections;
import java.util.List;

public class DefaultRecipeList extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv_recipeList;
    EditText defaultSearchBar;
    CheckBox cb_dairyFree, cb_glutenFree, cb_vegan, cb_vegetarian;

    Button btn_dietaryFilters, btn_filter, btn_cancel;

    DataBaseHelper dataBaseHelper;
    int dairyFree = 0; int glutenFree = 0; int vegan = 0; int vegetarian = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_recipe_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
        defaultSearchBar = (EditText) findViewById(R.id.defaultSearchBar);
        btn_dietaryFilters = findViewById(R.id.btn_dietaryFilters);



        int width = 600;
        int height = 800;

        View popUpView = LayoutInflater.from(getBaseContext()).inflate(R.layout.filterpopup, null);
        final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);
        cb_dairyFree = popUpView.findViewById(R.id.cb_DFfilter);
        cb_glutenFree = popUpView.findViewById(R.id.cb_GFfilter);
        cb_vegan = popUpView.findViewById(R.id.cb_veganFilter);
        cb_vegetarian = popUpView.findViewById(R.id.cb_vegetarianFilter);
        btn_filter = popUpView.findViewById(R.id.btn_filter);
        btn_cancel = popUpView.findViewById(R.id.btn_cancelFilter);
        cb_dairyFree.setFocusable(true);
        cb_glutenFree.setFocusable(true);
        cb_vegan.setFocusable(true);
        cb_glutenFree.setFocusableInTouchMode(true);
        cb_dairyFree.setFocusableInTouchMode(true);
        cb_vegan.setFocusableInTouchMode(true);
        cb_vegetarian.setFocusable(true);
        cb_vegetarian.setFocusableInTouchMode(true);

        //sets title depending on button clicked in default recipes fragment
        TextView title = findViewById(R.id.defaultTitle);
        String titleText = getIntent().getStringExtra("title");
        title.setText(titleText);

        Log.d(TAG, "onCreate: Started");
        lv_recipeList = (ListView) findViewById(R.id.lv_recipeList);

        dataBaseHelper = new DataBaseHelper(DefaultRecipeList.this);

        List<String> recipeList = dataBaseHelper.getAll(getIntent().getStringExtra("title"));


        if(dataBaseHelper.checkEmpty(getIntent().getStringExtra("title"), "DefaultRecipes")){
            List<String> displayList = this.dataBaseHelper.getAll(getIntent().getStringExtra("title"));
            Collections.sort(displayList, Collator.getInstance());
            DefaultRecipeListAdapter adapter = new DefaultRecipeListAdapter(this, R.layout.default_view_layout, displayList);
            lv_recipeList.setAdapter(adapter);
        }
        else{
            Toast.makeText(DefaultRecipeList.this, "No Recipes Found! Create your own, or go to 'FJ Recipes'", Toast.LENGTH_LONG).show();
            //new recipe button here?
            return;
        }

        lv_recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DefaultRecipeList.this, DefaultRecipeDisplay.class);
                //RecipeModel recipeModel = lv_recipeList.get(position)
                String item = recipeList.get(i);
                Bundle extras = new Bundle();
                extras.putString("Recipe", item);
                extras.putString("Title", titleText);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        defaultSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> filteredList = dataBaseHelper.filter(defaultSearchBar.getText().toString(), titleText, dairyFree, glutenFree, vegan, vegetarian);
                Collections.sort(filteredList, Collator.getInstance());
                DefaultRecipeListAdapter adapter = new DefaultRecipeListAdapter(getBaseContext(), R.layout.default_view_layout, filteredList);
                lv_recipeList.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_dietaryFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width = 700;
                int height = 700;

                View popUpView = LayoutInflater.from(getBaseContext()).inflate(R.layout.filterpopup, null);
                final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);

                // Show the soft keyboard for the EditText view
                InputMethodManager imm = (InputMethodManager) getBaseContext().getSystemService(getBaseContext().INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                // Add the popup window to the PopupWindow instance
                // popupWindow = new PopupWindow(popUpView, width, height, true);
                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


                btn_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(cb_dairyFree.isChecked()){
                            dairyFree = 1;
                        }
                        if(cb_glutenFree.isChecked()){
                            glutenFree = 1;
                        }
                        if(cb_vegan.isChecked()){
                            vegan = 1;
                        }
                        if(cb_vegetarian.isChecked()){
                            vegetarian = 1;
                        }
                        cb_dairyFree.setChecked(false);
                        cb_glutenFree.setChecked(false);
                        cb_vegan.setChecked(false);
                        cb_vegetarian.setChecked(false);

                        popupWindow.dismiss();

                        List<String> filteredList = dataBaseHelper.filter(defaultSearchBar.getText().toString(), titleText, dairyFree, glutenFree, vegan, vegetarian);
                        Collections.sort(filteredList, Collator.getInstance());
                        DefaultRecipeListAdapter adapter = new DefaultRecipeListAdapter(getBaseContext(), R.layout.default_view_layout, filteredList);
                        lv_recipeList.setAdapter(adapter);
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cb_dairyFree.setChecked(false);
                        cb_glutenFree.setChecked(false);
                        cb_vegan.setChecked(false);
                        cb_vegetarian.setChecked(false);

                        popupWindow.dismiss();
                    }
                });

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // navigate back to the previous fragment
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}