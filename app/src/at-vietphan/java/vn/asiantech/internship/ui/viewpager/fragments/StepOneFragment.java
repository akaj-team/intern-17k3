package vn.asiantech.internship.ui.viewpager.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.DictionaryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepOneFragment extends Fragment implements View.OnClickListener {
    private TextView mTvSkip;

    public StepOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_one, container, false);
        mTvSkip = view.findViewById(R.id.tvSkip);
        mTvSkip.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Skip step");
        builder.setMessage("Do you want to skip this step?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(view.getContext(), DictionaryActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No-op
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
