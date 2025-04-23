package com.example.task51c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.task51c.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(TAG, "onCreate: Activity created");

        // Check if video page should be shown
        boolean showVideo = getIntent().getBooleanExtra("show_video", false);
        
        if (showVideo) {
            // Navigate to video player page
            startActivity(new Intent(this, VideoPlayerActivity.class));
            finish();
        } else {
            // Show news page by default
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NewsFragment())
                .commit();
        }

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        Log.d(TAG, "setupBottomNavigation: Setting up bottom navigation");
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Log.d(TAG, "onNavigationItemSelected: Item clicked - " + item.getItemId());
            
            if (item.getItemId() == R.id.navigation_news) {
                Log.d(TAG, "onNavigationItemSelected: News selected");
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new NewsFragment())
                    .commit();
                return true;
            } else if (item.getItemId() == R.id.navigation_videos) {
                Log.d(TAG, "onNavigationItemSelected: Videos selected");
                // Navigate to login page
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
            return false;
        });
    }

    private boolean isLoggedIn() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean(KEY_IS_LOGGED_IN, false);
        Log.d(TAG, "isLoggedIn: " + isLoggedIn);
        return isLoggedIn;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult - requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult: Login successful, showing VideoFragment");
            // User successfully logged in, now show video fragment
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoFragment())
                .commit();
            
            // Update bottom navigation selection
            binding.bottomNavigation.setSelectedItemId(R.id.navigation_videos);
        }
    }

    public void setLoggedIn(boolean loggedIn) {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply();
        Log.d(TAG, "setLoggedIn: " + loggedIn);
    }
}