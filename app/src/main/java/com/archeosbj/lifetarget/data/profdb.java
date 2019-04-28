package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.profm;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_PROFILS;

public class profdb  extends SQLiteAssetHelper {
    public profdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<profm> getProfils(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id","foremail", "imgvar"};
        String tablename = TABLE_NAME_PROFILS;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<profm> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                profm Resto = new profm();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setForemail(cursor.getString(cursor.getColumnIndex("foremail")));
                Resto.setImgvar(cursor.getString(cursor.getColumnIndex("imgvar")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<profm> getProfByMail(String foremail){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","foremail", "imgvar"};
        String tablename = TABLE_NAME_PROFILS;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"foremail = ?",new String[]{foremail},null,null,null);
        List<profm> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                profm Resto = new profm();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setImgvar(cursor.getString(cursor.getColumnIndex("imgvar")));
                Resto.setForemail(cursor.getString(cursor.getColumnIndex("foremail")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
