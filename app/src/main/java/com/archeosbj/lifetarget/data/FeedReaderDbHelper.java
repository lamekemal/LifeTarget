package com.archeosbj.lifetarget.data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + databaseContract.dataEntry.TABLE_NAME + " (" +
                    databaseContract.dataEntry._ID + " INTEGER PRIMARY KEY," +
                    databaseContract.dataEntry.COLUMN_NAME_TITLE + " TEXT," +
                    databaseContract.dataEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + databaseContract.dataEntry.TABLE_NAME;


    public FeedReaderDbHelper(Context context) {
        super(context,  databaseContract.dataEntry.DATABASE_NAME, null,  databaseContract.dataEntry.DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public Cursor getWordMatches(String query, String[] columns) {
        String selection = databaseContract.dataEntry.COLUMN_NAME_TITLE + " MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(databaseContract.dataEntry.TABLE_NAME);

        Cursor cursor = builder.query(this.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
}