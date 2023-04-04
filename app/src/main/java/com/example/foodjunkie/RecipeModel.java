package com.example.foodjunkie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.content.Context;


public class RecipeModel {
    private String recipeName;
    private List<String> instructions;
    private List<String> ingredients;
    private Context context;

    private int glutenFree, dairyFree, vegan;

    public RecipeModel() {
    }

    public RecipeModel(Context context, String recipeName, List<String> ingredients, List<String> instructions, int dairyFree, int glutenFree, int vegan) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.dairyFree = dairyFree;
        this.glutenFree = glutenFree;
        this.vegan = vegan;
        this.context = context;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredient(int i) {
        return ingredients.get(i);
    }
    public List<String> getIngredientList(){ return ingredients;}

    public void setIngredients(int i, String string) {
        ingredients.set(i, string);
    }

    public String getInstruction(int i) {
        return instructions.get(i);
    }
    public List<String> getInstructionList(){ return instructions;}

    public void setInstructions(int i, String string) {
        instructions.set(i, string);
    }

    public int getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(int glutenFree) {
        this.glutenFree = glutenFree;
    }

    public int getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(int dairyFree) {
        this.dairyFree = dairyFree;
    }

    public int getVegan() {
        return vegan;
    }

    public void setVegan(int vegan) {
        this.vegan = vegan;
    }

    public int getImgResID(){
        String imgURL = recipeName.replace(" ", "").replace("-","").toLowerCase(Locale.ROOT);
        int resID = context.getResources().getIdentifier(imgURL, "drawable", context.getPackageName());
        return resID;
    }

}
