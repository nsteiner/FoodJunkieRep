package com.example.foodjunkie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class NewRecipe extends AppCompatActivity {

    //database variables
    EditText nameInput, ingredientInput, instructionInput;
    String recipeName, tempIngredient, tempInstruction, category = "breakfast";
    int glutenFree = 0; int dairyFree = 0; int vegan = 0;
    ArrayList<String> instructions, ingredients;

    Button btn_addRecipe, btn_addIngredient, btn_addInstruction;

    DataBaseHelper dataBaseHelper;

    RadioGroup rb_categories; int checked; RadioButton radioButton;
    RadioButton rb_breakfast, rb_lunch, rb_dinner, rb_snack, rb_dessert;
    CheckBox cb_glutenFree, cb_dairyFree, cb_vegan;

    RecipeModel newRecipe;

    int ingCounter, insCounter;

    Context context;


    //Notes
    //Have to put in if-statements for max amount of ingredients/instructions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        instructions = new ArrayList<>(30);
        ingredients = new ArrayList<>(30);

        btn_addRecipe = findViewById(R.id.btn_addRecipe2);
        btn_addIngredient = findViewById(R.id.btn_addIngredient2);
        btn_addInstruction = findViewById(R.id.btn_addInstruction2);

        cb_dairyFree = findViewById(R.id.cb_dairyFree2);
        cb_glutenFree = findViewById(R.id.cb_glutenFree2);
        cb_vegan = findViewById(R.id.cb_vegan2);

        rb_categories = (RadioGroup) findViewById(R.id.rb_categories);
        rb_breakfast = findViewById(R.id.rb_breakfast);
        rb_lunch = findViewById(R.id.rb_lunch);
        rb_dinner = findViewById(R.id.rb_dinner);
        rb_snack = findViewById(R.id.rb_snack);
        rb_dessert = findViewById(R.id.rb_dessert);


        rb_categories.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioId = rb_categories.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                if (rb_breakfast.equals(radioButton)) {
                    category = "Breakfast";
                } else if (rb_lunch.equals(radioButton)) {
                    category = "Lunch";
                } else if (rb_dinner.equals(radioButton)) {
                    category = "Dinner";
                } else if (rb_snack.equals(radioButton)) {
                    category = "Snacks";
                } else if (rb_dessert.equals(radioButton)) {
                    category = "Dessert";
                }
            }
        });



        //buttons for gluten free, dairy free, vegan


        dataBaseHelper = new DataBaseHelper(NewRecipe.this);

        nameInput = findViewById(R.id.recipeInput);
        ingredientInput = findViewById(R.id.ingredientInput);
        instructionInput = findViewById(R.id.instructionInput);


        btn_addRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recipeName = nameInput.getText().toString();

                for(int i = ingCounter; i < 30; i++){
                    ingredients.add("");
                }
                for(int i = insCounter; i < 30; i++){
                    instructions.add("");
                }

                //seeing if recipe is gluten free, dairy free, vegan

                if(cb_glutenFree.isChecked()){
                    glutenFree = 1;
                }
                if(cb_dairyFree.isChecked()){
                    dairyFree = 1;
                }
                if(cb_vegan.isChecked()){
                    vegan = 1;
                }


                newRecipe = new RecipeModel(context, recipeName, instructions, ingredients, dairyFree, glutenFree, vegan);
                dataBaseHelper.addOne(newRecipe, category);

                nameInput.setText("");
                instructionInput.setText("");
                ingredientInput.setText("");
                insCounter = 0;
                ingCounter = 0;
                //reset radio buttons and dietary checks
                rb_categories.clearCheck();
                cb_vegan.setChecked(false);
                cb_glutenFree.setChecked(false);
                cb_dairyFree.setChecked(false);

                for(int i = 0; i < 30; i++){
                    ingredients.set(i, "");
                    instructions.set(i, "");
                }


            }
        });

        btn_addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ingredients.size() == 30){
                    Toast.makeText(getBaseContext(), "Max ingredients reached", Toast.LENGTH_SHORT);
                }
                else{
                    ingredients.add(ingredientInput.getText().toString());
                    ingCounter++;
                    ingredientInput.setText("");
                }
            }
        });

        btn_addInstruction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(instructions.size() == 30){
                    Toast.makeText(getBaseContext(), "Max instructions reached", Toast.LENGTH_SHORT);
                }
                else{
                    instructions.add(instructionInput.getText().toString());
                    insCounter++;
                    instructionInput.setText("");
                }
            }
        });

        /*
        recipeName = findViewById(R.id.recipeInput).toString();
        tempIngredient = findViewById(R.id.ingredientInput).toString();
        tempInstruction = findViewById(R.id.instructionInput).toString();
         */

        /*
        rb_categories.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.rb_breakfast:
                        category = "MYBREAKFAST_TABLE";
                    case R.id.rb_lunch:
                        category = "MYLUNCH_TABLE";
                    case R.id.rb_dinner:
                        category = "MYDINNER_TABLE";
                    case R.id.rb_snack:
                        category = "MYSNACK_TABLE";
                    case R.id.rb_dessert:
                        category = "MYDESSERT_TABLE";

                }

            }
        });
         */




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