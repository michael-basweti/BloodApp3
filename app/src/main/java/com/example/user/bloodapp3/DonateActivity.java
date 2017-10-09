package com.example.user.bloodapp3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonateActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Spinner mSpinner;
    private Button mSMS;
    TextView textLat;
    TextView textLong;
    private DatabaseReference mDatabase;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        mSMS=(Button)findViewById(R.id.button2);
        mSpinner=(Spinner)findViewById(R.id.spinner2);
        mAuth= FirebaseAuth.getInstance();
        String [] relationship={"Nyahururu District Hospital","KNH","Nairobi Hospital","The Marter Hospital","Agha Khan",
                "Moi Referral","Tenwek Hospital","St. Marys Hospital"};
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,relationship);
        mSpinner.setAdapter(adapter1);

        mProgress=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Donate");
        mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());








    }


    @Override
    protected void onStart() {
        super.onStart();


        mSMS.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {



                post();


            }
        });
    }


    private void post() {
        mProgress.setMessage("sending request...");
        mProgress.show();
        final String relationship = mSpinner.getSelectedItem().toString().trim();

        if (!TextUtils.isEmpty(relationship)) {


            final DatabaseReference newPost = mDatabase.push();


            mDatabaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    newPost.child("Nearest_Hospital").setValue(relationship);


                    newPost.child("uid").setValue(mCurrentUser.getUid());
                    newPost.child("Name").setValue("Username:" + dataSnapshot.child("Name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(DonateActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("image").setValue(dataSnapshot.child("image").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(DonateActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("Phone").setValue("Phone No:" + dataSnapshot.child("Phone").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(DonateActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("Blood_Group").setValue("Blood Group:" + dataSnapshot.child("Blood_Group").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(DonateActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("Email").setValue("Email:" + dataSnapshot.child("Email").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(DonateActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    newPost.child("Address").setValue("Address:" + dataSnapshot.child("Address").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(DonateActivity.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mProgress.dismiss();
            Toast.makeText(DonateActivity.this, "Your Data Has been sent", Toast.LENGTH_LONG).show();

            Intent mainIntent = new Intent(DonateActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
    }





}
