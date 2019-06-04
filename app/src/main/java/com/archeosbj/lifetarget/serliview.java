package com.archeosbj.lifetarget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class serliview extends AppCompatActivity {

    String Id;
    String Name;
    String Contact;
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
    String Zonelivre;
    String Maxlivre;
    String Siteweb;
    String Horaire;
    String Reserveone;
    String Reservetwo;
    String Extras;
    String Description;
    String Payement;

    Button likers;
    Context contxent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serliview);

        Intent intent = getIntent();
        final String Tcken = intent.getStringExtra("ITEM");
        TinyDB tinydb = new TinyDB(getApplicationContext());
        final String[] HotelItm =tinydb.getArryString(Tcken);
        tinydb.remove(Tcken);

        Id = HotelItm[1];
        Name = HotelItm[2];
        Contact = HotelItm[3];
        Uniqueid = HotelItm[4];
        Service = HotelItm[5];
        Pointfort = HotelItm[6];
        Price = HotelItm[7];
        Primpimage = HotelItm[8];
        GaleryOne = HotelItm[9];
        Galerytwo = HotelItm[10];
        Galerytree = HotelItm[11];
        Galeryfour = HotelItm[12];
        Galeryfive = HotelItm[13];
        Galerysix = HotelItm[14];
        Zonelivre = HotelItm[15];
        Maxlivre = HotelItm[16];
        Siteweb = HotelItm[17];
        Horaire = HotelItm[18];
        Reserveone = HotelItm[19];
        Reservetwo = HotelItm[20];
        Extras = HotelItm[21];
        Description = HotelItm[22];
        Payement = HotelItm[23];
        applyIntentItemArry();
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
        final SessionManager session = new SessionManager(getApplicationContext());
        final ImageView ctr_primpimage = (ImageView) findViewById(R.id.primpimage);
        final ImageView ctr_galeryOne = (ImageView) findViewById(R.id.galeryOne);
        final ImageView ctr_galerytwo = (ImageView) findViewById(R.id.galerytwo);
        final ImageView ctr_galerytree = (ImageView) findViewById(R.id.galerytree);
        final ImageView ctr_galeryfour = (ImageView) findViewById(R.id.galeryfour);
        final ImageView ctr_galeryfive = (ImageView) findViewById(R.id.galeryfive);
        final ImageView ctr_galerysix = (ImageView) findViewById(R.id.galerysix);
         likers  = (Button) findViewById(R.id.butlike);

         likers.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (session.isLoggedIn()) {
                     //poste prefecrences
                     SQLiteHandler db;
                     db = new SQLiteHandler(getApplicationContext());

                     HashMap<String, String> user = db.getUserDetails();
                     String name = user.get("name");
                     String email = user.get("settings");
                     markpostfav(Uniqueid,email,"true", "Service de livraison");
                 }else{
                     Snackbar.make( v,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                             .setAction("Se connecter", null).show();
                 }
             }
         });
        applyimageck(ctr_galeryOne,GaleryOne);
        applyimageck(ctr_galerytwo,Galerytwo);
        applyimageck(ctr_galerytree,Galerytree);
        applyimageck(ctr_galeryfour,Galeryfour);
        applyimageck(ctr_galeryfive,Galeryfive);
        applyimageck(ctr_galerysix,Galerysix);
        TextView ctrTitle = (TextView) findViewById(R.id.titleName);
        TextView ctrTitleName = (TextView) findViewById(R.id.title_text);

        TextView ctrSpecialite = (TextView) findViewById(R.id.pelir);
        TextView ctrContact    = (TextView) findViewById(R.id.cntlir);
        TextView ctrHoraire    = (TextView) findViewById(R.id.hlir);
        TextView ctrSiteWeb    = (TextView) findViewById(R.id.sitelir);
        TextView ctrmplir    = (TextView) findViewById(R.id.mplir);

        TextView ctrZlireO = (TextView) findViewById(R.id.zlivr_text);
        TextView ctrZlireT = (TextView) findViewById(R.id.zlirlir);

        TextView ctrCtlireO = (TextView) findViewById(R.id.ctlivr_text);
        TextView ctrCtlireT = (TextView) findViewById(R.id.ctlirlir);

        TextView ctrDureeLirO = (TextView) findViewById(R.id.drmlivr_text);
        TextView ctrDureeLirT = (TextView) findViewById(R.id.dlirlir);

        ctrTitle.setText(Name);
        ctrTitleName.setText(Name);

        ctrSpecialite.setText(Pointfort);
        ctrContact.setText(Contact);
        ctrHoraire.setText(Horaire);
        ctrSiteWeb.setText(Siteweb);

        ctrZlireO.setText(Zonelivre);
        ctrZlireT.setText(Zonelivre);

        ctrCtlireO.setText(Price);
        ctrCtlireT.setText(Price);

        ctrDureeLirO.setText(Maxlivre);
        ctrDureeLirT.setText(Maxlivre);

        ctrmplir.setText(Payement);

        Storage storage = new Storage(contxent);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";

        String fileph = newDiri + File.separator + Primpimage;
        setBitmapImageFormSD(fileph,ctr_primpimage);

        String filepha = newDiri + File.separator + GaleryOne;
        setBitmapImageFormSD(filepha,ctr_galeryOne);

        String filephb = newDiri + File.separator + Galerytwo;
        setBitmapImageFormSD(filephb,ctr_galerytwo);

        String filephc = newDiri + File.separator + Galerytree;
        setBitmapImageFormSD(filephc,ctr_galerytree);

        String filephd = newDiri + File.separator + Galeryfour;
        setBitmapImageFormSD(filephd,ctr_galeryfour);

        String filephe = newDiri + File.separator + Galeryfive;
        setBitmapImageFormSD(filephe,ctr_galeryfive);

        String filephr = newDiri + File.separator + Galerysix;
        setBitmapImageFormSD(filephr,ctr_galerysix);

    }

    void setBitmapImageFormSD(String filepath, ImageView ImgV){
        Glide.with(getApplicationContext())
                .load(Uri.fromFile(new File(filepath)))
                .into(ImgV);
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
