package com.archeosbj.lifetarget;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.archeosbj.lifetarget.Adpter.SearchAdapter;
import com.archeosbj.lifetarget.Adpter.SearchAdapters;
import com.archeosbj.lifetarget.barcode.ScanActivity;
import com.archeosbj.lifetarget.data.database;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.loginandregistration.activity.LoginActivity;
import com.archeosbj.lifetarget.loginandregistration.helper.SQLiteHandler;
import com.archeosbj.lifetarget.loginandregistration.helper.SessionManager;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_INT;
import static com.archeosbj.lifetarget.categoriesView.CATEGORIES_SENDER;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATABASE_NAME;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.GENERAL_SETTINGS_NAME;

public class startactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private startactivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private SearchView mSearchView;
    private boolean mSearchViewVisibility = false;
    private boolean isConnected = false;
    //private boolean mSearchViewVisibility = false;

    MaterialSearchView mMetalSearch;
    private DrawerLayout drawer;

    //worked search zone
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    SearchAdapters personaladapter;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    database dbase;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    private static final int BARCODE_READER_REQUEST_CODE = 1;
    public static String tvresult;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = getApplicationContext().getSharedPreferences(GENERAL_SETTINGS_NAME, MODE_PRIVATE);
        //boolean myb = sharedPref.getBoolean("FirstStart",true);

        if (ActivityCompat.checkSelfPermission(startactivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(startactivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_PERMISSION_CONSTANT);
        }
         createTableFPB();

        //define Search zone
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        dbase = new database(this);
        materialSearchBar.inflateMenu(R.menu.startactivity);
        materialSearchBar.setText("Recherche");
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        personaladapter = new SearchAdapters(inflater);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Option 1
        personaladapter.setSuggestions(dbase.getLife());
        materialSearchBar.setCardViewElevation(10);
        //=================================<< IMPORTANT POUR ACTIVER LA RECHERCHE >> materialSearchBar.setCustomSuggestionAdapter(personaladapter);
       // Log.i("KEMAL",String.valueOf(personaladapter.getSuggestions().get(1).getAdress()));

        View hView =  navigationView.getHeaderView(0);
        ImageView nav_image = (ImageView)hView.findViewById(R.id.imageView);
        nav_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    loginlogan();
            }
        });
        SessionManager session = new SessionManager(getApplicationContext());
        TextView nav_textView = (TextView)hView.findViewById(R.id.textView);
        nav_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginlogan();
            }
        });

        TextView nav_textView2 = (TextView)hView.findViewById(R.id.textView2);

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            SQLiteHandler db;
            db = new SQLiteHandler(getApplicationContext());
            HashMap<String, String> user = db.getUserDetails();

            String name = user.get("name");
            String email = user.get("settings");
            nav_textView.setText(name);
            nav_textView2.setText(email);
        }else{
            nav_textView.setText("Utilisateur Anonyme");
            nav_textView2.setText("Tapez pour vous enregistrer ");
        }
        nav_textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginlogan();
            }
        });
        //loadsugestlist();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
               /* List<String> suggest = new ArrayList<>();
                for(String search:suggestList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);*/
                personaladapter.getFilter().filter(materialSearchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        materialSearchBar.getMenu().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.search_Av:
                        launchAvSearchAct();
                        break;
                    case R.id.message:
                        SessionManager xsession = new SessionManager(getApplicationContext());
                        if (xsession.isLoggedIn()) {
                            launchmessage();
                        }else{
                            Snackbar.make(  getCurrentFocus(),"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                                    .setAction("Se connecter", null).show();
                        }

                        break;
                    case R.id.qr_search:
                        Intent intent = new Intent(startactivity.this, ScanActivity.class);
                        int rc = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                        if (rc == PackageManager.PERMISSION_GRANTED) {
                            startActivity(intent);
                        } else {
                            requestCameraPermission();
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    //recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                //adapter = new SearchAdapter(this,dbase.getLifeByTitle(text));
                //recyclerView.setAdapter(adapter);
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case MaterialSearchBar.BUTTON_NAVIGATION:
                        drawer.openDrawer(Gravity.LEFT);
                        break;
                    case MaterialSearchBar.BUTTON_SPEECH:
                        break;
                    case MaterialSearchBar.BUTTON_BACK:
                        materialSearchBar.disableSearch();
                        break;
                }
            }
        });


        //region  TAB LAYOUT CODE ACTIVE
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        /**mViewPager.setAdapter(mSectionsPagerAdapter);**/
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons(tabLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //copyimagefiles();
                Intent intent = new Intent(startactivity.this, ScanActivity.class);
                int rc = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                if (rc == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    requestCameraPermission();
                    startActivity(intent);
                }

            }
        });


    }

    private void launchmessage() {
        Intent intent = new Intent(startactivity.this, msgtool.class);
        startActivity(intent);
    }

    void loginlogan(){
        /*SessionManager session; session = new SessionManager(getApplicationContext());
        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
        }*/
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
    }
    private void requestCameraPermission() {
        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
        }
    }

    //region COPIE DRAFT
    /*private void copyimagefiles() {
       /*try{
           Storage storage = new Storage(getApplicationContext());
           String path = storage.getExternalStorageDirectory();
           String newDir = path + File.separator + DATA_DIRECTORI;
           String newDiri = newDir + File.separator + "images";
           storage.createDirectory(newDir);
           storage.createDirectory(newDiri);
           String filepha = newDiri + File.separator + "01.jpg";
           InputStream in = getResources().openRawResource(R.raw.a);
           FileOutputStream out = new FileOutputStream(filepha);
           byte[] buff = new byte[1024];
           int read = 0;

           try {
               while ((read = in.read(buff)) > 0) {
                   out.write(buff, 0, read);
               }
           } finally {
               in.close();
               out.close();
           }

           String filephb = newDiri + File.separator + "02.jpg";
           InputStream inb = getResources().openRawResource(R.raw.b);
           FileOutputStream outb = new FileOutputStream(filephb);
           byte[] buffb = new byte[1024];
           int readb = 0;

           try {
               while ((readb = inb.read(buffb)) > 0) {
                   outb.write(buffb, 0, readb);
               }
           } finally {
               inb.close();
               outb.close();
           }

           String filephc = newDiri + File.separator + "03.jpg";
           InputStream inc = getResources().openRawResource(R.raw.c);
           FileOutputStream outc = new FileOutputStream(filephc);
           byte[] buffc = new byte[1024];
           int readc = 0;

           try {
               while ((readc = inc.read(buffc)) > 0) {
                   outc.write(buffc, 0, readc);
               }
           } finally {
               inc.close();
               outc.close();
           }

           String filephd = newDiri + File.separator + "04.jpg";
           InputStream ind = getResources().openRawResource(R.raw.d);
           FileOutputStream outd = new FileOutputStream(filephd);
           byte[] buffd = new byte[1024];
           int readd = 0;

           try {
               while ((readd = ind.read(buffd)) > 0) {
                   outd.write(buffd, 0, readd);
               }
           } finally {
               ind.close();
               outd.close();
           }

           String filephe = newDiri + File.separator + "05.jpg";
           InputStream ine = getResources().openRawResource(R.raw.e);
           FileOutputStream oute = new FileOutputStream(filephe);
           byte[] buffe = new byte[1024];
           int reade = 0;

           try {
               while ((reade = ine.read(buffe)) > 0) {
                   oute.write(buffe, 0, reade);
               }
           } finally {
               ine.close();
               oute.close();
           }

           String filephf = newDiri + File.separator + "06.jpg";
           InputStream inf = getResources().openRawResource(R.raw.f);
           FileOutputStream outf = new FileOutputStream(filephf);
           byte[] bufff = new byte[1024];
           int readf = 0;

           try {
               while ((readf = inf.read(bufff)) > 0) {
                   outf.write(bufff, 0, readf);
               }
           } finally {
               inf.close();
               outf.close();
           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }*/

    //}*/
    //endregion

    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter xadapter = new ViewPagerAdapter(getSupportFragmentManager());
        xadapter.addFrag(new ZeroFragment(), getResources().getString(R.string.tab_text_1));
        xadapter.addFrag(new OneFragment(), getResources().getString(R.string.tab_text_2));
        //xadapter.addFrag(new mfragtest(),"Meilleur");
        mViewPager.setAdapter(xadapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
    private void setupTabIcons(TabLayout tbl) {
        //tbl.getTabAt(0).setIcon(tabIcons[3]);
        //tbl.getTabAt(1).setIcon(tabIcons[0]);
        //tbl.getTabAt(0).select();
    }
    private void loadsugestlist() {
        suggestList = dbase.getTitles();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    void createTableFPB(){
        SQLiteDatabase myDB = this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_LIFE);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_FAV);
        myDB.execSQL(databaseContract.dataEntry.CREATE_LOGIN_TABLE);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_TRANS);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_INNOV);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_SERLI);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_SITES);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_RESTO);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_HOTEL);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_MSG);
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_FAVLS);
    }
    void createAddDatadb(String Title, String Adress,String Rat,String Desc){
       SQLiteDatabase myDB = this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        /* Create a Table in the Database. */
        myDB.execSQL(databaseContract.dataEntry.SQL_CREATE_ENTRIES_LIFE);

        ContentValues values = new ContentValues();
        values.put(databaseContract.dataEntry.COLUMN_NAME_TITLEs, Title);
        values.put(databaseContract.dataEntry.COLUMN_NAME_ADRSS, Adress);
        values.put(databaseContract.dataEntry.COLUMN_NAME_RAT, Rat);
        values.put(databaseContract.dataEntry.COLUMN_NAME_DESC, Desc);
        long newRowId = myDB.insert(databaseContract.dataEntry.TABLE_NAME, null, values);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.startactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_Av) {
            launchAvSearchAct();
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchAvSearchAct() {
        Intent intent = new Intent(getApplicationContext(), categoriesView.class);
        ///String message = "Recherche Avancer";
        //boolean var = false;
        intent.putExtra(CATEGORIES_SENDER, 0);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
    private void LaunchIntentWithCat(String s) {
        Intent intent = new Intent(getApplicationContext(), categoriesView.class);
        boolean var = true;
        intent.putExtra(CATEGORIES_SENDER, var);
        intent.putExtra(CATEGORIES_INT, s);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void DownloadImages(String imageURL, String filesnames){
        try {
            URL url = new URL(imageURL);
            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //set up some things on the connection
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            //and connect!
            urlConnection.connect();
            //set the path where we want to save the file in this case, going to save it on the root directory of the sd card.
            File SDCardRoot = Environment.getDataDirectory();

            //create a new file, specifying the path, and the filename which we want to save the file as.
            File file = new File(SDCardRoot,filesnames);
            //this will be used to write the downloaded data into the file we created
            FileOutputStream fileOutput = new FileOutputStream(file);
            //this will be used in reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();
            //this is the total size of the file
            int totalSize = urlConnection.getContentLength();
            //variable to store total downloaded bytes
            int downloadedSize = 0;
            byte[] buffer = new byte[1024];
            int bufferLength = 0; //used to store a temporary size of the buffer
            //now, read through the input buffer and write the contents to the file
            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                //add the data in the buffer to the file in the file output stream (the file on the sd card
                fileOutput.write(buffer, 0, bufferLength);
                //add up the size so we know how much is downloaded
                downloadedSize += bufferLength;
                //this is where you would do something to report the prgress, like this maybe
                //updateProgress(downloadedSize, totalSize);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final SessionManager session = new SessionManager(getApplicationContext());

        if (id == R.id.nav_camera) {
            if (session.isLoggedIn()) {
                Intent intent = new Intent(getApplicationContext(), CodePromos.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
            }else{
                Snackbar.make(  getCurrentFocus(),"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                        .setAction("Se connecter", null).show();
            }
        } else if (id == R.id.nav_gallery) {
            if (session.isLoggedIn()) {
                Intent intent = new Intent(getApplicationContext(), stinfos.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
            }else{
                Snackbar.make(  getCurrentFocus(),"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                        .setAction("Se connecter", null).show();
            }
        } else if (id == R.id.nav_slideshow) {
            if (session.isLoggedIn()) {
                Intent intent = new Intent(getApplicationContext(), msgtool.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
            }else{
                Snackbar.make(  getCurrentFocus(),"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                        .setAction("Se connecter", null).show();
            }
        } else if (id == R.id.nav_manage) {
                loginlogan();
        } else if (id == R.id.nav_share) {
            if (session.isLoggedIn()) {
                Intent intent = new Intent(getApplicationContext(), CodeDePartage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_left);
            }else{
                Snackbar.make(  getCurrentFocus(),"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                        .setAction("Se connecter", null).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            //define layout
            RelativeLayout ServiceLay = (RelativeLayout) rootView.findViewById(R.id.ServiceLay);
            RelativeLayout AccountLay = (RelativeLayout) rootView.findViewById(R.id.AccontLay);
            RelativeLayout ActuLay = (RelativeLayout) rootView.findViewById(R.id.ActuLay);

            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            //define default visibility
            ServiceLay.setVisibility(View.GONE);
            AccountLay.setVisibility(View.GONE);
            ActuLay.setVisibility(View.GONE);
            //define tabs page
            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){
                ServiceLay.setVisibility(View.VISIBLE);
                AccountLay.setVisibility(View.GONE);
                ActuLay.setVisibility(View.GONE);
            }else
            if (getArguments().getInt(ARG_SECTION_NUMBER)==3){
                AccountLay.setVisibility(View.VISIBLE);
                ServiceLay.setVisibility(View.GONE);
                ActuLay.setVisibility(View.GONE);
            }else
            if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                ActuLay.setVisibility(View.VISIBLE);
                ServiceLay.setVisibility(View.GONE);
                AccountLay.setVisibility(View.GONE);
            }

            return rootView;
        }
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

}
