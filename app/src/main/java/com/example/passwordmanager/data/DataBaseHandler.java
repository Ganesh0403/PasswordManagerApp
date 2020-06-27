package com.example.passwordmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.passwordmanager.model.Item;
import com.example.passwordmanager.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private final Context context;

    public DataBaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_TITLE_NAME + " TEXT,"
                + Constants.KEY_ITEM_PASS + " TEXT,"
                + Constants.KEY_DATE_NAME + " LONG);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TITLE_NAME,item.getWebName());
        values.put(Constants.KEY_ITEM_PASS,item.getPassword());
        values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME,null,values);
        db.close();
    }
    public Item getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID,
                        Constants.KEY_TITLE_NAME,Constants.KEY_ITEM_PASS,
                        Constants.KEY_DATE_NAME},Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);


        if (cursor!=null){
            cursor.moveToFirst();
        }

        Item item = new Item();
        if(cursor!=null)
        {
            item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            item.setWebName(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE_NAME)));
            item.setPassword(cursor.getString(cursor.getColumnIndex(Constants.KEY_ITEM_PASS)));

            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                    .getTime());

            item.setDateItemAdded(formattedDate);
        }

        return item;
    }
    public List<Item> getAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Item> itemList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID,
                        Constants.KEY_TITLE_NAME,Constants.KEY_ITEM_PASS
                        ,Constants.KEY_DATE_NAME},null,
                null,null,null,Constants.KEY_DATE_NAME + " DESC");

        if(cursor.moveToFirst()){
            do{
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                item.setWebName(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE_NAME)));
                item.setPassword(cursor.getString(cursor.getColumnIndex(Constants.KEY_ITEM_PASS)));

                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                        .getTime());

                item.setDateItemAdded(formattedDate);

                itemList.add(item);
            }while(cursor.moveToNext());
        }
        return itemList;
    }
    public int updateItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TITLE_NAME,item.getWebName());
        values.put(Constants.KEY_ITEM_PASS,item.getPassword());
        values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());

        return db.update(Constants.TABLE_NAME,values,Constants.KEY_ID + "=?", new String[]{String.valueOf(item.getId())});
    }
    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.TABLE_NAME,Constants.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public int getItemsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String select_all = "SELECT * FROM " + Constants.TABLE_NAME;

        Cursor cursor = db.rawQuery(select_all,null);

        return cursor.getCount();
    }
}
