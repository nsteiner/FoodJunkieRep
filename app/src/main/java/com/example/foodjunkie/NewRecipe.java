package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class NewRecipe extends AppCompatActivity {

    //database variables
    EditText nameInput, ingredientInput, instructionInput;
    String recipeName, tempIngredient, tempInstruction, category;
    ArrayList<String> instructions, ingredients;

    Button btn_addRecipe, btn_addIngredient, btn_addInstruction;

    DataBaseHelper dataBaseHelper;

    RadioGroup rb_categories;
    RadioButton rb_breakfast, rb_lunch, rb_dinner, rb_snack, rb_dessert;

    RecipeModel newRecipe;

    int ingCounter, insCounter;



    //Notes
    //Have to put in if-statements for max amount of ingredients/instructions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        instructions = new ArrayList<>(30);
        ingredients = new ArrayList<>(30);

        btn_addRecipe = findViewById(R.id.btn_addRecipe);
        btn_addIngredient = findViewById(R.id.btn_addIngredient);
        btn_addInstruction = findViewById(R.id.btn_addInstruction);

        rb_categories = findViewById(R.id.rb_categories);
        rb_breakfast = findViewById(R.id.rb_breakfast);
        rb_lunch = findViewById(R.id.rb_lunch);
        rb_dinner = findViewById(R.id.rb_dinner);
        rb_snack = findViewById(R.id.rb_snack);
        rb_dessert = findViewById(R.id.rb_dessert);


        dataBaseHelper = new DataBaseHelper(NewRecipe.this);

        nameInput = findViewById(R.id.recipeInput);
        ingredientInput = findViewById(R.id.ingredientInput);
        instructionInput = findViewById(R.id.instructionInput);


        btn_addRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recipeName = nameInput.getText().toString();

                category = "MYBREAKFAST_TABLE";

                for(int i = ingCounter; i < 30; i++){
                    ingredients.add("");
                }
                for(int i = insCounter; i < 30; i++){
                    instructions.add("");
                }

                newRecipe = new RecipeModel(recipeName, instructions, ingredients);
                dataBaseHelper.addOne(newRecipe, category);

                nameInput.setText("");
                instructionInput.setText("");
                ingredientInput.setText("");
                insCounter = 0;
                ingCounter = 0;
                //reset radio buttons

                for(int i = 0; i < 30; i++){
                    ingredients.set(i, "");
                    instructions.set(i, "");
                }


            }
        });

        btn_addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ingredients.add(ingredientInput.getText().toString());
                ingCounter++;
                ingredientInput.setText("");


            }
        });

        btn_addInstruction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instructions.add(instructionInput.getText().toString());
                insCounter++;
                instructionInput.setText("");
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
}










