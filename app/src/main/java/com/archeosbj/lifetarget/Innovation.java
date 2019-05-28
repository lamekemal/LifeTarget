package com.archeosbj.lifetarget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archeosbj.lifetarget.Adpter.InnovImageAdapter;
import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.ArrayList;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

public class Innovation extends AppCompatActivity {
    String Id;
    String Name;
    String Contact;
    String Uniqueid;
    String Service;
    String Mail;
    String Video;
    String Primpimage;
    String GaleryOne;
    String Galerytwo;
    String Galerytree;
    String Galeryfour;
    String Galeryfive;
    String Galerysix;
    String Description;
    String Bibliot;
    String Stectn;
    String Historq;
    String Reservetwo;
    String Innovname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innovation);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
       // toolbar.setTitle("Innovation");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolBar);

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String[] RestoItm = intent.getStringArrayExtra("ITEM");
        Id = RestoItm[1];
        Name = RestoItm[2] ;
        Contact = RestoItm[3] ;
        Uniqueid = RestoItm[4] ;
        Service = RestoItm[5] ;
        Mail = RestoItm[6] ;
        Video = RestoItm[7] ;
        Primpimage = RestoItm[8];
        GaleryOne = RestoItm[9];
        Galerytwo = RestoItm[10];
        Galerytree = RestoItm[11];
        Galeryfour = RestoItm[12];
        Galeryfive = RestoItm[13];
        Galerysix = RestoItm[14];
        Description = RestoItm[15];
        Bibliot = RestoItm[16];
        Stectn = RestoItm[17];
        Historq = RestoItm[18];
        Reservetwo = RestoItm[19];
        Innovname = RestoItm[20];
        collapsingToolbarLayout.setTitle(Innovname);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fabshar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Life Target");
                intent.putExtra(Intent.EXTRA_TEXT, "\n Telecharge Life Target sur le PlayStore pour découvrir c'est innovation: " + Innovname);
                startActivity(Intent.createChooser(intent,  "Partager par"));
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.innovRecycler);
        ImageView imagePrm = (ImageView)findViewById(R.id.infoProduitImage);

        ArrayList<Uri> imageList = new ArrayList<>();
        imageList.add(0,myIMGUri(Primpimage));
        imageList.add(1,myIMGUri(GaleryOne));
        imageList.add(2,myIMGUri(Galerytwo));
        imageList.add(3,myIMGUri(Galerytree));
        imageList.add(4,myIMGUri(Galeryfour));
        imageList.add(5,myIMGUri(Galeryfive));
        imageList.add(6,myIMGUri(Galerysix));
        Storage storage = new Storage(this);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + Primpimage;
        setBitmapImageFormSD(fileph,imagePrm);

        TextView textInnovDesc = (TextView) findViewById(R.id.textInnovDesc);
        TextView innoCreate = (TextView) findViewById(R.id.innoCreate);
        TextView societyText = (TextView) findViewById(R.id.societyText);
        TextView innoHistor = (TextView) findViewById(R.id.innoHistor);
        TextView innoBibliot = (TextView) findViewById(R.id.innoBibliot);
        innoBibliot.setText(Bibliot);
        innoHistor.setText(Historq);
        textInnovDesc.setText(Description);
        societyText.setText(Stectn);
        innoCreate.setText(Name);
        InnovImageAdapter adapter = new InnovImageAdapter(this, imageList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    Uri myIMGUri(String LocalIMGtoURI){
        Storage storage = new Storage(this);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + LocalIMGtoURI;

        return Uri.fromFile(new File(fileph));
    }
    void setBitmapImageFormSD(String filepath, ImageView ImgV){
        Glide.with(getApplicationContext())
                .load(Uri.fromFile(new File(filepath)))
                .into(ImgV);
    }
}
