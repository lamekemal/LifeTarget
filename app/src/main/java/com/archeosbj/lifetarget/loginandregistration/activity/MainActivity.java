package com.archeosbj.lifetarget.loginandregistration.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.archeosbj.lifetarget.PreferenceTools.TinyDB;
import com.archeosbj.lifetarget.R;
import com.archeosbj.lifetarget.data.databaseContract;
import com.archeosbj.lifetarget.loginandregistration.helper.SQLiteHandler;
import com.archeosbj.lifetarget.loginandregistration.helper.SessionManager;
import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;
import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.SET_PROFILS_LINK_URL;

public class MainActivity extends Activity {



	Bitmap bitmap;
	boolean check = true;
	Button SelectImageGallery, UploadImageServer;
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

	private TextView txtName;
	private TextView txtEmail;
	private TextView txtnum;
	private Button btnLogout;
	private Button btnImg;
	private ImageView accimg;

	private SQLiteHandler db;
	private SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_lgn);

		Context MyContext = this;

		txtName = (TextView) findViewById(R.id.name);
		txtEmail = (TextView) findViewById(R.id.email);
		txtnum = (TextView) findViewById(R.id.numero);
		btnLogout = (Button) findViewById(R.id.btnLogout);
		btnImg = (Button) findViewById(R.id.btnProf);
		accimg = (ImageView) findViewById(R.id.imageView3);
		// SqLite database handler
		db = new SQLiteHandler(getApplicationContext());

		// session manager
		session = new SessionManager(getApplicationContext());

		if (!session.isLoggedIn()) {
			logoutUser();
		}

		// Fetching user details from SQLite
		HashMap<String, String> user = db.getUserDetails();

		String name = user.get("name");
		String email = user.get("settings");
		String num = user.get("email");
		title = email;
		// Displaying the user details on the screen
		txtName.setText(name);
		txtEmail.setText(email);
		txtnum.setText(num);
		//Glide.get(getApplicationContext())
		btnImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Selectionner une image de la galerie"), 1);

				ImageUploadToServerFunction();
			}
		});

		TinyDB tini = new TinyDB(getApplicationContext());
		String imgn = tini.getString(databaseContract.dataEntry.DEFAULT_PREFS_SETTINGS_KEY_PROFILS_NM);

		if(imgn == ""){}else {
			Storage storage = new Storage(MyContext);
			String path = storage.getExternalStorageDirectory();
			String newDir = path + File.separator + DATA_DIRECTORI;
			String newDiri = newDir + File.separator + "images";

			String fileph = newDiri + File.separator + imgn;
			Glide.with(MyContext)
					.load(Uri.fromFile(new File(fileph)))
					.into(accimg);
		}

		// Logout button click event
		btnLogout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				logoutUser();
			}
		});
	}

	/**
	 * Logging out the user. Will set isLoggedIn flag to false in shared
	 * preferences Clears the user data from sqlite users table
	 * */
	private void logoutUser() {
		session.setLogin(false);

		db.deleteUsers();

		// Launching the login activity
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onActivityResult(int RC, int RQC, Intent I) {
		super.onActivityResult(RC, RQC, I);
		if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {
			Uri uri = I.getData();
			try {
				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
				accimg.setImageBitmap(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void ImageUploadToServerFunction(){
		ByteArrayOutputStream byteArrayOutputStreamObject ;
		byteArrayOutputStreamObject = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStreamObject);
		byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
		final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
		class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				progressDialog = ProgressDialog.show(MainActivity.this,"Chargement de l'image","Patienter",false,false);
			}
			@Override
			protected void onPostExecute(String string1) {
				super.onPostExecute(string1);
				// Dismiss the progress dialog after done uploading.
				progressDialog.dismiss();
				// Printing uploading success message coming from server on android app.
				Toast.makeText(MainActivity.this,string1,Toast.LENGTH_LONG).show();
				// Setting image as transparent after done uploading.
				//accimg.setImageResource(android.R.color.transparent);
				finish();
			}

			@Override
			protected String doInBackground(Void... params) {
				MainActivity.ImageProcessClass imageProcessClass = new MainActivity.ImageProcessClass();
				HashMap<String,String> HashMapParams = new HashMap<String,String>();

				HashMapParams.put(ImageName, GetImageNameEditText);
				HashMapParams.put(ImagePath, ConvertImage);
				HashMapParams.put("foremail", title);
				String FinalData = imageProcessClass.ImageHttpRequest(SET_PROFILS_LINK_URL, HashMapParams);
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
