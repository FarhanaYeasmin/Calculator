package com.bank.example.calculator;

        import android.renderscript.Double2;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String ADD = "\u002B";
    public static final String SUB = "\u2212";
    public static final String DIV = "\u00F7";
    public static final String MUL = "\u2715";

    private TextView showResultTV;
    private String input, operator;
    private double firstNum, secondNum;
    private boolean secondValueFirstDigi;
    private boolean resultShown;
    TextView tvOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showResultTV = (TextView) findViewById(R.id.showResult);
        tvOperator = (TextView) findViewById(R.id.tvOperator);
        clearVariables();
    }

    void clearVariables() {
        input = "0";
        operator = "";
        showResultTV.setText(input);
        secondValueFirstDigi = false;
        firstNum = secondNum = 0;
        tvOperator.setText("");
        resultShown = false;
    }

    public void enterOperator(View view) {

        switch (view.getId()) {
            case R.id.buttonPlus:
                operator = ADD;
                break;
            case R.id.buttonMinus:
                operator = SUB;
                break;
            case R.id.buttonMul:
                operator = MUL;
                break;
            case R.id.buttonDiv:
                operator = DIV;
                break;
        }
        tvOperator.setText(operator);
        secondValueFirstDigi = true;
        firstNum = Double.parseDouble(showResultTV.getText().toString());
    }

    public void enterNeg(View view) {
        if ("".equals(input))
            return;
        if (input.startsWith("-"))
            input = input.substring(1);
        else
            input = "-" + input;

        showResultTV.setText(input);
    }

    public void enterEqual(View view) {
        switch (view.getId()) {
            case R.id.buttonEqual:
                if(!resultShown)secondNum = Double.parseDouble(showResultTV.getText().toString());
                double ans = btnResultEqual(operator, firstNum, secondNum);
                input = "" + ans;
                showResultTV.setText(input);
                firstNum = ans;
                tvOperator.setText("");
                break;
        }
        secondValueFirstDigi = false;
        resultShown = true;
    }


    public void enterValue(View view) {
        if (resultShown) {
            input = "0";
            showResultTV.setText(input);
            secondValueFirstDigi = false;
            resultShown = false;
        }

        if (secondValueFirstDigi) {
            input = "0";
            showResultTV.setText("0");
            secondValueFirstDigi = false;
        }

        switch (view.getId()) {
            case R.id.button0:
                if ("0".equals(input) == false)
                    insertNumber(0);
                break;
            case R.id.button1:
                insertNumber(1);
                break;
            case R.id.button2:
                insertNumber(2);
                break;
            case R.id.button3:
                insertNumber(3);
                break;
            case R.id.button4:
                insertNumber(4);
                break;
            case R.id.button5:
                insertNumber(5);
                break;
            case R.id.button6:
                insertNumber(6);
                break;
            case R.id.button7:
                insertNumber(7);
                break;
            case R.id.button8:
                insertNumber(8);
                break;
            case R.id.button9:
                insertNumber(9);
                break;
            case R.id.buttonDot:
                insertDot();
                break;
        }
    }

    public double btnResultEqual(String operator, double operand1, double operand2) {
        if (operator == ADD) return operand1 + operand2;
        if (operator == SUB) return operand1 - operand2;

        if (operator == MUL) return operand1 * operand2;
        if (operator == DIV)
            return operand1 / operand2;
        return 0.0;
    }

    public void insertNumber(int displayNum) {
        if ("0".equals(input) == true) input = "";
        input = input + Integer.toString(displayNum);
        showResultTV.setText(input);
    }

    public void insertDot() {
        if (input.contains(".")) return;
        input = input + ".";
        showResultTV.setText(input);
    }

    public void clearValue(View view) {
        clearVariables();
    }

    public void delValue(View view) {

        if ("0".equals(input) == false) {

            if (input.length() > 1)
                input = input.substring(0, input.length() - 1);
            else
                input = "0";

            showResultTV.setText(input);
        }
    }
}





