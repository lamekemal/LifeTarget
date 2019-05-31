package com.archeosbj.lifetarget;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.archeosbj.lifetarget.Model.fav;
import com.archeosbj.lifetarget.Model.profm;
import com.archeosbj.lifetarget.PreferenceTools.TinyDB;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.data.favdb;
import com.archeosbj.lifetarget.data.profdb;
import com.archeosbj.lifetarget.httpTool.JSONParser;
import com.archeosbj.lifetarget.httpTool.JSONStringParser;
import com.archeosbj.lifetarget.loginandregistration.helper.SQLiteHandler;
import com.archeosbj.lifetarget.loginandregistration.helper.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.snatik.storage.Storage;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import im.delight.android.webview.AdvancedWebView;

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INDEX;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INT;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_SENDER;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_PROFILS_NM;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.FAV_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.FAV_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.GET_MSG_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.HOTEL_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.HOTEL_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.INNOV_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.INNOV_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.MSG_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.PROFILS_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.PROFILS_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.RESTO_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.RESTO_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SERLI_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SERLI_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SERVER_IMGURL_API;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SERVER_IMGURL_PROFILS;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SITES_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SITES_LINK_URL;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TRANS_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.TRANS_LINK_URL;

public class ZeroFragment extends Fragment{
    private String MCookies;
    public static Boolean heExecute = true;
    private AdvancedWebView mWebView;
    ProgressBar prgStatus ;
    public ZeroFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //GET SETTINGS
        View v = inflater.inflate(R.layout.zero_fragment, container, false);

        final ImageView imagelite = (ImageView) v.findViewById(R.id.but_resto);
        imagelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LaunchIntentWithCat(getString(R.string.resto_text_categories),1);
            }
        });
        final ImageView but_hotel = (ImageView) v.findViewById(R.id.but_hotel);
        but_hotel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LaunchIntentWithCat(getString(R.string.hotel_text_categories),2);
            }
        });

        final ImageView butrestof = (ImageView) v.findViewById(R.id.butrestof);
        butrestof.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LaunchScdIntentWithCat(getString(R.string.tourit_text_categories),3);
            }
        });

        final ImageView but_plus = (ImageView) v.findViewById(R.id.but_plus);
        but_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LaunchIntentWithCat(getString(R.string.livraison_text_categories),4);
            }
        });
        final ImageView but_bar = (ImageView) v.findViewById(R.id.but_bar);
        but_bar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LaunchIntentWithCat(getString(R.string.transport_text_categories),5);
            }
        });

        final ImageView but_cth = (ImageView) v.findViewById(R.id.but_cth);
        but_cth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LaunchScdIntentWithCat(getString(R.string.innov_text_categories),6);
            }
        });
        CookieManager.getInstance().removeAllCookie();

        /*mWebView = (AdvancedWebView) v.findViewById(R.id.awebview);
        /mWebView.loadUrl(BLANK_COOKIES_URL);
        /mWebView.setThirdPartyCookiesEnabled(true);
        /mWebView.setCookiesEnabled(true);
        /mWebView.setDesktopMode(true);*/
        return v;
    }

        //region DRAFT
        /*private class MyWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mWebView, url);
                MCookies = CookieManager.getInstance().getCookie(url);
                Log.e(TAG, "Downloader Cookies At time: " + CookieManager.getInstance().getCookie(url));
                if(ZeroFragment.heExecute) {
                    Log.e(TAG, "He recieve order for start from == " + ZeroFragment.heExecute.toString());
                    new StartdbLoadFromNet().execute(RESTO_LINK_URL,HOTEL_LINK_URL,SITES_LINK_URL,
                            SERLI_LINK_URL,TRANS_LINK_URL,INNOV_LINK_URL,CookieManager.getInstance().getCookie(url));
                    ZeroFragment.heExecute = false;
                }

            }

        }*/

       /*@Override
        public void onPageFinished(String url) {
            if (MCookies == null){
                MCookies = CookieManager.getInstance().getCookie(url);
                Log.e(TAG, "KEBIR url direct: " + CookieManager.getInstance().getCookie(url));
                if(ZeroFragment.heExecute) {
                    Log.e(TAG, "He recieve order for start from == " + ZeroFragment.heExecute.toString());
                    new StartdbLoadFromNet().execute(RESTO_LINK_URL,HOTEL_LINK_URL,SITES_LINK_URL,
                            SERLI_LINK_URL,TRANS_LINK_URL,INNOV_LINK_URL,CookieManager.getInstance().getCookie(url));
                    ZeroFragment.heExecute = false;
                }
            }

        }*/
        //endregion
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        Log.e(TAG, "strting load");

        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        long period = 20; // the period between successive executions
        exec.scheduleAtFixedRate(new MyTask(), 0, period, TimeUnit.SECONDS);


        /*new StartdbLoadFromNet().execute(RESTO_LINK_URL,HOTEL_LINK_URL,SITES_LINK_URL,
                SERLI_LINK_URL,TRANS_LINK_URL,INNOV_LINK_URL,"",FAV_LINK_URL,PROFILS_LINK_URL,GET_MSG_LINK_URL);*/

        //region DRAFT
        /*CookieSyncManager.createInstance(getActivity());
        /CookieSyncManager.getInstance().startSync();
        /CookieManager.getInstance().setAcceptCookie(true);
        /mWebView.loadUrl(BLANK_COOKIES_URL);
        /final WebView webviex = (WebView) v.findViewById(R.id.webview);
        /if (android.os.Build.VERSION.SDK_INT >= 21) {
        /    android.webkit.CookieManager.getInstance().setAcceptThirdPartyCookies(webviex, true);
        /}
        /webviex.getSettings().setJavaScriptEnabled(true);
        /webviex.setWebViewClient(new MyWebViewClient());
        /webviex.loadUrl(BLANK_COOKIES_URL);
        /webviex.requestFocus();

        /if (MCookies == null){
        /    String rcookies = CookieManager.getInstance().getCookie(BLANK_COOKIES_URL);
        /    MCookies = rcookies;
        /}*/
        //endregion

    }
    class MyTask implements Runnable {

        @Override
        public void run() {
            new StartdbLoadFromNet().execute(RESTO_LINK_URL,HOTEL_LINK_URL,SITES_LINK_URL,
                    SERLI_LINK_URL,TRANS_LINK_URL,INNOV_LINK_URL,"",FAV_LINK_URL,PROFILS_LINK_URL,GET_MSG_LINK_URL);
        }
    }


    private void LaunchIntentWithCat(String s, Integer i) {
        Intent intent = new Intent(getContext(), categoriesView.class);
        Integer var = 1;
        intent.putExtra(CATEGORIES_SENDER, var);
        intent.putExtra(CATEGORIES_INT, s);
        intent.putExtra(CATEGORIES_INDEX, i);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
    }

    private void LaunchScdIntentWithCat(String s, Integer i) {
        Intent intent = new Intent(getContext(), sitesTviewer.class);
        Integer var = 1;
        intent.putExtra(CATEGORIES_SENDER, var);
        intent.putExtra(CATEGORIES_INT, s);
        intent.putExtra(CATEGORIES_INDEX, i);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
    }

    private class GetHotel extends AsyncTask<String, Void, ArrayList<String> > {

        @Override
        protected ArrayList<String>  doInBackground(String... params) {
            String urldisplay = params[0];

            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            try {
                noDATAhelper(urldisplay,databaseContract.dataEntry.SQL_DELETE_ENTRIES_HOTEL,databaseContract.dataEntry.SQL_CREATE_ENTRIES_HOTEL
                        ,databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_HOTEL);
                JSONArray feeds  = json.getJSONArray(HOTEL_JSON_CATEGORIES);
                Log.e(TAG, "GetHotel Response from url (nbr): " + feeds.length());
                int totalcc = 0;
                int m = 0;

                if(feeds.length()>2){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_HOTEL);
                }

                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String Title = c.optString("title");
                    String Adress = c.optString("adress");
                    String Payement = c.optString("payement");
                    String Siteweb = c.optString("siteweb");
                    String Description = c.optString("description");
                    String Longdescription = c.optString("longdescription");
                    String Uniqueid = c.optString("uniqueid");
                    String Rating = c.optString("rating");
                    String Service = c.optString("service");
                    String Pointfort = c.optString("pointfort");
                    String Pointfaible = c.optString("pointfaible");
                    String Mets = c.optString("mets");
                    String Modified = c.optString("modified");
                    String GaleryOne = c.optString("galeryOne");
                    String Galerytwo = c.optString("galerytwo");
                    String Galeryfor = c.optString("galeryfor");
                    String Galeryfive = c.optString("galeryfive");
                    String Galerysix = c.optString("galerysix");
                    String Prinpimage = c.optString("prinpimage");
                    Integer iaRating = RatingNumber(Uniqueid);
                    String isSrat = String.valueOf(iaRating);
                    createAddDatadbHOTEL( Title,  Id, Adress, Payement, Siteweb, Longdescription, Uniqueid,
                            Service, Pointfort, Pointfaible, Prinpimage, GaleryOne, Galerytwo,
                            Galeryfor, Galeryfive, Galerysix, Mets, Modified,  Description, isSrat);
                   if(CheckFavoriteInstant(Uniqueid)) {
                       AddFavoriteInstant( Title,  Adress, isSrat, Description, Prinpimage, Uniqueid);
                   }
                    mophs.add(totalcc,Prinpimage);
                    totalcc++;


                    mophs.add(totalcc,GaleryOne);
                    totalcc++;

                    mophs.add(totalcc,Galerytwo);
                    totalcc++;

                    mophs.add(totalcc,Galeryfor);
                    totalcc++;

                    mophs.add(totalcc,Galeryfive);
                    totalcc++;

                    mophs.add(totalcc,Galerysix);
                    totalcc++;
                    m++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                Log.e(TAG, "GET HOTEL Response from url: " + cookies);
                System.out.print("Caught NullPointerException");

            }

            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String>  result) {
            if(result.size() > 1){
                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_HOTEL,result);
                new AsyncSaveImageAuto().execute(result);
            }
            Log.e("KEMAL","ENFANT HOTEL JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT HOTEL JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    public String sendRequest(String url) {
        String result = "";
        try {

            HttpClient client = new DefaultHttpClient();
            HttpParams httpParameters = client.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);
            HttpConnectionParams.setTcpNoDelay(httpParameters, true);
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            InputStream ips = response.getEntity().getContent();

            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
                    "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);

            }
            buf.close();
            ips.close();
            result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private class GetRESTO extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String>  doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];

            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            try {
                noDATAhelper(urldisplay,databaseContract.dataEntry.SQL_DELETE_ENTRIES_RESTO,databaseContract.dataEntry.SQL_CREATE_ENTRIES_RESTO
                ,databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_RESTO);
                // Getting Array of Contacts
                JSONArray feeds  = json.getJSONArray(RESTO_JSON_CATEGORIES);

                int totalcc = 0;
                int m = 0;

                if(feeds.length()>2){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_RESTO);
                }

                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String Title = c.optString("title");
                    String Adress = c.optString("adress");
                    String Payement = c.optString("horaire");
                    String Siteweb = c.optString("siteweb");
                    String Description = c.optString("description");
                    String Longdescription = c.optString("longdescription");
                    String Uniqueid = c.optString("uniqueid");
                    String Rating = c.optString("rating");
                    String Service = c.optString("service");
                    String Pointfort = c.optString("pointfort");
                    String Pointfaible = c.optString("pointfaible");
                    String Prinpimage = c.optString("prinpimage");
                    String Mets = c.optString("mets");
                    String Modified = c.optString("modified");
                    String GaleryOne = c.optString("galeryOne");
                    String Galerytwo = c.optString("galerytwo");
                    String Galeryfor = c.optString("galeryfor");
                    String Galeryfive = c.optString("galeryfive");
                    String Galerysix = c.optString("galerysix");
                    Integer iaRating = RatingNumber(Uniqueid);
                    String isSrat = String.valueOf(iaRating);
                    createAddDatadbRESTO( Title,  Id, Adress, Payement, Siteweb, Longdescription, Uniqueid,
                            Service, Pointfort, Pointfaible, Prinpimage, GaleryOne, Galerytwo,
                            Galeryfor, Galeryfive, Galerysix, Mets, Modified,  Description, isSrat);
                    if(CheckFavoriteInstant(Uniqueid)) {
                        AddFavoriteInstant( Title,  Adress, isSrat, Description, Prinpimage, Uniqueid);
                    }
                    mophs.add(totalcc,GaleryOne);
                    totalcc++;

                    mophs.add(totalcc,Galerytwo);
                    totalcc++;

                    mophs.add(totalcc,Galeryfor);
                    totalcc++;

                    mophs.add(totalcc,Galeryfive);
                    totalcc++;

                    mophs.add(totalcc,Galerysix);
                    totalcc++;

                    mophs.add(totalcc,Prinpimage);
                    totalcc++;
                    m++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                Log.e(TAG, "GET RESTO Response from url: " + cookies);
                System.out.print("Caught NullPointerException");
            }

            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if(result.size() > 1){
                //AsyncSaveImageAutoFrnt(result);
                //Log.e("KEMAL","ENFANT RESTO PASSS DOWNLOAD ");
                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_RESTO,result);
                new AsyncSaveImageAuto().execute(result);
               // new AsyncSaveImageAuto().execute(result);
            }
            Log.e("KEMAL","ENFANT RESTO JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT RESTO JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }
    void noDATAhelper(String urldisplay, String TABLED, String TableC, String D){
        TinyDB tinydb = new TinyDB(getContext());
        try {
            tinydb.remove(D);
        }catch (Exception e){}
        JSONStringParser jSParser = new JSONStringParser();
        String del = "10001";
        String jstring = jSParser.getJSONStringFromUrl(urldisplay);
        if (del.equals(jstring)){
            Log.e("JSTRING","jstring AS USE TO DELETE ");
            SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            myDB.execSQL(TABLED);
            myDB.execSQL(TableC);
        }
    }
    private class GetSITES extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            try {
                noDATAhelper(urldisplay,databaseContract.dataEntry.SQL_DELETE_ENTRIES_SITES,databaseContract.dataEntry.SQL_CREATE_ENTRIES_SITES
                        , databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_SITES);
                // Getting Array of Contacts
                JSONArray feeds  = json.getJSONArray(SITES_JSON_CATEGORIES);

                int totalcc = 0;
                int m = 0;

                if(feeds.length()>2){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_SITES);
                }

                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String uniqueid = c.optString("uniqueid");
                    String name = c.optString("name");
                    String contact = c.optString("contact");
                    String service = c.optString("service");
                    String mail = c.optString("mail");
                    String horaire = c.optString("horaire");
                    String price = c.optString("price");
                    String pointfort = c.optString("pointfort");
                    String extras = c.optString("extras");
                    String localisation = c.optString("localisation");
                    String more = c.optString("more");
                    String primpimage = c.optString("primpimage");
                    String galeryOne = c.optString("galeryOne");
                    String galerytwo = c.optString("galerytwo");
                    String galerytree = c.optString("galerytree");
                    String galeryfour = c.optString("galeryfour");
                    String galeryfive = c.optString("galeryfive");
                    String galerysix = c.optString("galerysix");
                    String galeryseven = c.optString("galeryseven");
                    String reserveone = c.optString("reserveone");
                    String reservetwo = c.optString("reservetwo");
                    String plusStr = c.optString("plusStr");
                    String Modified = c.optString("modified");
                    Integer iaRating = RatingNumber(uniqueid);
                    String isSrat = String.valueOf(iaRating);
                    createAddDatadbSITES(uniqueid, Id, name, contact, service, mail,
                            horaire, price, pointfort, extras, localisation,
                            more,primpimage, galeryOne, galerytwo, galerytree, galeryfour,
                            galeryfive, galerysix, galeryseven, isSrat,
                            reservetwo, plusStr, Modified);

                    if(CheckFavoriteInstant(uniqueid)) {
                        AddFavoriteInstant( name,  localisation, isSrat, "Sites Touristiques", primpimage, uniqueid);
                    }
                    mophs.add(totalcc,primpimage);
                    totalcc++;

                    mophs.add(totalcc,galeryOne);
                    totalcc++;

                    mophs.add(totalcc,galerytwo);
                    totalcc++;

                    mophs.add(totalcc,galerytree);
                    totalcc++;

                    mophs.add(totalcc,galeryfour);
                    totalcc++;

                    mophs.add(totalcc,galeryfive);
                    totalcc++;

                    mophs.add(totalcc,galerysix);
                    totalcc++;

                    mophs.add(totalcc,galeryseven);
                    totalcc++;
                    m++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                Log.e(TAG, "GET SITES Response from url: " + cookies);
                System.out.print("Caught NullPointerException");
            }

            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if(result.size() > 1){
                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_SITES,result);
                new AsyncSaveImageAuto().execute(result);
            }
            Log.e("KEMAL","ENFANT SITES JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT SITES JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    private class GetSERLI extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            //ArrayList<Tour> feedList = new ArrayList<>();
            JSONParser jParser = new JSONParser();
            JSONStringParser jSParser = new JSONStringParser();
            String cookies = params[1];
            String del = "10001";
            String jstring = jSParser.getJSONStringFromUrl(urldisplay);
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            try {
                Log.e("JSTRING","jstring = " + jstring +"=="+ del);
                if (del.equals(jstring)){
                    TinyDB tinydb = new TinyDB(getContext());
                    try {
                        tinydb.remove(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_SERLI);
                    }catch (Exception e){}

                    Log.e("JSTRING","jstring AS USE TO DELETE ");
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_SERLI);
                    myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_SERLI);
                }
                // Getting Array of Contacts
                JSONArray feeds  = json.getJSONArray(SERLI_JSON_CATEGORIES);

                int totalcc = 0;
                int m = 0;

                if(feeds.length()>2){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_SERLI);
                }

                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    String Id = c.optString("id");
                    String uniqueid = c.optString("uniqueid");
                    String name = c.optString("name");
                    String contact = c.optString("contact");
                    String service = c.optString("service");
                    String description = c.optString("description");
                    String horaire = c.optString("horaire");
                    String price = c.optString("price");
                    String pointfort = c.optString("pointfort");
                    String extras = c.optString("extras");
                    String siteweb = c.optString("siteweb");
                    String payement = c.optString("payement");
                    String primpimage = c.optString("primpimage");
                    String galeryOne = c.optString("galeryOne");
                    String galerytwo = c.optString("galerytwo");
                    String galerytree = c.optString("galerytree");
                    String galeryfour = c.optString("galeryfour");
                    String galeryfive = c.optString("galeryfive");
                    String galerysix = c.optString("galerysix");
                    String reserveone = c.optString("reserveone");
                    String reservetwo = c.optString("reservetwo");
                    String zonelivre = c.optString("zonelivre");
                    String maxlivre = c.optString("maxlivre");
                    String Modified = c.optString("modified");

                    Integer iaRating = RatingNumber(uniqueid);
                    String isSrat = String.valueOf(iaRating);

                    createAddDatadbSERLI( uniqueid,  Id,  name,  contact,  service,  description,
                            horaire,  price,  pointfort,  extras,  siteweb,
                            payement,  primpimage,  galeryOne,  galerytwo,  galerytree,
                            galeryfour,  galeryfive,  galerysix,  zonelivre,
                            isSrat,  reservetwo,  maxlivre,  Modified);
                    if(CheckFavoriteInstant(uniqueid)) {
                        AddFavoriteInstant( name,  contact, isSrat, zonelivre, primpimage, uniqueid);
                    }
                    mophs.add(totalcc,primpimage);
                    totalcc++;

                    mophs.add(totalcc,galeryOne);
                    totalcc++;

                    mophs.add(totalcc,galerytwo);
                    totalcc++;

                    mophs.add(totalcc,galerytree);
                    totalcc++;

                    mophs.add(totalcc,galeryfour);
                    totalcc++;

                    mophs.add(totalcc,galeryfive);
                    totalcc++;

                    mophs.add(totalcc,galerysix);
                    totalcc++;
                    m++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                Log.e(TAG, "NullPointerException : SERLI");

            }

            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if(result.size() > 1){
                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_SERLI,result);
                new AsyncSaveImageAuto().execute(result);
            }
            Log.e("KEMAL","ENFANT SERLI JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT SERLI JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    private class GetTRANS extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            //Log.e(TAG, "Response from url: " + json.toString());
            try {

                noDATAhelper(urldisplay,databaseContract.dataEntry.SQL_DELETE_ENTRIES_TRANS,databaseContract.dataEntry.SQL_CREATE_ENTRIES_TRANS
                ,databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_TRANS);
                // Getting Array of Contacts
                JSONArray feeds  = json.getJSONArray(TRANS_JSON_CATEGORIES);
                //Log.e(TAG, "Response from url: " + feeds.length());
                // looping through All Contacts

                int totalcc = 0;
                int m = 0;

                if(feeds.length()>2){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_TRANS);
                }

                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String uniqueid = c.optString("uniqueid");
                    String name = c.optString("name");
                    String contact = c.optString("contact");
                    String service = c.optString("service");
                    String description = c.optString("description");
                    String horaire = c.optString("horaire");
                    String price = c.optString("price");
                    String pointfort = c.optString("pointfort");
                    String extras = c.optString("extras");
                    String mail = c.optString("mail");
                    String payement = c.optString("price");
                    String primpimage = c.optString("primpimage");
                    String galeryOne = c.optString("galeryOne");
                    String galerytwo = c.optString("galerytwo");
                    String galerytree = c.optString("galerytree");
                    String reserveone = c.optString("reserveone");
                    String reservetwo = c.optString("reservetwo");
                    String loanbus = c.optString("loanbus");
                    String transline = c.optString("transline");
                    String Modified = c.optString("modified");
                    String maxcap = c.optString("maxcap");

                    Integer iaRating = RatingNumber(uniqueid);
                    String isSrat = String.valueOf(iaRating);

                    createAddDatadbTRANS( uniqueid,  name,  contact,  service,  description,
                            horaire,  price,  pointfort,  extras,  mail,payement,  primpimage,
                            galeryOne,  galerytwo,  galerytree,loanbus,  transline,  isSrat,
                            reservetwo,  maxcap,  Modified,  Id);
                    if(CheckFavoriteInstant(uniqueid)) {
                        AddFavoriteInstant( name,  contact, isSrat, description, primpimage, uniqueid);
                    }
                    //Log.e(TAG, "Response from url: " + feedList.size());
                    mophs.add(totalcc,primpimage);
                    totalcc++;

                    mophs.add(totalcc,galeryOne);
                    totalcc++;

                    mophs.add(totalcc,galerytwo);
                    totalcc++;

                    mophs.add(totalcc,galerytree);
                    totalcc++;
                    m++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                Log.e(TAG, "GET TRANS Response from url: " + cookies);
                System.out.print("Caught NullPointerException");
            }
            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if(result.size() > 1){
                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_TRANS,result);
                new AsyncSaveImageAuto().execute(result);
            }
            Log.e("KEMAL","ENFANT TRANS JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT TRANS JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    private class GetINNOV extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            //Log.e(TAG, "Response from url: " + json.toString());
            try {
                noDATAhelper(urldisplay,databaseContract.dataEntry.SQL_DELETE_ENTRIES_INNOV,databaseContract.dataEntry.SQL_CREATE_ENTRIES_INNOV
                ,databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_INNOV);
                // Getting Array of Contacts
                JSONArray feeds  = json.getJSONArray(INNOV_JSON_CATEGORIES);
                //Log.e(TAG, "Response from url: " + feeds.length());
                // looping through All Contacts

                int totalcc = 0;
                int m = 0;

                if(feeds.length()>2){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_INNOV);
                }

                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String uniqueid = c.optString("uniqueid");
                    String name = c.optString("name");
                    String contact = c.optString("contact");
                    String service = c.optString("service");
                    String description = c.optString("description");
                    String video = c.optString("video");
                    String innovname = c.optString("innovname");
                    String bibliot = c.optString("bibliot");
                    String stectn = c.optString("stectn");
                    String historq = c.optString("historq");
                    String mail = c.optString("mail");
                    String primpimage = c.optString("primpimage");
                    String galeryOne = c.optString("galeryOne");
                    String galerytwo = c.optString("galerytwo");
                    String galerytree = c.optString("galerytree");
                    String galeryfour = c.optString("galeryfour");
                    String galeryfive = c.optString("galeryfive");
                    String galerysix = c.optString("galerysix");
                    String reservetwo = c.optString("reservetwo");
                    String Modified = c.optString("modified");
                    createAddDatadbINNOV( uniqueid,  name,  contact,  service,  description,
                            innovname,  video,  bibliot,  stectn,  historq,  mail,
                            primpimage,  galeryOne,  galerytwo,  galerytree,  galeryfour,
                            galeryfive,  galerysix,  reservetwo, Modified,  Id);
                    if(CheckFavoriteInstant(uniqueid)) {
                        AddFavoriteInstant( name,  contact, "", innovname, primpimage, uniqueid);
                    }
                    //Log.e(TAG, "Response from url: " + feedList.size());
                    mophs.add(totalcc,primpimage);
                    totalcc++;

                    mophs.add(totalcc,galeryOne);
                    totalcc++;

                    mophs.add(totalcc,galerytwo);
                    totalcc++;

                    mophs.add(totalcc,galerytree);
                    totalcc++;

                    mophs.add(totalcc,galeryfour);
                    totalcc++;

                    mophs.add(totalcc,galeryfive);
                    totalcc++;

                    mophs.add(totalcc,galerysix);
                    totalcc++;
                    m++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                Log.e(TAG, "GET INNOV Response from url: " + cookies);
                System.out.print("Caught NullPointerException");
            }

            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if(result.size() > 1){
                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_INNOV,result);
                new AsyncSaveImageAuto().execute(result);
            }
            Log.e("KEMAL","ENFANT INNOV JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT INNOV JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    private class GetFAV extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            //Log.e("KEMAL","ENFANT AVEC  " + json.toString());
            try {
                JSONArray feeds  = json.getJSONArray(FAV_JSON_CATEGORIES);
                Log.e("KEMAL","ROW  " + feeds.length());
                int m = 0;
                if(feeds.length()>1){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_FAV);
                }
                while (m < feeds.length()) {
                    JSONObject c = feeds.getJSONObject(m);
                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String fvuniqid = c.optString("fvuniqid");
                    String byemail = c.optString("byemail");
                    String boolvar = c.optString("boolvar");
                    String genre = c.optString("genre");
                    String created_at = c.optString("created_at");
                    createAddDatadbFAV(fvuniqid, byemail,boolvar,genre,created_at,  Id);
                    m++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
            }
            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.e("KEMAL","ENFANT FAV JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT FAV JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }
    private class GetMSG extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);
            //Log.e("KEMAL","ENFANT AVEC  " + json.toString());
            try {
                JSONArray feeds  = json.getJSONArray(MSG_JSON_CATEGORIES);
               // Log.e("KEMAL","ROW  " + feeds.length());
                int m = 0;
                if(feeds.length()>1){
                    SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    myDB.execSQL(databaseContract.dataEntry.SQL_DELETE_ENTRIES_MSG);
                }
                while (m < feeds.length()) {
                    JSONObject c = feeds.getJSONObject(m);
                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String formsg = c.optString("formsg");
                    String ofmsg = c.optString("ofmsg");
                    String msg = c.optString("msg");
                    String onmsg = c.optString("onmsg");
                    String created_at = c.optString("created_at");
                    createAddDatadbMSG(formsg, ofmsg,msg,onmsg,created_at,  Id);
                    m++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
            }
            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.e("KEMAL","ENFANT FAV JAI FINIS ");
        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT FAV JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    private class GetPROFS extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<String> mophs = new ArrayList<String>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject jsonX = jParser.getJSONFromUrl(urldisplay);
            JSONObject json =  urlToJsonObj(urldisplay);;
            //Log.e("KEMAL","ENFANT PROFILS IMG  " + json.toString());
            try {
                JSONArray feeds  = json.getJSONArray(PROFILS_JSON_CATEGORIES);
                Log.e("KEMAL","ROW PROFILS " + feeds.length());
                int m = 0;

                while (m < feeds.length()) {
                    JSONObject c = feeds.getJSONObject(m);
                    // Storing each json item in variable
                    String Id = c.optString("id");
                    String foremail = c.optString("foremail");
                    String imgvar = c.optString("imgvar");
                    createAddDatadbPROFS(foremail, imgvar,Id);
                    m++;
                }
                //profils tool corrected
                Thread.sleep(50);
                    profdb myprofils = new profdb(getActivity().getApplicationContext());
                    SessionManager session = new SessionManager(getActivity().getApplicationContext());
                    if (session.isLoggedIn()) {
                        Log.e("KEMAL","PREPARE DATABASE FOR PROFILS SAVE ");
                        SQLiteHandler db;
                        db = new SQLiteHandler(getActivity().getApplicationContext());
                        HashMap<String, String> user = db.getUserDetails();

                        String name = user.get("name");
                        String email = user.get("settings");
                        List<profm> melist = myprofils.getProfByMail(email);
                        if(melist.size() >0){
                            profm myUprofils = melist.get(0);
                            String imgn = myUprofils.getImgvar();
                            TinyDB tiny = new TinyDB(getActivity().getApplicationContext());
                            tiny.putString(DEFAULT_PREFS_SETTINGS_KEY_PROFILS_NM,imgn);
                            final String filename = imgn;
                            Storage storage = new Storage(getActivity().getApplicationContext());
                            String path = storage.getExternalStorageDirectory();
                            String newDir = path + File.separator + DATA_DIRECTORI;
                            String newDiri = newDir + File.separator + "images";
                            storage.createDirectory(newDir);
                            storage.createDirectory(newDiri);
                            String fileph = newDiri + File.separator + filename;
                            if(storage.isFileExist(fileph)){
                                Log.e("DOWNLOAD TASK","FICHIER EXISTE DEJA --- " +  imgn);
                            }else {
                                Glide.with(getActivity().getApplicationContext())
                                        .asBitmap()
                                        .load(SERVER_IMGURL_PROFILS +imgn)
                                        .into(new Target<Bitmap>() {
                                            @Override
                                            public void onStart() {Log.e("KEMAL STORAGE", "READY TO VAR DOWN = "+ filename);}
                                            @Override
                                            public void onStop() {}
                                            @Override
                                            public void onDestroy() {}
                                            @Override
                                            public void onLoadStarted(@Nullable Drawable placeholder) {}
                                            @Override
                                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                                Log.d("GlideMar", "marker onLoadFailed");
                                            }

                                            @Override
                                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                                Storage storage = new Storage(getActivity().getApplicationContext());
                                                String path = storage.getExternalStorageDirectory();
                                                String newDir = path + File.separator + DATA_DIRECTORI;
                                                String newDiri = newDir + File.separator + "images";
                                                storage.createDirectory(newDir);
                                                storage.createDirectory(newDiri);
                                                String fileph = newDiri + File.separator + filename;
                                                try {
                                                    storage.createFile(fileph, resource);
                                                }catch (Exception e){}

                                            }
                                            @Override
                                            public void onLoadCleared(@Nullable Drawable placeholder) {}
                                            @Override
                                            public void getSize(@NonNull SizeReadyCallback cb) {cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);}
                                            @Override
                                            public void removeCallback(@NonNull SizeReadyCallback cb) {}
                                            @Override
                                            public void setRequest(@Nullable Request request) { }
                                            @Nullable
                                            @Override
                                            public Request getRequest() {return null; }
                                        });
                            }
                        }
                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mophs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.e("KEMAL","ENFANT PROFILS JAI FINIS ");

        }

        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","ENFANT PROFILS JAI COMMENCER ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    public void AsyncSaveImageAutoFrnt(ArrayList<String> mi){
        for(int i = 0; i <= mi.size()-1; i++)
        {
            Log.e("DOWNLOAD TASK",SERVER_IMGURL_API + mi.get(i) + " TO THIS " +  mi.get(i));
            //new DownloadTask().execute(SERVER_IMGURL_API + params[0].get(i), params[0].get(i));
        }
    }

    public class AsyncSaveImageAuto extends AsyncTask<ArrayList<String>, Void, Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // After completing execution of given task, control will return here.
            // Hence if you want to populate UI elements with fetched data, do it here.
        }
        @Override
        protected Void doInBackground(ArrayList<String>... params) {
            Log.e("KEMAL STORAGE", "RECIEVED FOR DOWNLOAD FILE BY GLIDE");
            try{
                for(int i = 0; i < params[0].size(); i++)
                {
                    final String filename = params[0].get(i);
                    Storage storage = new Storage(getContext());
                    String path = storage.getExternalStorageDirectory();
                    String newDir = path + File.separator + DATA_DIRECTORI;
                    String newDiri = newDir + File.separator + "images";
                    storage.createDirectory(newDir);
                    storage.createDirectory(newDiri);
                    String fileph = newDiri + File.separator + filename;
                    if(storage.isFileExist(fileph)){
                        Log.e("DOWNLOAD TASK","FICHIER EXISTE DEJA --- " +  params[0].get(i));
                    }else {
                        Log.e("DOWNLOAD TASK",SERVER_IMGURL_API + params[0].get(i) + " T" +  params[0].get(i));
                        Glide.with(getActivity().getApplicationContext())
                                .asBitmap()
                                .load(SERVER_IMGURL_API + params[0].get(i))
                                .into(new Target<Bitmap>() {
                                    @Override
                                    public void onStart() {Log.e("KEMAL STORAGE", "READY TO VAR DOWN = "+ filename);}
                                    @Override
                                    public void onStop() {}
                                    @Override
                                    public void onDestroy() {}
                                    @Override
                                    public void onLoadStarted(@Nullable Drawable placeholder) {}
                                    @Override
                                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                        Log.d("GlideMar", "marker onLoadFailed");
                                    }

                                    @Override
                                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                        Storage storage = new Storage(getContext());
                                        String path = storage.getExternalStorageDirectory();
                                        String newDir = path + File.separator + DATA_DIRECTORI;
                                        String newDiri = newDir + File.separator + "images";
                                        storage.createDirectory(newDir);
                                        storage.createDirectory(newDiri);
                                        String fileph = newDiri + File.separator + filename;
                                        try {
                                            storage.createFile(fileph, resource);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }

                                    }
                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {}
                                    @Override
                                    public void getSize(@NonNull SizeReadyCallback cb) {cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);}
                                    @Override
                                    public void removeCallback(@NonNull SizeReadyCallback cb) {}
                                    @Override
                                    public void setRequest(@Nullable Request request) { }
                                    @Nullable
                                    @Override
                                    public Request getRequest() {return null; }
                                });
                    }
                } return null;
            }catch(OutOfMemoryError e){
                e.printStackTrace();
                return null;
            }
        }
    }

    void createAddDatadb(String Title, String Adress,String Rat,String Desc){
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_LIFE);

        ContentValues values = new ContentValues();
        values.put(databaseContract.dataEntry.COLUMN_NAME_TITLEs, Title);
        values.put(databaseContract.dataEntry.COLUMN_NAME_ADRSS, Adress);
        values.put(databaseContract.dataEntry.COLUMN_NAME_RAT, Rat);
        values.put(databaseContract.dataEntry.COLUMN_NAME_DESC, Desc);
        long newRowId = myDB.insert(databaseContract.dataEntry.TABLE_NAME, null, values);
    }

    void createAddDatadbHOTEL(String Title, String Id,String Adress,String Payement,String Siteweb,String Longdescription,String Uniqueid,
                              String Service,String Pointfort,String Pointfaible,String Prinpimage,String GaleryOne,String Galerytwo,
                              String Galeryfor,String Galeryfive,String Galerysix,String Mets,String Modified, String Description,String rating)
            {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_HOTEL);

        ContentValues values = new ContentValues();
       // values.put(databaseContract.dataEntry.hID, Id );
        values.put(databaseContract.dataEntry.COLUMN_NAME_hTITLEs,Title);
        values.put(databaseContract.dataEntry.COLUMN_NAME_hADRSS,Adress);
        values.put(databaseContract.dataEntry.COLUMN_NAME_payement,Payement);
        values.put(databaseContract.dataEntry.COLUMN_NAME_siteweb ,Siteweb);
        values.put(databaseContract.dataEntry.COLUMN_NAME_longdescription ,Longdescription);
        values.put(databaseContract.dataEntry.COLUMN_NAME_uniqueid ,Uniqueid);
        values.put(databaseContract.dataEntry.COLUMN_NAME_rating , rating);
        values.put(databaseContract.dataEntry.COLUMN_NAME_service ,Service);
        values.put(databaseContract.dataEntry.COLUMN_NAME_pointfort ,Pointfort);
        values.put(databaseContract.dataEntry.COLUMN_NAME_pointfaible ,Pointfaible);
        values.put(databaseContract.dataEntry.COLUMN_NAME_prinpimage ,Prinpimage);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galeryOne ,GaleryOne);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galerytwo ,Galerytwo);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfor ,Galeryfor);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfive ,Galeryfive);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galerysix ,Galerysix);
        values.put(databaseContract.dataEntry.COLUMN_NAME_mets ,Mets);
        values.put(databaseContract.dataEntry.COLUMN_NAME_modified,Modified);
        values.put(databaseContract.dataEntry.COLUMN_NAME_hDESC,Description);
        long kid = myDB.insert(databaseContract.dataEntry.TABLE_NAME_HOTEL, null, values);
                if(kid == -1){
                    long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_HOTEL, null, values,SQLiteDatabase.CONFLICT_REPLACE);
                }else {
                    if(Title!=null){
                        createAddDatadb(Title,Adress,rating,"Hotel");
                    }
                }
    }
    void createAddDatadbRESTO(String Title, String Id,String Adress,String Payement,String Siteweb,String Longdescription,String Uniqueid,
                              String Service,String Pointfort,String Pointfaible,String Prinpimage,String GaleryOne,String Galerytwo,
                              String Galeryfor,String Galeryfive,String Galerysix,String Mets,String Modified, String Description,String rating)
    {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_RESTO);

        ContentValues values = new ContentValues();
        //values.put(databaseContract.dataEntry.hID, Id );
        values.put(databaseContract.dataEntry.COLUMN_NAME_hTITLEs,Title);
        values.put(databaseContract.dataEntry.COLUMN_NAME_hADRSS,Adress);
        values.put(databaseContract.dataEntry.COLUMN_NAME_horaire,Payement);
        values.put(databaseContract.dataEntry.COLUMN_NAME_siteweb ,Siteweb);
        values.put(databaseContract.dataEntry.COLUMN_NAME_longdescription ,Longdescription);
        values.put(databaseContract.dataEntry.COLUMN_NAME_uniqueid ,Uniqueid);
        values.put(databaseContract.dataEntry.COLUMN_NAME_rating , rating);
        values.put(databaseContract.dataEntry.COLUMN_NAME_service ,Service);
        values.put(databaseContract.dataEntry.COLUMN_NAME_pointfort ,Pointfort);
        values.put(databaseContract.dataEntry.COLUMN_NAME_pointfaible ,Pointfaible);
        values.put(databaseContract.dataEntry.COLUMN_NAME_prinpimage ,Prinpimage);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galeryOne ,GaleryOne);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galerytwo ,Galerytwo);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfor ,Galeryfor);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfive ,Galeryfive);
        values.put(databaseContract.dataEntry.COLUMN_NAME_galerysix ,Galerysix);
        values.put(databaseContract.dataEntry.COLUMN_NAME_mets ,Mets);
        values.put(databaseContract.dataEntry.COLUMN_NAME_modified,Modified);
        values.put(databaseContract.dataEntry.COLUMN_NAME_hDESC,Description);
        long kid = myDB.insert(databaseContract.dataEntry.TABLE_NAME_RESTO, null, values);
        if(kid == -1){
            long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_RESTO, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        }else {
            if(Title!=null){
                createAddDatadb(Title,Adress,rating,"Restaurant");
            }
        }
    }

    void createAddDatadbSITES(String uniqueid, String Id, String name, String contact, String service, String mail,
                String horaire, String price, String pointfort, String extras, String localisation,
                String more,String primpimage, String galeryOne, String galerytwo, String galerytree, String galeryfour,
                String galeryfive, String galerysix, String galeryseven, String reserveone,
                String reservetwo, String plusStr, String Modified)
            {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_SITES);
        ContentValues values = new ContentValues();
        //values.put(databaseContract.dataEntry.hID,Id);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_name,name);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_contact,contact);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_horaire,horaire);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_mail,mail);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_uniqueid,uniqueid);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_service,service);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_pointfort,pointfort);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_price,price);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_primpimage,primpimage);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galeryOne,galeryOne);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galerytwo,galerytwo);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galerytree,galerytree);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfour,galeryfour);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfive,galeryfive);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galerysix,galerysix);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_galeryseven,galeryseven);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_localisation,localisation);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_more,more);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_reserveone,reserveone);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_reservetwo,reservetwo);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_extras,extras);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_modified, Modified);
	    values.put(databaseContract.dataEntry.COLUMN_NAME_plusStr,plusStr);
                long kid = myDB.insert(databaseContract.dataEntry.TABLE_NAME_SITES, null, values);
                if(kid == -1){
                    long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_SITES, null, values,SQLiteDatabase.CONFLICT_REPLACE);
                }else {
                    if(name!=null){
                        createAddDatadb(name,localisation,"0","Sites Touristique");
                    }
                }
    }
    void createAddDatadbSERLI(String uniqueid, String Id, String name, String contact, String service, String description, String
							horaire, String price, String pointfort, String extras, String siteweb, String
							payement, String primpimage, String galeryOne, String galerytwo, String galerytree, String
							galeryfour, String galeryfive, String galerysix, String zonelivre, String
							reserveone, String reservetwo, String maxlivre, String Modified)
            {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_SERLI);

        ContentValues values = new ContentValues();
		//values.put(databaseContract.dataEntry.hID,Id);
		values.put(databaseContract.dataEntry.COLUMN_NAME_name,name);
		values.put(databaseContract.dataEntry.COLUMN_NAME_contact,contact);
		values.put(databaseContract.dataEntry.COLUMN_NAME_horaire,horaire);
		values.put(databaseContract.dataEntry.COLUMN_NAME_uniqueid,uniqueid);
		values.put(databaseContract.dataEntry.COLUMN_NAME_service,service);
		values.put(databaseContract.dataEntry.COLUMN_NAME_pointfort,pointfort);
		values.put(databaseContract.dataEntry.COLUMN_NAME_payement,payement);
		values.put(databaseContract.dataEntry.COLUMN_NAME_primpimage,primpimage);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryOne,galeryOne);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerytwo,galerytwo);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerytree,galerytree);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfour,galeryfour);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfive,galeryfive);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerysix,galerysix);
		values.put(databaseContract.dataEntry.COLUMN_NAME_zonelivre,zonelivre);
		values.put(databaseContract.dataEntry.COLUMN_NAME_maxlivre,maxlivre);
		values.put(databaseContract.dataEntry.COLUMN_NAME_siteweb,siteweb);
		values.put(databaseContract.dataEntry.COLUMN_NAME_price,price);
		values.put(databaseContract.dataEntry.COLUMN_NAME_reserveone,reserveone);
		values.put(databaseContract.dataEntry.COLUMN_NAME_reservetwo,reservetwo);
		values.put(databaseContract.dataEntry.COLUMN_NAME_extras,extras);
		values.put(databaseContract.dataEntry.COLUMN_NAME_modified,Modified);
		values.put(databaseContract.dataEntry.COLUMN_NAME_hDESC,description);
                long kid = myDB.insert(databaseContract.dataEntry.TABLE_NAME_SERLI, null, values);
                if(kid == -1){
                    long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_SERLI, null, values,SQLiteDatabase.CONFLICT_REPLACE);
                }else {
                    if(name!=null){
                        createAddDatadb(name,siteweb,"0","Service de livraison");
                    }
                }
    }

    void createAddDatadbTRANS(String uniqueid, String name, String contact, String service, String description, String
				horaire, String price, String pointfort, String extras, String mail, String
				payement, String primpimage, String galeryOne, String galerytwo, String galerytree, String
				loanbus, String transline, String reserveone, String reservetwo, String maxcap, String Modified, String Id)
            {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_TRANS);

        ContentValues values = new ContentValues();
		//values.put(databaseContract.dataEntry.hID,Id);
		values.put(databaseContract.dataEntry.COLUMN_NAME_name,name);
		values.put(databaseContract.dataEntry.COLUMN_NAME_contact,contact);
		values.put(databaseContract.dataEntry.COLUMN_NAME_horaire,horaire);
		values.put(databaseContract.dataEntry.COLUMN_NAME_uniqueid,uniqueid);
		values.put(databaseContract.dataEntry.COLUMN_NAME_service,service);
		values.put(databaseContract.dataEntry.COLUMN_NAME_pointfort,pointfort);
		values.put(databaseContract.dataEntry.COLUMN_NAME_price,price);
		values.put(databaseContract.dataEntry.COLUMN_NAME_mail,mail);
		values.put(databaseContract.dataEntry.COLUMN_NAME_primpimage,primpimage);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryOne,galeryOne);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerytwo,galerytwo);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerytree,galerytree);
		values.put(databaseContract.dataEntry.COLUMN_NAME_hDESC,description);
		values.put(databaseContract.dataEntry.COLUMN_NAME_loanbus,loanbus);
		values.put(databaseContract.dataEntry.COLUMN_NAME_transline,transline);
		values.put(databaseContract.dataEntry.COLUMN_NAME_reserveone,reserveone);
		values.put(databaseContract.dataEntry.COLUMN_NAME_reservetwo,reservetwo);
		values.put(databaseContract.dataEntry.COLUMN_NAME_maxcap,maxcap);
        values.put(databaseContract.dataEntry.COLUMN_NAME_payement,payement);
		values.put(databaseContract.dataEntry.COLUMN_NAME_modified,Modified);
		values.put(databaseContract.dataEntry.COLUMN_NAME_extras,extras);
                long kid = myDB.insert(databaseContract.dataEntry.TABLE_NAME_TRANS, null, values);
                if(kid == -1){
                    long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_TRANS, null, values,SQLiteDatabase.CONFLICT_REPLACE);
                }else {
                    if(name!=null){
                        createAddDatadb(name,transline,"0","Transport");
                    }
                }
    }

    void createAddDatadbINNOV(String uniqueid, String name, String contact, String service, String description,
				String innovname, String video, String bibliot, String stectn, String historq, String mail,
				String primpimage, String galeryOne, String galerytwo, String galerytree, String galeryfour,
				String galeryfive, String galerysix, String reservetwo,String Modified, String Id)
            {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_INNOV);

        ContentValues values = new ContentValues();
		//values.put(databaseContract.dataEntry.hID,Id);
		values.put(databaseContract.dataEntry.COLUMN_NAME_name,name);
		values.put(databaseContract.dataEntry.COLUMN_NAME_contact,contact);
		values.put(databaseContract.dataEntry.COLUMN_NAME_uniqueid,uniqueid);
		values.put(databaseContract.dataEntry.COLUMN_NAME_service,service);
		values.put(databaseContract.dataEntry.COLUMN_NAME_innovname,innovname);
		values.put(databaseContract.dataEntry.COLUMN_NAME_video,video);
		values.put(databaseContract.dataEntry.COLUMN_NAME_mail,mail);
		values.put(databaseContract.dataEntry.COLUMN_NAME_primpimage,primpimage);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryOne,galeryOne);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerytwo,galerytwo);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfour,galeryfour);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerytree,galerytree);
		values.put(databaseContract.dataEntry.COLUMN_NAME_hDESC,description);
		values.put(databaseContract.dataEntry.COLUMN_NAME_bibliot,bibliot);
		values.put(databaseContract.dataEntry.COLUMN_NAME_stectn,stectn);
		values.put(databaseContract.dataEntry.COLUMN_NAME_historq,historq);
		values.put(databaseContract.dataEntry.COLUMN_NAME_reservetwo,reservetwo);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galeryfive,galeryfive);
		values.put(databaseContract.dataEntry.COLUMN_NAME_modified,Modified);
		values.put(databaseContract.dataEntry.COLUMN_NAME_galerysix,galerysix);
        long kid =myDB.insert(databaseContract.dataEntry.TABLE_NAME_INNOV, null, values);
        if(kid == -1){
            long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_INNOV, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        }else {
            if(name!=null){
                createAddDatadb(name,innovname,"0","Innovation");
            }
        }
    }
    void createAddDatadbFAV(String fvuniqid, String byemail, String boolvar, String genre, String created_at, String Id)
    {
        Log.e("KEMAL db", "jai demarrer");
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_FAV);
        ContentValues values = new ContentValues();
        //values.put(databaseContract.dataEntry.hID,Id);
        values.put(databaseContract.dataEntry.COLUMN_NAME_fvuniqid,fvuniqid);
        values.put(databaseContract.dataEntry.COLUMN_NAME_byemail,byemail);
        values.put(databaseContract.dataEntry.COLUMN_NAME_boolvar,boolvar);
        values.put(databaseContract.dataEntry.COLUMN_NAME_genre,genre);
        values.put(databaseContract.dataEntry.COLUMN_NAME_created_at,created_at);
        //long kid =myDB.insert(databaseContract.dataEntry.TABLE_NAME_FAV, null, values);
        myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_FAV, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        //if(kid == -1){
        //    long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_FAV, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        //}else {
        //}
    }

    void createAddDatadbMSG(String fvuniqid, String byemail, String boolvar, String genre, String created_at, String Id)
    {
        Log.e("KEMAL db", "jai demarrer");
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_MSG);
        ContentValues values = new ContentValues();
        //values.put(databaseContract.dataEntry.hID,Id);
        values.put(databaseContract.dataEntry.COLUMN_NAME_formsg,fvuniqid);
        values.put(databaseContract.dataEntry.COLUMN_NAME_ofmsg,byemail);
        values.put(databaseContract.dataEntry.COLUMN_NAME_msg,boolvar);
        values.put(databaseContract.dataEntry.COLUMN_NAME_onmsg,genre);
        values.put(databaseContract.dataEntry.COLUMN_NAME_created_at,created_at);
        //long kid =myDB.insert(databaseContract.dataEntry.TABLE_NAME_FAV, null, values);
        myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_MSG, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        //if(kid == -1){
        //    long cid =  myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_FAV, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        //}else {
        //}
    }

    void createAddDatadbPROFS(String foremail, String imgvar, String Id)
    {
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_PROFILS);
        ContentValues values = new ContentValues();
        //values.put(databaseContract.dataEntry.hID,Id);
        values.put(databaseContract.dataEntry.COLUMN_NAME_foremail,foremail);
        values.put(databaseContract.dataEntry.COLUMN_NAME_imgvar,imgvar);
        myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_PROFILS, null, values,SQLiteDatabase.CONFLICT_REPLACE);
    }
    private class DownloadTask extends AsyncTask<String,Void, Bitmap>{
        protected void onPreExecute(){}
        protected Bitmap doInBackground(String...urls){
            Log.e("kml", "TRAINING FOR DOWNLOAD");
            URL url = null;
            try{
                url = new URL(urls[0]);
            }catch(MalformedURLException e){
                Log.e("kml", "URL ERROR");
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try{
                Log.e("kml", "START IMAGE DOWNLOAS");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                if(bmp!=null){
                    Log.e("kml", "FINISH IMAGE DOWNLOAS");
                    //mProgressDialog.dismiss();"
                    Storage storage = new Storage(getContext());
                    String path = storage.getExternalStorageDirectory();
                    String newDir = path + File.separator + DATA_DIRECTORI;
                    String newDiri = newDir + File.separator + "images";
                    storage.createDirectory(newDir);
                    storage.createDirectory(newDiri);
                    String fileph = newDiri + File.separator + urls[1];
                    storage.createFile(fileph, bmp);
                }else {
                    Log.e("kml", "IMAGE DOWNLOAS");
                }
                return bmp;

            }catch(IOException e){
                Log.e("kil", e.getMessage());
                e.printStackTrace();
            }finally{
                connection.disconnect();
            }
            return null;
        }
        protected void onPostExecute(Bitmap result){}
    }

    //hotel link first
    private class StartdbLoadFromNet extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                Thread.sleep(500);
                Log.e("KEMAL","STARTUP " + params[6]);
                new GetFAV().execute(params[7],params[6]);
                Thread.sleep(50);
                new GetRESTO().execute(params[0],params[6] );
                new GetHotel().execute(params[1],params[6]);
                new GetSITES().execute(params[2],params[6]);
                new GetSERLI().execute(params[3],params[6]);
                new GetTRANS().execute(params[4],params[6]);
                new GetINNOV().execute(params[5],params[6]);
                new GetPROFS().execute(params[8],params[6]);
                new GetMSG().execute(params[9],params[6]);
            } catch (InterruptedException  e) {
                Toast.makeText(getContext(),"Erreur systeme",Toast.LENGTH_LONG);
            }
            return "Executed";
        }
        @Override
        protected void onPreExecute() {
            Log.e("KEMAL","PARENT RESTO---INNOV JAI COMMENCER ");
        }
        @Override
        protected void onPostExecute(String result) {
            Log.e("KEMAL","PARENT RESTO---INNOV JAI FINIS ");

        }
    }

    private int RatingNumber(String fvuniqid){

        favdb obase = new favdb(getContext());
        List<fav> result = obase.getFavByFvUniq(fvuniqid);
        int workT = 0;
        Log.e("KEMAL","SIZE OF FAVORITE" + result.size());
        if(result.size() > 0){
        for(int i = 0; i < result.size(); i++)
        {
            if (result.get(i).getBoolvar() == "true"){
                workT = workT + 1;
            }else {
                workT =workT + 1;
            }
        }}
        return workT;
    }
    private boolean CheckFavoriteInstant(String fvuniqid){
        SessionManager session = new SessionManager(getContext());
        if (session.isLoggedIn()) {
            SQLiteHandler db;
            db = new SQLiteHandler(getContext());
            HashMap<String, String> user = db.getUserDetails();
            String email = user.get("settings");
            favdb obase = new favdb(getContext());
            List<fav> result = obase.getFavByFvuniqAndEmail(fvuniqid,email);
            if(result.size() > 0){
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }

   String urlToJson(String URl){
        try {
            OkHttpClient client = new OkHttpClient();
            com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
                    .url(URl)
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Postman-Token", "ab212bbb-1902-4c7b-b9d7-9c6cc2b798d7")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return  response.body().string();
            }catch (IOException e){
                return null;
            }
        }catch (NullPointerException e){
            return null;
        }

    }
    JSONObject urlToJsonObj(String URL){
        if (!URL.equals(null)){
            String jsonStr = urlToJson(URL);
            try {
                Log.e("OKHTTP TOOLS","OKHTTP RESULT = " + jsonStr);
                    if (!jsonStr.equals(null)){
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    return jsonObj;
                }
                return null;
            }catch (org.json.JSONException e){
                return null;
            }catch(NullPointerException e)
            {
                return null;
            }catch (Exception e ){
                Log.e("OKHTTP TOOLS","ERREUR NON PRIS EN CHARGE : MSG = " + e.getMessage());
                return null;
            }
        }else {return null;}
    }
    private void AddFavoriteInstant(String Title, String Adress,String Rat,String Desc,String Imurl,String Unic){
        SQLiteDatabase myDB = getActivity().openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_PROFILS);
        ContentValues values = new ContentValues();
        //values.put(databaseContract.dataEntry.hID,Id);
        values.put(databaseContract.dataEntry.COLUMN_NAME_TITLEs,Title);
        values.put(databaseContract.dataEntry.COLUMN_NAME_ADRSS,Adress);
        values.put(databaseContract.dataEntry.COLUMN_NAME_RAT,Rat);
        values.put( databaseContract.dataEntry.COLUMN_NAME_unic,Desc);
        values.put( databaseContract.dataEntry.COLUMN_NAME_imurl,Imurl);
        values.put(databaseContract.dataEntry.COLUMN_NAME_DESC,Unic);
        myDB.insertWithOnConflict(databaseContract.dataEntry.TABLE_NAME_FAVL, null, values,SQLiteDatabase.CONFLICT_REPLACE);
    }
}
