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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignupActivity";
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog mProgressDialog;
    
    @BindView(R.id.signup_business_name)
    TextInputLayout mBusinessName;
    @BindView(R.id.signup_email)
    TextInputLayout mEmail;
    @BindView(R.id.signup_phone)
    TextInputLayout mPhone;
    @BindView(R.id.signup_password)
    TextInputLayout mPassword;
    @BindView(R.id.signup_confirm_password)
    TextInputLayout mConfirmPassword;
    @BindView(R.id.signup_button)
    Button mSignupButton;
    @BindView(R.id.to_Login)
    TextView mToLogin;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        
        auth = FirebaseAuth.getInstance();
        createAuthStateListenter();
        createAuthProgressDialog();
        mSignupButton.setOnClickListener(this);
        mToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSignupButton) {
            createAccount();
        }
        if (view == mToLogin) {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
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
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createAccount() {
        String businessName = mBusinessName.getEditText().getText().toString().trim();
        String email = mEmail.getEditText().getText().toString().trim();
        String phone = mPhone.getEditText().getText().toString().trim();
        String password = mPassword.getEditText().getText().toString().trim();
        String confirmPassword = mConfirmPassword.getEditText().getText().toString().trim();

        boolean validBusinessName = isValidName(businessName);
        boolean validEmail = isValidEmail(email);
        boolean validPhone = isValidPhoneNumber(phone);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if(!validBusinessName || !validEmail || !validPhone || !validPassword) return;

        mProgressDialog.show();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Snackbar.make(mSignupButton, "Successful", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(getResources().getColor(R.color.colorPrimary))
                                    .setActionTextColor(getResources().getColor(R.color.colorPrimaryDark))
                                    .show();
                            storeValuesInDB(businessName, email, phone);
                        } else {
                            Snackbar.make(mSignupButton, "Error! Try again", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(getResources().getColor(R.color.colorPrimary))
                                    .setActionTextColor(getResources().getColor(R.color.colorPrimaryDark))
                                    .show();
                        }
                    }
                });
    }
    private void storeValuesInDB(String businesName, String email, String phone) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(getString(R.string.db_node_users))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.field_business_name))
                .setValue(businesName);
        reference.child(getString(R.string.db_node_users))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.field_email))
                .setValue(email);
        reference.child(getString(R.string.db_node_users))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.field_phone_number))
                .setValue(phone);
        reference.child(getString(R.string.db_node_users))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.field_security_level))
                .setValue("0");

    }

    private void createAuthProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Getting you set up...");
        mProgressDialog.setCancelable(false);
    }

    //form validation
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mBusinessName.setError("Please enter your business' name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber) == false) {
            mPhone.setError("Invalid phone number format");
            return false;
        };
        return true;
    }

}