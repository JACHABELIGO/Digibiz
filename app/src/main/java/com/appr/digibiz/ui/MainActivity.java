package com.appr.digibiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.appr.digibiz.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.to_Inventory)
    CardView mToInventory;
    @BindView(R.id.to_Invoice)
    CardView mToInvoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToInventory.setOnClickListener(this);
        mToInvoice.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.log_out) {
            Snackbar.make(mToInventory, "Logging out", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.colorAccent))
                    .setActionTextColor(getResources().getColor(R.color.colorPrimaryDark))
                    .show();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == mToInventory) {
            startActivity(new Intent(MainActivity.this, InventoryActivity.class));
        }
        if(view == mToInvoice) {
            startActivity(new Intent(MainActivity.this, tabs.class));
        }
    }




}