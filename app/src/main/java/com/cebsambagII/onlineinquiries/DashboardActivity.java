package com.cebsambagII.onlineinquiries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class DashboardActivity extends AppCompatActivity {


    DatabaseReference complaintRef, announcementRef;
    DrawerLayout drawerLayout;

    private CardView  adminComplaintBtn,adminAnnouncementBtn;
    private TextView totalcomplaints, totalannouncement;


    private int countComplaint = 0;
    private int countAnnouncement = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);


        complaintRef = FirebaseDatabase.getInstance().getReference().child("ComplaintAppDB");
        announcementRef = FirebaseDatabase.getInstance().getReference().child("Uploads");



        totalcomplaints = findViewById(R.id.totalcomplaints);
        totalannouncement = findViewById(R.id.totalannouncement);


        complaintRef.orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists())
                        {
                            countComplaint = (int) snapshot.getChildrenCount();
                            totalcomplaints.setText(Integer.toString(countComplaint));

                        }
                        else {
                            totalcomplaints.setText("0");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        announcementRef.orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists())
                        {
                            countAnnouncement = (int) snapshot.getChildrenCount();
                            totalannouncement.setText(Integer.toString(countAnnouncement));

                        }
                        else {
                            totalannouncement.setText("0");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        adminComplaintBtn = findViewById(R.id.adminComplaintBtn);
        adminComplaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenComplaintListActivity();
            }
        });
        adminAnnouncementBtn = findViewById(R.id.adminAnnouncementBtn);
        adminAnnouncementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAnnouncementsActivity();
            }
        });


    }



    private void OpenComplaintListActivity() {
        Intent intent = new Intent(this,ViewComplaintList.class);
        startActivity(intent);
    }

    private void OpenAnnouncementsActivity() {
        Intent intent = new Intent(this,ViewAnnouncementList.class);
        startActivity(intent);
    }


    public void  ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void DashBoard(View view) {
        recreate();
    }

    public void ComplaintsNavBtn(View view) {
        redirectActivity(this,ViewComplaintList.class);
    }

    public void AnnouncementsNavBtn(View view) {
        redirectActivity(this,ViewAnnouncementList.class);
    }
    
    public void Logout(View view) {
        Logout(this);
    }

    public static void Logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aclass) {
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public void onBackPressed() {

    }
}