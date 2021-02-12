package com.cebsambagII.onlineinquiries;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cebsambagII.onlineinquiries.model.ComplainantModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectedComplainantMessageActivity extends AppCompatActivity {

    private TextView fullname;
    private TextView age;
    private TextView gender;
    private TextView housecode;
    private TextView sitio;
    private TextView contactnumber;
    private TextView message;
    private TextView date;
    private Button deleteButton;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_complainant_message);

        fullname = findViewById(R.id.cm_fullname);
        age = findViewById(R.id.cm_age);
        gender = findViewById(R.id.cm_gender);
        housecode = findViewById(R.id.cm_housecode);
        sitio = findViewById(R.id.cm_sitio);
        contactnumber = findViewById(R.id.cm_contactnumber);
        message = findViewById(R.id.cm_message);
        date = findViewById(R.id.cm_date);

        drawerLayout = findViewById(R.id.drawer_layout);

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            ComplainantModel complainantModel = (ComplainantModel) intent.getSerializableExtra("data");

            fullname.setText(complainantModel.getFullname());
            age.setText(complainantModel.getAge());
            gender.setText(complainantModel.getGender());
            housecode.setText(complainantModel.getHouseCode());
            sitio.setText(complainantModel.getSitio());
            contactnumber.setText(complainantModel.getContactNumber());
            message.setText(complainantModel.getComplaintMessage());
            date.setText(complainantModel.getDate());


        deleteButton = findViewById(R.id.complaint_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SelectedComplainantMessageActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference dbRef = database.getReference("ComplaintAppDB").child(complainantModel.getId());
                        dbRef.removeValue();
                        Toast.makeText(SelectedComplainantMessageActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();

                        backToComplaintList();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        }
    }

   private void backToComplaintList() {
        Intent intent = new Intent(SelectedComplainantMessageActivity.this,ViewComplaintList.class);
          startActivity(intent);
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
        DashboardActivity.redirectActivity(this,ViewAnnouncementList.class);
    }

    public void Logout(View view) {
        DashboardActivity.Logout(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        DashboardActivity.closeDrawer(drawerLayout);
    }

}