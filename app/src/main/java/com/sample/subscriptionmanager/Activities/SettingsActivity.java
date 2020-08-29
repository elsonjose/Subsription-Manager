package com.sample.subscriptionmanager.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sample.subscriptionmanager.MainActivity;
import com.sample.subscriptionmanager.R;
import com.sample.subscriptionmanager.Utils.AppConstants;

public class SettingsActivity extends AppCompatActivity {

    Switch settingsThemeManualModeSwitch;

    public static final String TAG="SettingsActivity";

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
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

        settingsThemeManualModeSwitch = findViewById(R.id.settingThemeManualModeSwitch);

        if(appDataPref.contains(AppConstants.THEME_VALUE))
        {
            String theme = appDataPref.getString(AppConstants.THEME_VALUE,AppConstants.THEME_LIGHT);
            if(theme.equals(AppConstants.THEME_DARK))
            {
                settingsThemeManualModeSwitch.setChecked(true);
            }
            else
            {
                settingsThemeManualModeSwitch.setChecked(false);
            }
        }
        else
        {
            settingsThemeManualModeSwitch.setChecked(false);
            appDataPrefEditor.putString(AppConstants.THEME_VALUE,AppConstants.THEME_LIGHT);
            appDataPrefEditor.commit();
            setTheme(R.style.AppTheme);

        }

        settingsThemeManualModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    appDataPrefEditor.putString(AppConstants.THEME_VALUE,AppConstants.THEME_DARK);
                    appDataPrefEditor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
                else
                {
                    appDataPrefEditor.putString(AppConstants.THEME_VALUE,AppConstants.THEME_LIGHT);
                    appDataPrefEditor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedId  = item.getItemId();
        switch (selectedId)
        {
            case android.R.id.home: onBackPressed();
                                    return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
}
