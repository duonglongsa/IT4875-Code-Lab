package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView row1, row2;
    private double num1, num2;
    private String currentOperator = "";
    private boolean isEditingRow2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
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
        Toast.makeText(getApplicationContext(), "Not supported yet!", Toast.LENGTH_LONG).show();
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