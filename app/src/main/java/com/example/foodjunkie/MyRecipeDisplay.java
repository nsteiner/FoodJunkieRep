package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyRecipeDisplay extends AppCompatActivity {

    TextView recipeName, tv_vegan, tv_glutenFree, tv_dairyFree, tv_ingredients, tv_instructions;
    ScrollView sv_ingredients, sv_instructions;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_display);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        recipeName = findViewById(R.id.tv_recipesName);
        sv_ingredients = findViewById(R.id.sv_ingredientList);
        sv_instructions = findViewById(R.id.sv_instructionList);
        tv_vegan = findViewById(R.id.tv_vegan);
        tv_dairyFree = findViewById(R.id.tv_dairyFree);
        tv_glutenFree = findViewById(R.id.tv_glutenFree);
        tv_ingredients = findViewById(R.id.tv_ingredientOutput);
        tv_instructions = findViewById(R.id.tv_instructionOutput);

        dataBaseHelper = new DataBaseHelper(MyRecipeDisplay.this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String nameForObject = extras.getString("Recipe");
        String title = extras.getString("Title");
        RecipeModel recipe = dataBaseHelper.getRecipe(nameForObject, "My" + title);



        //set Recipe Name
        recipeName.setText(recipe.getRecipeName());

        //combining arrays into one string
        String printIngredients = "";
        String printInstructions = "";
        for(int i = 0; i < recipe.getIngredientList().indexOf(""); i++){
            printIngredients = printIngredients + recipe.getIngredient(i) + "\n" + "\n";
        }
        for(int i = 0; i < recipe.getInstructionList().indexOf(""); i++){
            printInstructions = printInstructions + "Step " + (i + 1) + ") " + recipe.getInstruction(i) + "\n" + "\n";
        }

        //set scroll views
        tv_ingredients.setText(printIngredients);
        tv_instructions.setText(printInstructions);

        //dietary indicators
        if(recipe.getDairyFree() == 0){
            tv_dairyFree.setVisibility(View.INVISIBLE);
        }
        if(recipe.getGlutenFree() == 0){
            tv_glutenFree.setVisibility(View.INVISIBLE);
        }
        if(recipe.getVegan() == 0){
            tv_vegan.setVisibility(View.INVISIBLE);
        }


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