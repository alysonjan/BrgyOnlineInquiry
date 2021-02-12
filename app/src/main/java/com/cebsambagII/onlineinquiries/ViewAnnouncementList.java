package com.cebsambagII.onlineinquiries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cebsambagII.onlineinquiries.Adapter.AdminAnnouncementAdapter;
import com.cebsambagII.onlineinquiries.Adapter.ImageAdapter;
import com.cebsambagII.onlineinquiries.model.ComplainantModel;
import com.cebsambagII.onlineinquiries.model.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewAnnouncementList extends AppCompatActivity implements AdminAnnouncementAdapter.SelectedAnnouncement {


    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private AdminAnnouncementAdapter adminAnnouncementAdapter;
    private List<Upload> mUploads;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_announcement_list);

        recyclerView = findViewById(R.id.admin_view_announcements_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mLayoutManager = (new LinearLayoutManager(getBaseContext()));
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);


        mUploads = new ArrayList<>();
        readAnnouncements();

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view) {
        DashboardActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        DashboardActivity.closeDrawer(drawerLayout);
    }

    public void DashBoard(View view) {
        DashboardActivity.redirectActivity(this,DashboardActivity.class);
    }

    public void ComplaintsNavBtn(View view) {
        DashboardActivity.redirectActivity(this,ViewComplaintList.class);
    }

    public void AnnouncementsNavBtn(View view) {
        recreate();
    }

    public void Logout(View view) {
        DashboardActivity.Logout(this);
    }


    public void CreateAnnouncement(View view) {
        Intent intent = new Intent(this,CreateAnnouncementActivity.class );
        startActivity(intent);
    }


    private void readAnnouncements() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Uploads");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Upload upload = snapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                adminAnnouncementAdapter = new AdminAnnouncementAdapter(getBaseContext(),mUploads,ViewAnnouncementList.this::selectedAnnouncement);
                recyclerView.setAdapter(adminAnnouncementAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void selectedAnnouncement(Upload upload) {
        startActivity(new Intent(ViewAnnouncementList.this,SelectedAnnouncementActivity.class).putExtra("ann_data",upload));
    }


    @Override
    protected void onPause() {
        super.onPause();
        DashboardActivity.closeDrawer(drawerLayout);
    }
}

