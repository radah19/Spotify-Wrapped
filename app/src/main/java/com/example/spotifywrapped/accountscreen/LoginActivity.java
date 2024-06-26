package com.example.spotifywrapped.accountscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyAPIManager;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;
import com.example.spotifywrapped.useraccounts.User;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1337; // Any arbitrary number for the request code
    private static final String CLIENT_ID = "63de5aae6e48409384f20082069e98e1";
    private static final String REDIRECT_URI = "spotifywrapped://auth";
    //private static final String CLIENT_SECRET = "your_client_secret_here";

    private EditText emailInput;
    private EditText passwordInput;
    public RelativeLayout loadingScreen;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(v -> {
            // Start CreateAccount activity
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loadingScreen = findViewById(R.id.loadingScreen);
        loadingScreen.setVisibility(View.INVISIBLE);
        loadingText = findViewById(R.id.loadingText);
        Button loginButton = findViewById(R.id.createAccount_btn);

        // --- Testing for database ---
        /*
        ArrayList<String> friendsList = new ArrayList<String>();
        friendsList.add("Dennis");
        friendsList.add("Andrew");
        DatabaseManager.setFirebaseAuth();
        DatabaseManager.addUser("Ethan", "lucky", friendsList, 14);
         DatabaseManager.retrieveUser("Ethan", LoginActivity.this);

         */

        loginButton.setOnClickListener(v -> {
                String email_input = emailInput.getText().toString();
                String password_input = passwordInput.getText().toString();
                loadingScreen.setVisibility(View.VISIBLE);

                DatabaseManager.setFirebaseAuth();
                DatabaseManager.loginVerification(email_input, password_input, this);
            });
    }

    public void initiateSpotifyLogin() {
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{
                "user-read-private", "user-read-email",
                "streaming", "user-top-read"
        });
        AuthorizationRequest request = builder.build();

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);
            switch (response.getType()) {
                case TOKEN:
                    // Use the token to make requests on behalf of the user
                    String accessToken = response.getAccessToken();
                    try {
                        proceedWithLoggedInUser(accessToken);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case ERROR:
                    // Handle error response
                    Toast.makeText(this, "Authentication error: " + response.getError(), Toast.LENGTH_LONG).show();
                    break;

                default:
                    // Most likely auth flow was cancelled
                    Toast.makeText(this, "Authentication cancelled", Toast.LENGTH_LONG).show();
                    loadingScreen.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }

    private void proceedWithLoggedInUser(String accessToken) throws JSONException {
        // Here you can update your User class with the access token and proceed
        SpotifyAPIManager spotApi = SpotifyAPIManager.getInstance();
        spotApi.setAccessToken(accessToken);

        // Perform further user setup
        try {
            String getUserData = spotApi.getUserData();

            if(!getUserData.equals("TRANSACTION FAILED")) {
                JSONObject userData = new JSONObject(getUserData);

                User.setSpotifyUserId(userData.getString("id"));

                SpotifyWrappedListActivity.ls_summaries = DatabaseManager.loadSpotifyWrapListForUser();

                Intent myIntent = new Intent(this, SpotifyWrappedListActivity.class);
                this.startActivity(myIntent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
