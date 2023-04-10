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
import java.util.Objects;

import android.database.SQLException;
public class DataBaseHelper extends SQLiteOpenHelper {
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
        cv.put("RECIPE_ING1", recipeModel.getIngredient(0));
        cv.put("RECIPE_ING2", recipeModel.getIngredient(1));
        cv.put("RECIPE_ING3", recipeModel.getIngredient(2));
        cv.put("RECIPE_ING4", recipeModel.getIngredient(3));
        cv.put("RECIPE_ING5", recipeModel.getIngredient(4));
        cv.put("RECIPE_ING6", recipeModel.getIngredient(5));
        cv.put("RECIPE_ING7", recipeModel.getIngredient(6));
        cv.put("RECIPE_ING8", recipeModel.getIngredient(7));
        cv.put("RECIPE_ING9", recipeModel.getIngredient(8));
        cv.put("RECIPE_ING10", recipeModel.getIngredient(9));
        cv.put("RECIPE_ING11", recipeModel.getIngredient(10));
        cv.put("RECIPE_ING12", recipeModel.getIngredient(11));
        cv.put("RECIPE_ING13", recipeModel.getIngredient(12));
        cv.put("RECIPE_ING14", recipeModel.getIngredient(13));
        cv.put("RECIPE_ING15", recipeModel.getIngredient(14));
        cv.put("RECIPE_ING16", recipeModel.getIngredient(15));
        cv.put("RECIPE_ING17", recipeModel.getIngredient(16));
        cv.put("RECIPE_ING18", recipeModel.getIngredient(17));
        cv.put("RECIPE_ING19", recipeModel.getIngredient(18));
        cv.put("RECIPE_ING20", recipeModel.getIngredient(19));
        cv.put("RECIPE_ING21", recipeModel.getIngredient(20));
        cv.put("RECIPE_ING22", recipeModel.getIngredient(21));
        cv.put("RECIPE_ING23", recipeModel.getIngredient(22));
        cv.put("RECIPE_ING24", recipeModel.getIngredient(23));
        cv.put("RECIPE_ING25", recipeModel.getIngredient(24));
        cv.put("RECIPE_ING26", recipeModel.getIngredient(25));
        cv.put("RECIPE_ING27", recipeModel.getIngredient(26));
        cv.put("RECIPE_ING28", recipeModel.getIngredient(27));
        cv.put("RECIPE_ING29", recipeModel.getIngredient(28));
        cv.put("RECIPE_ING30", recipeModel.getIngredient(29));
        cv.put("RECIPE_INS1", recipeModel.getInstruction(0));
        cv.put("RECIPE_INS2", recipeModel.getInstruction(1));
        cv.put("RECIPE_INS3", recipeModel.getInstruction(2));
        cv.put("RECIPE_INS4", recipeModel.getInstruction(3));
        cv.put("RECIPE_INS5", recipeModel.getInstruction(4));
        cv.put("RECIPE_INS6", recipeModel.getInstruction(5));
        cv.put("RECIPE_INS7", recipeModel.getInstruction(6));
        cv.put("RECIPE_INS8", recipeModel.getInstruction(7));
        cv.put("RECIPE_INS9", recipeModel.getInstruction(8));
        cv.put("RECIPE_INS10", recipeModel.getInstruction(9));
        cv.put("RECIPE_INS11", recipeModel.getInstruction(10));
        cv.put("RECIPE_INS12", recipeModel.getInstruction(11));
        cv.put("RECIPE_INS13", recipeModel.getInstruction(12));
        cv.put("RECIPE_INS14", recipeModel.getInstruction(13));
        cv.put("RECIPE_INS15", recipeModel.getInstruction(14));
        cv.put("RECIPE_INS16", recipeModel.getInstruction(15));
        cv.put("RECIPE_INS17", recipeModel.getInstruction(16));
        cv.put("RECIPE_INS18", recipeModel.getInstruction(17));
        cv.put("RECIPE_INS19", recipeModel.getInstruction(18));
        cv.put("RECIPE_INS20", recipeModel.getInstruction(19));
        cv.put("RECIPE_INS21", recipeModel.getInstruction(20));
        cv.put("RECIPE_INS22", recipeModel.getInstruction(21));
        cv.put("RECIPE_INS23", recipeModel.getInstruction(22));
        cv.put("RECIPE_INS24", recipeModel.getInstruction(23));
        cv.put("RECIPE_INS25", recipeModel.getInstruction(24));
        cv.put("RECIPE_INS26", recipeModel.getInstruction(25));
        cv.put("RECIPE_INS27", recipeModel.getInstruction(26));
        cv.put("RECIPE_INS28", recipeModel.getInstruction(27));
        cv.put("RECIPE_INS29", recipeModel.getInstruction(28));
        cv.put("RECIPE_INS30", recipeModel.getInstruction(29));
        cv.put("DAIRY_FREE", recipeModel.getDairyFree());
        cv.put("GLUTEN_FREE", recipeModel.getGlutenFree());
        cv.put("VEGAN", recipeModel.getVegan());
        cv.put("VEGETARIAN", recipeModel.getVegetarian());

        //long insert = db.insert(table, null, cv);
        switch (table) {
            case "Breakfast":
                db.insert("MYBREAKFAST_TABLE", null, cv);
                break;
            case "Lunch":
                db.insert("MYLUNCH_TABLE", null, cv);
                break;
            case "Dinner":
                db.insert("MYDINNER_TABLE", null, cv);
                break;
            case "Snacks":
                db.insert("MYSNACKS_TABLE", null, cv);
                break;
            case "Dessert":
                db.insert("MYDESSERT_TABLE", null, cv);
                break;
        }
        //(table name (must be MY..._TABLE, null, cv)
    }

    public boolean checkEmpty(String category, String list) {
        String table = "MYBREAKFAST_TABLE";
        if (list == "MyRecipes") {
            table = "MY" + category.toUpperCase() + "_TABLE";
        } else {
            table = category.toUpperCase() + "_TABLE";
        }
        String queryString = "SELECT * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }


    //pulling information from the database
    public List<String> getAll(String table) {
        List<String> returnList = new ArrayList<>();
        switch (table) {
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
                String name = cursor.getString(0);
                returnList.add(name);
            } while (cursor.moveToNext());
        }

        // make sure to close sqlite
        cursor.close();
        db.close();
        return returnList;
    }

    public RecipeModel getRecipe(String recipeName, String table) {
        String switchTable = "";
        switch (table) {
            case "Breakfast":
                switchTable = "BREAKFAST_TABLE";
                break;
            case "Lunch":
                switchTable = "LUNCH_TABLE";
                break;
            case "Dinner":
                switchTable = "DINNER_TABLE";
                break;
            case "Dessert":
                switchTable = "DESSERT_TABLE";
                break;
            case "Snacks":
                switchTable = "SNACKS_TABLE";
                break;
            case "MyBreakfast":
                switchTable = "MYBREAKFAST_TABLE";
                break;
            case "MyLunch":
                switchTable = "MYLUNCH_TABLE";
                break;
            case "MyDinner":
                switchTable = "MYDINNER_TABLE";
                break;
            case "MyDessert":
                switchTable = "MYDESSERT_TABLE";
                break;
            case "MySnacks":
                switchTable = "MYSNACKS_TABLE";
                break;
        }
        String queryString = "SELECT * FROM " + switchTable + " WHERE RECIPE_NAME ='" + recipeName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();

        List<String> ingredients = new ArrayList<>(30);
        List<String> instructions = new ArrayList<>(30);
        List<String> tags = new ArrayList<>(10);
        RecipeModel displayRecipe;

        String name = cursor.getString(0);
        for (int i = 1; i < 31; i++) {
            ingredients.add(cursor.getString(i));
        }
        for (int i = 31; i < 61; i++) {
            instructions.add(cursor.getString(i));
        }

        int dairy_free = cursor.getInt(61);
        int gluten_free = cursor.getInt(62);
        int vegan = cursor.getInt(63);
        int vegetarian = cursor.getInt(64);

        for (int i = 65; i < 75; i++){
            tags.add(cursor.getString(i));
        }

        displayRecipe = new RecipeModel(context, name, ingredients, instructions, tags, dairy_free, gluten_free, vegan, vegetarian);

        return displayRecipe;
    }

    public List<String> dietaryFilter(Cursor cursor, int dairyFreeFilter, int glutenFreeFilter, int veganFilter, int vegetarianFilter, List<String> returnList){
        List<Integer> dairyFreeList = new ArrayList<>();
        List<Integer> glutenFreeList = new ArrayList<>();
        List<Integer> veganList = new ArrayList<>();
        List<Integer> vegetarianList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                int isRecipeDF = cursor.getInt(61);
                int isRecipeGF = cursor.getInt(62);
                int isRecipeVegan = cursor.getInt(63);
                int isRecipeVegetarian = cursor.getInt(64);
                dairyFreeList.add(isRecipeDF);
                glutenFreeList.add(isRecipeGF);
                veganList.add(isRecipeVegan);
                vegetarianList.add(isRecipeVegetarian);
                returnList.add(name);
            }
            while(cursor.moveToNext());
        }
        if (cursor.moveToFirst()) {
            do {
                if(dairyFreeFilter == 1){
                    for(int i = 0; i < returnList.size(); i++){
                        System.out.println(dairyFreeList.get(i));
                        if(dairyFreeList.get(i) != 1){
                            returnList.remove(i);
                        }
                    }
                }
                if(glutenFreeFilter == 1){
                    for(int i = 0; i < returnList.size(); i++){
                        if(glutenFreeList.get(i) != 1){
                            returnList.remove(i);
                        }
                    }
                }
                if(veganFilter == 1){
                    for(int i = 0; i < returnList.size(); i++){
                        if(veganList.get(i) != 1){
                            returnList.remove(i);
                        }
                    }
                }
                if(vegetarianFilter == 1){
                    for(int i = 0; i < returnList.size(); i++){
                        if(vegetarianList.get(i) != 1){
                            returnList.remove(i);
                        }
                    }
                }
            } while (cursor.moveToNext());
        }
        return returnList;
    }


    public List<String> filter(String searchInput, String table, int dairyFreeFilter, int glutenFreeFilter, int veganFilter, int vegetarianFilter) {
        String switchTable = "";
        switch (table) {
            case "Breakfast":
                switchTable = "BREAKFAST_TABLE";
                break;
            case "Lunch":
                switchTable = "LUNCH_TABLE";
                break;
            case "Dinner":
                switchTable = "DINNER_TABLE";
                break;
            case "Dessert":
                switchTable = "DESSERT_TABLE";
                break;
            case "Snacks":
                switchTable = "SNACKS_TABLE";
                break;
            case "MyBreakfast":
                switchTable = "MYBREAKFAST_TABLE";
                break;
            case "MyLunch":
                switchTable = "MYLUNCH_TABLE";
                break;
            case "MyDinner":
                switchTable = "MYDINNER_TABLE";
                break;
            case "MyDessert":
                switchTable = "MYDESSERT_TABLE";
                break;
            case "MySnacks":
                switchTable = "MYSNACKS_TABLE";
                break;
        }

        List<String> returnList = new ArrayList<>();


        String queryString = "SELECT * FROM " + switchTable + " WHERE RECIPE_NAME LIKE '%" + searchInput + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        returnList = dietaryFilter(cursor, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString2 = "SELECT * FROM " + switchTable + " WHERE TAG1 LIKE'%" + searchInput + "%'";
        Cursor cursor2 = db.rawQuery(queryString2, null);

        returnList = dietaryFilter(cursor2, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString3 = "SELECT * FROM " + switchTable + " WHERE TAG2 LIKE'%" + searchInput + "%'";
        Cursor cursor3 = db.rawQuery(queryString3, null);

        returnList = dietaryFilter(cursor3, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString4 = "SELECT * FROM " + switchTable + " WHERE TAG3 LIKE'%" + searchInput + "%'";
        Cursor cursor4 = db.rawQuery(queryString4, null);

        returnList = dietaryFilter(cursor4, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString5 = "SELECT * FROM " + switchTable + " WHERE TAG4 LIKE'%" + searchInput + "%'";
        Cursor cursor5 = db.rawQuery(queryString5, null);

        returnList = dietaryFilter(cursor5, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString6 = "SELECT * FROM " + switchTable + " WHERE TAG5 LIKE'%" + searchInput + "%'";
        Cursor cursor6 = db.rawQuery(queryString6, null);

        returnList = dietaryFilter(cursor6, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString7 = "SELECT * FROM " + switchTable + " WHERE TAG6 LIKE'%" + searchInput + "%'";
        Cursor cursor7 = db.rawQuery(queryString7, null);

        returnList = dietaryFilter(cursor7, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString8 = "SELECT * FROM " + switchTable + " WHERE TAG7 LIKE'%" + searchInput + "%'";
        Cursor cursor8 = db.rawQuery(queryString8, null);

        returnList = dietaryFilter(cursor8, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString9 = "SELECT * FROM " + switchTable + " WHERE TAG8 LIKE'%" + searchInput + "%'";
        Cursor cursor9 = db.rawQuery(queryString9, null);

        returnList = dietaryFilter(cursor9, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString10 = "SELECT * FROM " + switchTable + " WHERE TAG9 LIKE'%" + searchInput + "%'";
        Cursor cursor10 = db.rawQuery(queryString10, null);

        returnList = dietaryFilter(cursor10, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);


        String queryString11 = "SELECT * FROM " + switchTable + " WHERE TAG10 LIKE'%" + searchInput + "%'";
        Cursor cursor11 = db.rawQuery(queryString11, null);

        returnList = dietaryFilter(cursor11, dairyFreeFilter, glutenFreeFilter, veganFilter, vegetarianFilter, returnList);

        // make sure to close sqlite
        cursor.close();
        db.close();


        for(int i = 0; i < returnList.size(); i++){
            String recipeModel = returnList.get(i);
            for(int j = i + 1; j < returnList.size(); j++){
                String recipeModelComparison = returnList.get(j);
                String recipeModelName = recipeModel;
                String recipeModelComparisonName = recipeModelComparison;
                if(Objects.equals(recipeModelName, recipeModelComparisonName)){
                    returnList.remove(j);
                }
            }
            for(int k = 0; k < i; k++){
                String recipeModelComparison = returnList.get(k);
                String recipeModelName = recipeModel;
                String recipeModelComparisonName = recipeModelComparison;
                if(Objects.equals(recipeModelName, recipeModelComparisonName)){
                    returnList.remove(k);
                }
            }
        }
        return returnList;
    }


    public boolean checkMyRecipes(String recipeName, String table) {
        String switchTable = "";
        switch (table) {
            case "Breakfast":
                switchTable = "MYBREAKFAST_TABLE";
                break;
            case "Lunch":
                switchTable = "MYLUNCH_TABLE";
                break;
            case "Dinner":
                switchTable = "MYDINNER_TABLE";
                break;
            case "Dessert":
                switchTable = "MYDESSERT_TABLE";
                break;
            case "Snacks":
                switchTable = "MYSNACKS_TABLE";
                break;
        }
        String queryString = "SELECT * FROM " + switchTable + " WHERE RECIPE_NAME ='" + recipeName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}