package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerFrom, spinnerTo;
    EditText numberFrom;
    TextView numberTo;
    Button swicth;

    String[] arraySpinner = new String[] {
            "Kilometre (km)", "Metre (m)", "Decimetre (dm)", "Centimetre (cm)", "Millimetre (mm)"
    };

    HashMap<String, Double> convertFactor = new HashMap<String, Double>();

    HashMap<String, String> unit = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHashMap();


        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);

        numberFrom = (EditText) findViewById(R.id.numberFrom);
        numberTo = (TextView) findViewById(R.id.numberTo);

        swicth = (Button) findViewById(R.id.swicth);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        numberFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculate();
            }
        });

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    private  void calculate(){
        Double fromFactor = convertFactor.get(spinnerFrom.getSelectedItem().toString());
        Double toFactor = convertFactor.get(spinnerTo.getSelectedItem().toString());
        String toUnit = unit.get(spinnerTo.getSelectedItem().toString());
        try {
            Double input = Double.parseDouble(numberFrom.getText().toString());
            Double output = input * fromFactor / toFactor;
            numberTo.setText(output.toString() + toUnit);
        } catch (Exception e) {
            numberTo.setText("--");
        }
    }

    private void addHashMap(){
        convertFactor.put("Kilometre (km)", 1000.0);
        convertFactor.put("Metre (m)", 1.0);
        convertFactor.put("Decimetre (dm)", 0.1);
        convertFactor.put("Centimetre (cm)", 0.01);
        convertFactor.put("Millimetre (mm)", 0.001);

        unit.put("Kilometre (km)", " km");
        unit.put("Metre (m)", " m");
        unit.put("Decimetre (dm)", " dm");
        unit.put("Centimetre (cm)", " cm");
        unit.put("Millimetre (mm)", " mm");
    }


    public  void onSwitch(View view){
        int fromPosition = spinnerFrom.getSelectedItemPosition();
        int toPosition = spinnerTo.getSelectedItemPosition();

        spinnerFrom.setSelection(toPosition);
        spinnerTo.setSelection(fromPosition);
        calculate();
    }
}