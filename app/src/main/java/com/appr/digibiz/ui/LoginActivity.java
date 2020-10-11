package com.appr.digibiz.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.appr.digibiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.login_button)
    Button mLoginButton;
    @BindView(R.id.login_email)
    TextInputLayout mEmail;
    @BindView(R.id.login_password)
    TextInputLayout mPassword;
    @BindView(R.id.to_Signup)
    TextView mToSignUp;

    private static final String TAG = "LoginActivity";
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener  authStateListener;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        createAuthStateListenter();
        createAuthProgressDialog();

        mToSignUp.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mToSignUp) {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        }
        if(view == mLoginButton) {
            loginWithPassword();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        //essential for the auth state listener to work
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
    private void createAuthStateListenter() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    //if there exists an already authenticated account, the user is redirected to the main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    private void createAuthProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging you in");
        mProgressDialog.setCancelable(false);
    }
    private void loginWithPassword() {
        String email = mEmail.getEditText().getText().toString().trim();
        String password = mPassword.getEditText().getText().toString().trim();
        if (email.equals("")) {
            mEmail.setError("Please enter a valid email");
            return;
        }
        if (password.equals("")) {
            mPassword.setError("Password cannot be blank");
            return;
        }

        mProgressDialog.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if(!task.isSuccessful()) {
                            Snackbar.make(mLoginButton, "Wrong password and/or email", Snackbar.LENGTH_LONG)
                                    .setBackgroundTint(getResources().getColor(R.color.errorDarkRed))
                                    .setActionTextColor(getResources().getColor(R.color.colorPrimaryDark))
                                    .show();
                        }
                    }
                });
    }
}