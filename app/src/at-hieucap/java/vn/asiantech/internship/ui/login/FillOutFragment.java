package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import vn.asiantech.internship.R;


/**
 * Created by tiboo on 23/11/2017.
 */

public class FillOutFragment extends Fragment implements View.OnClickListener {
    private ImageView mImgBack;
    private EditText mEdtEmail;
    private CheckBox mChkCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fill_out, container, false);

        // item back
        mImgBack = v.findViewById(R.id.imgBack);
        mImgBack.setOnClickListener(this);
        //

        //checkbos
        mChkCheck = v.findViewById(R.id.chk);
        mChkCheck.setOnClickListener(this);
        //
        mEdtEmail = v.findViewById(R.id.edtEmail);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                mImgBack.setSelected(!mImgBack.isSelected());
                break;
            case R.id.chk:
                mChkCheck.setSelected(!mChkCheck.isSelected());
                String ed_text = mEdtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(ed_text)) {
                    Log.d("vv", "null");
                } else {
                    Log.d("vvv", " " + ed_text);
                }
                break;
        }



    }

}

