package com.example.swarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById (R.id.botton_navigation);
        bottomNav.setOnNavigationItemSelectedListener (navListner);
        contextOfApplication = getApplicationContext();
        getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, new HomeFragment ()).commit ();
    }

    // a static variable to get a reference of our application context
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener () {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId ()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment ();
                            break;
                        case R.id.nav_fav:
                            selectedFragment = new FavoriteFragment();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container, selectedFragment).commit ();

                    return true;
                }
            };



}
