package com.cebsambagII.onlineinquiries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cebsambagII.onlineinquiries.model.ComplainantModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ComplaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        EditText fullname = findViewById(R.id.fullname);
        EditText age = findViewById(R.id.age);
        EditText gender = findViewById(R.id.gender);
        EditText houseCode = findViewById(R.id.houseCode);
        EditText sitio = findViewById(R.id.sitio);
        EditText contactNumber = findViewById(R.id.contactNumber);
        EditText complaintMessage = findViewById(R.id.complaintMessage);

        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(fullname.getText())) {
                    fullname.setError("This field can't be empty! ");
                }else if (TextUtils.isEmpty(age.getText())) {
                    age.setError("This field can't be empty! ");
                }else if (TextUtils.isEmpty(gender.getText())) {
                    gender.setError("This field can't be empty! ");
                }else if (TextUtils.isEmpty(houseCode.getText())) {
                    houseCode.setError("This field can't be empty! ");
                }else if (TextUtils.isEmpty(sitio.getText())) {
                    sitio.setError("This field can't be empty! ");
                }else if (TextUtils.isEmpty(contactNumber.getText())) {
                    contactNumber.setError("This field can't be empty! ");
                }else if (TextUtils.isEmpty(complaintMessage.getText())) {
                    complaintMessage.setError("This field can't be empty! ");
                }else {
                    addDataToFirebase(
                            fullname.getText().toString(),
                            age.getText().toString(),
                            gender.getText().toString(),
                            houseCode.getText().toString(),
                            sitio.getText().toString(),
                            contactNumber.getText().toString(),
                            complaintMessage.getText().toString());
                    backToCategoryActivity();
                }

            }
        });

     }

    private void backToCategoryActivity() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    private void addDataToFirebase(String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ComplaintAppDB");
        String id = myRef.push().getKey();
        ComplainantModel constants = new ComplainantModel(id,toString,toString1,toString2,toString3,toString4,toString5,toString6);

        //

        myRef.child(id).setValue(constants).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Your Complaint Sent Successfully!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to send!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}