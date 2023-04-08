package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyRecipeList extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv_recipeList;

    DataBaseHelper dataBaseHelper;
    EditText myRecipeSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_list);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        //sets title depending on button clicked in default recipes fragment
        TextView title = findViewById(R.id.myTitle);
        String titleText = getIntent().getStringExtra("title");
        title.setText(titleText);
        Log.d(TAG, "onCreate: Started");
        lv_recipeList = (ListView) findViewById(R.id.lv_myRecipeList);
        myRecipeSearchBar = findViewById(R.id.myRecipeSearchBar);
        dataBaseHelper = new DataBaseHelper(MyRecipeList.this);

        List<String> recipeList = dataBaseHelper.getAll("My" + getIntent().getStringExtra("title"));



        if(dataBaseHelper.checkEmpty(getIntent().getStringExtra("title"), "MyRecipes")){
            MyRecipeListAdapter adapter = new MyRecipeListAdapter(this, R.layout.myrecipe_view_layout, recipeList);
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
                List<String> filteredList = dataBaseHelper.filter(myRecipeSearchBar.getText().toString(), titleText);
                MyRecipeListAdapter adapter = new MyRecipeListAdapter(getBaseContext(), R.layout.myrecipe_view_layout, filteredList);
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