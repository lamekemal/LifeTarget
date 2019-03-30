package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.hotel;
import com.archeosbj.lifetarget.Model.resto;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_HOTEL;

public class hoteldb extends SQLiteAssetHelper {

    public hoteldb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<hotel> getHotel(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id","title", "adress", "payement", "siteweb", "description", "longdescription",
                "uniqueid", "rating", "service", "pointfort", "pointfaible", "prinpimage", "galeryOne", "galerytwo",
                "galeryfor", "galeryfive", "galerysix", "mets", "modified"};
        String tablename = TABLE_NAME_HOTEL;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<hotel> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                hotel Hotel = new hotel();
                Hotel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Hotel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                Hotel.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
                Hotel.setPayement(cursor.getString(cursor.getColumnIndex("payement")));
                Hotel.setSiteweb(cursor.getString(cursor.getColumnIndex("siteweb")));
                Hotel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                Hotel.setLongdescription(cursor.getString(cursor.getColumnIndex("longdescription")));
                Hotel.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Hotel.setRating(cursor.getString(cursor.getColumnIndex("rating")));
                Hotel.setService(cursor.getString(cursor.getColumnIndex("service")));
                Hotel.setPointfort(cursor.getString(cursor.getColumnIndex("pointfort")));
                Hotel.setPointfaible(cursor.getString(cursor.getColumnIndex("pointfaible")));
                Hotel.setPrinpimage(cursor.getString(cursor.getColumnIndex("prinpimage")));
                Hotel.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Hotel.setGaleryfor(cursor.getString(cursor.getColumnIndex("galeryfor")));
                Hotel.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Hotel.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Hotel.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Hotel.setMets(cursor.getString(cursor.getColumnIndex("mets")));
                Hotel.setModified(cursor.getString(cursor.getColumnIndex("modified")));
                result.add(Hotel);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<String> getTitles(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"titles"};
        String tablename = TABLE_NAME_HOTEL;

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

    public List<hotel> getHotelByTitle(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id","title", "adress", "payement", "siteweb", "description", "longdescription",
                "uniqueid", "rating", "service", "pointfort", "pointfaible", "prinpimage", "galeryOne", "galerytwo",
                "galeryfor", "galeryfive", "galerysix", "mets", "modified"};
        String tablename = TABLE_NAME_HOTEL;
        qb.setTables(tablename);

        //this is querry fonction
        //if i want to get EXACT TITLE , i change
        //Cursor cursor = qb.query(db,sqlSelect,"Title = ?",new String[]{title},null,null,null);

        Cursor cursor = qb.query(db,sqlSelect,"Title LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<hotel> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                hotel Hotel = new hotel();
                Hotel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Hotel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                Hotel.setAdress(cursor.getString(cursor.getColumnIndex("adress")));
                Hotel.setPayement(cursor.getString(cursor.getColumnIndex("payement")));
                Hotel.setSiteweb(cursor.getString(cursor.getColumnIndex("siteweb")));
                Hotel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                Hotel.setLongdescription(cursor.getString(cursor.getColumnIndex("longdescription")));
                Hotel.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Hotel.setRating(cursor.getString(cursor.getColumnIndex("rating")));
                Hotel.setService(cursor.getString(cursor.getColumnIndex("service")));
                Hotel.setPointfort(cursor.getString(cursor.getColumnIndex("pointfort")));
                Hotel.setPointfaible(cursor.getString(cursor.getColumnIndex("pointfaible")));
                Hotel.setPrinpimage(cursor.getString(cursor.getColumnIndex("prinpimage")));
                Hotel.setGaleryOne(cursor.getString(cursor.getColumnIndex("galeryOne")));
                Hotel.setGaleryfor(cursor.getString(cursor.getColumnIndex("galeryfor")));
                Hotel.setGalerytwo(cursor.getString(cursor.getColumnIndex("galerytwo")));
                Hotel.setGaleryfive(cursor.getString(cursor.getColumnIndex("galeryfive")));
                Hotel.setGalerysix(cursor.getString(cursor.getColumnIndex("galerysix")));
                Hotel.setMets(cursor.getString(cursor.getColumnIndex("mets")));
                Hotel.setModified(cursor.getString(cursor.getColumnIndex("modified")));
                result.add(Hotel);
            }while (cursor.moveToNext());

        }
        return result;
    }
}
