package com.example.foodjunkie;

import android.content.Context;

public class PantryModel {
    private int quantity;
    private String unit;
    private String ingredientName;
    Context context;
    public PantryModel(Context context, int quantity, String unit, String ingredientName) {
        this.context=context;
        this.unit=unit;
        this.quantity = quantity;
        this.ingredientName = ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getUnit() {
        return unit;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    @Override
    public String toString() {
        return unit + " " + quantity + " " + ingredientName;
    }
}
