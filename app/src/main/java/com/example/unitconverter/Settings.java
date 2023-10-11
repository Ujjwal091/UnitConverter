package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Settings extends AppCompatActivity {
    public static int selectedQuantity = R.id.areaBtn;
    protected RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // this will enable the back button in toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        radioGroup = findViewById(R.id.quantity_group);

        // this will mark the quantity selected by user
        radioGroup.check(selectedQuantity);
    }

    // this method will be called when user select the "area" on settings page
    public void selectArea(View view) {
        radioGroup.check(R.id.areaBtn);
        selectedQuantity = R.id.areaBtn;
    }

    // this method will be called when user select the "length" on settings page
    public void selectLength(View view) {
        radioGroup.check(R.id.lengthBtn);
        selectedQuantity = R.id.lengthBtn;
    }

    // this method will be called when user select the "temperature" on settings page
    public void selectTemperature(View view) {
        radioGroup.check(R.id.temperatureBtn);
        selectedQuantity = R.id.temperatureBtn;
    }

    // this method will be called when user select the "volume" on settings page
    public void selectVolume(View view) {
        radioGroup.check(R.id.volumeBtn);
        selectedQuantity = R.id.volumeBtn;
    }

    // this method will be called when user select the "mass" on settings page
    public void selectMass(View view) {
        radioGroup.check(R.id.massBtn);
        selectedQuantity = R.id.massBtn;
    }

    // method will be called when user click on "data" on settings page
    public void selectData(View view) {
        radioGroup.check(R.id.dataBtn);
        selectedQuantity = R.id.dataBtn;
    }
}