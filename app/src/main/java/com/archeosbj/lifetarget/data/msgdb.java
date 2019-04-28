/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */

package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.msgm;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_MSG;

public class msgdb  extends SQLiteAssetHelper {
    public msgdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<msgm> getMessage(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id","formsg", "ofmsg","msg", "onmsg","created_at"};
        String tablename = TABLE_NAME_MSG;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<msgm> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                msgm Resto = new msgm();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setFormsg(cursor.getString(cursor.getColumnIndex("formsg")));
                Resto.setOfmsg(cursor.getString(cursor.getColumnIndex("ofmsg")));
                Resto.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
                Resto.setOnmsg(cursor.getString(cursor.getColumnIndex("onmsg")));
                Resto.setCreated(cursor.getString(cursor.getColumnIndex("created_at")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<msgm> getMessageByFormsg(String formsg){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","formsg", "ofmsg","msg", "onmsg","created_at"};
        String tablename = TABLE_NAME_MSG;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"formsg = ?",new String[]{formsg},null,null,null);
        List<msgm> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                msgm Resto = new msgm();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setFormsg(cursor.getString(cursor.getColumnIndex("formsg")));
                Resto.setOfmsg(cursor.getString(cursor.getColumnIndex("ofmsg")));
                Resto.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
                Resto.setOnmsg(cursor.getString(cursor.getColumnIndex("onmsg")));
                Resto.setCreated(cursor.getString(cursor.getColumnIndex("created_at")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }


    public List<msgm> getMessageByOfmsg(String ofmsg){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","formsg", "ofmsg","msg", "onmsg","created_at"};
        String tablename = TABLE_NAME_MSG;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"ofmsg = ?",new String[]{ofmsg},null,null,null);
        List<msgm> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                msgm Resto = new msgm();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setFormsg(cursor.getString(cursor.getColumnIndex("formsg")));
                Resto.setOfmsg(cursor.getString(cursor.getColumnIndex("ofmsg")));
                Resto.setMsg(cursor.getString(cursor.getColumnIndex("msg")));
                Resto.setOnmsg(cursor.getString(cursor.getColumnIndex("onmsg")));
                Resto.setCreated(cursor.getString(cursor.getColumnIndex("created_at")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
