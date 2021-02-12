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

import com.cebsambagII.onlineinquiries.Adapter.ComplainantAdapter;
import com.cebsambagII.onlineinquiries.model.ComplainantModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewComplaintList extends AppCompatActivity implements ComplainantAdapter.SelectedComplainant {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private ComplainantAdapter complainantAdapter;
    private List<ComplainantModel> mComplainantModels;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_view_complaint_list);

        recyclerView = findViewById(R.id.admin_view_complaints_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);


        mComplainantModels = new ArrayList<>();
        readComplainants();

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
        recreate();
    }

    public void AnnouncementsNavBtn(View view) {
        DashboardActivity.redirectActivity(this,ViewAnnouncementList.class);
    }

    public void Logout(View view) {
        DashboardActivity.Logout(this);
    }



    private void readComplainants() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ComplaintAppDB");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mComplainantModels.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ComplainantModel complainantModel = snapshot.getValue(ComplainantModel.class);
                    mComplainantModels.add(complainantModel);

                }
                
                complainantAdapter = new ComplainantAdapter(getBaseContext(),mComplainantModels,ViewComplaintList.this::selectedComplainant);
                recyclerView.setAdapter(complainantAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void selectedComplainant(ComplainantModel complainantModel) {

        startActivity(new Intent(ViewComplaintList.this, SelectedComplainantMessageActivity.class).putExtra("data",complainantModel));
    }

    @Override
    protected void onPause() {
        super.onPause();
        DashboardActivity.closeDrawer(drawerLayout);
    }

}