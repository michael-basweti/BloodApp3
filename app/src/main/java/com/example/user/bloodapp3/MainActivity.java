package com.example.user.bloodapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button mButton2;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton2=(Button) findViewById(R.id.button2);
        mButton4=(Button) findViewById(R.id.button4);
        mButton5=(Button) findViewById(R.id.button5);
        mButton6=(Button) findViewById(R.id.button6);
        mAuth= FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();
        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent=new Intent(MainActivity.this,About.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent=new Intent(MainActivity.this,Contact.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent=new Intent(MainActivity.this,DonateActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent=new Intent(MainActivity.this,RequestActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);

            }
        });
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    Intent homeIntent=new Intent(MainActivity.this,LoginActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }

            }
        };
        mAuth= FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);



    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_logout) {

            logout();

        }
        if (item.getItemId() == R.id.admin) {

            Intent Admin = new Intent(MainActivity.this, Requests.class);
            Admin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(Admin);

        }

        if (item.getItemId() == R.id.donation) {

            Intent donations = new Intent(MainActivity.this, Donations.class);
            donations.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(donations);

        }

        if (item.getItemId() == R.id.action_settings) {

            Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
}
