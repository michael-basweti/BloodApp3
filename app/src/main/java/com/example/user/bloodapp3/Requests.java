package com.example.user.bloodapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Requests extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseUsers;
    private boolean mProcessLike=false;
    private DatabaseReference mDatabaseLikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        mAuth=FirebaseAuth.getInstance();


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Request");



        mDatabase.keepSynced(true);


        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase.keepSynced(true);




        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key=getRef(position).getKey();

                viewHolder.setBlood_Group(model.getBlood_Group());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setEmail(model.getEmail());
                viewHolder.setHospital(model.getNearest_Hospital());
                viewHolder.setName(model.getName());
                viewHolder.setPhone(model.getPhone());




                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity.this,post_key,Toast.LENGTH_LONG).show();

                        Intent singleBlogIntent=new Intent(Requests.this,DetailActivity.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);

                    }
                });
                viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Intent singleBlogIntent=new Intent(Requests.this,DetailActivity.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);
                        return true;

                    }
                });


            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }




    public static class BlogViewHolder extends RecyclerView.ViewHolder{


        View mView;

        FirebaseAuth mAuth;
        public BlogViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

            mAuth=FirebaseAuth.getInstance();


        }
        public void setEmail(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.email);
            post_title.setText(gender);
        }
        public void setPhone(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.phone);
            post_title.setText(gender);
        }

        public void setAddress(String age){
            TextView post_title=(TextView)mView.findViewById(R.id.address);
            post_title.setText(age);
        }
        public void setName(String desc){
            TextView post_desc=(TextView)mView.findViewById(R.id.post_username);
            post_desc.setText(desc);
        }

        public void setBlood_Group(String username){
            TextView post_username=(TextView)mView.findViewById(R.id.blood_group);
            post_username.setText(username);
        }
        public void setHospital(String latitude){
            TextView textLat=(TextView)mView.findViewById(R.id.nearest_hospital);
            textLat.setText(latitude);
        }


    }
}
