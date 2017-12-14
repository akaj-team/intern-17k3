package vn.asiantech.internship.viewpagerandtablelayout.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.utils.InitData;
import vn.asiantech.internship.viewpagerandtablelayout.adapters.HomeSlideAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSlideFragment extends Fragment {
    private View mView;
    private TextView mTvVietNamtext;
    private TextView mTvEngLishtext;
    private ImageView mImgAnimals;
    private int mPositionItem = HomeSlideAdapter.positionItem;

    public HomeSlideFragment() {
        //No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_slide, container, false);
        initViews();
        initDataToItem();
        return mView;
    }

    /**
     * Init Data
     */
    private void initViews() {
        mTvEngLishtext = mView.findViewById(R.id.tvEnglish);
        mTvVietNamtext = mView.findViewById(R.id.tvVietnamese);
        mImgAnimals = mView.findViewById(R.id.imgAnimals);
    }

    /**
     * Init Data To Fragment Item
     */
    private void initDataToItem() {
        mTvEngLishtext.setText(InitData.listDictionary().get(mPositionItem).getEnglishText());
        mTvVietNamtext.setText(InitData.listDictionary().get(mPositionItem).getVietnamText());
        mImgAnimals.setImageResource(InitData.listDictionary().get(mPositionItem).getImage());
    }
}
