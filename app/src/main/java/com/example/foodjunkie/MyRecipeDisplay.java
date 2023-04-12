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
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MyRecipeDisplay extends AppCompatActivity {

    TextView recipeName, tv_vegan, tv_glutenFree, tv_dairyFree, tv_ingredients, tv_instructions, tv_pantry, tv_vegetarian;
    ScrollView sv_ingredients, sv_instructions, sv_pantry;
    DataBaseHelper dataBaseHelper;
    DBHelper dbHelper;

    Button btn_checkPantry, btn_exitPantry;
    ImageView imageView;

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
        tv_vegetarian = findViewById(R.id.tv_vegetarian);

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
                        if(ingWord.toLowerCase(Locale.ROOT).equals(item.toLowerCase(Locale.ROOT))) {
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
        if(recipe.getVegetarian() == 0){
            tv_vegetarian.setVisibility(View.INVISIBLE);
        }


        dbHelper = new DBHelper(getBaseContext());
        btn_checkPantry = findViewById(R.id.btn_myOpenPantry);
        btn_checkPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width = 550;
                int height = 1300;

                View popUpView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pantrypopup, null);
                final PopupWindow popupWindow = new PopupWindow(popUpView, width, height, true);
                List<PantryModel> pantryList = dbHelper.getAll();
                sv_pantry = popUpView.findViewById(R.id.sv_pantry);
                tv_pantry = popUpView.findViewById(R.id.textView2);
                btn_exitPantry = popUpView.findViewById(R.id.btn_exitPantry);
                for(int i = 0; i < pantryList.size(); i++){
                    tv_pantry.setText(tv_pantry.getText() + pantryList.get(i).toString() + "\n" + "\n");
                }
                popupWindow.setContentView(popUpView);
                popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 120);

                btn_exitPantry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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