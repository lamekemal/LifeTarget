package com.archeosbj.lifetarget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import java.io.File;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;
import static com.archeosbj.lifetarget.hotelviwer.decodeSampledBitmapFromResource;

public class TourItmViewer extends AppCompatActivity {
    private DrawerLayout drawer;
    private boolean isVisibleFloat = false;
    private FloatingActionButton fabservice;
    private FloatingActionButton fabcservice;
    private FloatingActionButton fabshare;
    private FloatingActionButton fabfavorite;

    String Id;
    String Name;
    String Contact;
    String Horaire;
    String Mail;
    String Uniqueid;
    String Service;
    String Pointfort;
    String Price;
    String Primpimage;
    String GaleryOne;
    String Galerytwo;
    String Galerytree;
    String Galeryfour;
    String Galeryfive;
    String Galerysix;
    String Galeryseven;
    String Localisation;
    String More;
    String Reserveone;
    String Reservetwo;
    String Extras;
    String PlusStr;

    Context contxent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_itm_viewer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = findViewById(R.id.fab);
        fabservice = findViewById(R.id.service);
        fabcservice = findViewById(R.id.custumservice);
        fabshare = findViewById(R.id.share);
        fabfavorite = findViewById(R.id.favorite);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        FloatingActionButton fabplus = findViewById(R.id.faba);
        fabplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isVisibleFloat == false){
                   fabservice.show();
                   fabcservice.show();
                   fabshare.show();
                   fabfavorite.show();
                   isVisibleFloat = true;
               }else if(isVisibleFloat == true){
                   fabservice.hide();
                   fabcservice.hide();
                   fabshare.hide();
                   fabfavorite.hide();
                   isVisibleFloat = false;
               }
            }
        });


        //basics
        Intent intent = getIntent();
        String[] RestoItm = intent.getStringArrayExtra("ITEM");

        Id =RestoItm[1] ;
        Name =RestoItm[2] ;
        Contact =RestoItm[3] ;
        Horaire =RestoItm[4] ;
        Mail =RestoItm[5] ;
        Uniqueid =RestoItm[6] ;
        Service =RestoItm[7] ;
        Pointfort =RestoItm[8] ;
        Price =RestoItm[9] ;
        Primpimage =RestoItm[10];
        GaleryOne =RestoItm[11];
        Galerytwo =RestoItm[12];
        Galerytree =RestoItm[13];
        Galeryfour =RestoItm[14];
        Galeryfive =RestoItm[15];
        Galerysix =RestoItm[16];
        Galeryseven =RestoItm[17];
        Localisation =RestoItm[18];
        More =RestoItm[19];
        Reserveone =RestoItm[20];
        Reservetwo =RestoItm[21];
        Extras =RestoItm[22];
        PlusStr =RestoItm[23];

        applyIntentItemArry();
    }

    private void applyIntentItemArry() {

        TextView ctrLocalisation = (TextView) findViewById(R.id.text_localisation);
        TextView ctrTitle = (TextView) findViewById(R.id.text_title);
        TextView ctrPointfort = (TextView) findViewById(R.id.service_text);
        //TextView ctrExtras = (TextView) findViewById(R.id.service_text);

        final ImageView ctrPrinpimage = (ImageView) findViewById(R.id.image_primp);
        final ImageView ctrGaleryOne = (ImageView) findViewById(R.id.galery_one);
        final ImageView ctrGalerytwo = (ImageView) findViewById(R.id.galery_two);
        final ImageView ctrGalerytree = (ImageView) findViewById(R.id.galery_tree);
        final ImageView ctrGaleryfor = (ImageView) findViewById(R.id.galery_four);
        final ImageView ctrGaleryfive = (ImageView) findViewById(R.id.galery_five);
        final ImageView ctrGalerysix = (ImageView) findViewById(R.id.galery_six);
        final ImageView ctrGaleryseven = (ImageView) findViewById(R.id.galery_seven);

        final NavigationView Navview = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = Navview.getMenu();
        final MenuItem ctrdescription = menu.findItem(R.id.activity_main_alerts_menu_item);

        ctrdescription.setTitle(Extras);

        Storage storage = new Storage(contxent);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";

        String fileph = newDiri + File.separator + Primpimage;
        setBitmapImageFormSD(fileph,ctrPrinpimage);

        String gone = newDiri + File.separator + GaleryOne;
        setBitmapImageFormSD(gone,ctrGaleryOne);

        String gtwo = newDiri + File.separator + Galerytwo;
        setBitmapImageFormSD(gtwo,ctrGalerytwo);

        String gtree = newDiri + File.separator + Galerytree;
        setBitmapImageFormSD(gtree,ctrGalerytree);

        String gfor = newDiri + File.separator + Galeryfour;
        setBitmapImageFormSD(gfor,ctrGaleryfor);

        String gfive = newDiri + File.separator + Galeryfive;
        setBitmapImageFormSD(gfive,ctrGaleryfive);

        String gsix = newDiri + File.separator + Galerysix;
        setBitmapImageFormSD(gsix,ctrGalerysix);

        String gseven = newDiri + File.separator + Galeryseven;
        setBitmapImageFormSD(gseven,ctrGaleryseven);
    }
    void setBitmapImageFormSD(String filepath, ImageView ImgV){
        Glide.with(getApplicationContext())
                .load(Uri.fromFile(new File(filepath)))
                .into(ImgV);
    }
    public static void setBitmapImageFormMomory(String filepath, ImageView ImgV){
        int reqWidth = 500;
        int reqHeight =   500;
        Log.e("ImageSIZE", String.valueOf(reqWidth));
        Bitmap bm =  decodeSampledBitmapFromResource(filepath,reqWidth,reqHeight);
        ImgV.setImageBitmap(bm);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
