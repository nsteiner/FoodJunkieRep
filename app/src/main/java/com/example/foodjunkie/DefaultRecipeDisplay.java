package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DefaultRecipeDisplay extends AppCompatActivity {

    Button btn_addRecipe, btn_checkPantry;
    TextView recipeName, tv_vegan, tv_dairyFree, tv_glutenFree, tv_ingredients, tv_instructions, recipeAdded, tv_pantry;
    ScrollView sv_ingredients, sv_instructions, sv_pantry;
    ImageView imageView3;

    DataBaseHelper dataBaseHelper;
    DBHelper dbHelper;
    String pantrycheck;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_recipe_display);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        btn_addRecipe = findViewById(R.id.btn_addingRecipe);
        recipeName = findViewById(R.id.tv_recipesName);
        sv_ingredients = findViewById(R.id.sv_ingredients);
        sv_instructions = findViewById(R.id.sv_instructions);
        tv_vegan = findViewById(R.id.tv_vegan);
        tv_dairyFree = findViewById(R.id.tv_dairyFree);
        tv_glutenFree = findViewById(R.id.tv_glutenFree);
        tv_ingredients = findViewById(R.id.tv_ingredientsOutput);
        tv_instructions = findViewById(R.id.tv_instructionsOutput);
        recipeAdded = findViewById(R.id.tv_recipeAdded);
        imageView = findViewById(R.id.imageView);
        btn_checkPantry = findViewById(R.id.btn_checkPantry);


        dataBaseHelper = new DataBaseHelper(DefaultRecipeDisplay.this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String nameForObject = extras.getString("Recipe");
        String title = extras.getString("Title");
        RecipeModel recipe = dataBaseHelper.getRecipe(nameForObject, title);



        //set Recipe Name
        recipeName.setText(recipe.getRecipeName());
        imageView.setImageResource(recipe.getImgResID());

        //combining arrays into one string
        String printIngredients = "";
        String printInstructions = "";
        dbHelper = new DBHelper(getBaseContext());
        List<String> list = dbHelper.getAllIng();
        for(int i = 0; i < recipe.getIngredientList().indexOf(""); i++){
            String recipeIng = recipe.getIngredient(i);
            String[] items = recipeIng.split(" "); // split into individual words
            boolean matchFound = false; // initialize flag
            for(String item : items) {
                for(String ing : list) {
                    String[] ingWords = ing.split(" "); // split into individual words
                    for(String ingWord : ingWords) {
                        if(ingWord.equals(item)) {
                            matchFound = true;
                            break;
                        }
                    }
                    if(matchFound) {
                        break;
                    }
                }
                if(matchFound) {
                    break;
                }
            }
            String pantrycheck = matchFound ? "✅" : "❌"; // set pantrycheck based on matchFound flag
            printIngredients = printIngredients + recipeIng + " " + pantrycheck + "\n" + "\n";
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

        if(dataBaseHelper.checkMyRecipes(recipe.getRecipeName(), title)){
            recipeAdded.setVisibility(View.VISIBLE);
            btn_addRecipe.setVisibility(View.GONE);
        }

        btn_addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.addOne(recipe, title);
                recipeAdded.setVisibility(View.VISIBLE);
                btn_addRecipe.setVisibility(View.GONE);
            }
        });


        dbHelper = new DBHelper(getBaseContext());
        btn_checkPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width = 550;
                int height = 1300;

                View popUpView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pantrypopup, null);
                final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);
                List<PantryModel>pantryList = dbHelper.getAll();
                sv_pantry = popUpView.findViewById(R.id.sv_pantry);
                tv_pantry = popUpView.findViewById(R.id.textView2);
                for(int i = 0; i < pantryList.size(); i++){
                    tv_pantry.setText(tv_pantry.getText() + pantryList.get(i).toString() + "\n" + "\n");
                }

                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 120);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = 1100;
                int height = 1100;
                View popUpView = LayoutInflater.from(getBaseContext()).inflate(R.layout.imagepopup, null);
                final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);
                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, -300);
                imageView3 = popUpView.findViewById(R.id.imageView3);
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                String nameForObject = extras.getString("Recipe");
                String title = extras.getString("Title");
                RecipeModel recipe = dataBaseHelper.getRecipe(nameForObject, title);
                imageView3.setImageResource(recipe.getImgResID());


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