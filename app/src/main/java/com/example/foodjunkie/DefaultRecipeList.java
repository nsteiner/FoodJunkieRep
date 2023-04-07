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

public class DefaultRecipeList extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv_recipeList;
    EditText defaultSearchBar;

    DataBaseHelper dataBaseHelper;

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

        //sets title depending on button clicked in default recipes fragment
        TextView title = findViewById(R.id.defaultTitle);
        String titleText = getIntent().getStringExtra("title");
        title.setText(titleText);



        Log.d(TAG, "onCreate: Started");
        lv_recipeList = (ListView) findViewById(R.id.lv_recipeList);

        dataBaseHelper = new DataBaseHelper(DefaultRecipeList.this);

        List<RecipeModel> recipeList = dataBaseHelper.getAll(getIntent().getStringExtra("title"));


        if(dataBaseHelper.checkEmpty(getIntent().getStringExtra("title"), "DefaultRecipes")){
            List<RecipeModel> displayList = this.dataBaseHelper.getAll(getIntent().getStringExtra("title"));
            DefaultRecipeListAdapter adapter = new DefaultRecipeListAdapter(this, R.layout.default_view_layout, displayList);
            lv_recipeList.setAdapter(adapter);
            for(int i = 0; i < displayList.size() ; i++){
                System.out.println(displayList.get(i).getRecipeName());
            }
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
                String item = recipeList.get(i).getRecipeName();
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
                //(DefaultRecipeList.this).adapter.getFilter().filter(charSequence);
                List<RecipeModel> filteredList = dataBaseHelper.filter(defaultSearchBar.getText().toString(), titleText);
                DefaultRecipeListAdapter adapter = new DefaultRecipeListAdapter(getBaseContext(), R.layout.default_view_layout, filteredList);
                lv_recipeList.setAdapter(adapter);
                for(int j = 0; j < filteredList.size() ; j++){
                    System.out.println(filteredList.get(j).getRecipeName());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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