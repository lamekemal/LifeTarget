package com.archeosbj.lifetarget;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.archeosbj.lifetarget.Adpter.feedAdapter;
import com.archeosbj.lifetarget.Model.Actu;
import com.archeosbj.lifetarget.data.database;
import com.archeosbj.lifetarget.httpTool.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.FEED_JSON_CATEGORIES;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.FEED_LINK_URL;

public class OneFragment extends Fragment {

    //worked feed zone
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    feedAdapter adapter;
    List<String> suggestList = new ArrayList<>();
    database dbase;
    private boolean stateUser;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.one_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recicler_feed);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //data sources for list<>
        dbase = new database(getContext());
        List<Actu> feedList = new ArrayList<>();
        adapter = new feedAdapter(getContext(),feedList);
        //new feedFormJson(getContext(),"").getActu();
        //new GetFeed().execute();
        //List<Actu> feedactu = new feedFormJson(getContext(),"http://192.168.43.132/api/feed/getfeed.php").getActu();
        //define adapter.

        recyclerView.setAdapter(adapter);
        return v;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            // check if you are connected or not
            try {
                /*WebView myWebView = new WebView(getContext());
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.loadUrl(BLANK_COOKIES_URL);
                String cookies = CookieManager.getInstance().getCookie(BLANK_COOKIES_URL);
                myWebView.destroy();*/

                new GetFeed().execute(FEED_LINK_URL,"cookies");
                //ProgressBar myHost = (ProgressBar) getActivity().findViewById(R.id.progress_load);
                //myHost.setVisibility(View.VISIBLE);

            }catch (RuntimeException e){
                Toast.makeText(getContext(),getString(R.string.feed_dwn_error), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class GetFeed extends AsyncTask<String, Void, ArrayList<Actu>> {

        @Override
        protected ArrayList<Actu> doInBackground(String... params) {
            String urldisplay = params[0];

            /*for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }*/
            ArrayList<Actu> feedList = new ArrayList<>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject json = jParser.getJSONFromUrl(urldisplay);
            try {
                Log.e(TAG, "Response from url: " + json.toString());
                // Getting Array of Contacts
                JSONArray feeds  = json.getJSONArray(FEED_JSON_CATEGORIES);
                Log.e(TAG, "Response from url: " + feeds.length());
                // looping through All Contacts
                int m = 0;
                while (m < feeds.length()) {

                    JSONObject c = feeds.getJSONObject(m);

                    // Storing each json item in variable
                    String id = c.optString("id");
                    String Pubimg = c.optString("pubimg");
                    String Description = c.optString("description");
                    String Userimg = c.optString("userimg");
                    String Pubdate = c.optString("modified");
                    String Username = c.optString("username");
                    String Title = c.optString("title");
                    String distilled = c.optString("distilled");
                    String created = c.optString("created");
                    String modified = c.optString("modified");

                    Actu feedsL = new Actu();
                    feedsL.setId(Integer.parseInt(id));
                    feedsL.setDescription(Description);
                    feedsL.setPubimg(Pubimg);
                    feedsL.setTitle(Title);
                    feedsL.setPubdate(Pubdate);
                    feedsL.setUsername(Username);
                    feedsL.setUserimg(Userimg);
                    /*Phone number is agin JSON Object
                    JSONObject phone = c.getJSONObject(TAG_PHONE);
                    String mobile = phone.getString(TAG_PHONE_MOBILE);
                    String home = phone.getString(TAG_PHONE_HOME);
                    String office = phone.getString(TAG_PHONE_OFFICE);*/
                    feedList.add(feedsL);
                    Log.e(TAG, "Response from url: " + feedList.size());
                    m++;
                }

            }catch (JSONException e) {
                e.printStackTrace();
            }catch(NullPointerException e)
            {
                System.out.print("Caught NullPointerException");
            }

            return feedList;
        }

        @Override
        protected void onPostExecute(ArrayList<Actu> result) {
            //ProgressBar myHost = (ProgressBar) getActivity().findViewById(R.id.progress_load);
            //myHost.setVisibility(View.GONE);
            adapter = new feedAdapter(getContext(),result);
            recyclerView.setAdapter(adapter);
            //Log.e(TAG, "Response from url: " + result.size());
        }

        @Override
        protected void onPreExecute() {
            //ProgressBar myHost = (ProgressBar) getActivity().findViewById(R.id.progress_load);
            //myHost.setBackgroundColor(Color.MAGENTA);
            //myHost.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //ProgressBar myHost = (ProgressBar) getActivity().findViewById(R.id.progress_load);
            //myHost.setBackgroundColor(Color.GREEN);
            //myHost.setVisibility(View.VISIBLE);
        }
    }
}
