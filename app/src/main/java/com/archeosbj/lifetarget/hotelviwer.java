package com.archeosbj.lifetarget;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.loginandregistration.app.AppController;
import com.archeosbj.lifetarget.loginandregistration.helper.SQLiteHandler;
import com.archeosbj.lifetarget.loginandregistration.helper.SessionManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.snatik.storage.Storage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

public class hotelviwer extends AppCompatActivity{
    RelativeLayout galeryview;
    RelativeLayout descriptionview;
    RelativeLayout servicesview;
    RelativeLayout pfortview;
    RelativeLayout pfaibleview;

    String Id;
    String Title;
    String Adress;
    String Payement;
    String Siteweb;
    String Description;
    String Longdescription;
    String Uniqueid;
    String Rating;
    String Service;
    String Pointfort;
    String Pointfaible;
    String Prinpimage;
    String Mets;
    String Modified;
    String GaleryOne;
    String Galerytwo;
    String Galeryfor;
    String Galeryfive;
    String Galerysix;

    private FloatingActionButton fabservice;
    private FloatingActionButton fabcservice;
    private FloatingActionButton fabshare;
    private FloatingActionButton fabfavorite;
    private boolean isVisibleFloat = false;
    private MapView mapView;
    private GoogleMap gmap;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    Context contxent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contxent = this;
        setContentView(R.layout.activity_hotelviwer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

        galeryview = (RelativeLayout) findViewById(R.id.galey_view);
        descriptionview = (RelativeLayout) findViewById(R.id.description_view);
        servicesview = (RelativeLayout) findViewById(R.id.informtion_view);
        pfortview = (RelativeLayout) findViewById(R.id.point_fort_view);
        pfaibleview = (RelativeLayout) findViewById(R.id.point_faible_view);

        Intent intent = getIntent();
        String[] RestoItm = intent.getStringArrayExtra("ITEM");

        Id = RestoItm[1];
        Title = RestoItm[2];
        Adress = RestoItm[3];
        Payement = RestoItm[4];
        Siteweb = RestoItm[5];
        Description = RestoItm[6];
        Longdescription = RestoItm[7];
        Uniqueid = RestoItm[8];
        Rating = RestoItm[9];
        Service = RestoItm[10];
        Pointfort = RestoItm[11];
        Pointfaible = RestoItm[12];
        Prinpimage = RestoItm[13];
        Mets = RestoItm[14];
        Modified = RestoItm[15];
        GaleryOne = RestoItm[16];
        Galerytwo = RestoItm[17];
        Galeryfor = RestoItm[18];
        Galeryfive = RestoItm[19];
        Galerysix = RestoItm[20];
        //LatLng sydney = new LatLng(-34, 151);
        applyIntentItemArry();

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;
                gmap.setMinZoomPreference(12);
                LatLng ny = new LatLng(40.7143528, -74.0059731);
                gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
            }
        });

        ImageView ctrGaleryOne = (ImageView) findViewById(R.id.galery_one);
        ImageView ctrGalerytwo = (ImageView) findViewById(R.id.galery_two);
        final ImageView ctrGaleryfor = (ImageView) findViewById(R.id.galery_four);
        ImageView ctrGaleryfive = (ImageView) findViewById(R.id.galery_five);
        ImageView ctrGalerysix = (ImageView) findViewById(R.id.galery_six);

        ctrGaleryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), imageViewer.class);
                String[] RestoItm = new String[7];
                RestoItm[0] = GaleryOne;
                RestoItm[1] = Prinpimage;
                RestoItm[2] = GaleryOne ;
                RestoItm[3] = Galerytwo ;
                RestoItm[4] = Galeryfor ;
                RestoItm[5] = Galeryfive;
                RestoItm[5] = Galerysix;
                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        ctrGalerytwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), imageViewer.class);
                String[] RestoItm = new String[7];
                RestoItm[0] = Galerytwo;
                RestoItm[1] = Prinpimage;
                RestoItm[2] = GaleryOne ;
                RestoItm[3] = Galerytwo ;
                RestoItm[4] = Galeryfor ;
                RestoItm[5] = Galeryfive;
                RestoItm[5] = Galerysix;
                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        ctrGaleryfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), imageViewer.class);
                String[] RestoItm = new String[7];
                RestoItm[0] = Galeryfor;
                RestoItm[1] = Prinpimage;
                RestoItm[2] = GaleryOne ;
                RestoItm[3] = Galerytwo ;
                RestoItm[4] = Galeryfor ;
                RestoItm[5] = Galeryfive;
                RestoItm[5] = Galerysix;

                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        ctrGaleryfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), imageViewer.class);
                String[] RestoItm = new String[7];
                RestoItm[0] = Galeryfive;
                RestoItm[1] = Prinpimage;
                RestoItm[2] = GaleryOne ;
                RestoItm[3] = Galerytwo ;
                RestoItm[4] = Galeryfor ;
                RestoItm[5] = Galeryfive;
                RestoItm[5] = Galerysix;

                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        ctrGalerysix .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), imageViewer.class);
                String[] RestoItm = new String[7];
                RestoItm[0] = Galerysix;
                RestoItm[1] = Prinpimage;
                RestoItm[2] = GaleryOne ;
                RestoItm[3] = Galerytwo ;
                RestoItm[4] = Galeryfor ;
                RestoItm[5] = Galeryfive;
                RestoItm[5] = Galerysix;

                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        fabservice = findViewById(R.id.service);
        fabcservice = findViewById(R.id.custumservice);
        fabshare = findViewById(R.id.share);
        fabfavorite = findViewById(R.id.favorite);
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
        fabactionservice();
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
                    String email = user.get("email");
                    String[] HotelItm = new String[28];
                    HotelItm[0] = "";
                    HotelItm[1] = name;
                    HotelItm[2] = email;
                    HotelItm[3] = Title;
                    HotelItm[4] = Uniqueid;
                    HotelItm[5] = Service;
                    HotelItm[6] = Rating;
                    HotelItm[7] = Pointfaible;
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
                    String email = user.get("email");
                    String[] HotelItm = new String[28];
                    HotelItm[0] = "";
                    HotelItm[1] = name;
                    HotelItm[2] = email;
                    HotelItm[3] = Title;
                    HotelItm[4] = Uniqueid;
                    HotelItm[5] = Service;
                    HotelItm[6] = Rating;
                    HotelItm[7] = Pointfaible;
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
                    String email = user.get("email");
                    String title =  Title;
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
                    String email = user.get("email");
                    markpostfav(Uniqueid,email,"true", Title);
                }else{
                    Snackbar.make( v,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                            .setAction("Se connecter", null).show();
                }
            }
        });
    }

    private void applyIntentItemArry() {
        final ImageView ctrPrinpimage = (ImageView) findViewById(R.id.image_primp);
        TextView ctrTitle = (TextView) findViewById(R.id.title);
        TextView ctrDescription = (TextView) findViewById(R.id.description);
        TextView ctrPayement = (TextView) findViewById(R.id.Payement_text);
        final ImageView ctrGaleryOne = (ImageView) findViewById(R.id.galery_one);
        final ImageView ctrGalerytwo = (ImageView) findViewById(R.id.galery_two);
        final ImageView ctrGaleryfor = (ImageView) findViewById(R.id.galery_four);
        final ImageView ctrGaleryfive = (ImageView) findViewById(R.id.galery_five);
        final ImageView ctrGalerysix = (ImageView) findViewById(R.id.galery_six);
        TextView ctrLongdescription = (TextView) findViewById(R.id.long_description);
        TextView ctrMets = (TextView) findViewById(R.id.mets_text);
        TextView ctrAdress = (TextView) findViewById(R.id.adress_text);
        TextView ctrSiteweb = (TextView) findViewById(R.id.site_web_text);
        TextView ctrPointfort = (TextView) findViewById(R.id.point_fort);
        TextView ratingTXT = (TextView) findViewById(R.id.rating);
        TextView ctrService = (TextView) findViewById(R.id.service_text);
        ratingTXT.setText(Rating);
        ctrTitle.setText(Title);
        ctrDescription.setText(Description);
        ctrPayement.setText(Payement);
        ctrLongdescription.setText(Longdescription);
        ctrMets.setText(Mets);
        ctrAdress.setText(Adress);
        ctrSiteweb.setText(Siteweb);
        ctrPointfort.setText(Pointfort);
        //ctrPointfaible.setText(Pointfaible);
        SpannableString content = new SpannableString(Service);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        ctrService.setText(content);
        ctrService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    //Creating intents for making a call
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + Service));
                    getApplicationContext().startActivity(callIntent);

                }else{
                    //Toast.makeText(getApplicationContext(), "Aucune permission.", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(hotelviwer.this, new String[]{Manifest.permission.CALL_PHONE}, 0);
                }
            }
        });


        Storage storage = new Storage(contxent);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + Prinpimage;
        setBitmapImageFormSD(fileph,ctrPrinpimage);
        //Bitmap bm = BitmapFactory.decodeFile(fileph);
        //ctrPrinpimage.setImageBitmap(bm);


        String gone = newDiri + File.separator + GaleryOne;
        setBitmapImageFormSD(gone,ctrGaleryOne);
        //Bitmap bmone = BitmapFactory.decodeFile(gone);
        //ctrGaleryOne.setImageBitmap(bmone);

        String gtwo = newDiri + File.separator + Galerytwo;
        //Bitmap bmtwo = BitmapFactory.decodeFile(gtwo);
        //ctrGalerytwo.setImageBitmap(bmtwo);
        setBitmapImageFormSD(gtwo,ctrGalerytwo);

        String gfor = newDiri + File.separator + Galeryfor;
        //Bitmap bmfor = BitmapFactory.decodeFile(gfor);
        //ctrGaleryfor.setImageBitmap(bmfor);
        setBitmapImageFormSD(gfor,ctrGaleryfor);

        String gfive = newDiri + File.separator + Galeryfive;
        //Bitmap bmfive = BitmapFactory.decodeFile(gfive);
        //ctrGaleryfive.setImageBitmap(bmfive);
        setBitmapImageFormSD(gfive,ctrGaleryfive);

        String gsix = newDiri + File.separator + Galerysix;
        //Bitmap bmsix = BitmapFactory.decodeFile(gsix);
        //ctrGalerysix.setImageBitmap(bmsix);
        setBitmapImageFormSD(gsix,ctrGalerysix);
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

    public static void setBitmapImageFormMomory(String filepath, ImageView ImgV){
            int reqWidth = 500;
            int reqHeight =   500;
            Uri.fromFile(new File(filepath));
            Bitmap bm =  decodeSampledBitmapFromResource(filepath,reqWidth,reqHeight);
            ImgV.setImageBitmap(bm);
    }

    public static Bitmap decodeSampledBitmapFromResource(String filepath,
                                                             int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            if (!(options == null)){
                BitmapFactory.decodeFile(filepath, options);

                final int height = options.outHeight;
                final int width = options.outWidth;
                int inSampleSize = 1;
                if (height > reqHeight || width > reqWidth) {
                    final int halfHeight = height / 4;
                    final int halfWidth = width / 4;
                    /*int scale = 1;
                    if (options.outHeight > halfHeight || options.outWidth > halfWidth) {
                        scale = (int)Math.pow(2, (int) Math.ceil(Math.log(halfHeight /
                                (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
                    }*/
                    while ((halfHeight / inSampleSize) >= reqHeight
                            && (halfWidth / inSampleSize) >= reqWidth) {
                        inSampleSize *= 2;
                    }
                }
                options.inSampleSize = inSampleSize;
                options.inJustDecodeBounds = false;
            }
            return BitmapFactory.decodeFile(filepath, options);
        }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_galery:
                    galeryview.setVisibility(View.VISIBLE);
                    descriptionview.setVisibility(View.GONE);
                    servicesview.setVisibility(View.GONE);
                    pfortview.setVisibility(View.GONE);
                    pfaibleview.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_description:
                    galeryview.setVisibility(View.GONE);
                    descriptionview.setVisibility(View.VISIBLE);
                    servicesview.setVisibility(View.GONE);
                    pfortview.setVisibility(View.GONE);
                    pfaibleview.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_services:
                    galeryview.setVisibility(View.GONE);
                    descriptionview.setVisibility(View.GONE);
                    servicesview.setVisibility(View.VISIBLE);
                    pfortview.setVisibility(View.GONE);
                    pfaibleview.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_pfort:
                    galeryview.setVisibility(View.GONE);
                    descriptionview.setVisibility(View.GONE);
                    servicesview.setVisibility(View.GONE);
                    pfortview.setVisibility(View.VISIBLE);
                    pfaibleview.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_pfailble:
                    galeryview.setVisibility(View.GONE);
                    descriptionview.setVisibility(View.GONE);
                    servicesview.setVisibility(View.GONE);
                    pfortview.setVisibility(View.GONE);
                    pfaibleview.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };
}
