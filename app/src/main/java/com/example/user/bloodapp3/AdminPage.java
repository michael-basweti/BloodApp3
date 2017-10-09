package com.example.user.bloodapp3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class AdminPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        mAuth= FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    Intent ADMINLOGOUT=new Intent(AdminPage.this,AdminLogin.class);
                    ADMINLOGOUT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(ADMINLOGOUT);
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

        getMenuInflater().inflate(R.menu.admin, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_logout) {

            logout();

        }


        if (item.getItemId() == R.id.action_settings) {

            Intent mainIntent = new Intent(AdminPage.this, AdminPage.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
}
