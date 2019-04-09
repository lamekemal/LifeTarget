package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.Tour;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_SITES;

public class sitesdb extends SQLiteAssetHelper {


    public sitesdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Tour> getSites(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id", "uniqueid", "name", "contact", "service", "mail", "horaire", "price",
                "pointfort", "extras", "localisation", "more", "primpimage", "galeryOne",
                "galerytwo","galerytree", "galeryfour", "galeryfive", "galerysix",
                "galeryseven", "reserveone", "reservetwo", "plusStr", "modified"};

        String tablename = TABLE_NAME_SITES;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Tour> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Tour Resto = new Tour();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setName(cursor.getString(cursor.getColumnIndex("name")));
                Resto.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                Resto.setHoraire(cursor.getString(cursor.getColumnIndex("horaire")));
                Resto.setMore(cursor.getString(cursor.getColumnIndex("more")));
                Resto.setMail(cursor.getString(cursor.getColumnIndex("mail")));
                Resto.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                Resto.setLocalisation(cursor.getString(cursor.getColumnIndex("localisation")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setPlusStr(cursor.getString(cursor.getColumnIndex("plusStr")));
                Resto.setService(cursor.getString(cursor.getColumnIndex("service")));
                Resto.setPointfort(cursor.getString(cursor.getColumnIndex("pointfort")));
                Resto.setExtras(cursor.getString(cursor.getColumnIndex("extras")));
                Resto.setPrimpimage(cursor.getString(cursor.getColumnIndex("primpimage")));
                Resto.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Resto.setGalerytree(cursor.getString(cursor.getColumnIndex("galerytree")));
                Resto.setGaleryfour(cursor.getString(cursor.getColumnIndex("galeryfour")));
                Resto.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Resto.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Resto.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Resto.setGaleryseven(cursor.getString(cursor.getColumnIndex("galeryseven")));
                Resto.setReserveone(cursor.getString(cursor.getColumnIndex("reserveone")));
                Resto.setReservetwo(cursor.getString(cursor.getColumnIndex("reservetwo")));
                result.add(Resto);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<String> getNames(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"name"};
        String tablename = TABLE_NAME_SITES;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(cursor.getColumnIndex("name")));
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<Tour> getSitesByNames(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id", "uniqueid", "name", "contact", "service", "mail", "horaire", "price",
                "pointfort", "extras", "localisation", "more", "primpimage", "galeryOne",
                "galerytwo","galerytree", "galeryfour", "galeryfive", "galerysix",
                "galeryseven", "reserveone", "reservetwo", "plusStr", "modified"};
        String tablename = TABLE_NAME_SITES;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"Title LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<Tour> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Tour Resto = new Tour();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setName(cursor.getString(cursor.getColumnIndex("name")));
                Resto.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                Resto.setHoraire(cursor.getString(cursor.getColumnIndex("horaire")));
                Resto.setMore(cursor.getString(cursor.getColumnIndex("more")));
                Resto.setMail(cursor.getString(cursor.getColumnIndex("mail")));
                Resto.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                Resto.setLocalisation(cursor.getString(cursor.getColumnIndex("localisation")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setPlusStr(cursor.getString(cursor.getColumnIndex("plusStr")));
                Resto.setService(cursor.getString(cursor.getColumnIndex("service")));
                Resto.setPointfort(cursor.getString(cursor.getColumnIndex("pointfort")));
                Resto.setExtras(cursor.getString(cursor.getColumnIndex("extras")));
                Resto.setPrimpimage(cursor.getString(cursor.getColumnIndex("primpimage")));
                Resto.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Resto.setGalerytree(cursor.getString(cursor.getColumnIndex("galerytree")));
                Resto.setGaleryfour(cursor.getString(cursor.getColumnIndex("galeryfor")));
                Resto.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Resto.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Resto.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Resto.setGaleryseven(cursor.getString(cursor.getColumnIndex("galeryseven")));
                Resto.setReserveone(cursor.getString(cursor.getColumnIndex("reserveone")));
                Resto.setReservetwo(cursor.getString(cursor.getColumnIndex("reservetwo")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
