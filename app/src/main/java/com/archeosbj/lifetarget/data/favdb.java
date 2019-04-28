package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.fav;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_FAV;

public class favdb extends SQLiteAssetHelper {
    public favdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<fav> getFav(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id","fvuniqid", "byemail", "boolvar", "genre", "created_at"};
        String tablename = TABLE_NAME_FAV;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<fav> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                fav Resto = new fav();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setFvuniqid(cursor.getString(cursor.getColumnIndex("fvuniqid")));
                Resto.setByemail(cursor.getString(cursor.getColumnIndex("byemail")));
                Resto.setBoolvar(cursor.getString(cursor.getColumnIndex("boolvar")));
                Resto.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
                Resto.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<fav> getFavByFvUniq(String fvuniqid){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","fvuniqid", "byemail", "boolvar", "genre", "created_at"};
        String tablename = TABLE_NAME_FAV;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"fvuniqid = ?",new String[]{fvuniqid},null,null,null);
        List<fav> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                fav Resto = new fav();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setFvuniqid(cursor.getString(cursor.getColumnIndex("fvuniqid")));
                Resto.setByemail(cursor.getString(cursor.getColumnIndex("byemail")));
                Resto.setBoolvar(cursor.getString(cursor.getColumnIndex("boolvar")));
                Resto.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
                Resto.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<fav> getFavByFvuniqAndEmail(String fvuniqid, String byemail){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","fvuniqid", "byemail", "boolvar", "genre", "created_at"};
        String tablename = TABLE_NAME_FAV;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"fvuniqid = ? AND byemail = ?",new String[]{fvuniqid,byemail},null,null,null);
        List<fav> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                fav Resto = new fav();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setFvuniqid(cursor.getString(cursor.getColumnIndex("fvuniqid")));
                Resto.setByemail(cursor.getString(cursor.getColumnIndex("byemail")));
                Resto.setBoolvar(cursor.getString(cursor.getColumnIndex("boolvar")));
                Resto.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
                Resto.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
