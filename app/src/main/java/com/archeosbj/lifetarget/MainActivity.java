package com.archeosbj.lifetarget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.archeosbj.lifetarget.Model.Life;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Life life;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String[] life = intent.getStringArrayExtra("ITEM");
        TextView title = (TextView) findViewById(R.id.title);
        TextView adress = (TextView) findViewById(R.id.adress);
        TextView description = (TextView)findViewById(R.id.description);
        TextView rating = (TextView) findViewById(R.id.rating);

        title.setText(life[4]);
        adress.setText(life[1]);
        description.setText(life[2]);
        rating.setText(life[3]);
    }

}
