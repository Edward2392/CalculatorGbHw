package com.example.calculatorgbhw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.calculatorgbhw.SettingActivity.KEY;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText output;
    TextView operation;
    Double operand = null;
    String lastOperation = "=";

    private void setTheme() {
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(KEY, false)) {
            this.setTheme(R.style.Theme_CalculatorGBHW);
        } else {
            this.setTheme(R.style.Theme_CalculatorNEW);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        output = (EditText) findViewById(R.id.output);
        operation = (TextView) findViewById(R.id.operation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        operation.setText(lastOperation);
    }

    public void onNumberClick(View view) {

        Button button = (Button) view;
        output.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = output.getText().toString();
        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                output.setText("");
            }
        }
        lastOperation = op;
        operation.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {

        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        result.setText(operand.toString().replace('.', ','));
        output.setText("");
    }


    public void onSwitch(View view) {
        Button btnSet = findViewById(R.id.btnSetting);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runSetting = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(runSetting);
            }
        });
    }


}