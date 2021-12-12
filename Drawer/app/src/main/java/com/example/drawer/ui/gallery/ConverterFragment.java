package com.example.drawer.ui.gallery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drawer.R;
import com.example.drawer.databinding.FragmentConverterBinding;

import java.util.HashMap;

public class ConverterFragment extends Fragment {

    private FragmentConverterBinding binding;
    Spinner spinnerFrom, spinnerTo;
    EditText numberFrom;
    TextView numberTo;
    Button swicth;

    String[] arraySpinner = new String[] {
            "Kilometre (km)", "Metre (m)", "Decimetre (dm)", "Centimetre (cm)", "Millimetre (mm)"
    };

    HashMap<String, Double> convertFactor = new HashMap<String, Double>();

    HashMap<String, String> unit = new HashMap<String, String>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_converter, container, false);
        addHashMap();


        spinnerFrom = (Spinner) view.findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) view.findViewById(R.id.spinnerTo);

        numberFrom = (EditText) view.findViewById(R.id.numberFrom);
        numberTo = (TextView) view.findViewById(R.id.numberTo);

        swicth = (Button) view.findViewById(R.id.swicth);
        swicth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSwitch(view);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
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
       return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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