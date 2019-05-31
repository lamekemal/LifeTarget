/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */

package com.archeosbj.lifetarget.httpTool;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class HttpJSONParser {

    static String urlToJson(String URl){
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
   public static JSONObject ParseUrlToJSON(String URL){
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
}
