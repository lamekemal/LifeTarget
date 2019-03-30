package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.Life;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME;

public class database extends SQLiteAssetHelper {


    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Life> getLife(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Id", "Title","Adress","Rating","Description"};
        String tablename = TABLE_NAME;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Life> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Life life = new Life();
                life.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                life.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                life.setAdress(cursor.getString(cursor.getColumnIndex("Adress")));
                life.setRating(cursor.getString(cursor.getColumnIndex("Rating")));
                life.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                result.add(life);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<String> getTitles(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Title"};
        String tablename = TABLE_NAME;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(cursor.getColumnIndex("Title")));
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<Life> getLifeByTitle(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"Id", "Title","Adress","Rating","Description"};
        String tablename = TABLE_NAME;
        qb.setTables(tablename);

        //this is querry fonction
        //if i want to get EXACT TITLE , i change
        //Cursor cursor = qb.query(db,sqlSelect,"Title = ?",new String[]{title},null,null,null);

        Cursor cursor = qb.query(db,sqlSelect,"Title LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<Life> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Life life = new Life();
                life.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                life.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                life.setAdress(cursor.getString(cursor.getColumnIndex("Adress")));
                life.setRating(cursor.getString(cursor.getColumnIndex("Rating")));
                life.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                result.add(life);
            }while (cursor.moveToNext());

        }
        return result;
    }
}
