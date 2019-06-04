package com.archeosbj.lifetarget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.archeosbj.lifetarget.PreferenceTools.TinyDB;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.loginandregistration.app.AppController;
import com.archeosbj.lifetarget.loginandregistration.helper.SQLiteHandler;
import com.archeosbj.lifetarget.loginandregistration.helper.SessionManager;
import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        final String Tcken = intent.getStringExtra("ITEM");
        TinyDB tinydb = new TinyDB(getApplicationContext());
        final String[] RestoItm =tinydb.getArryString(Tcken);
        tinydb.remove(Tcken);

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
        fabactionservice();
    }
    void applyimageck(ImageView img, final String first){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), imageViewer.class);
                String[] RestoItm = new String[7];
                RestoItm[0] = first;
                RestoItm[1] = Primpimage;
                RestoItm[2] = Galerysix ;
                RestoItm[3] = Galerytwo ;
                RestoItm[4] = Galeryfour ;
                RestoItm[5] = Galeryfive;
                RestoItm[6] = GaleryOne;
                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
    }
    private void applyIntentItemArry() {

        TextView ctrLocalisation = (TextView) findViewById(R.id.text_localisation);
        TextView ctrTitle = (TextView) findViewById(R.id.text_title);
       // TextView ctrPointfort = (TextView) findViewById(R.id.service_text);
        //TextView ctrExtras = (TextView) findViewById(R.id.service_text);

        ctrLocalisation.setText(Localisation);
        ctrTitle.setText(Name);
        //ctrPointfort.setText(Pointfort);

        final ImageView ctrPrinpimage = (ImageView) findViewById(R.id.image_primp);
        final ImageView ctrGaleryOne = (ImageView) findViewById(R.id.galery_one);
        final ImageView ctrGalerytwo = (ImageView) findViewById(R.id.galery_two);
        final ImageView ctrGalerytree = (ImageView) findViewById(R.id.galery_tree);
        final ImageView ctrGaleryfor = (ImageView) findViewById(R.id.galery_four);
        final ImageView ctrGaleryfive = (ImageView) findViewById(R.id.galery_five);
        final ImageView ctrGalerysix = (ImageView) findViewById(R.id.galery_six);
        final ImageView ctrGaleryseven = (ImageView) findViewById(R.id.galery_seven);
        applyimageck(ctrGaleryOne,GaleryOne);
        applyimageck(ctrGalerytwo,Galerytwo);
        applyimageck(ctrGalerytree,Galerytree);
        applyimageck(ctrGaleryfor,Galeryfour);
        applyimageck(ctrGaleryfive,Galeryfive);
        applyimageck(ctrGalerysix,Galerysix);

        final NavigationView Navview = (NavigationView) findViewById(R.id.nav_view);
        View hView =  Navview.getHeaderView(0);
        TextView nav_desc = (TextView)hView.findViewById(R.id.dskText);
        /*Menu menu = Navview.getMenu();
        final MenuItem ctrdescription = menu.findItem(R.id.activity_main_alerts_menu_item);

        ctrdescription.setTitle(Extras);*/

         Button btndec = (Button) hView.findViewById(R.id.buttondec);
            btndec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ListeDesFavoris.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
                }
            });

        nav_desc.setText(Extras);
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

    void fabactionservice(){
        //fab action
        final SessionManager session = new SessionManager(getApplicationContext());
        fabservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()) {
                    SQLiteHandler db;
                    db = new SQLiteHandler(getApplicationContext());

                    HashMap<String, String> user = db.getUserDetails();
                    Intent intent = new Intent(getApplicationContext(), Main4cservice.class);
                    String name = user.get("name");
                    String email = user.get("settings");
                    String[] HotelItm = new String[28];
                    HotelItm[0] = "";
                    HotelItm[1] = name;
                    HotelItm[2] = email;
                    HotelItm[3] = Name;
                    HotelItm[4] = Uniqueid;
                    HotelItm[5] = Service;
                    //HotelItm[6] = Rating;
                    //HotelItm[7] = Pointfaible;
                    HotelItm[8] = Pointfort;
                    intent.putExtra("ITEM",HotelItm);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);

                }else{
                    Snackbar.make( v,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                            .setAction("Se connecter", null).show();
                }
            }
        });
        fabcservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()) {
                    SQLiteHandler db;
                    db = new SQLiteHandler(getApplicationContext());

                    HashMap<String, String> user = db.getUserDetails();
                    Intent intent = new Intent(getApplicationContext(), Main4cservice.class);
                    String name = user.get("name");
                    String email = user.get("settings");
                    String[] HotelItm = new String[28];
                    HotelItm[0] = "";
                    HotelItm[1] = name;
                    HotelItm[2] = email;
                    HotelItm[3] = Name;
                    HotelItm[4] = Uniqueid;
                    HotelItm[5] = Service;
                    //HotelItm[6] = Rating;
                    //HotelItm[7] = Pointfaible;
                    HotelItm[8] = Pointfort;
                    intent.putExtra("ITEM",HotelItm);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);

                }else{
                    Snackbar.make( v,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                            .setAction("Se connecter", null).show();
                }
            }
        });
        fabshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()) {
                    SQLiteHandler db;
                    db = new SQLiteHandler(getApplicationContext());

                    HashMap<String, String> user = db.getUserDetails();
                    String name = user.get("name");
                    String email = user.get("settings");
                    String title =  Name;
                    String uniqi =  Uniqueid;
                    String userURL = "";

                    Intent intent = new Intent(getApplicationContext(), Main4post.class);
                    String[] HotelItm = new String[28];
                    HotelItm[0] = "";
                    HotelItm[1] = name;
                    HotelItm[2] = email;
                    HotelItm[3] = title;
                    HotelItm[4] = uniqi;
                    HotelItm[5] = userURL;
                    intent.putExtra("ITEM",HotelItm);
                    startActivity(intent);

                    overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);

                }else{
                    Snackbar.make( v,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                            .setAction("Se connecter", null).show();
                }
            }
        });
        fabfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()) {
                    //poste prefecrences
                    SQLiteHandler db;
                    db = new SQLiteHandler(getApplicationContext());

                    HashMap<String, String> user = db.getUserDetails();
                    String name = user.get("name");
                    String email = user.get("settings");
                    markpostfav(Uniqueid,email,"true", Name);
                }else{
                    Snackbar.make( v,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                            .setAction("Se connecter", null).show();
                }
            }
        });
    }

    private void markpostfav(final String fvuniqid, final String byemail,
                             final String boolvar,final String genre) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                databaseContract.AppConfig.URL_POST_FAVORITE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        //succees
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("fvuniqid", fvuniqid);
                params.put("byemail", byemail);
                params.put("boolvar", boolvar);
                params.put("genre", genre);

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


}
