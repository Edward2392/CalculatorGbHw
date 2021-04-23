package com.example.calculatorgbhw;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText output;
    TextView operation;
    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result =(TextView) findViewById(R.id.result);
        output = (EditText) findViewById(R.id.output);
        operation = (TextView) findViewById(R.id.operation);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        operation.setText(lastOperation);
    }
    public void onNumberClick(View view){

        Button button = (Button)view;
        output.append(button.getText());

        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onOperationClick(View view){

        Button button = (Button)view;
        String op = button.getText().toString();
        String number = output.getText().toString();
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                output.setText("");
            }
        }
        lastOperation = op;
        operation.setText(lastOperation);
    }

    private void performOperation(Double number, String operation){

        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        result.setText(operand.toString().replace('.', ','));
        output.setText("");
    }
}