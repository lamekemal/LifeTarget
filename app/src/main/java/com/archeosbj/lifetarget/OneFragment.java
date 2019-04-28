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

        dbase = new database(getContext());
        List<Actu> feedList = new ArrayList<>();
        adapter = new feedAdapter(getContext(),feedList);

        recyclerView.setAdapter(adapter);
        return v;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            // check if you are connected or not
            try {
                Log.e(TAG, "JAI DEMANDER ");
                new GetFeed().execute(FEED_LINK_URL,"cookies");

            }catch (RuntimeException e){
                Toast.makeText(getContext(),getString(R.string.feed_dwn_error), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class GetFeed extends AsyncTask<String, Void, ArrayList<Actu>> {

        @Override
        protected ArrayList<Actu> doInBackground(String... params) {
            String urldisplay = params[0];
            ArrayList<Actu> feedList = new ArrayList<>();
            JSONParser jParser = new JSONParser();
            String cookies = params[1];
            JSONObject json = jParser.getJSONFromUrl(urldisplay);
            try {
                Log.e(TAG, "FEED REPONSE: " + json.toString());
                JSONArray feeds  = json.getJSONArray(FEED_JSON_CATEGORIES);
                Log.e(TAG, "FEED REPONSE LGH: " + feeds.length());
                // looping through All Contacts
                int m = 0;
                while (m < feeds.length()) {
                    JSONObject c = feeds.getJSONObject(m);
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
                    feedList.add(feedsL);
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
            adapter = new feedAdapter(getContext(),result);
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }
}
