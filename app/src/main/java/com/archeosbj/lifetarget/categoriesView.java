package com.archeosbj.lifetarget;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.archeosbj.lifetarget.Adpter.HotelAdapter;
import com.archeosbj.lifetarget.Adpter.RestoAdapter;
import com.archeosbj.lifetarget.Adpter.SearchAdapter;
import com.archeosbj.lifetarget.Adpter.SerliAdapter;
import com.archeosbj.lifetarget.Adpter.TransAdapter;
import com.archeosbj.lifetarget.Model.Life;
import com.archeosbj.lifetarget.Model.Serli;
import com.archeosbj.lifetarget.Model.Trans;
import com.archeosbj.lifetarget.Model.hotel;
import com.archeosbj.lifetarget.Model.resto;
import com.archeosbj.lifetarget.PreferenceTools.TinyDB;
import com.archeosbj.lifetarget.data.database;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.data.hoteldb;
import com.archeosbj.lifetarget.data.restodb;
import com.archeosbj.lifetarget.data.serlidb;
import com.archeosbj.lifetarget.data.transdb;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

public class categoriesView extends AppCompatActivity {
    //worked search zone
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    database dbase;
    private boolean stateSearch;
    private int indexcat;
    private boolean sendbycat;
    public static String CATEGORIES_INT = "APP_CATEGORIES";
    public static String CATEGORIES_INDEX = "APP_CATEGORIES_INDEX"; //INDEX IS INTEGER
    public static String CATEGORIES_SENDER = "APP_CATEGORIES_SENDER";
    private ProgressDialog pDialog;
    private ProgressBar pBar;
    private String categoriesString = "Life Target";
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_view);
        dbase = new database(this);
        recyclerView = (RecyclerView) findViewById(R.id.recicler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Intent intent = getIntent();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        pBar = (ProgressBar)  findViewById(R.id.load_prg);
        pBar.setMax(100);

        sendbycat = intent.getIntExtra(CATEGORIES_SENDER,2) ==1;
        indexcat = intent.getIntExtra(CATEGORIES_INDEX,9);
        //Toast.makeText(getBaseContext(),String.valueOf(indexcat) ,Toast.LENGTH_SHORT).show();
        if ((intent.getIntExtra(CATEGORIES_SENDER,2) ==1)){
            //CATEGORIE VIEW
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(intent.getStringExtra(CATEGORIES_INT));
            toolbar.setVisibility(View.VISIBLE);
            materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_zone);
            materialSearchBar.setPlaceHolderColor(getResources().getColor(R.color.white));
            materialSearchBar.setVisibility(View.GONE);
            stateSearch = false;
            setSupportActionBar(toolbar);
        }else{
            //RECHERCHE AVANCER
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(intent.getStringExtra(CATEGORIES_INT));
            toolbar.setVisibility(View.GONE);
            materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_zone);
            materialSearchBar.setPlaceHolderColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);
            //Toast.makeText(getBaseContext(),"NON CATEGORISER",Toast.LENGTH_SHORT).show();
            makeuniversalsearch();
        }

        //Intent intent = getIntent();
        //materialSearchBar.setPlaceHolder("me");
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_zone);
        materialSearchBar.setHint(getString(R.string.search_hint));
        materialSearchBar.setCardViewElevation(10);
        loadsuggestlist();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()));
                    suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //listner pour la recherche en générale sur la page de categorie ou de recherche avancer //important (sameListnercode)
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    dbase = new database(getBaseContext());
                    adapter = new SearchAdapter(getBaseContext(),dbase.getLife(),new SearchAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Life item) {
                            //on item click
                            recomandCLIK(item);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearchs(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        ImageView imagelite = (ImageView) findViewById(R.id.indicator_search);
        imagelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //materialSearchBar.setVisibility(View.VISIBLE);
                if(stateSearch ==false){
                    materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_zone);
                    materialSearchBar.setVisibility(View.VISIBLE);
                    stateSearch = true;
                }else if (stateSearch == true){
                    materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_zone);
                    materialSearchBar.setVisibility(View.GONE);
                    stateSearch = false;
                }

            }
        });

        if (sendbycat == true){
       // Toast.makeText(getApplicationContext(),"CATEGORISER",Toast.LENGTH_SHORT).show();
            //CATEGORIE VIEW
            if(indexcat ==1){
                TinyDB tinydb = new TinyDB(getApplicationContext());
                ArrayList<String> result = tinydb.getListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_RESTO);
                Log.e("TEST KEMAL", "whislen boot = " + result.size());
                if(result.size()>2){
                  new  loadData().execute(result);
                }

                 new Runnable() {
                    @Override
                    public void run() {
                        actResto();
                        Log.e("TEST DATA", "RESTO CHILDREN RUN");
                        handler.postDelayed(this, 10000);
                    }
                }.run();

            }else if(indexcat ==2){
                TinyDB tinydb = new TinyDB(getApplicationContext());
                ArrayList<String> result = tinydb.getListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_HOTEL);
                if(result.size()>2){
                    new  loadData().execute(result);
                }
                new Runnable() {
                    @Override
                    public void run() {
                        actHotel();
                        handler.postDelayed(this, 10000);
                    }
                }.run();

            }else if(indexcat ==3){
                //Toast.makeText(getApplicationContext(),String.valueOf(sendbycat),Toast.LENGTH_SHORT).show();
                /*dbase = new database(getBaseContext());
                adapter = new SearchAdapter(this,dbase.getLife(),new SearchAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Life item) {
                    recomandCLIK(item);
                        //on item click
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        String[] itemstr = new String[12];
                        //itemstr[0] = item.getId();
                        itemstr[1] = item.getAdress();
                        itemstr[2] = item.getDescription();
                        itemstr[3] = item.getRating();
                        itemstr[4] = item.getTitle();
                        intent.putExtra("ITEM",itemstr);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );

                    }
                });
                recyclerView.setAdapter(adapter);*/

            }else if(indexcat ==4){
                TinyDB tinydb = new TinyDB(getApplicationContext());
                ArrayList<String> result = tinydb.getListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_SERLI);
                if(result.size()>2){
                    new  loadData().execute(result);
                }
               new Runnable() {
                    @Override
                    public void run() {
                        actSerli();
                        handler.postDelayed(this, 10000);
                    }
                }.run();

            }else if(indexcat ==5){
                TinyDB tinydb = new TinyDB(getApplicationContext());
                ArrayList<String> result = tinydb.getListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_TRANS);
                if(result.size()>2){
                    new  loadData().execute(result);
                }
                new Runnable() {
                    @Override
                    public void run() {
                        actTrans();
                        handler.postDelayed(this, 10000);
                    }
                }.run();
            }else if(indexcat ==6){

            }
        }


    }

    void actResto(){
        restodb obase = new restodb(this);
        RestoAdapter xadapter = new RestoAdapter(this,obase.getResto(),new RestoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(resto item) {
                //on item click
                Intent intent = new Intent(getBaseContext(), hotelviwer.class);
                String[] RestoItm = new String[24];
                RestoItm[0] = "1";
                RestoItm[1] = String.valueOf(item.getId()) ;
                RestoItm[2] = item.getTitle();
                RestoItm[3] = item.getAdress();
                RestoItm[4] = item.getHoraire();
                RestoItm[5] = item.getSiteweb();
                RestoItm[6] = item.getDescription();
                RestoItm[7] = item.getLongdescription();
                RestoItm[8] = item.getUniqueid();
                RestoItm[9] = item.getRating();
                RestoItm[10] = item.getService();
                RestoItm[11] = item.getPointfort();
                RestoItm[12] = item.getPointfaible();
                RestoItm[13] = item.getPrinpimage();
                RestoItm[14] = item.getMets();
                RestoItm[15] = item.getModified();
                RestoItm[16] = item.getGaleryOne();
                RestoItm[17] =  item.getGalerytwo();
                RestoItm[18] = item.getGaleryfor();
                RestoItm[19] =  item.getGaleryfive();
                RestoItm[20] =  item.getGalerysix();

                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        Log.e("TEST DATA", "RESTO ROW" + obase.getResto().size());
        recyclerView.setAdapter(xadapter);
    }
    void actHotel(){
        hoteldb lbase = new hoteldb(this);
        HotelAdapter yadapter = new HotelAdapter(this,lbase.getHotel(),new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(hotel item) {
                //on item click
                Intent intent = new Intent(getBaseContext(), hotelviwer.class);
                String[] HotelItm = new String[24];
                HotelItm[0] = "2";
                HotelItm[1] = String.valueOf(item.getId()) ;
                HotelItm[2] = item.getTitle();
                HotelItm[3] = item.getAdress();
                HotelItm[4] = item.getPayement();
                HotelItm[5] = item.getSiteweb();
                HotelItm[6] = item.getDescription();
                HotelItm[7] = item.getLongdescription();
                HotelItm[8] = item.getUniqueid();
                HotelItm[9] = item.getRating();
                HotelItm[10] = item.getService();
                HotelItm[11] = item.getPointfort();
                HotelItm[12] = item.getPointfaible();
                HotelItm[13] = item.getPrinpimage();
                HotelItm[14] = item.getMets();
                HotelItm[15] = item.getModified();
                HotelItm[16] = item.getGaleryOne();
                HotelItm[17] =  item.getGalerytwo();
                HotelItm[18] = item.getGaleryfor();
                HotelItm[19] =  item.getGaleryfive();
                HotelItm[20] =  item.getGalerysix();

                intent.putExtra("ITEM",HotelItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        recyclerView.setAdapter(yadapter);
    }
    void actSerli(){
        serlidb nbase = new serlidb(this);
        SerliAdapter liadapter = new SerliAdapter(this,nbase.getSerli(),new SerliAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serli item) {
                //on item click
                Intent intent = new Intent(getBaseContext(), serliview.class);
                String[] HotelItm = new String[28];
                HotelItm[0] = "4";
                HotelItm[1] = String.valueOf(item.getId()) ;
                HotelItm[2] = item.getName();
                HotelItm[3] = item.getContact();
                HotelItm[4] = item.getUniqueid();
                HotelItm[5] = item.getService();
                HotelItm[6] = item.getPointfort();
                HotelItm[7] = item.getPrice();
                HotelItm[8] = item.getPrimpimage();
                HotelItm[9] = item.getGaleryOne();
                HotelItm[10] = item.getGalerytwo();
                HotelItm[11] = item.getGalerytree();
                HotelItm[12] = item.getGaleryfour();
                HotelItm[13] = item.getGaleryfive();
                HotelItm[14] = item.getGalerysix();
                HotelItm[15] = item.getZonelivre();
                HotelItm[16] = item.getMaxlivre();
                HotelItm[17] =  item.getSiteweb();
                HotelItm[18] = item.getHoraire();
                HotelItm[19] =  item.getReserveone();
                HotelItm[20] =  item.getReservetwo();
                HotelItm[21] =  item.getExtras();
                HotelItm[22] =  item.getDescription();
                HotelItm[23] =  item.getPayement();
                intent.putExtra("ITEM",HotelItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        recyclerView.setAdapter(liadapter);
    }

    void actTrans(){
        transdb lbase = new transdb(this);
        TransAdapter yadapter = new TransAdapter(this,lbase.getTrans(),new TransAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trans item) {
                //on item click
                Intent intent = new Intent(getBaseContext(), TransportActivity.class);
                String[] HotelItm = new String[24];
                HotelItm[0] = "5";
                HotelItm[1] = String.valueOf(item.getId()) ;
                HotelItm[2] = item.getName();
                HotelItm[3] = item.getContact();
                HotelItm[4] = item.getUniqueid();
                HotelItm[5] = item.getService();
                HotelItm[6] = item.getPointfort();
                HotelItm[7] = item.getPrice();
                HotelItm[8] = item.getMail();
                HotelItm[9] = item.getPrimpimage();
                HotelItm[10] = item.getGaleryOne();
                HotelItm[11] = item.getGalerytwo();
                HotelItm[12] = item.getGalerytree();
                HotelItm[13] = item.getTransline();
                HotelItm[14] = item.getLoanbus();
                HotelItm[15] =  item.getMaxcap();
                HotelItm[16] = item.getHoraire();
                HotelItm[17] =  item.getReserveone();
                HotelItm[19] =  item.getReservetwo();
                HotelItm[20] =  item.getExtras();
                HotelItm[21] =  item.getDescription();
                HotelItm[22] =  item.getPayement();
                intent.putExtra("ITEM",HotelItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });
        recyclerView.setAdapter(yadapter);
    }


    private void makeuniversalsearch() {
        //init adpter first  //condition de categorie ici //important (sameListnercode)
        adapter = new SearchAdapter(this,dbase.getLife(),new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Life item) {
                recomandCLIK(item);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private class loadData extends AsyncTask<ArrayList<String>,Integer,Boolean> {

            @Override
            protected Boolean doInBackground(ArrayList<String>... params) {

                int whis =0;
                int whiset = 0;
                int whislen = params[0].size();
                Log.e("TEST KEMAL", "whislen boot = " + whislen);
                while (whis < whislen)
                {
                    final String filename = params[0].get(whis);
                    Storage storage = new Storage(getApplicationContext());
                    String path = storage.getExternalStorageDirectory();
                    String newDir = path + File.separator + DATA_DIRECTORI;
                    String newDiri = newDir + File.separator + "images";
                    storage.createDirectory(newDir);
                    storage.createDirectory(newDiri);
                    String fileph = newDiri + File.separator + filename;
                    if(storage.isFileExist(fileph)){
                        whiset=whiset + 1;
                    } else {
                        whiset= whiset+0;
                    }
                    whis = whis + 1;
                    int prg = Math.round ((whiset * 100)/whislen);
                    Log.e("TEST KEMAL", "whislen for  = " + filename + " on ID = " + whis);
                    publishProgress(prg);
                }
                return true;
            }

            @Override
            public void onPreExecute()
            {
                pDialog.setMessage("Telechargement ...");
                showDialog();
                pBar.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPostExecute(Boolean result)
            {
                super.onPostExecute(result);
                if (result){
                    pBar.setVisibility(View.GONE);
                    if(indexcat ==1){
                        actResto();
                    }else if(indexcat ==2){
                        actHotel();
                    }else if(indexcat ==3){

                    }else if(indexcat ==4){
                        actSerli();
                    }else if(indexcat ==5){
                        actTrans();
                    }else if(indexcat ==6){
                    }
                }
                hideDialog();
            }

            @Override
            public void onProgressUpdate(Integer... params)
            {
                 pBar.setProgress(params[0]);
                // show in spinner, access UI elements
            }

        }

    void recomandCLIK(Life item){
        if(item.getDescription() =="Service de livraison"){
            Toast.makeText(getApplicationContext(),"Erreur " + item.getDescription() ,Toast.LENGTH_SHORT);
        }else if(item.getDescription() =="Sites Touristique"){
            Toast.makeText(getApplicationContext(),"Erreur " + item.getDescription() ,Toast.LENGTH_SHORT);
            /*Intent intent = new Intent(getBaseContext(), sitesTviewer.class);
            intent.putExtra(CATEGORIES_SENDER, item);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );*/
        }else if(item.getDescription() =="Restaurant"){
            Toast.makeText(getApplicationContext(),"Erreur " + item.getDescription() ,Toast.LENGTH_SHORT);
            /*Intent intent = new Intent(getBaseContext(), hotelviwer.class);
            intent.putExtra(CATEGORIES_SENDER, item);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );*/
        }else if(item.getDescription() =="Hotel"){
            Toast.makeText(getApplicationContext(),"Erreur " + item.getDescription() ,Toast.LENGTH_SHORT);
            /*Intent intent = new Intent(getBaseContext(), hotelviwer.class);
            intent.putExtra(CATEGORIES_SENDER, item);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );*/
        }else if(item.getDescription() =="Transport"){
            Toast.makeText(getApplicationContext(),"Erreur " + item.getDescription() ,Toast.LENGTH_SHORT);

        }else if(item.getDescription() =="Innovation"){
            Toast.makeText(getApplicationContext(),"Erreur " + item.getDescription() ,Toast.LENGTH_SHORT);

        }
    }
    private void startSearchs(String s) {
        ////important (sameListnercode)
        adapter = new SearchAdapter(this,dbase.getLifeByTitle(s),new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Life item) {
                //on item click
                recomandCLIK(item);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadsuggestlist() {
        suggestList = dbase.getTitles();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

   /* @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }*/

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }
}
