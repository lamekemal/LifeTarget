/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */
package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.GeneralModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_FAVL;

public class lifedbtwo extends SQLiteAssetHelper {


    public lifedbtwo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<GeneralModel> getFavList(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Id", "Title","Adress","Rating","Description","urlimg", "uniqId"};
        String tablename = TABLE_NAME_FAVL;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<GeneralModel> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                GeneralModel life = new GeneralModel();
                life.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                life.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                life.setAdress(cursor.getString(cursor.getColumnIndex("Adress")));
                life.setRating(cursor.getString(cursor.getColumnIndex("Rating")));
                life.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                life.setUrlimage(cursor.getString(cursor.getColumnIndex("urlimg")));
                life.setUniqId(cursor.getString(cursor.getColumnIndex("uniqId")));
                result.add(life);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<GeneralModel> getFavListByTitle(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"Id", "Title","Adress","Rating","Description","urlimg", "uniqId"};
        String tablename = TABLE_NAME_FAVL;
        qb.setTables(tablename);

        //this is querry fonction
        //if i want to get EXACT TITLE , i change
        //Cursor cursor = qb.query(db,sqlSelect,"Title = ?",new String[]{title},null,null,null);

        Cursor cursor = qb.query(db,sqlSelect,"Title LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<GeneralModel> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                GeneralModel life = new GeneralModel();
                life.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                life.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                life.setAdress(cursor.getString(cursor.getColumnIndex("Adress")));
                life.setRating(cursor.getString(cursor.getColumnIndex("Rating")));
                life.setDescription(cursor.getString(cursor.getColumnIndex("Description")));
                life.setUrlimage(cursor.getString(cursor.getColumnIndex("urlimg")));
                life.setUniqId(cursor.getString(cursor.getColumnIndex("uniqId")));
                result.add(life);
            }while (cursor.moveToNext());

        }
        return result;
    }
}
