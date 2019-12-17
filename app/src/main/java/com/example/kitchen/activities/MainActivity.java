package com.example.kitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kitchen.R;
import com.example.kitchen.fragments.breakfastFragment;
import com.example.kitchen.fragments.dinnerFragment;
import com.example.kitchen.fragments.lunchFragment;
import com.example.kitchen.my_adapter.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new breakfastFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.action_breakfast:
                fragment = new breakfastFragment();
                break;
            case R.id.action_lunch:
                fragment = new lunchFragment();
                break;
            case R.id.action_dinner:
                fragment = new dinnerFragment();
                break;
        }
        return loadFragment(fragment);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.LogOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                Toast msg = Toast.makeText(MainActivity.this, "Anda Telah Log Out...", Toast.LENGTH_LONG);
                msg.show();
                break;

            case R.id.Quit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
