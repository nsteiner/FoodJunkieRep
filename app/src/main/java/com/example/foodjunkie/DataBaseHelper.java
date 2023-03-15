package com.example.foodjunkie;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String RECIPE_TABLE = "RECIPE_TABLE";
    public static final String RECIPE_NAME = "RECIPE_NAME";

    private final Context context;

    //constructor
    public DataBaseHelper(@Nullable Context context) {
        super(context, "DefaultRecipes.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + RECIPE_TABLE + " (" + RECIPE_NAME + " TEXT, " + "RECIPE_ING1" + " TEXT,"  + "RECIPE_ING2" + " TEXT," + "RECIPE_ING3" + " TEXT," + "RECIPE_ING4" + " TEXT," + "RECIPE_ING5" + " TEXT," + "RECIPE_ING6" + " TEXT," + "RECIPE_ING7" + " TEXT," + "RECIPE_ING8" + " TEXT," + "RECIPE_ING9" + " TEXT," + "RECIPE_ING10" + " TEXT,"  + "RECIPE_ING11" + " TEXT,"  + "RECIPE_ING12" + " TEXT," + "RECIPE_ING13" + " TEXT," + "RECIPE_ING14" + " TEXT," + "RECIPE_ING15" + " TEXT," + "RECIPE_ING16" + " TEXT," + "RECIPE_ING17" + " TEXT," + "RECIPE_ING18" + " TEXT," + "RECIPE_ING19" + " TEXT," + "RECIPE_ING20" + " TEXT,"  + "RECIPE_ING21" + " TEXT,"  + "RECIPE_ING22" + " TEXT," + "RECIPE_ING23" + " TEXT," + "RECIPE_ING24" + " TEXT," + "RECIPE_ING25" + " TEXT," + "RECIPE_ING26" + " TEXT," + "RECIPE_ING27" + " TEXT," + "RECIPE_ING28" + " TEXT," + "RECIPE_ING29" + " TEXT," + "RECIPE_ING30" + " TEXT,"
                + "RECIPE_INS1" + " TEXT,"  + "RECIPE_INS2" + " TEXT," + "RECIPE_INS3" + " TEXT," + "RECIPE_INS4" + " TEXT," + "RECIPE_INS5" + " TEXT," + "RECIPE_INS6" + " TEXT," + "RECIPE_INS7" + " TEXT," + "RECIPE_INS8" + " TEXT," + "RECIPE_INS9" + " TEXT," + "RECIPE_INS10" + " TEXT,"  + "RECIPE_INS11" + " TEXT,"  + "RECIPE_INS12" + " TEXT," + "RECIPE_INS13" + " TEXT," + "RECIPE_INS14" + " TEXT," + "RECIPE_INS15" + " TEXT," + "RECIPE_INS16" + " TEXT," + "RECIPE_INS17" + " TEXT," + "RECIPE_INS18" + " TEXT," + "RECIPE_INS19" + " TEXT," + "RECIPE_INS20" + " TEXT,"  + "RECIPE_INS21" + " TEXT,"  + "RECIPE_INS22" + " TEXT," + "RECIPE_INS23" + " TEXT," + "RECIPE_INS24" + " TEXT," + "RECIPE_INS25" + " TEXT," + "RECIPE_INS26" + " TEXT," + "RECIPE_INS27" + " TEXT," + "RECIPE_INS28" + " TEXT," + "RECIPE_INS29" + " TEXT," + "RECIPE_INS30" + " TEXT)";

        db.execSQL(createTableStatement);
    }

    //method that helps with updated versions of apps
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //adding a row to the database
    public boolean addOne(RecipeModel recipeModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        //getWritableDatabase() is included in SQLiteOpenHelper class, used to write to database
        //getReadableDatabase() is to reading database
        ContentValues cv = new ContentValues();
        //works like an associate array/hashmap; take pairs of values and pair them together

        cv.put(RECIPE_NAME, recipeModel.getRecipeName());
        cv.put("RECIPE_ING1", recipeModel.getIngredients(0)); cv.put("RECIPE_ING2", recipeModel.getIngredients(1)); cv.put("RECIPE_ING3", recipeModel.getIngredients(2)); cv.put("RECIPE_ING4", recipeModel.getIngredients(3));cv.put("RECIPE_ING5", recipeModel.getIngredients(4)); cv.put("RECIPE_ING6", recipeModel.getIngredients(5)); cv.put("RECIPE_ING7", recipeModel.getIngredients(6)); cv.put("RECIPE_ING8", recipeModel.getIngredients(7)); cv.put("RECIPE_ING9", recipeModel.getIngredients(8)); cv.put("RECIPE_ING10", recipeModel.getIngredients(9)); cv.put("RECIPE_ING11", recipeModel.getIngredients(10)); cv.put("RECIPE_ING12", recipeModel.getIngredients(11)); cv.put("RECIPE_ING13", recipeModel.getIngredients(12)); cv.put("RECIPE_ING14", recipeModel.getIngredients(13)); cv.put("RECIPE_ING15", recipeModel.getIngredients(14));cv.put("RECIPE_ING16", recipeModel.getIngredients(15)); cv.put("RECIPE_ING17", recipeModel.getIngredients(16)); cv.put("RECIPE_ING18", recipeModel.getIngredients(17)); cv.put("RECIPE_ING19", recipeModel.getIngredients(18)); cv.put("RECIPE_ING20", recipeModel.getIngredients(19)); cv.put("RECIPE_ING21", recipeModel.getIngredients(20)); cv.put("RECIPE_ING22", recipeModel.getIngredients(21)); cv.put("RECIPE_ING23", recipeModel.getIngredients(22)); cv.put("RECIPE_ING24", recipeModel.getIngredients(23));cv.put("RECIPE_ING25", recipeModel.getIngredients(24)); cv.put("RECIPE_ING26", recipeModel.getIngredients(25)); cv.put("RECIPE_ING27", recipeModel.getIngredients(26)); cv.put("RECIPE_ING28", recipeModel.getIngredients(27)); cv.put("RECIPE_ING29", recipeModel.getIngredients(28)); cv.put("RECIPE_ING30", recipeModel.getIngredients(29));
        cv.put("RECIPE_INS1", recipeModel.getInstructions(0)); cv.put("RECIPE_INS2", recipeModel.getInstructions(1)); cv.put("RECIPE_INS3", recipeModel.getInstructions(2)); cv.put("RECIPE_INS4", recipeModel.getInstructions(3));cv.put("RECIPE_INS5", recipeModel.getInstructions(4)); cv.put("RECIPE_INS6", recipeModel.getInstructions(5)); cv.put("RECIPE_INS7", recipeModel.getInstructions(6)); cv.put("RECIPE_INS8", recipeModel.getInstructions(7)); cv.put("RECIPE_INS9", recipeModel.getInstructions(8)); cv.put("RECIPE_INS10", recipeModel.getInstructions(9)); cv.put("RECIPE_INS11", recipeModel.getInstructions(10)); cv.put("RECIPE_INS12", recipeModel.getInstructions(11)); cv.put("RECIPE_INS13", recipeModel.getInstructions(12)); cv.put("RECIPE_INS14", recipeModel.getInstructions(13)); cv.put("RECIPE_INS15", recipeModel.getInstructions(14));cv.put("RECIPE_INS16", recipeModel.getInstructions(15)); cv.put("RECIPE_INS17", recipeModel.getInstructions(16)); cv.put("RECIPE_INS18", recipeModel.getInstructions(17)); cv.put("RECIPE_INS19", recipeModel.getInstructions(18)); cv.put("RECIPE_INS20", recipeModel.getInstructions(19)); cv.put("RECIPE_INS21", recipeModel.getInstructions(20)); cv.put("RECIPE_INS22", recipeModel.getInstructions(21)); cv.put("RECIPE_INS23", recipeModel.getInstructions(22)); cv.put("RECIPE_INS24", recipeModel.getInstructions(23));cv.put("RECIPE_INS25", recipeModel.getInstructions(24)); cv.put("RECIPE_INS26", recipeModel.getInstructions(25)); cv.put("RECIPE_INS27", recipeModel.getInstructions(26)); cv.put("RECIPE_INS28", recipeModel.getInstructions(27)); cv.put("RECIPE_INS29", recipeModel.getInstructions(28)); cv.put("RECIPE_INS30", recipeModel.getInstructions(29));



        long insert = db.insert(RECIPE_TABLE, null, cv);
        //(table name, null, cv)
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    //pulling information from the database

    public List<String> getAll() {
        List<String> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + RECIPE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        //writable database locks it so others can't access it

        Cursor cursor = db.rawQuery(queryString, null);
        //^now have query to run, cursor is return type of query
        //when you ask for query, get result back (rows of items); cursor is this result set

        //next, want to loop through results, create new recipe object for each row; if statement checks if there's items in database; if moveToFirst is true, there's an item in that row
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String time = cursor.getString(1);
                String instructions = cursor.getString(2);

                //RecipeModel newRecipe = new RecipeModel(name, time, instructions);
                //returnList.add(newRecipe.getRecipeName());

            } while (cursor.moveToNext());

        }

        // make sure to close sqlite
        cursor.close();
        db.close();
        return returnList;

    }



    public void generateRecipes(DataBaseHelper dataBaseHelper) {
        //structure for default recipes
        List<String> applePieIng = new ArrayList<String>(30);
        applePieIng.add("3 Tablespoons Granulated Sugar");
        applePieIng.add("8-10 Thinly Sliced Medium Apples");
        applePieIng.add("Butter and cinnamon");
        List<String> applePieIns = new ArrayList<String>(30);
        applePieIns.add("Mix the four ingredients in a medium bowl");
        applePieIns.add("Roll out and place an uncooked pie crust in the bottom of a deep 9-inch pie plate");
        applePieIns.add("Place the apple mixture in the uncooked pie shell. Place small pieces of butter on top of apple mixture");
        applePieIns.add("Top with second pie crust, crimp the edges, and then cut a few slits in the top crust to allow steam to vent");
        applePieIns.add("Mix the egg white and 1 tablespoon of water together in a small bowl and then brush the top with water and sprinkle with sugar before baking");
        applePieIns.add("Bake at 450 for the first 15 minutes, then reduce the heat to 350 and continue cooking for an additional 30 to 40 minutes");
        applePieIns.add("Cool for 10 minutes before serving");
        for(int i = 3; i <= 30;i++){
            applePieIng.add("");
        }
        for(int i = 7; i <= 30;i++){
            applePieIns.add("");
        }
        RecipeModel applePie = new RecipeModel("Apple Pie", applePieIng, applePieIns);








        String queryString = "SELECT * FROM " + RECIPE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);


        if (cursor.moveToFirst() == false) {
            dataBaseHelper.addOne(applePie);
            //dataBaseHelper.addOne(pepPizza);
            //dataBaseHelper.addOne(tomBisque);
        }
    }



}
