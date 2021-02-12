package com.cebsambagII.onlineinquiries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {
    private CardView complaintButton;
    private CardView eventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        complaintButton = findViewById(R.id.complaintButton);
        complaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openComplaintActivity();
            }
        });

        eventButton = findViewById(R.id.eventButton);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnnouncementActivity();
            }
        });
    }

    private void openComplaintActivity() {
        Intent intent = new Intent(this, ComplaintActivity.class);
        startActivity(intent);
    }

    private void openAnnouncementActivity() {
        Intent intent = new Intent(this, AnnouncementActivity.class);
        startActivity(intent);
    }
}