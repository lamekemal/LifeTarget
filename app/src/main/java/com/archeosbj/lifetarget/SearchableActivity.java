package com.archeosbj.lifetarget;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.archeosbj.lifetarget.data.FeedReaderDbHelper;

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            FeedReaderDbHelper db = new FeedReaderDbHelper(getApplicationContext());
            Cursor c = db.getWordMatches(query, null);
            //doMySearch(query);
        }
    }


}
