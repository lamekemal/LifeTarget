package com.archeosbj.lifetarget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.archeosbj.lifetarget.Adpter.GeneralMAdapter;
import com.archeosbj.lifetarget.Model.GeneralModel;
import com.archeosbj.lifetarget.data.lifedbtwo;

public class ListeDesFavoris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_favoris);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        lifedbtwo obase = new lifedbtwo(this);
        GeneralMAdapter xadapter = new GeneralMAdapter(this,obase.getFavList(),new GeneralMAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GeneralModel item) {
                //-------------------------------------
            }
        });
        recyclerView.setAdapter(xadapter);
    }
}
