package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class DefaultRecipeDisplay extends AppCompatActivity {

    Button btn_addRecipe;
    TextView recipeName, tv_vegan, tv_dairyFree, tv_glutenFree, tv_ingredients, tv_instructions, recipeAdded;
    ScrollView sv_ingredients, sv_instructions;

    DataBaseHelper dataBaseHelper;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_recipe_display);

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

    }

}