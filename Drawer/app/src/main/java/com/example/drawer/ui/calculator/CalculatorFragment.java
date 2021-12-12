package com.example.drawer.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.drawer.R;
import com.example.drawer.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {

    private FragmentCalculatorBinding binding;
    private TextView row1, row2;
    private double num1, num2;
    private String currentOperator = "";
    private boolean isEditingRow2 = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        row1 = view.findViewById(R.id.row1);
        row2 = view.findViewById(R.id.row2);
        initButton(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_1:
                    oneBtnOnClick(view);
                    break;
                case R.id.button_2:
                    twoBtnOnClick(view);
                    break;
                case R.id.button_3:
                    threeBtnOnClick(view);
                    break;
                case R.id.button_4:
                    fourBtnOnClick(view);
                    break;
                case R.id.button_5:
                    fiveBtnOnClick(view);
                    break;
                case R.id.button_6:
                    sixBtnOnClick(view);
                    break;
                case R.id.button_7:
                    sevenBtnOnClick(view);
                    break;
                case R.id.button_8:
                    eightBtnOnClick(view);
                    break;
                case R.id.button_9:
                    nineBtnOnClick(view);
                    break;
                case R.id.button_delete:
                    deleteBtnOnClick(view);
                    break;
                case R.id.button_plus:
                    plusBtnOnClick(view);
                    break;
                case R.id.button_minus:
                    minusBtnOnClick(view);
                    break;
                case R.id.button_multi:
                    multiBtnOnClick(view);
                    break;
                case R.id.button_divide:
                    divideBtnOnClick(view);
                    break;
                case R.id.button_equal:
                    equalBtnOnClick(view);
                    break;
                case R.id.button_CE:
                    onCEBtnOnClick(view);
                    break;
                case R.id.button_dot:
                    dotBtnOnClick(view);
                    break;
                case R.id.button_C:
                    resetBtnOnClick(view);
                    break;
                case R.id.button_0:
                    zeroBtnOnClick(view);
                    break;
                case R.id.button_negative:
                    onNegativeClick(view);
                    break;
                default:
                    comingSoon(view);
                    break;
            }
        }
    };

    public void initButton(View view){
        view.findViewById(R.id.button_0).setOnClickListener(mListener);
        view.findViewById(R.id.button_1).setOnClickListener(mListener);
        view.findViewById(R.id.button_2).setOnClickListener(mListener);
        view.findViewById(R.id.button_3).setOnClickListener(mListener);
        view.findViewById(R.id.button_4).setOnClickListener(mListener);
        view.findViewById(R.id.button_5).setOnClickListener(mListener);
        view.findViewById(R.id.button_6).setOnClickListener(mListener);
        view.findViewById(R.id.button_7).setOnClickListener(mListener);
        view.findViewById(R.id.button_8).setOnClickListener(mListener);
        view.findViewById(R.id.button_9).setOnClickListener(mListener);
        view.findViewById(R.id.button_minus).setOnClickListener(mListener);
        view.findViewById(R.id.button_plus).setOnClickListener(mListener);
        view.findViewById(R.id.button_divide).setOnClickListener(mListener);
        view.findViewById(R.id.button_multi).setOnClickListener(mListener);
        view.findViewById(R.id.button_dot).setOnClickListener(mListener);
        view.findViewById(R.id.button_equal).setOnClickListener(mListener);
        view.findViewById(R.id.button_negative).setOnClickListener(mListener);
        view.findViewById(R.id.button_delete).setOnClickListener(mListener);
        view.findViewById(R.id.button_C).setOnClickListener(mListener);
        view.findViewById(R.id.button_CE).setOnClickListener(mListener);
        view.findViewById(R.id.button_sqrt).setOnClickListener(mListener);
        view.findViewById(R.id.button_square).setOnClickListener(mListener);
        view.findViewById(R.id.button_percent).setOnClickListener(mListener);
        view.findViewById(R.id.button_frag).setOnClickListener(mListener);
    }

    private  void onOperatorClick(String operator){
        if (isEditingRow2 && !row1.getText().toString().isEmpty()) {
            num2 = Double.parseDouble(row2.getText().toString());
            double result = calculate(num1, num2);
            row1.setText(result + " " +  operator);
            row2.setText(String.valueOf(result));
            num1 = result;
            currentOperator = operator;
        } else {
            currentOperator = operator;
            row1.setText(row2.getText().toString());
            row1.append(" " + operator);
            num1 = Double.parseDouble(row2.getText().toString());
        }
        isEditingRow2 = false;
    }

    private void setRow2(String value){
        if(row2.getText().toString().length() == 1 && row2.getText().toString().equals("0")){
            row2.setText(value);
            isEditingRow2 = true;
        }
        else if (isEditingRow2){
            row2.append(value);
        } else{
            row2.setText(value);
            isEditingRow2 = true;
        }
    }

    double calculate(double x, double y){
        double result = 0;
        switch (currentOperator){
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
            case "x":
                result = x * y;
                break;
            case "/":
                result = x / y;
                break;
        }
        return result;
    }

    public void deleteBtnOnClick(View view){
        String currentText = row2.getText().toString();
        if(currentText.length() == 1) {
            row2.setText("0");
        } else
            row2.setText(currentText.substring(0, currentText.length() - 1));
    }
    public void plusBtnOnClick(View view){
        onOperatorClick("+");
    }
    public void minusBtnOnClick(View view){
        onOperatorClick("-");
    }
    public void multiBtnOnClick(View view){
        onOperatorClick("x");
    }
    public void divideBtnOnClick(View view){
        onOperatorClick("/");
    }
    public void dotBtnOnClick(View view){
        setRow2(".");
    }
    public void equalBtnOnClick(View view){
        num2 = Double.parseDouble(row2.getText().toString());
        double result = calculate(num1, num2);
        num1 = result;
        row1.append(" " + row2.getText().toString() + " =");
        row2.setText(String.valueOf(result));
        isEditingRow2 = false;
    }
    public void onNegativeClick(View view){
        String currentRow = row2.getText().toString();
        if(currentRow.charAt(0) == '-'){
            row2.setText(currentRow.replace("-",""));
        } else {
            row2.setText('-' + currentRow);
        }
    }
    public void resetBtnOnClick(View view){
        row2.setText("0");
        row1.setText("");
        currentOperator = "";
    }
    public void onCEBtnOnClick(View view){
        row2.setText("0");
    }
    public void comingSoon(View view){
        Toast.makeText(getActivity().getApplicationContext(), "Not supported yet!", Toast.LENGTH_LONG).show();
    }
    public void zeroBtnOnClick(View view){
        setRow2("0");
    }
    public void oneBtnOnClick(View view){
        setRow2("1");
    }
    public void twoBtnOnClick(View view){
        setRow2("2");
    }
    public void threeBtnOnClick(View view){
        setRow2("3");
    }
    public void fourBtnOnClick(View view){
        setRow2("4");
    }
    public void fiveBtnOnClick(View view){
        setRow2("5");
    }
    public void sixBtnOnClick(View view){
        setRow2("6");
    }
    public void sevenBtnOnClick(View view){
        setRow2("7");
    }
    public void eightBtnOnClick(View view){
        setRow2("8");
    }
    public void nineBtnOnClick(View view){
        setRow2("9");
    }
}