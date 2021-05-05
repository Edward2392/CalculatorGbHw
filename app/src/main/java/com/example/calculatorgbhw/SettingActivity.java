package com.example.calculatorgbhw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
    public final static String KEY = "KEY";

    SwitchCompat switchTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        boolean checkedTheme = sharedPreferences.getBoolean(KEY, true);

        if (checkedTheme) {
            setTheme(R.style.Theme_CalculatorGBHW);
        } else {
            setTheme(R.style.Theme_CalculatorNEW);
        }
        setContentView(R.layout.activity_setting);

        switchTheme = findViewById(R.id.switch_Theme);

        switchTheme.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isPressed()) {
                    sharedPreferences.edit().putBoolean(KEY, isChecked).apply();
                    recreate();
                }
            }
        });
    }
























/*
    public void onSwitchTheme(View view) {

        boolean Check = ((Switch) view).isChecked();

        if (Check){
            setTheme(R.style.Theme_CalculatorNEW);
        }else {
            setTheme(R.style.Theme_CalculatorGBHW);
        }recreate();

    }*/
}