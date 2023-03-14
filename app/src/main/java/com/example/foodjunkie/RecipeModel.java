package com.example.foodjunkie;

import java.util.ArrayList;
import java.util.List;

public class RecipeModel {
    private String recipeName;
    private List<String> instructions;
    private List<String> ingredients;

    public RecipeModel() {
    }

    public RecipeModel(String recipeName, List<String> ingredients, List<String> instructions) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients(int i) {
        return ingredients.get(i);
    }

    public void setIngredients(int i, String string) {
        ingredients.set(i, string);
    }

    public String getInstructions(int i) {
        return instructions.get(i);
    }

    public void setInstructions(int i, String string) {
        instructions.set(i, string);
    }



}
