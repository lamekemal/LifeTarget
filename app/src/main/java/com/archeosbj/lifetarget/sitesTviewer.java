package com.archeosbj.lifetarget;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.archeosbj.lifetarget.Adpter.InnovAdapter;
import com.archeosbj.lifetarget.Adpter.TourAdapter;
import com.archeosbj.lifetarget.Model.Innov;
import com.archeosbj.lifetarget.Model.Tour;
import com.archeosbj.lifetarget.PreferenceTools.TinyDB;
import com.archeosbj.lifetarget.data.database;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.data.innovdb;
import com.archeosbj.lifetarget.data.sitesdb;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INDEX;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INT;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_SENDER;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

public class sitesTviewer extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TourAdapter adapter;
    private List<Tour> TourList;
    private List<Innov> InnovList;
    RecyclerView.LayoutManager layoutManager;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    database dbase;
    private boolean stateSearch;
    private int indexcat;
    private boolean sendbycat;
    private ProgressDialog pDialog;
    private ProgressBar pBar;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_tviewer);
        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(intent.getStringExtra(CATEGORIES_INT));
        toolbar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sendbycat = intent.getIntExtra(CATEGORIES_SENDER,2) ==1;
        indexcat = intent.getIntExtra(CATEGORIES_INDEX,9);

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_zone);
        materialSearchBar.setPlaceHolderColor(getResources().getColor(R.color.white));
        materialSearchBar.setVisibility(View.GONE);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        pBar = (ProgressBar)  findViewById(R.id.load_prg);
        pBar.setMax(100);

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
        stateSearch = false;
        setSupportActionBar(toolbar);

        TourList = new ArrayList<>();
        InnovList = new ArrayList<>();

      /* try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        if(indexcat ==3){
            //SItes touristk
            TinyDB tinydb = new TinyDB(getApplicationContext());
            ArrayList<String> result = tinydb.getListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_SITES);
            if(result.size()>2){
                new loadData().execute(result);
            }
            new Runnable() {
                @Override
                public void run() {
                    actSites();
                    handler.postDelayed(this, 10000);
                }
            }.run();

        }else if(indexcat ==6){
            //Innov zone
            TinyDB tinydb = new TinyDB(getApplicationContext());
            ArrayList<String> result = tinydb.getListString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_INNOV);
            if(result.size()>2){
                new loadData().execute(result);
            }
            new Runnable() {
                @Override
                public void run() {
                    actInnov();
                    handler.postDelayed(this, 10000);
                }
            }.run();
        }
    }
    void actSites(){
        sitesdb obase = new sitesdb(this);
        TourAdapter xadapter = new TourAdapter(this,obase.getSites(),new TourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tour item) {
                //on item click
                Intent intent = new Intent(getBaseContext(), TourItmViewer.class);
                String[] RestoItm = new String[26];
                RestoItm[0] = "";
                RestoItm[1] = String.valueOf(item.getId()) ;
                RestoItm[2] = item.getName();
                RestoItm[3] = item.getContact();
                RestoItm[4] = item.getHoraire();
                RestoItm[5] = item.getMail();
                RestoItm[6] = item.getUniqueid();
                RestoItm[7] = item.getService();
                RestoItm[8] = item.getPointfort();
                RestoItm[9] = item.getPrice();
                RestoItm[10] = item.getPrimpimage();
                RestoItm[11] = item.getGaleryOne();
                RestoItm[12] = item.getGalerytwo();
                RestoItm[13] = item.getGalerytree();
                RestoItm[14] = item.getGaleryfour();
                RestoItm[15] = item.getGaleryfive();
                RestoItm[16] = item.getGalerysix();
                RestoItm[17] =  item.getGaleryseven();
                RestoItm[18] = item.getLocalisation();
                RestoItm[19] =  item.getMore();
                RestoItm[20] =  item.getReserveone();
                RestoItm[21] =  item.getReservetwo();
                RestoItm[22] =  item.getExtras();
                RestoItm[23] =  item.getPlusStr();

                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });



        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(xadapter);
    }
    void actInnov(){
        innovdb cbase = new innovdb(this);
        InnovAdapter vadapter = new InnovAdapter(this,cbase.getInnov(),new InnovAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Innov item) {
                //on item click
                Intent intent = new Intent(getBaseContext(), Innovation.class);
                String[] RestoItm = new String[26];
                RestoItm[0] = "";
                RestoItm[1] = String.valueOf(item.getId()) ;
                RestoItm[2] = item.getName();
                RestoItm[3] = item.getContact();
                RestoItm[4] = item.getUniqueid();
                RestoItm[5] = item.getService();
                RestoItm[6] = item.getMail();
                RestoItm[7] = item.getVideo();
                RestoItm[8] = item.getPrimpimage();
                RestoItm[9] = item.getGaleryOne();
                RestoItm[10] = item.getGalerytwo();
                RestoItm[11] = item.getGalerytree();
                RestoItm[12] = item.getGaleryfour();
                RestoItm[13] = item.getGaleryfive();
                RestoItm[14] = item.getGalerysix();
                RestoItm[15] =  item.getDescription();
                RestoItm[16] = item.getBibliot();
                RestoItm[17] =  item.getStectn();
                RestoItm[18] =  item.getHistorq();
                RestoItm[19] =  item.getReservetwo();
                RestoItm[20] =  item.getInnovname();
                intent.putExtra("ITEM",RestoItm);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(vadapter);
    }

    private class loadData extends AsyncTask<ArrayList<String>,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(ArrayList<String>... params) {
            int whis =0;
            int whiset = 0;
            int whislen = params[0].size();
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
                if(indexcat ==3){
                    //SItes touristk
                    actSites();
                }else if(indexcat ==6){
                    //Innov zone
                    actInnov();
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

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}