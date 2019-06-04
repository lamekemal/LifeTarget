package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.archeosbj.lifetarget.Model.Serli;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_VERSION;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TABLE_NAME_SERLI;

public class serlidb extends SQLiteAssetHelper {


    public serlidb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Serli> getSerli(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id", "uniqueid", "name", "contact", "service", "description",
               "horaire" , "price", "pointfort", "extras", "siteweb",
                "payement", "primpimage", "galeryOne", "galerytwo", "galerytree",
                "galeryfour", "galeryfive", "galerysix", "zonelivre",
                "reserveone", "reservetwo", "maxlivre", "modified"};

        String tablename = TABLE_NAME_SERLI;

        qb.setTables(tablename);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Serli> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Serli Resto = new Serli();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setName(cursor.getString(cursor.getColumnIndex("name")));
                Resto.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                Resto.setHoraire(cursor.getString(cursor.getColumnIndex("horaire")));
                Resto.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                Resto.setPayement(cursor.getString(cursor.getColumnIndex("payement")));
                Resto.setZonelivre(cursor.getString(cursor.getColumnIndex("zonelivre")));
                Resto.setMaxlivre(cursor.getString(cursor.getColumnIndex("maxlivre")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setSiteweb(cursor.getString(cursor.getColumnIndex("siteweb")));
                Resto.setDescription(cursor.getString(cursor.getColumnIndex("description")));
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
                Resto.setMaxlivre(cursor.getString(cursor.getColumnIndex("maxlivre")));
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
        String tablename = TABLE_NAME_SERLI;

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

    public List<Serli> getSitesByNames(String title){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect={"id", "uniqueid", "name", "contact", "service", "description",
                "horaire" , "price", "pointfort", "extras", "siteweb",
                "payement", "primpimage", "galeryOne", "galerytwo", "galerytree",
                "galeryfour", "galeryfive", "galerysix", "zonelivre",
                "reserveone", "reservetwo", "maxlivre", "modified"};
        String tablename = TABLE_NAME_SERLI;
        qb.setTables(tablename);

        Cursor cursor = qb.query(db,sqlSelect,"name LIKE ?",new String[]{"%"+title+"%"},null,null,null);
        List<Serli> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Serli Resto = new Serli();
                Resto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                Resto.setName(cursor.getString(cursor.getColumnIndex("name")));
                Resto.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                Resto.setHoraire(cursor.getString(cursor.getColumnIndex("horaire")));
                Resto.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                Resto.setPayement(cursor.getString(cursor.getColumnIndex("payement")));
                Resto.setZonelivre(cursor.getString(cursor.getColumnIndex("zonelivre")));
                Resto.setMaxlivre(cursor.getString(cursor.getColumnIndex("maxlivre")));
                Resto.setUniqueid(cursor.getString(cursor.getColumnIndex("uniqueid")));
                Resto.setSiteweb(cursor.getString(cursor.getColumnIndex("siteweb")));
                Resto.setDescription(cursor.getString(cursor.getColumnIndex("description")));
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
                Resto.setMaxlivre(cursor.getString(cursor.getColumnIndex("maxlivre")));
                Resto.setReserveone(cursor.getString(cursor.getColumnIndex("reserveone")));
                Resto.setReservetwo(cursor.getString(cursor.getColumnIndex("reservetwo")));
                result.add(Resto);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
