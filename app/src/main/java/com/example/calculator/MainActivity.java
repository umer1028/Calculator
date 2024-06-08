package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView solutionTv;
    private TextView resultTv;
    private String currentInput = "";
    private String currentResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solutionTv = findViewById(R.id.solution_tv);
        resultTv = findViewById(R.id.result_tv);

        // Set onClick listeners for all buttons
        int[] buttonIds = {
                R.id.button_C, R.id.button_open_bracket, R.id.button_close_bracket, R.id.button_divide,
                R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_multiply,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_add,
                R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_subtract,
                R.id.button_ac, R.id.button_zero, R.id.button_dot, R.id.button_equal
        };

        for (int id : buttonIds) {
            MaterialButton button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentInput = "";
                currentResult = "";
                break;
            case "ac":
                if (currentInput.length() > 0) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                }
                break;
            case "=":
                currentResult = calculateResult(currentInput);
                break;
            default:
                currentInput += buttonText;
                break;
        }

        solutionTv.setText(currentInput);
        resultTv.setText(currentResult);
    }

    private String calculateResult(String input) {
        try {
            Expression expression = new ExpressionBuilder(input).build();
            double result = expression.evaluate();
            // Format the result
            if (result == (long) result) {
                return String.format("%d", (long) result);
            } else {
                return String.format("%s", result);
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}