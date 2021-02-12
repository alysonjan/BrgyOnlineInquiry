package com.cebsambagII.onlineinquiries;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.cebsambagII.onlineinquiries.model.Upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SelectedAnnouncementActivity extends AppCompatActivity {

    private TextView title,date;
    private Button deleteButton;


    private FirebaseStorage mStorage;


    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_announcement);


        title = findViewById(R.id.single_selected_title);
        date = findViewById(R.id.ann_date);

        deleteButton = findViewById(R.id.announcement_delete_button);

        mStorage = FirebaseStorage.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            Upload upload = (Upload) intent.getSerializableExtra("ann_data");

            date.setText(upload.getDate());
            title.setText(upload.getmCaption());

            String imageUri = upload.getmImageUrl();
            ImageView selectedImage = (ImageView) findViewById(R.id.selected_announcement_image);
            Picasso.get().load(imageUri).into(selectedImage);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SelectedAnnouncementActivity.this);
                    builder.setTitle("Delete");
                    builder.setMessage("Are you sure you want to delete? ");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            StorageReference imageRef = mStorage.getReferenceFromUrl(upload.getmImageUrl());
                            imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference dbRef = database.getReference("Uploads").child(upload.getId());
                                  dbRef.removeValue();

                                }
                            });
                            Toast.makeText(SelectedAnnouncementActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                            backToAnnouncementList();

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


    private void backToAnnouncementList() {
        Intent intent = new Intent(SelectedAnnouncementActivity.this,ViewAnnouncementList.class);
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