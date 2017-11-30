package vn.asiantech.internship.calculation;

import android.widget.TextView;

/**
 * Created at 2017
 * Created by jackty on 27/11/2017.
 */
class Calculation {
    private float result;

    Calculation() {
    }

    void sum(float numA, float numB, TextView tvResult) {
        result = numA + numB;
        tvResult.setText(String.valueOf(result));
    }

    void subTraction(float numA, float numB, TextView tvResult) {
        result = numA - numB;
        tvResult.setText(String.valueOf(result));
    }

    void mulTiplication(float numA, float numB, TextView tvResult) {
        result = numA * numB;
        tvResult.setText(String.valueOf(result));
    }

    void diVision(float numA, float numB, TextView tvResult) {
        result = numA / numB;
        tvResult.setText(String.valueOf(result));
    }
}
