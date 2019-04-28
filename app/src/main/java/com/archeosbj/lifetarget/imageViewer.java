package com.archeosbj.lifetarget;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;

import static android.support.constraint.Constraints.TAG;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

public class imageViewer extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    static String[] RestoItm;
    static Integer R1 =700;
    static Integer R2 = 700;

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        View v = (View) findViewById(R.id.section_label);
        R1 = v.getWidth();
        R2 = v.getHeight();
        //show ImageView width and height
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        Intent intent = getIntent();
        RestoItm = intent.getStringArrayExtra("ITEM");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.hideOverflowMenu();
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        Button butBck = (Button) findViewById(R.id.button_bck);
        butBck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TextView TextTitle = (TextView) findViewById(R.id.textView_title);
        TextTitle.setText("Slider pour découvrir les images");
    }
    void setBitmapImageFormSD(String filepath, ImageView ImgV){
        Glide.with(getApplicationContext())
                .load(Uri.fromFile(new File(filepath)))
                .into(ImgV);
    }
    public static void setBitmapImageFormMomory(String filepath, ImageView ImgV, int varw, int varh){
        int reqWidth = ImgV.getWidth();
        int reqHeight =   ImgV.getHeight();
        //Log.e("ImageSIZE", String.valueOf(reqWidth));
        Bitmap bm =  decodeSampledBitmapFromResource(filepath,varw,varh);
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
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            args.putStringArray("ITM",RestoItm);
            args.putInt("WTH",R2);
            args.putInt("HGH",R1);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_image_viewer, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            Storage storage = new Storage(getContext());
            String path = storage.getExternalStorageDirectory();
            String newDir = path + File.separator + DATA_DIRECTORI;
            String newDiri = newDir + File.separator + "images";
            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){
                String[] maxyT = getArguments().getStringArray("ITM");

                if( !(maxyT[0] == "")) {
                    ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);
                    //String[] maxyT = getArguments().getStringArray("ITM");
                    String fileph = newDiri + File.separator + maxyT[0];
                    Integer intR1 = getArguments().getInt("WTH");
                    Integer intR2 = getArguments().getInt("HGH");
                    setBitmapImageFormMomory(fileph, textView, intR2, intR1);
                    PhotoViewAttacher pAttacher;
                    pAttacher = new PhotoViewAttacher(textView);
                    pAttacher.update();
                }
                try {
                    if(!(maxyT[9] == "")){
                        ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);
                        Glide.with(getContext())
                                .load(maxyT[9])
                                .into(textView);
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    Log.e(TAG, "ArrayIndexOutOfBoundsException " + e.getMessage());
                }

            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                String[] maxyT = getArguments().getStringArray("ITM");
                if( !(maxyT[1] == "")){
                    ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);

                    String fileph = newDiri + File.separator + maxyT[1];
                    Integer intR1 = getArguments().getInt("WTH");
                    Integer intR2 = getArguments().getInt("HGH");
                    setBitmapImageFormMomory(fileph,textView, intR2, intR1);
                    PhotoViewAttacher pAttacher;
                    pAttacher = new PhotoViewAttacher(textView);
                    pAttacher.update();
                }
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==3){
                String[] maxyT = getArguments().getStringArray("ITM");
                if( !(maxyT[2] == "")){
                ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);
                //String[] maxyT = getArguments().getStringArray("ITM");
                String fileph = newDiri + File.separator + maxyT[2];
                Integer intR1 = getArguments().getInt("WTH");
                Integer intR2 = getArguments().getInt("HGH");
                setBitmapImageFormMomory(fileph,textView,intR2, intR1);
                PhotoViewAttacher pAttacher;
                pAttacher = new PhotoViewAttacher(textView);
                pAttacher.update();
                }
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==4){
                String[] maxyT = getArguments().getStringArray("ITM");
                if( !(maxyT[3] == "")) {
                    ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);
                    //String[] maxyT = getArguments().getStringArray("ITM");
                    String fileph = newDiri + File.separator + maxyT[3];
                    Integer intR1 = getArguments().getInt("WTH");
                    Integer intR2 = getArguments().getInt("HGH");
                    setBitmapImageFormMomory(fileph, textView, intR2, intR1);
                    PhotoViewAttacher pAttacher;
                    pAttacher = new PhotoViewAttacher(textView);
                    pAttacher.update();
                }
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==5){
                String[] maxyT = getArguments().getStringArray("ITM");
                if( !(maxyT[4] == "")) {
                    ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);
                    //String[] maxyT = getArguments().getStringArray("ITM");
                    String fileph = newDiri + File.separator + maxyT[4];
                    Integer intR1 = getArguments().getInt("WTH");
                    Integer intR2 = getArguments().getInt("HGH");
                    setBitmapImageFormMomory(fileph, textView, intR2, intR1);
                    PhotoViewAttacher pAttacher;
                    pAttacher = new PhotoViewAttacher(textView);
                    pAttacher.update();
                }
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==6){
                String[] maxyT = getArguments().getStringArray("ITM");
                if( !(maxyT[5] == "")) {
                    ImageView textView = (ImageView) rootView.findViewById(R.id.section_label);
                    //String[] maxyT = getArguments().getStringArray("ITM");
                    String fileph = newDiri + File.separator + maxyT[5];
                    Integer intR1 = getArguments().getInt("WTH");
                    Integer intR2 = getArguments().getInt("HGH");
                    setBitmapImageFormMomory(fileph, textView, intR2, intR1);
                    PhotoViewAttacher pAttacher;
                    pAttacher = new PhotoViewAttacher(textView);
                    pAttacher.update();
                }
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
            return 6;
        }
    }
}
