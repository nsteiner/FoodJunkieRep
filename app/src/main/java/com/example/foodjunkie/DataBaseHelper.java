package com.example.foodjunkie;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.database.SQLException;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String BREAKFAST_TABLE = "BREAKFAST_TABLE";
    public static final String MYBREAKFAST_TABLE = "MYBREAKFAST_TABLE";
    public static final String LUNCH_TABLE = "LUNCH_TABLE";
    public static final String MYLUNCH_TABLE = "MYLUNCH_TABLE";
    public static final String DINNER_TABLE = "DINNER_TABLE";
    public static final String MYDINNER_TABLE = "MYDINNER_TABLE";
    public static final String SNACK_TABLE = "SNACK_TABLE";
    public static final String MYSNACK_TABLE = "MYSNACK_TABLE";
    public static final String DESSERT_TABLE = "DESSERT_TABLE";
    public static final String MYDESSERT_TABLE = "MYDESSERT_TABLE";


    public static final String RECIPE_NAME = "RECIPE_NAME";

    private static String DB_PATH = "/data/data/com.example.foodjunkie/databases/";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "DefaultRecipes.db";
    private final Context context;
    static SQLiteDatabase sqliteDataBase;

    //constructor
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String createTableStatement = "CREATE TABLE " + RECIPE_TABLE + " (" + RECIPE_NAME + " TEXT, " + "RECIPE_ING1" + " TEXT,"  + "RECIPE_ING2" + " TEXT," + "RECIPE_ING3" + " TEXT," + "RECIPE_ING4" + " TEXT," + "RECIPE_ING5" + " TEXT," + "RECIPE_ING6" + " TEXT," + "RECIPE_ING7" + " TEXT," + "RECIPE_ING8" + " TEXT," + "RECIPE_ING9" + " TEXT," + "RECIPE_ING10" + " TEXT,"  + "RECIPE_ING11" + " TEXT,"  + "RECIPE_ING12" + " TEXT," + "RECIPE_ING13" + " TEXT," + "RECIPE_ING14" + " TEXT," + "RECIPE_ING15" + " TEXT," + "RECIPE_ING16" + " TEXT," + "RECIPE_ING17" + " TEXT," + "RECIPE_ING18" + " TEXT," + "RECIPE_ING19" + " TEXT," + "RECIPE_ING20" + " TEXT,"  + "RECIPE_ING21" + " TEXT,"  + "RECIPE_ING22" + " TEXT," + "RECIPE_ING23" + " TEXT," + "RECIPE_ING24" + " TEXT," + "RECIPE_ING25" + " TEXT," + "RECIPE_ING26" + " TEXT," + "RECIPE_ING27" + " TEXT," + "RECIPE_ING28" + " TEXT," + "RECIPE_ING29" + " TEXT," + "RECIPE_ING30" + " TEXT,"
                + "RECIPE_INS1" + " TEXT,"  + "RECIPE_INS2" + " TEXT," + "RECIPE_INS3" + " TEXT," + "RECIPE_INS4" + " TEXT," + "RECIPE_INS5" + " TEXT," + "RECIPE_INS6" + " TEXT," + "RECIPE_INS7" + " TEXT," + "RECIPE_INS8" + " TEXT," + "RECIPE_INS9" + " TEXT," + "RECIPE_INS10" + " TEXT,"  + "RECIPE_INS11" + " TEXT,"  + "RECIPE_INS12" + " TEXT," + "RECIPE_INS13" + " TEXT," + "RECIPE_INS14" + " TEXT," + "RECIPE_INS15" + " TEXT," + "RECIPE_INS16" + " TEXT," + "RECIPE_INS17" + " TEXT," + "RECIPE_INS18" + " TEXT," + "RECIPE_INS19" + " TEXT," + "RECIPE_INS20" + " TEXT,"  + "RECIPE_INS21" + " TEXT,"  + "RECIPE_INS22" + " TEXT," + "RECIPE_INS23" + " TEXT," + "RECIPE_INS24" + " TEXT," + "RECIPE_INS25" + " TEXT," + "RECIPE_INS26" + " TEXT," + "RECIPE_INS27" + " TEXT," + "RECIPE_INS28" + " TEXT," + "RECIPE_INS29" + " TEXT," + "RECIPE_INS30" + " TEXT)";
        db.execSQL(createTableStatement);
         */
    }

    //method that helps with updated versions of apps
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void createDataBase() throws IOException {
        //check if the database exists
        boolean databaseExist = checkDataBase();

        if (databaseExist) {
            // Do Nothing.
        } else {
            this.getWritableDatabase();
            copyDataBase();
        }// end if else dbExist
    }

    public boolean checkDataBase() {
        File databaseFile = new File(DB_PATH + DATABASE_NAME);
        return databaseFile.exists();
    }

    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    //added code
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (sqliteDataBase != null)
            sqliteDataBase.close();
        super.close();
    }


    //adding a row to the database
    public void addOne(RecipeModel recipeModel, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        //getWritableDatabase() is included in SQLiteOpenHelper class, used to write to database
        //getReadableDatabase() is to reading database
        ContentValues cv = new ContentValues();
        //works like an associate array/hashmap; take pairs of values and pair them together

        cv.put(RECIPE_NAME, recipeModel.getRecipeName());
        cv.put("RECIPE_ING1", recipeModel.getIngredients(0));
        cv.put("RECIPE_ING2", recipeModel.getIngredients(1));
        cv.put("RECIPE_ING3", recipeModel.getIngredients(2));
        cv.put("RECIPE_ING4", recipeModel.getIngredients(3));
        cv.put("RECIPE_ING5", recipeModel.getIngredients(4));
        cv.put("RECIPE_ING6", recipeModel.getIngredients(5));
        cv.put("RECIPE_ING7", recipeModel.getIngredients(6));
        cv.put("RECIPE_ING8", recipeModel.getIngredients(7));
        cv.put("RECIPE_ING9", recipeModel.getIngredients(8));
        cv.put("RECIPE_ING10", recipeModel.getIngredients(9));
        cv.put("RECIPE_ING11", recipeModel.getIngredients(10));
        cv.put("RECIPE_ING12", recipeModel.getIngredients(11));
        cv.put("RECIPE_ING13", recipeModel.getIngredients(12));
        cv.put("RECIPE_ING14", recipeModel.getIngredients(13));
        cv.put("RECIPE_ING15", recipeModel.getIngredients(14));
        cv.put("RECIPE_ING16", recipeModel.getIngredients(15));
        cv.put("RECIPE_ING17", recipeModel.getIngredients(16));
        cv.put("RECIPE_ING18", recipeModel.getIngredients(17));
        cv.put("RECIPE_ING19", recipeModel.getIngredients(18));
        cv.put("RECIPE_ING20", recipeModel.getIngredients(19));
        cv.put("RECIPE_ING21", recipeModel.getIngredients(20));
        cv.put("RECIPE_ING22", recipeModel.getIngredients(21));
        cv.put("RECIPE_ING23", recipeModel.getIngredients(22));
        cv.put("RECIPE_ING24", recipeModel.getIngredients(23));
        cv.put("RECIPE_ING25", recipeModel.getIngredients(24));
        cv.put("RECIPE_ING26", recipeModel.getIngredients(25));
        cv.put("RECIPE_ING27", recipeModel.getIngredients(26));
        cv.put("RECIPE_ING28", recipeModel.getIngredients(27));
        cv.put("RECIPE_ING29", recipeModel.getIngredients(28));
        cv.put("RECIPE_ING30", recipeModel.getIngredients(29));
        cv.put("RECIPE_INS1", recipeModel.getInstructions(0));
        cv.put("RECIPE_INS2", recipeModel.getInstructions(1));
        cv.put("RECIPE_INS3", recipeModel.getInstructions(2));
        cv.put("RECIPE_INS4", recipeModel.getInstructions(3));
        cv.put("RECIPE_INS5", recipeModel.getInstructions(4));
        cv.put("RECIPE_INS6", recipeModel.getInstructions(5));
        cv.put("RECIPE_INS7", recipeModel.getInstructions(6));
        cv.put("RECIPE_INS8", recipeModel.getInstructions(7));
        cv.put("RECIPE_INS9", recipeModel.getInstructions(8));
        cv.put("RECIPE_INS10", recipeModel.getInstructions(9));
        cv.put("RECIPE_INS11", recipeModel.getInstructions(10));
        cv.put("RECIPE_INS12", recipeModel.getInstructions(11));
        cv.put("RECIPE_INS13", recipeModel.getInstructions(12));
        cv.put("RECIPE_INS14", recipeModel.getInstructions(13));
        cv.put("RECIPE_INS15", recipeModel.getInstructions(14));
        cv.put("RECIPE_INS16", recipeModel.getInstructions(15));
        cv.put("RECIPE_INS17", recipeModel.getInstructions(16));
        cv.put("RECIPE_INS18", recipeModel.getInstructions(17));
        cv.put("RECIPE_INS19", recipeModel.getInstructions(18));
        cv.put("RECIPE_INS20", recipeModel.getInstructions(19));
        cv.put("RECIPE_INS21", recipeModel.getInstructions(20));
        cv.put("RECIPE_INS22", recipeModel.getInstructions(21));
        cv.put("RECIPE_INS23", recipeModel.getInstructions(22));
        cv.put("RECIPE_INS24", recipeModel.getInstructions(23));
        cv.put("RECIPE_INS25", recipeModel.getInstructions(24));
        cv.put("RECIPE_INS26", recipeModel.getInstructions(25));
        cv.put("RECIPE_INS27", recipeModel.getInstructions(26));
        cv.put("RECIPE_INS28", recipeModel.getInstructions(27));
        cv.put("RECIPE_INS29", recipeModel.getInstructions(28));
        cv.put("RECIPE_INS30", recipeModel.getInstructions(29));
        cv.put("DAIRY_FREE", recipeModel.getDairyFree());
        cv.put("GLUTEN_FREE", recipeModel.getGlutenFree());
        cv.put("VEGAN", recipeModel.getVegan());

        //long insert = db.insert(table, null, cv);
        switch (table) {
            case "breakfast":
                db.insert("MYBREAKFAST_TABLE", null, cv);
                break;
            case "lunch":
                db.insert("MYLUNCH_TABLE", null, cv);
                break;
            case "dinner":
                db.insert("MYDINNER_TABLE", null, cv);
                break;
            case "snack":
                db.insert("MYSNACK_TABLE", null, cv);
                break;
            case "dessert":
                db.insert("MYDESSERT_TABLE", null, cv);
                break;
        }
        //(table name (must be MY..._TABLE, null, cv)
    }

    public boolean checkEmpty(String category, String list){
        String table = "MYBREAKFAST_TABLE";
        if(list == "MyRecipes"){
            table = "MY" + category.toUpperCase() + "_TABLE";
        }
        else{
            table = category.toUpperCase() + "_TABLE";
        }
        String queryString = "SELECT * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }


    //pulling information from the database
    public List<RecipeModel> getAll(String table) {
        List<RecipeModel> returnList = new ArrayList<>();
        switch(table){
            case "Breakfast":
                table = "BREAKFAST_TABLE";
                break;
            case "Lunch":
                table = "LUNCH_TABLE";
                break;
            case "Dinner":
                table = "DINNER_TABLE";
                break;
            case "Dessert":
                table = "DESSERT_TABLE";
                break;
            case "Snacks":
                table = "SNACKS_TABLE";
                break;
            case "MyBreakfast":
                table = "MYBREAKFAST_TABLE";
                break;
            case "MyLunch":
                table = "MYLUNCH_TABLE";
                break;
            case "MyDinner":
                table = "MYDINNER_TABLE";
                break;
            case "MyDessert":
                table = "MYDESSERT_TABLE";
                break;
            case "MySnacks":
                table = "MYSNACKS_TABLE";
                break;
        }
        String queryString = "SELECT * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        //writable database locks it so others can't access it
        Cursor cursor = db.rawQuery(queryString, null);
        //^now have query to run, cursor is return type of query
        //when you ask for query, get result back (rows of items); cursor is this result set
        //next, want to loop through results, create new recipe object for each row; if statement checks if there's items in database; if moveToFirst is true, there's an item in that row
        if (cursor.moveToFirst()) {
            do {
                List<String> ingredients = new ArrayList<>(30);
                List<String> instructions = new ArrayList<>(30);
                String name = cursor.getString(0);
                for(int i = 1; i < 31;i++){
                    ingredients.add(cursor.getString(i));
                }
                for(int i = 31; i < 61; i++){
                    instructions.add(cursor.getString(i));
                }
                int dairy_free = cursor.getInt(61);
                int gluten_free = cursor.getInt(62);
                int vegan = cursor.getInt(63);

                RecipeModel newRecipe = new RecipeModel(name, ingredients, instructions, dairy_free, gluten_free, vegan);
                returnList.add(newRecipe);
            } while (cursor.moveToNext());
        }

        // make sure to close sqlite
        cursor.close();
        db.close();
        return returnList;
    }




}
