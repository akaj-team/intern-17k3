package vn.asiantech.internship.calculation;

import android.widget.TextView;

/**
 * Created at 2017
 * Created by jackty on 27/11/2017.
 */
class Calculation {
    private float mResult;

    Calculation() {
    }

    void sum(float numA, float numB, TextView tvResult) {
        mResult = numA + numB;
        tvResult.setText(String.valueOf(mResult));
    }

    void subTraction(float numA, float numB, TextView tvResult) {
        mResult = numA - numB;
        tvResult.setText(String.valueOf(mResult));
    }

    void mulTiplication(float numA, float numB, TextView tvResult) {
        mResult = numA * numB;
        tvResult.setText(String.valueOf(mResult));
    }

    void diVision(float numA, float numB, TextView tvResult) {
        mResult = numA / numB;
        tvResult.setText(String.valueOf(mResult));
    }
}
