package com.sortscript.amdsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    EditText userNameEdt, emailEdt, phoneNmbrEdt, passwordEdt, confirmPassEdt;
    //loginUsingGoogle
    private static final String TAG = "MainActivity";
    private LinearLayout signInButton;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 100;
    String name, email;
    String idToken;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        initViews();
        //loginUsingGoogle

        firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        //this is where we start the Auth state Listener to listen for whether the user is signed in or not
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Get signedIn user
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //if user is signed in, we call a helper method to save the user details to Firebase
                if (user != null) {
                    // User is signed in
                    // you could place other firebase code
                    //logic to save the user details to Firebase
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("485819960632-nm3aurotsqodpl18iahu91hb3svbo2ev.apps.googleusercontent.com")//you can also use R.string.default_web_client_id
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            if (account != null) {
                idToken = account.getIdToken();
            } else {
                Toast.makeText(this, "Login acc", Toast.LENGTH_SHORT).show();
            }
            if (account != null) {
                name = account.getDisplayName();
            } else {

            }
            if (account != null) {
                email = account.getEmail();
            }
            // you can store user data to SharedPreference
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            firebaseAuthWithGoogle(credential);
        } else {

            // Google Sign In failed, update UI appropriately
            Log.e(TAG, "Login Unsuccessful. " + result.getStatus());
            Toast.makeText(this, "Login Unsuccessful" + result.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(AuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Login successful", Toast.LENGTH_SHORT).show();
                            DatabaseReference reference =  FirebaseDatabase.getInstance().getReference().child("Universities").child("UsersId");
                            HashMap<String , String> map = new HashMap<>();
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            String userName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                           String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            map.put("UserId" , userId);
                            map.put("UserName",userName);
                            map.put("Email",email);
                            reference.child(userId).setValue(map);
                            gotoProfile();
                        } else {
                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());
                            task.getException().printStackTrace();
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private void gotoProfile() {
        Intent intent = new Intent(Registration.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authStateListener != null) {
            FirebaseAuth.getInstance().signOut();
        }
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void initViews() {
        userNameEdt = findViewById(R.id.userNameEdt);
        emailEdt = findViewById(R.id.emailEdt);
        phoneNmbrEdt = findViewById(R.id.phoneNmbrEdt);
        passwordEdt = findViewById(R.id.passwordEdt);
        confirmPassEdt = findViewById(R.id.confirmPassEdt);
    }

    public void MovetoSignIn(View view) {
        startActivity(new Intent(Registration.this, MainActivity.class));
        finish();
    }

    public void AccountVerification(View view) {
        String username = userNameEdt.getText().toString().trim();
        String email = emailEdt.getText().toString().trim();
        String phoneNmbr = phoneNmbrEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();
        String confirmPass = confirmPassEdt.getText().toString().trim();
        authenticationsEdt(username, email, phoneNmbr, password, confirmPass);
    }

    private void authenticationsEdt(String username, String email, String phoneNmbr, String password, String confirmPass) {
        if (email.isEmpty()) {
            emailEdt.setError("Email is empty");
            emailEdt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEdt.setError("Enter the valid email address");
            emailEdt.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordEdt.setError("Enter the password");
            passwordEdt.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordEdt.setError("Length of the password should be more than 6");
            passwordEdt.requestFocus();
            return;
        }
        if (!(confirmPass.equals(password))) {
            passwordEdt.setError("Password doesn't match");
            passwordEdt.requestFocus();
            return;
        }
//        if (!(phoneNmbr.isEmpty())) {
//            phoneNmbrEdt.setError("Enter Number with Country Code");
//            phoneNmbrEdt.requestFocus();
//            return;
//        }
        if (username.isEmpty()) {
            userNameEdt.setError("Enter UserName");
            userNameEdt.requestFocus();
            return;
        } else {
            Intent intent = new Intent(Registration.this, AccountVerification.class);
            UserModel userModel = new UserModel(email, username, phoneNmbr, password);
            intent.putExtra("sampleObj", userModel);

            startActivity(intent);

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}