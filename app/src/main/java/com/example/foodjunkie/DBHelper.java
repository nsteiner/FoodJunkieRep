package com.example.foodjunkie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String INGNAME = "INGNAME";

    //fix
    private static String DB_PATH = "/data/data/com.example.foodjunkie/databases/";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "DefaultRecipes.db";
    private static final String TABLE_NAME = "PANTRY";
    public static final String COLUMN_NAME_QUANTITY = "quantity";
    public static final String COLUMN_NAME_UNIT = "unit";
    public static final String COLUMN_NAME_NAME = "name";

   /* private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_QUANTITY + " INTEGER," +
                    COLUMN_NAME_UNIT + " TEXT," +
                    COLUMN_NAME_NAME + " TEXT)";*/


    private final Context context;
    static SQLiteDatabase sqliteDataBase;

    //constructor
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_CREATE_ENTRIES);
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
    public void addOne(PantryModel pantryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        //getWritableDatabase() is included in SQLiteOpenHelper class, used to write to database
        //getReadableDatabase() is to reading database
        ContentValues cv = new ContentValues();
        //works like an associate array/hashmap; take pairs of values and pair them together

        //make pantry model class
        cv.put("UNIT", pantryModel.getUnit());
        cv.put("QUANTITY", pantryModel.getQuantity());
        cv.put("INGREDIENT", pantryModel.getIngredientName());
        db.insert("PANTRY", null, cv);

        //long insert = db.insert(table, null, cv);
    }

    public boolean checkEmpty(){
        String table = "PANTRY";
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
    public List<PantryModel> getAll() {
        List<PantryModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM PANTRY";
        SQLiteDatabase db = this.getReadableDatabase();
        //writable database locks it so others can't access it
        Cursor cursor = db.rawQuery(queryString, null);
        //^now have query to run, cursor is return type of query
        //when you ask for query, get result back (rows of items); cursor is this result set
        //next, want to loop through results, create new recipe object for each row; if statement checks if there's items in database; if moveToFirst is true, there's an item in that row
        if (cursor.moveToFirst()) {
            do {
                int quantity = cursor.getInt(0);
                String unit = cursor.getString(1);
                String ingredientName = cursor.getString(2);
                PantryModel newPantry = new PantryModel(context, quantity, unit, ingredientName);
                returnList.add(newPantry);
            } while (cursor.moveToNext());
        }

        // make sure to close sqlite
        cursor.close();
        db.close();
        return returnList;
    }

    public PantryModel getPantry(String ingName){
        String queryString = "SELECT * FROM PANTRY WHERE INGREDIENT ='" + ingName +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();


        PantryModel displayPantry;

        int quantity = cursor.getInt(0);
        String unit = cursor.getString(1);
        String ingredientName = cursor.getString(2);



        displayPantry = new PantryModel(context, quantity, unit, ingredientName);

        return displayPantry;
    }



}