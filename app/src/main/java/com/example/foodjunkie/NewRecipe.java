package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class NewRecipe extends AppCompatActivity {

    //database variables
    EditText nameInput, ingredientInput, instructionInput;
    String recipeName, tempIngredient, tempInstruction;
    ArrayList<String> instructions, ingredients, tempInstructions, tempIngredients;
    //FirebaseDatabase db;
    private DatabaseReference db;

    Button btnAddRecipe, btnAddIngredient, btnAddInstruction;

    public void writeNewRecipe(String recipeName, String tempIngredient, String tempInstruction) {

    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        tempInstructions = new ArrayList<>();
        tempIngredients = new ArrayList<>();
        instructions = new ArrayList<>();
        ingredients = new ArrayList<>();

        btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnAddInstruction = findViewById(R.id.btnAddInstruction);

        nameInput = findViewById(R.id.recipeInput);
        ingredientInput = findViewById(R.id.ingredientInput);
        instructionInput = findViewById(R.id.instructionsInput);


        recipeName = findViewById(R.id.recipeInput).toString();
        tempIngredient = findViewById(R.id.ingredientInput).toString();
        tempInstruction = findViewById(R.id.instructionsInput).toString();


        db = FirebaseDatabase.getInstance().getReference();

        btnAddRecipe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Recipe newRecipe = new Recipe(recipeName, tempInstructions, tempIngredients);

                //code that adds newRecipe to database
                //db = FirebaseDatabase.getInstance();
                //reference = db.getReference("Recipes");
                //reference.child(recipeName).setValue(newRecipe);
                db.child("Recipes").child
            }
        });

        btnAddIngredient.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tempInstructions.add(tempIngredient);
                ingredientInput.setText("");



            }
        });

        btnAddInstruction.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tempInstructions.add(tempInstruction);
                instructionInput.setText("");
            }
        });
*/


    }





