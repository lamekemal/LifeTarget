package com.archeosbj.lifetarget;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.archeosbj.lifetarget.Adpter.InnovAdapter;
import com.archeosbj.lifetarget.Adpter.TourAdapter;
import com.archeosbj.lifetarget.Model.Innov;
import com.archeosbj.lifetarget.Model.Tour;
import com.archeosbj.lifetarget.data.database;
import com.archeosbj.lifetarget.data.innovdb;
import com.archeosbj.lifetarget.data.sitesdb;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INDEX;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INT;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_SENDER;

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
        }else if(indexcat ==6){
            //Innov zone
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
                    RestoItm[7] = item.getService();
                    RestoItm[5] = item.getMail();
                    RestoItm[8] = item.getVideo();
                    RestoItm[10] = item.getPrimpimage();
                    RestoItm[11] = item.getGaleryOne();
                    RestoItm[12] = item.getGalerytwo();
                    RestoItm[13] = item.getGalerytree();
                    RestoItm[14] = item.getGaleryfour();
                    RestoItm[15] = item.getGaleryfive();
                    RestoItm[16] = item.getGalerysix();
                    RestoItm[17] =  item.getDescription();
                    RestoItm[18] = item.getBibliot();
                    RestoItm[19] =  item.getStectn();
                    RestoItm[20] =  item.getHistorq();
                    RestoItm[21] =  item.getReservetwo();

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
}