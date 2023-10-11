package com.example.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // list of options to show in drop down menu (spinner)
    private String[] dropDownOption;

    // stores the id of EditText that contains text (value) to be converted
    private EditText sourceText;

    // stores the id of EdiText that contains text (value) after conversion from source quantity
    private EditText targetText;

    // stores the id of EditText which is currently being edited by the user
    private EditText currentEditingField;

    // stores the id of EditText which is not currently being edited by the user
    private EditText anotherEditingField;

    private Spinner sourceUnitSpinner;
    private Spinner targetUnitSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find and set Toolbar
        // since appcompat toolbar has been used,
        // so to setup app bar we have used setSupportActionBar()
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // this condition checks whether actionbar is setup so to avoid app crash and warnings
        if (getSupportActionBar() != null) {
            // if actionBar is available in this activity then set its title
            getSupportActionBar().setTitle("Unit Converter");
        }

        selectQuantity();

        sourceText = findViewById(R.id.sourceUnitText);
        sourceText.setShowSoftInputOnFocus(false);

        targetText = findViewById(R.id.targetUnitText);
        targetText.setShowSoftInputOnFocus(false);


        sourceUnitSpinner = findViewById(R.id.sourceUnitSelector);
        targetUnitSpinner = findViewById(R.id.targetUnitSelector);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropDownOption);

        // Apply the adapter to the spinner
        sourceUnitSpinner.setAdapter(adapter);
        targetUnitSpinner.setAdapter(adapter);

        anotherEditingField = targetText;
        currentEditingField = sourceText;
        currentEditingField.requestFocus();

//        clear();

        sourceUnitSpinner.setOnItemSelectedListener(this);
        targetUnitSpinner.setOnItemSelectedListener(this);

    }

    // functions of first row from top
    public void seven(View view) {
        updateText("7");
    }

    public void eight(View view) {
        updateText("8");
    }

    public void nine(View view) {
        updateText("9");
    }

    public void backSpace(View view) {
        int cursorPos = currentEditingField.getSelectionStart();
        int textLen = currentEditingField.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) currentEditingField.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            currentEditingField.setText(selection);
            currentEditingField.setSelection(cursorPos - 1);

            if (textLen != 1) convertValue();
            else {
                anotherEditingField.setText("");
            }
        }
    }

    // functions of second row from top
    public void four(View view) {
        updateText("4");
    }

    public void five(View view) {
        updateText("5");
    }

    public void six(View view) {
        updateText("6");
    }

    public void clear(View view) {
        clear();
    }
    public void clear() {
        currentEditingField.setText("");
        anotherEditingField.setText("");
    }

    // functions of third row from top
    public void one(View view) {
        updateText("1");
    }

    public void two(View view) {
        updateText("2");
    }

    public void three(View view) {
        updateText("3");
    }

    public void upArrow(View view) {
        if (currentEditingField.getId() == targetText.getId()) {
            sourceUnitSpinner = findViewById(R.id.sourceUnitSelector);
            targetUnitSpinner = findViewById(R.id.targetUnitSelector);

        }
        currentEditingField.clearFocus();
        currentEditingField = sourceText;
        currentEditingField.requestFocus();
        currentEditingField.setSelection(currentEditingField.getText().length());
        anotherEditingField = targetText;
    }

    // functions of last row
    public void plusMinus(View view) {
        if (Settings.selectedQuantity == R.id.temperatureBtn) {
            if (currentEditingField.getText().length() == 0 || (Double.parseDouble(currentEditingField.getText().toString()) >= 0 && currentEditingField.getSelectionStart() == 0)) {
                updateText("-");
            }
        } else {
            Toast.makeText(this, "This quantity can not be negative", Toast.LENGTH_SHORT).show();
        }
    }

    public void zero(View view) {
        updateText("0");
    }

    public void point(View view) {

        if (!currentEditingField.getText().toString().contains(".")) {
            updateText(".");
        }
    }

    public void downArrow(View view) {
        if (currentEditingField.getId() == sourceText.getId()) {
            sourceUnitSpinner = findViewById(R.id.targetUnitSelector);
            targetUnitSpinner = findViewById(R.id.sourceUnitSelector);
        }
        currentEditingField.clearFocus();
        currentEditingField = targetText;
        currentEditingField.requestFocus();
        currentEditingField.setSelection(currentEditingField.getText().length());
        anotherEditingField = sourceText;
    }

    private void updateText(String strToAdd) {
        String oldStr = currentEditingField.getText().toString();

        if (oldStr.length() < 15) {
            int cursorPos = currentEditingField.getSelectionStart();

            String leftStr = oldStr.substring(0, cursorPos);
            String rightStr = oldStr.substring(cursorPos);

            // put text to textField after inserting character
            currentEditingField.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            // anotherEditingField.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));

            // set Cursor to its correct location
            currentEditingField.setSelection(++cursorPos);
            convertValue();
        } else {
            Toast.makeText(this, "You can not insert more than 15 characters" + sourceUnitSpinner.getSelectedItemId(), Toast.LENGTH_SHORT).show();
        }
    }

    // setup optionMenu in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // move to "settings activity" on clicking settings button in toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opt_setting) {

            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            clear();
        }
        return super.onOptionsItemSelected(item);
    }

    // automatically get called by onCreate() method
    // this method will used to change the value of drop-down lists (Spinner)
    public void selectQuantity() {
        if (Settings.selectedQuantity == R.id.areaBtn) {
            dropDownOption = Area.units;
        } else if (Settings.selectedQuantity == R.id.lengthBtn) {
            dropDownOption = Length.units;
        } else if (Settings.selectedQuantity == R.id.temperatureBtn) {
            dropDownOption = Temperature.units;
        } else if (Settings.selectedQuantity == R.id.volumeBtn) {
            dropDownOption = Volume.units;
        } else if (Settings.selectedQuantity == R.id.massBtn) {
            dropDownOption = Mass.units;
        } else if (Settings.selectedQuantity == R.id.dataBtn) {
            dropDownOption = Data.units;
        }
    }

    public void convertValue() {
        double conData;
        if (Settings.selectedQuantity == R.id.areaBtn) {
            conData = convertArea();
        } else if (Settings.selectedQuantity == R.id.lengthBtn) {
            conData = convertLength();
        } else if (Settings.selectedQuantity == R.id.temperatureBtn) {
            conData = convertTemperature();
        } else if (Settings.selectedQuantity == R.id.volumeBtn) {
            conData = convertVolume();
        } else if (Settings.selectedQuantity == R.id.massBtn) {
            conData = convertMass();
        } else if (Settings.selectedQuantity == R.id.dataBtn) {
            conData = convertData();
        } else {
            conData = 0;
        }
        String text = String.valueOf(conData);
        anotherEditingField.setText(text);
    }

    public double convertArea() {
        long selectedSourceUnitId = sourceUnitSpinner.getSelectedItemId();
        long selectedTargetUnitId = targetUnitSpinner.getSelectedItemId();

        double convertedValue;

        Area.area selectedUnit;
        Area.area targetUnit;

        if (selectedSourceUnitId == 0) {
            selectedUnit = Area.area.Acres;
        } else if (selectedSourceUnitId == 1) {
            selectedUnit = Area.area.Ares;
        } else if (selectedSourceUnitId == 2) {
            selectedUnit = Area.area.Hectares;
        } else if (selectedSourceUnitId == 3) {
            selectedUnit = Area.area.Square_centimetres;
        } else if (selectedSourceUnitId == 4) {
            selectedUnit = Area.area.Square_feet;
        } else if (selectedSourceUnitId == 5) {
            selectedUnit = Area.area.Square_inches;
        } else {
            selectedUnit = Area.area.Square_metres;
        }

        if (selectedTargetUnitId == 0) {
            targetUnit = Area.area.Acres;
        } else if (selectedTargetUnitId == 1) {
            targetUnit = Area.area.Ares;
        } else if (selectedTargetUnitId == 2) {
            targetUnit = Area.area.Hectares;
        } else if (selectedTargetUnitId == 3) {
            targetUnit = Area.area.Square_centimetres;
        } else if (selectedTargetUnitId == 4) {
            targetUnit = Area.area.Square_feet;
        } else if (selectedTargetUnitId == 5) {
            targetUnit = Area.area.Square_inches;
        } else {
            targetUnit = Area.area.Square_metres;
        }

        try {
            convertedValue = Area.convert(Double.parseDouble(currentEditingField.getText().toString()), selectedUnit, targetUnit);
        } catch (Exception e) {
            convertedValue = 0;
        }
        return convertedValue;
    }

    public double convertLength() {
        long selectedSourceUnitId = sourceUnitSpinner.getSelectedItemId();
        long selectedTargetUnitId = targetUnitSpinner.getSelectedItemId();

        double convertedValue;

        Length.length selectedUnit;
        Length.length targetUnit;

        if (selectedSourceUnitId == 0) {
            selectedUnit = Length.length.MilliMeters;
        } else if (selectedSourceUnitId == 1) {
            selectedUnit = Length.length.Centimetres;
        } else if (selectedSourceUnitId == 2) {
            selectedUnit = Length.length.Meters;
        } else if (selectedSourceUnitId == 3) {
            selectedUnit = Length.length.Kilometres;
        } else if (selectedSourceUnitId == 4) {
            selectedUnit = Length.length.Inches;
        } else {
            selectedUnit = Length.length.Feet;
        }

        if (selectedTargetUnitId == 0) {
            targetUnit = Length.length.MilliMeters;
        } else if (selectedTargetUnitId == 1) {
            targetUnit = Length.length.Centimetres;
        } else if (selectedTargetUnitId == 2) {
            targetUnit = Length.length.Meters;
        } else if (selectedTargetUnitId == 3) {
            targetUnit = Length.length.Kilometres;
        } else if (selectedTargetUnitId == 4) {
            targetUnit = Length.length.Inches;
        } else {
            targetUnit = Length.length.Feet;
        }
        try {
            convertedValue = Length.convert(Double.parseDouble(currentEditingField.getText().toString()), selectedUnit, targetUnit);
        } catch (Exception e) {
            convertedValue = 0;
        }
        return convertedValue;
    }


    // this method will convert the data in temperature from one unit to another unit
    public double convertTemperature() {
        long selectedSourceUnitId = sourceUnitSpinner.getSelectedItemId();
        long selectedTargetUnitId = targetUnitSpinner.getSelectedItemId();

        double convertedValue;

        Temperature.temperature selectedUnit;
        Temperature.temperature targetUnit;

        // if selected source unit is celsius
        if (selectedSourceUnitId == 0) {
            selectedUnit = Temperature.temperature.Celsius;
        }

        // if selected source unit is fahrenheit
        else if (selectedSourceUnitId == 1) {
            selectedUnit = Temperature.temperature.Fahrenheit;
        }

        // if selected source unit is kelvin
        else {
            selectedUnit = Temperature.temperature.Kelvin;
        }


        // if selected target unit is celsius
        if (selectedTargetUnitId == 0) {
            targetUnit = Temperature.temperature.Celsius;
        }

        // if selected target unit is fahrenheit
        else if (selectedTargetUnitId == 1) {
            targetUnit = Temperature.temperature.Fahrenheit;
        }

        // if selected target unit is kelvin
        else {
            targetUnit = Temperature.temperature.Kelvin;
        }
        try {
            convertedValue = Temperature.convert(Double.parseDouble(currentEditingField.getText().toString()), selectedUnit, targetUnit);
        } catch (Exception e) {
            convertedValue = 0;
        }
        return convertedValue;
    }

    // this method will convert the data in volume from one unit to another unit
    public double convertVolume() {
        long selectedSourceUnitId = sourceUnitSpinner.getSelectedItemId();
        long selectedTargetUnitId = targetUnitSpinner.getSelectedItemId();

        double convertedValue;

        Volume.volume selectedUnit;
        Volume.volume targetUnit;

        // if selected source unit is litres
        if (selectedSourceUnitId == 0) {
            selectedUnit = Volume.volume.Litres;
        }

        // if selected source unit is millilitres
        else if (selectedSourceUnitId == 1) {
            selectedUnit = Volume.volume.Millilitres;
        }

        // if selected source unit is cubic centimetres
        else if (selectedSourceUnitId == 2) {
            selectedUnit = Volume.volume.Cubic_centimetres;
        }

        // if selected source unit is cubic metres
        else if (selectedSourceUnitId == 3) {
            selectedUnit = Volume.volume.Cubic_metres;
        }

        // if selected source unit is cubic inches
        else {
            selectedUnit = Volume.volume.Cubic_inches;
        }


        // if selected target unit is litres
        if (selectedTargetUnitId == 0) {
            targetUnit = Volume.volume.Litres;
        }

        // if selected target unit is millilitres
        else if (selectedTargetUnitId == 1) {
            targetUnit = Volume.volume.Millilitres;
        }

        // if selected target unit is cubic centimetres
        else if (selectedTargetUnitId == 2) {
            targetUnit = Volume.volume.Cubic_centimetres;
        }

        // if selected target unit is cubic metres
        else if (selectedTargetUnitId == 3) {
            targetUnit = Volume.volume.Cubic_metres;
        }

        // if selected target unit is cubic inches
        else {
            targetUnit = Volume.volume.Cubic_inches;
        }
        try {
            // if the value will not the valid double constant below line will throw an error
            convertedValue = Volume.convert(Double.parseDouble(currentEditingField.getText().toString()), selectedUnit, targetUnit);
        } catch (Exception e) {
            convertedValue = 0;
        }
        return convertedValue;
    }

    // this method convert the value of quantity mass
    public double convertMass() {
        long selectedSourceUnitId = sourceUnitSpinner.getSelectedItemId();
        long selectedTargetUnitId = targetUnitSpinner.getSelectedItemId();

        double convertedValue;

        Mass.mass selectedUnit;
        Mass.mass targetUnit;


        // if selected source unit is  tons
        if (selectedSourceUnitId == 0) {
            selectedUnit = Mass.mass.Tons;
        }

        // if selected source unit is ponds
        else if (selectedSourceUnitId == 1) {
            selectedUnit = Mass.mass.Pounds;
        }

        // if selected source unit is ounces
        else if (selectedSourceUnitId == 2) {
            selectedUnit = Mass.mass.Ounces;
        }

        // if selected source unit is kilogrammes
        else if (selectedSourceUnitId == 3) {
            selectedUnit = Mass.mass.Kilogrammes;
        }

        // if selected source unit is grams
        else {
            selectedUnit = Mass.mass.Grams;
        }


        // if selected target unit is
        if (selectedTargetUnitId == 0) {
            targetUnit = Mass.mass.Tons;
        }

        // if selected target unit is pounds
        else if (selectedTargetUnitId == 1) {
            targetUnit = Mass.mass.Pounds;
        }

        // if selected target unit is ounces
        else if (selectedTargetUnitId == 2) {
            targetUnit = Mass.mass.Ounces;
        }

        // if selected target unit is kilogrammes
        else if (selectedTargetUnitId == 3) {
            targetUnit = Mass.mass.Kilogrammes;
        }

        // if selected target unit is grams
        else {
            targetUnit = Mass.mass.Grams;
        }
        try {
            // if the value will not the valid double constant below line will throw an error
            convertedValue = Mass.convert(Double.parseDouble(currentEditingField.getText().toString()), selectedUnit, targetUnit);
        } catch (Exception e) {
            convertedValue = 0;
        }
        return convertedValue;
    }

    // this method convert the value of quantity data
    public double convertData() {
        long selectedSourceUnitId = sourceUnitSpinner.getSelectedItemId();
        long selectedTargetUnitId = targetUnitSpinner.getSelectedItemId();

        double convertedValue;

        Data.data selectedUnit;
        Data.data targetUnit;


        // if selected source unit is bits
        if (selectedSourceUnitId == 0) {
            selectedUnit = Data.data.Bits;
        }

        // if selected source unit is bytes
        else if (selectedSourceUnitId == 1) {
            selectedUnit = Data.data.Bytes;
        }

        // if selected source unit is kilobytes
        else if (selectedSourceUnitId == 2) {
            selectedUnit = Data.data.Kilobytes;
        }

        // if selected source unit is megabytes
        else if (selectedSourceUnitId == 3) {
            selectedUnit = Data.data.Megabytes;
        }

        // if selected source unit is gigabytes
        else if (selectedSourceUnitId == 4) {
            selectedUnit = Data.data.Gigabytes;
        }

        // selected source unit is terabytes
        else {
            selectedUnit = Data.data.Terabytes;
        }


        // if selected target unit is bits
        if (selectedTargetUnitId == 0) {
            targetUnit = Data.data.Bits;
        }

        // if selected target unit is bytes
        else if (selectedTargetUnitId == 1) {
            targetUnit = Data.data.Bytes;
        }

        // if selected target unit is kilobytes
        else if (selectedTargetUnitId == 2) {
            targetUnit = Data.data.Kilobytes;
        }

        // if selected target unit is megabytes
        else if (selectedTargetUnitId == 3) {
            targetUnit = Data.data.Megabytes;
        }

        // if selected target unit is gigabytes
        else if (selectedTargetUnitId == 4) {
            targetUnit = Data.data.Gigabytes;
        }

        // if selected target unit is terabytes
        else {
            targetUnit = Data.data.Terabytes;
        }
        try {
            // if value is not a valid double constant it will throw an error
            convertedValue = Data.convert(Double.parseDouble(currentEditingField.getText().toString()), selectedUnit, targetUnit);
        } catch (Exception e) {
            convertedValue = 0;
        }
        return convertedValue;
    }

    // this method will call automatically when ever user select the unit from the drop-down list
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        try {
            // since the unit may me changed by the user so its value in another quantity must me calculated
            // and because the value may be like "." or "-" conversion of these value into number will throw exception
            // so exception handling is important
            convertValue();
        } catch (Exception ignored) {
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}