package com.sample.subscriptionmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sample.subscriptionmanager.Activities.SettingsActivity;
import com.sample.subscriptionmanager.Utils.AppConstants;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences appDataPref = getSharedPreferences(AppConstants.THEME_CONFIG_FILE, Context.MODE_PRIVATE);
        final SharedPreferences.Editor appDataPrefEditor = appDataPref.edit();
        if(appDataPref.contains(AppConstants.THEME_VALUE))
        {
            String theme = appDataPref.getString(AppConstants.THEME_VALUE,AppConstants.THEME_LIGHT);
            if(theme.equals(AppConstants.THEME_LIGHT))
            {
                setTheme(R.style.AppTheme);
            }
            else
            {
                setTheme(R.style.DarkTheme);

            }
        }
        else
        {
            appDataPrefEditor.putString(AppConstants.THEME_VALUE,AppConstants.THEME_LIGHT);
            appDataPrefEditor.commit();
            setTheme(R.style.AppTheme);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int selectedId = item.getItemId();
        switch (selectedId)
        {
            case R.id.action_bar_menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
