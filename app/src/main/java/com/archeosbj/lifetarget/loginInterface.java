package com.archeosbj.lifetarget;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginInterface extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    ProgressDialog dialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interface);


        // Initialize Firebase Auth
        /*mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/
        mAuth = FirebaseAuth.getInstance();

        Button nav_textView2 = (Button)findViewById(R.id.gloginBtn);
        nav_textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInwithGoogle();
            }
        });

    }

    private void init() {

        dialog = new ProgressDialog(loginInterface.this);
        dialog.setMessage("Loading..");
        dialog.setTitle("Please Wait");
        dialog.setCancelable(false);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LoginActivity", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LoginActivity", "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                // updateUI(user);
                // [END_EXCLUDE]
            }
        };

      /*  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();*/

       /* mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(loginInterface.this /* FragmentActivity ,this /* OnConnectionFailedListener )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*private void listener() {
        iv_google.setOnClickListener(this);
        tv_google.setOnClickListener(this);
    }*/
    protected void signInwithGoogle(){
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();*/
        /*mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(loginInterface.this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.e("LoginActivity", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.e("MARIO", "firebaseAuthWithGoogle:" + acct.getId());
        //showProgressDialog();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.w("MARIO", "signInWithCredential:failure", task.getException());
                            Snackbar.make(getCurrentFocus(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("MARIO", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(null);
                            Intent intent = new Intent(loginInterface.this, startactivity.class);
                            startActivity(intent);
                            loginInterface.this.finish();
                        }
                       // hideProgressDialog();
                    }});
    }
    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();
        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                //TO USE
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                String personPhoneURL = acct.getPhotoUrl().toString();
                //                User user = new User();
                //                user.setUsername(personName);
                //                user.setEmail(personEmail);
//                user.setPersonId(personId);
//                user.setPersonProfileUrl(personPhoneURL);
//                user.setSignedInWithGoogle(true);

                // updateFirebaseData(user,personEmail);

                /*Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Uri.class, new UriSerializer())
                        .create();*/

//                String userData = gson.toJson(user);
//                      EPreferenceManager.getSingleton().setUserdata(getActivity(),userData);
                firebaseAuthWithGoogle(acct);
            } else {
                Toast.makeText(loginInterface.this,"There was a trouble signing in-Please try again",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
