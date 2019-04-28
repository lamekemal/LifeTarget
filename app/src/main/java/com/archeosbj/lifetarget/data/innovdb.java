package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.Innov;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_INNOV;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_TRANS;

public class innovdb  extends SQLiteAssetHelper {

    public innovdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Innov> getInnov(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id","uniqueid", "name", "contact", "service", "description",
                "innovname", "video", "bibliot", "stectn", "historq", "mail",
                "primpimage", "galeryOne", "galerytwo", "galerytree", "galeryfour",
                "galeryfive", "galerysix","reservetwo","modified"};
        String tablename = TABLE_NAME_INNOV;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Innov> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Innov Resto = new Innov();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setName(cursor.getString(cursor.getColumnIndex("name")));
                Resto.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                Resto.setInnovname(cursor.getString(cursor.getColumnIndex("innovname")));
                Resto.setVideo(cursor.getString(cursor.getColumnIndex("video")));
                Resto.setBibliot(cursor.getString(cursor.getColumnIndex("bibliot")));
                Resto.setMail(cursor.getString(cursor.getColumnIndex("mail")));
                Resto.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setStectn(cursor.getString(cursor.getColumnIndex("stectn")));
                Resto.setService(cursor.getString(cursor.getColumnIndex("service")));
                Resto.setHistorq(cursor.getString(cursor.getColumnIndex("historq")));
                Resto.setPrimpimage(cursor.getString(cursor.getColumnIndex("primpimage")));
                Resto.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Resto.setGalerytree(cursor.getString(cursor.getColumnIndex("galerytree")));
                Resto.setGaleryfour(cursor.getString(cursor.getColumnIndex("galeryfour")));
                Resto.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Resto.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Resto.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
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
        String tablename = TABLE_NAME_INNOV;

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

    public List<Innov> getInnovByNames(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","uniqueid", "name", "contact", "service", "description",
                "innovname", "video", "bibliot", "stectn", "historq", "mail",
                "primpimage", "galeryOne", "galerytwo", "galerytree", "galeryfour",
                "galeryfive", "galerysix","reservetwo","modified"};
        String tablename = TABLE_NAME_TRANS;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"Title LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<Innov> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Innov Resto = new Innov();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setName(cursor.getString(cursor.getColumnIndex("name")));
                Resto.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                Resto.setInnovname(cursor.getString(cursor.getColumnIndex("innovname")));
                Resto.setVideo(cursor.getString(cursor.getColumnIndex("video")));
                Resto.setBibliot(cursor.getString(cursor.getColumnIndex("bibliot")));
                Resto.setMail(cursor.getString(cursor.getColumnIndex("mail")));
                Resto.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setStectn(cursor.getString(cursor.getColumnIndex("stectn")));
                Resto.setService(cursor.getString(cursor.getColumnIndex("service")));
                Resto.setHistorq(cursor.getString(cursor.getColumnIndex("historq")));
                Resto.setPrimpimage(cursor.getString(cursor.getColumnIndex("primpimage")));
                Resto.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Resto.setGalerytree(cursor.getString(cursor.getColumnIndex("galerytree")));
                Resto.setGaleryfour(cursor.getString(cursor.getColumnIndex("galeryfour")));
                Resto.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Resto.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Resto.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Resto.setReservetwo(cursor.getString(cursor.getColumnIndex("reservetwo")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }
}