package com.cebsambagII.onlineinquiries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {
    private Button getStartedButton;
    private TextView adminButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getStartedButton = (Button) findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity();
            }
        });

        adminButton = (TextView)findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminLoginActivity();
            }
        });


    }

    private void openAdminLoginActivity() {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }


    private void openCategoryActivity() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}