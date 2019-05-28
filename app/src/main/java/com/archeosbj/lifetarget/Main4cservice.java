package com.archeosbj.lifetarget;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.loginandregistration.app.AppController;
import com.archeosbj.lifetarget.loginandregistration.helper.SQLiteHandler;
import com.archeosbj.lifetarget.loginandregistration.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main4cservice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4cservice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SessionManager session = new SessionManager(getApplicationContext());
        FloatingActionButton fab = findViewById(R.id.fab);
        final EditText textmsg = (EditText) findViewById(R.id.editText);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (session.isLoggedIn()) {
                    //poste prefecrences
                    SQLiteHandler db;
                    db = new SQLiteHandler(getApplicationContext());

                    HashMap<String, String> user = db.getUserDetails();
                    String name = user.get("name");
                    String email = user.get("settings");
                    String ymsg = textmsg.getText().toString();
                    markpostfav("support@lifetargeteasy.com",email,ymsg, "CService");
                    onBackPressed();
                }else{
                    Snackbar.make( view,"Non disponible, veuillez vous connecter ", Snackbar.LENGTH_LONG)
                            .setAction("Se connecter", null).show();
                }
            }
        });
    }
    private void markpostfav(final String formsg, final String ofmsg,
                             final String msg,final String onmsg) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                databaseContract.AppConfig.SET_MSG_LINK_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        //succees
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("formsg", formsg);
                params.put("ofmsg", ofmsg);
                params.put("msg", msg);
                params.put("onmsg", onmsg);

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
