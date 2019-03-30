package com.archeosbj.lifetarget;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SET_FEED_LINK_URL;

public class Main4post extends AppCompatActivity {

    Bitmap bitmap;
    boolean check = true;
    Button SelectImageGallery, UploadImageServer;
    ImageView imageView;
    EditText imageName;
    ProgressDialog progressDialog ;
    String GetImageNameEditText = "LifeTarget";
    String ImageName = "image_name" ;
    String ImagePath = "image_path" ;

    String name;
    String email;
    String title;
    String uniqi;
    String userURL;
    String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4post);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageName = (EditText)findViewById(R.id.editTextImageName);
        SelectImageGallery = (Button)findViewById(R.id.buttonSelect);
        UploadImageServer = (Button)findViewById(R.id.buttonUpload);

        Intent intent = getIntent();
        String[] RestoItm = intent.getStringArrayExtra("ITEM");

        name   = RestoItm[1];
        email    =RestoItm[2];
        title   = RestoItm[3];
        uniqi   = RestoItm[4];
        userURL   = RestoItm[5];

        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selectionner une image de la galerie"), 1);
            }
        });

        UploadImageServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description = imageName.getText().toString();
                ImageUploadToServerFunction();
            }
        });
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {
        super.onActivityResult(RC, RQC, I);
        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {
            Uri uri = I.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ImageUploadToServerFunction(){
        ByteArrayOutputStream byteArrayOutputStreamObject ;
        byteArrayOutputStreamObject = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(Main4post.this,"Chargement de l'image","Patienter",false,false);
            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();
                // Printing uploading success message coming from server on android app.
                Toast.makeText(Main4post.this,string1,Toast.LENGTH_LONG).show();
                // Setting image as transparent after done uploading.
                imageView.setImageResource(android.R.color.transparent);
                finish();
            }

            @Override
            protected String doInBackground(Void... params) {
                ImageProcessClass imageProcessClass = new ImageProcessClass();
                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName, GetImageNameEditText);
                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("title", title);
                HashMapParams.put("description", description);
                HashMapParams.put("username", name);
                HashMapParams.put("userimg", userURL);
                HashMapParams.put("distilled","BY," + email + "," + uniqi);
                String FinalData = imageProcessClass.ImageHttpRequest(SET_FEED_LINK_URL, HashMapParams);
                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);
                httpURLConnectionObject = (HttpURLConnection) url.openConnection();
                httpURLConnectionObject.setReadTimeout(19000);
                httpURLConnectionObject.setConnectTimeout(19000);
                httpURLConnectionObject.setRequestMethod("POST");
                httpURLConnectionObject.setDoInput(true);
                httpURLConnectionObject.setDoOutput(true);
                OutPutStream = httpURLConnectionObject.getOutputStream();
                bufferedWriterObject = new BufferedWriter(
                        new OutputStreamWriter(OutPutStream, "UTF-8"));
                bufferedWriterObject.write(bufferedWriterDataFN(PData));
                bufferedWriterObject.flush();
                bufferedWriterObject.close();
                OutPutStream.close();
                RC = httpURLConnectionObject.getResponseCode();
                if (RC == HttpsURLConnection.HTTP_OK) {
                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String RC2;
                    while ((RC2 = bufferedReaderObject.readLine()) != null){
                        stringBuilder.append(RC2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {
            StringBuilder stringBuilderObject;
            stringBuilderObject = new StringBuilder();
            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilderObject.append("&");
                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));
                stringBuilderObject.append("=");
                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }
            return stringBuilderObject.toString();
        }

    }
}