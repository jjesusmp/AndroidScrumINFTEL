package com.example.asus.androidscruminftel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.example.asus.androidscruminftel.model.User;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener{

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private String userJson;
    //String stringUrl = "http://192.168.183.24:8080/AppInftelScrum/webresources/entity.usuarioscrum";
    String stringUrl = "http://192.168.1.147:443/sigin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        AndroidScrumINFTELActivity.getInstance().setmGoogleApiClient(new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build());

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(AndroidScrumINFTELActivity.getInstance().getmGoogleApiClient());
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            AndroidScrumINFTELActivity.getInstance().setUserName(acct.getDisplayName());
            AndroidScrumINFTELActivity.getInstance().setEmail(acct.getEmail());
            Log.i("es", acct.getEmail());
//(String email, String idusuario, String nombre, String refreshToken)

            String urlImage;

            if(acct.getPhotoUrl()==null){
                urlImage="";
            } else{
                urlImage=acct.getPhotoUrl().toString();
            }

            User user;
            user = new User(acct.getDisplayName(), acct.getEmail(), urlImage);
            userJson = user.toJSON();

            new PostHttp(getBaseContext()).execute(stringUrl,userJson.toString());

            Intent intent = new Intent(this,MyProjectsActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(),R.string.common_google_play_services_sign_in_failed_text,Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}