package com.example.user.bloodapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Detail1 extends AppCompatActivity {
    private String mPost_key=null;
    private DatabaseReference mDatabase;

    private ImageView mBlogSingleImage;
    private TextView mBlogSingleTitle;
    private TextView mBlogSingleDesc;
    private TextView mBlogSinglephone;
    private TextView mBlogSinglehospital;
    private TextView mBlogSingleblood;
    private TextView mBlogSinglename;



    private Button mSingleRemoveBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Donate");

        mDatabase.keepSynced(true);


        mAuth= FirebaseAuth.getInstance();

        mPost_key=getIntent().getExtras().getString("blog_id");

        mBlogSingleDesc=(TextView)findViewById(R.id.email);
        mBlogSingleImage=(ImageView)findViewById(R.id.singleBlogImage);
        mBlogSingleTitle=(TextView) findViewById(R.id.address);
        mBlogSinglephone=(TextView) findViewById(R.id.phone);
        mBlogSinglehospital=(TextView) findViewById(R.id.hospital);
        mBlogSingleblood=(TextView) findViewById(R.id.blood_group);
        mBlogSinglename=(TextView) findViewById(R.id.name);



        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDatabase.keepSynced(true);
                String post_title=(String)dataSnapshot.child("Name").getValue();
                String post_desc=(String)dataSnapshot.child("Address").getValue();
                String post_image=(String)dataSnapshot.child("image").getValue();
                String post_email=(String)dataSnapshot.child("Email").getValue();
                String post_phone=(String)dataSnapshot.child("Phone").getValue();
                String post_hospital=(String)dataSnapshot.child("Nearest_Hospital").getValue();
                String post_blood=(String)dataSnapshot.child("Blood_Group").getValue();

                String post_uid=(String)dataSnapshot.child("uid").getValue();

                mBlogSingleTitle.setText(post_title);
                mBlogSingleDesc.setText(post_desc);
                mBlogSinglephone.setText(post_phone);
                mBlogSinglehospital.setText(post_hospital);
                mBlogSingleblood.setText(post_blood);
                mBlogSinglename.setText(post_email);


                Picasso.with(Detail1.this).load(post_image).into(mBlogSingleImage);

                if (mAuth.getCurrentUser().getUid().equals(post_uid)){


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });



        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);



    }
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}


