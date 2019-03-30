package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.Life;
import com.archeosbj.lifetarget.Model.resto;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_RESTO;

public class restodb extends SQLiteAssetHelper {


    public restodb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<resto> getResto(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id","title", "adress", "horaire", "siteweb", "description", "longdescription",
                "uniqueid", "rating", "service", "pointfort", "pointfaible", "prinpimage", "galeryOne", "galerytwo",
                "galeryfor", "galeryfive", "galerysix", "mets", "modified"};

        String tablename = TABLE_NAME_RESTO;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<resto> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                resto Resto = new resto();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                Resto.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
                Resto.setHoraire(cursor.getString(cursor.getColumnIndex("horaire")));
                Resto.setSiteweb(cursor.getString(cursor.getColumnIndex("siteweb")));
                Resto.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                Resto.setLongdescription(cursor.getString(cursor.getColumnIndex("longdescription")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setRating(cursor.getString(cursor.getColumnIndex("rating")));
                Resto.setService(cursor.getString(cursor.getColumnIndex("service")));
                Resto.setPointfort(cursor.getString(cursor.getColumnIndex("pointfort")));
                Resto.setPointfaible(cursor.getString(cursor.getColumnIndex("pointfaible")));
                Resto.setPrinpimage(cursor.getString(cursor.getColumnIndex("prinpimage")));
                Resto.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Resto.setGaleryfor(cursor.getString(cursor.getColumnIndex("galeryfor")));
                Resto.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Resto.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Resto.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Resto.setMets(cursor.getString(cursor.getColumnIndex("mets")));
                Resto.setModified(cursor.getString(cursor.getColumnIndex("modified")));
                result.add(Resto);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<String> getTitles(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"title"};
        String tablename = TABLE_NAME_RESTO;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(cursor.getColumnIndex("title")));
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<resto> getRestoByTitle(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","title", "adress", "horaire", "siteweb", "description", "longdescription",
                "uniqueid", "rating", "service", "pointfort", "pointfaible", "prinpimage", "galeryOne", "galerytwo",
                "galeryfor", "galeryfive", "galerysix", "mets", "modified"};
        String tablename = TABLE_NAME_RESTO;
        qb.setTables(tablename);

        //this is querry fonction
        //if i want to get EXACT TITLE , i change
        //Cursor cursor = qb.query(db,sqlSelect,"Title = ?",new String[]{title},null,null,null);

        Cursor cursor = qb.query(db,sqlSelect,"Title LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<resto> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                resto Resto = new resto();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                Resto.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
                Resto.setHoraire(cursor.getString(cursor.getColumnIndex("horaire")));
                Resto.setSiteweb(cursor.getString(cursor.getColumnIndex("siteweb")));
                Resto.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                Resto.setLongdescription(cursor.getString(cursor.getColumnIndex("longdescription")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setRating(cursor.getString(cursor.getColumnIndex("rating")));
                Resto.setService(cursor.getString(cursor.getColumnIndex("service")));
                Resto.setPointfort(cursor.getString(cursor.getColumnIndex("pointfort")));
                Resto.setPointfaible(cursor.getString(cursor.getColumnIndex("pointfaible")));
                Resto.setPrinpimage(cursor.getString(cursor.getColumnIndex("prinpimage")));
                Resto.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Resto.setGaleryfor(cursor.getString(cursor.getColumnIndex("galeryfor")));
                Resto.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Resto.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Resto.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Resto.setMets(cursor.getString(cursor.getColumnIndex("mets")));
                Resto.setModified(cursor.getString(cursor.getColumnIndex("modified")));
                result.add(Resto);
            }while (cursor.moveToNext());

        }
        return result;
    }
}
