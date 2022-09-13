package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG = "SignupActivtiy";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etPhone;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ConstraintLayout constraintLayout = findViewById(R.id.sign_up_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        etUsername = findViewById(R.id.etNewUsername);
        etPassword = findViewById(R.id.etNewPassword);
        etEmail = findViewById(R.id.etNewEmail);
        etPhone = findViewById(R.id.etNewPhone);
        btnSignup = findViewById(R.id.btnNewAccount);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Finish signup button");
                // Create the ParseUser
                ParseUser user = new ParseUser();
// Set core properties
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setEmail(etEmail.getText().toString());
// Set custom properties
                user.put("phone", etPhone.getText().toString());
// Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            final Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            Toast.makeText(getApplicationContext(),"Sign up failed",Toast.LENGTH_SHORT).show();
                            // to figure out what went wrong
                            Log.e(TAG, "Issue with sign up", e);
                        }
                    }
                });
            }
        });
    }
}