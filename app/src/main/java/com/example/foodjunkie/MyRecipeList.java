package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.google.android.material.search.SearchBar;

import java.text.Collator;
import java.util.Collections;
import java.util.List;

public class MyRecipeList extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv_recipeList;
    CheckBox cb_dairyFree, cb_glutenFree, cb_vegan, cb_vegetarian;

    Button btn_dietaryFilters, btn_filter, btn_cancel, btn_deleteRecipe;
    DataBaseHelper dataBaseHelper;
    EditText myRecipeSearchBar;
    int dairyFree; int glutenFree; int vegan; int vegetarian;
    int deleteCondition;
    MyRecipeListAdapter adapter;
    List<String> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_list);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }



        myRecipeSearchBar = (EditText) findViewById(R.id.myRecipeSearchBar);
        btn_dietaryFilters = findViewById(R.id.btn_dietaryFilters2);
        btn_deleteRecipe = findViewById(R.id.btn_deleteRecipe);


        //sets title depending on button clicked in default recipes fragment
        TextView title = findViewById(R.id.myTitle);
        String titleText = getIntent().getStringExtra("title");
        title.setText(titleText);
        Log.d(TAG, "onCreate: Started");
        lv_recipeList = (ListView) findViewById(R.id.lv_myRecipeList);
        dataBaseHelper = new DataBaseHelper(MyRecipeList.this);

        
        

        if(dataBaseHelper.checkEmpty(getIntent().getStringExtra("title"), "MyRecipes")){
            recipeList = this.dataBaseHelper.getAll("My" + getIntent().getStringExtra("title"));
            Collections.sort(recipeList, Collator.getInstance());
            adapter = new MyRecipeListAdapter(this, R.layout.myrecipe_view_layout, recipeList);
            lv_recipeList.setAdapter(adapter);
        }
        else{
            Toast.makeText(MyRecipeList.this, "No Recipes Found! Create your own, or go to 'FJ Recipes'", Toast.LENGTH_LONG).show();
            //new recipe button here?
            return;
        }

        myRecipeSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //(DefaultRecipeList.this).adapter.getFilter().filter(charSequence);
                recipeList = dataBaseHelper.filter(myRecipeSearchBar.getText().toString(), titleText, dairyFree, glutenFree, vegan, vegetarian);
                Collections.sort(recipeList, Collator.getInstance());
                adapter = new MyRecipeListAdapter(getBaseContext(), R.layout.myrecipe_view_layout, recipeList);
                lv_recipeList.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lv_recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyRecipeList.this, MyRecipeDisplay.class);
                //RecipeModel recipeModel = lv_recipeList.get(position)
                String item = recipeList.get(i);
                Bundle extras = new Bundle();
                extras.putString("Recipe", item);
                extras.putString("Title", titleText);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        btn_dietaryFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                // Add the popup window to the PopupWindow instance
                // popupWindow = new PopupWindow(popUpView, width, height, true);
                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                btn_filter = (Button) popUpView.findViewById(R.id.btn_filter);
                btn_cancel = (Button) popUpView.findViewById(R.id.btn_cancelFilter);

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

                        recipeList = dataBaseHelper.filter(myRecipeSearchBar.getText().toString(), "My" + titleText, dairyFree, glutenFree, vegan, vegetarian);
                        Collections.sort(recipeList, Collator.getInstance());
                        adapter = new MyRecipeListAdapter(getBaseContext(), R.layout.myrecipe_view_layout, recipeList);
                        lv_recipeList.setAdapter(adapter);

                        popupWindow.dismiss();
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

        
        btn_deleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Long click the recipe you want to delete!", Toast.LENGTH_SHORT).show();
                deleteCondition = 1;
            }
        });

        lv_recipeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                /*String item = adapter.getItem(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getBaseContext());
                dataBaseHelper.delete(item, titleText);
                adapter.remove(item);
                adapter.notifyDataSetChanged();
                return true;
                 */
                if(deleteCondition == 1) {
                    String item = adapter.getItem(position);
                    DBHelper dbHelper = new DBHelper(getBaseContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    String switchTable = "";
                    switch (titleText) {
                        case "Breakfast":
                            switchTable = "MYBREAKFAST_TABLE";
                            break;
                        case "Lunch":
                            switchTable = "MYLUNCH_TABLE";
                            break;
                        case "Dinner":
                            switchTable = "MYDINNER_TABLE";
                            break;
                        case "Dessert":
                            switchTable = "MYDESSERT_TABLE";
                            break;
                        case "Snacks":
                            switchTable = "MYSNACKS_TABLE";
                            break;
                    }
                    db.delete(switchTable, "RECIPE_NAME=?", new String[]{item});
                    adapter.remove(item);
                    adapter.notifyDataSetChanged();
                    deleteCondition = 0;
                    return true;
                }
                else{return false;}
            }
        });

        //delete ingredient from lv and database if long clicked

    }
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