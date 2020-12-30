package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText screen;
    Double numberOnScreen = 0.0;
    Double previousNumber = 0.0;
    boolean performingMath = false;
    String operand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.screen = (EditText)findViewById(R.id.screen);
    }

    public void number(View v) {
        TextView view = (TextView) v;
        String input = view.getText().toString();

        if (input.equals(".")) {
            if (this.screen.getText().toString().isEmpty()) {
                this.screen.setText("0");
            }

            if (this.screen.getText().toString().contains(".")) {
                input = "";
            }
        }

        if (this.performingMath) {
            this.screen.setText(input);
            this.performingMath = false;
        } else {
            this.screen.setText(this.screen.getText().toString() + input);
        }

        this.numberOnScreen = Double.parseDouble(this.screen.getText().toString());
    }

    public void button(View v) {
        TextView view = (TextView) v;
        String input = view.getText().toString();

        if (!this.screen.getText().toString().isEmpty() && !input.equals("=") && !input.equals("Clear") && !input.equals("+/-")) {
            this.previousNumber = Double.parseDouble(this.screen.getText().toString());

            this.screen.setText(input);

            this.operand = input;
            this.performingMath = true;
        } else if (input.equals("=")) {
            if (this.operand.equals("+")) {
                Double res = this.previousNumber + this.numberOnScreen;
                this.screen.setText(res.toString());
            } else if (this.operand.equals("-")) {
                Double res = this.previousNumber - this.numberOnScreen;
                this.screen.setText(res.toString());
            } else if (this.operand.equals("*")) {
                Double res = this.previousNumber * this.numberOnScreen;
                this.screen.setText(res.toString());
            } else if (this.operand.equals("/")) {
                Double res = this.previousNumber / this.numberOnScreen;
                this.screen.setText(res.toString());
            }
        } else if (input.equals("Clear")) {
            this.screen.setText("");
            this.previousNumber = 0.0;
            this.numberOnScreen = 0.0;
            this.operand = null;
        } else if (input.equals("+/-")) {
            this.numberOnScreen *= -1.0;
            this.screen.setText(this.numberOnScreen.toString());
        }
    }
}