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

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSlideFragment extends Fragment {
    private static final String ARG_ID = "args_resource";
    private View mView;
    private TextView mTvVietNamtext;
    private TextView mTvEngLishtext;
    private ImageView mImgAnimals;

    public HomeSlideFragment() {
        //No-op
    }

    public static HomeSlideFragment newInstance(int id) {
        HomeSlideFragment homeSlideFragment = new HomeSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        homeSlideFragment.setArguments(args);
        return homeSlideFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int idFragment = getArguments().getInt(ARG_ID);
        mView = inflater.inflate(R.layout.fragment_home_slide, container, false);
        initViews();
        initDataToItem(idFragment);
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
    private void initDataToItem(int positionItem) {
        mTvEngLishtext.setText(InitData.listDictionary().get(positionItem).getEnglishText());
        mTvVietNamtext.setText(InitData.listDictionary().get(positionItem).getVietnamText());
        mImgAnimals.setImageResource(InitData.listDictionary().get(positionItem).getImage());
    }
}
