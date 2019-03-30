package com.archeosbj.lifetarget.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.archeosbj.lifetarget.Model.Actu;
import com.archeosbj.lifetarget.Model.Life;
import com.archeosbj.lifetarget.httpTool.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.FEED_JSON_CATEGORIES;

public class feedFormJson  {
    private Context context;
    private String FeedApiUrl;

    public feedFormJson(Context context, String FeedApiUrl) {
        this.context = context;
        //this.life = life;
        this.FeedApiUrl = FeedApiUrl;
    }
    public List<Actu> getActu(){
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = FeedApiUrl;
        String jsonStr = sh.makeServiceCall(url);
        List<Actu> result = new ArrayList<>();

        Log.e(TAG, "Response from url: " + jsonStr);
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray feeds = jsonObj.getJSONArray(FEED_JSON_CATEGORIES);

                // looping through All Contacts
                for (int i = 0; i < feeds.length(); i++) {
                    JSONObject c = feeds.getJSONObject(i);
                    String id = c.getString("id");
                    String Pubimg = c.getString("pubimg");
                    String Description = c.getString("description");
                    String Userimg = c.getString("userimg");
                    String Pubdate = c.getString("pubdate");
                    String Username = c.getString("username");
                    String Title = c.getString("title");
                    /*//* Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");*/

                    Actu feedsL = new Actu();
                    feedsL.setId(Integer.parseInt(id));
                    feedsL.setDescription(Description);
                    feedsL.setPubimg(Pubimg);
                    feedsL.setTitle(Title);
                    feedsL.setPubdate(Pubdate);
                    feedsL.setUsername(Username);
                    feedsL.setUserimg(Userimg);

                    result.add(feedsL);
                }
            } catch (final JSONException e) {

                Log.e(TAG, e.getMessage());
            }

        return result;
    }

}