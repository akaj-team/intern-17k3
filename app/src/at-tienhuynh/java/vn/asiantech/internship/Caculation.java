package vn.asiantech.internship;

import android.widget.TextView;

/**
 * Created at 2017
 * Created by jackty on 27/11/2017.
 */
public class Caculation {
    private float mResult;

    public Caculation() {
    }

    protected Float Sum(float numA, float numB, TextView tvResult) {
        mResult = numA + numB;
        tvResult.setText(String.valueOf(mResult));
        return mResult;
    }

    protected Float Subtraction(float numA, float numB, TextView tvResult) {
        mResult = numA - numB;
        tvResult.setText(String.valueOf(mResult));
        return mResult;
    }

    protected Float Multiplication(float numA, float numB, TextView tvResult) {
        mResult = numA * numB;
        tvResult.setText(String.valueOf(mResult));
        return mResult;
    }

    protected Float Division(float numA, float numB, TextView tvResult) {
        mResult = numA / numB;
        tvResult.setText(String.valueOf(mResult));
        return mResult;
    }


}