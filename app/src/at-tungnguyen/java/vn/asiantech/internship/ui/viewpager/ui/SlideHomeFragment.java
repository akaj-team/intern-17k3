package vn.asiantech.internship.ui.viewpager.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.model.Animal;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideHomeFragment extends Fragment {
    private TextView mTvNameEnglish;
    private ImageView mImgAnimal;
    private Animal mAnimal;
    private TextView mTvNameVietName;
    private View mView;

    public SlideHomeFragment() {
        //No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_slide_home, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        mTvNameEnglish = mView.findViewById(R.id.tvEnglish);
        mImgAnimal = mView.findViewById(R.id.imgAnimal);
        mTvNameVietName = mView.findViewById(R.id.tvVietnamese);
    }

    private void initData() {
        mTvNameEnglish.setText(mAnimal.getNameEngLish());
        mImgAnimal.setImageResource(mAnimal.getImgAnimal());
        mTvNameVietName.setText(mAnimal.getNameVietNamese());
    }

    public void getAnimal(Animal animal) {
        mAnimal = animal;
    }
}
