package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Created by phanhiep on 23/11/2017.
 */

public class InfoFragment extends Fragment {
    private EditText edtMobile, edtName, edtEmail;
    private CheckBox chkBox;
    private ImageView imgNext;
    private boolean checked;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        edtMobile = view.findViewById(R.id.txtMobile);
        edtName = view.findViewById(R.id.txtName);
        edtEmail = view.findViewById(R.id.txtEmail);
        chkBox = view.findViewById(R.id.chk);
        imgNext = view.findViewById(R.id.imgNext);
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edtMobile.getText().toString().equals("")) || (edtName.getText().toString().equals("")) || (edtEmail.getText().toString().equals(""))) {
                    Toast.makeText(getContext(),"Chua dien",Toast.LENGTH_SHORT).show();
                }else{
                    if(chkBox.isChecked()){
                        imgNext.setImageResource(R.drawable.ic_enable);
                    }else{
                        imgNext.setImageResource(R.drawable.ic_disable);
                    }
                }


//                if ((edtMobile.getText().toString() == "") || (edtName.getText().toString() == "") || (edtEmail.getText().toString() == "")) {
//                    imgNext.setImageResource(R.drawable.ic_disable);
//                } else {
//                    imgNext.setImageResource(R.drawable.ic_enable);
//                }
            }
        });

        return view;
    }
}
