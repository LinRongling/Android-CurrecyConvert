package com.example.currecyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity {

    CheckBox green;

    boolean b_green;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        green = (CheckBox)findViewById(R.id.green);

        settings = getSharedPreferences("PREF", MODE_PRIVATE);

        b_green = settings.getBoolean("GREEN", false);

        if (b_green) {
            green.setChecked(true);
        }

        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    b_green = true;
                    saveSettingBoolean("GREEN", b_green);
                } else {
                    b_green = false;
                    saveSettingBoolean("GREEN", b_green);
                }
            }
        });

    }

    public void saveSettingBoolean(String s, boolean b) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(s, b);
        editor.apply();
    }
}
