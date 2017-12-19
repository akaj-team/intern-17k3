package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class CardFragment extends Fragment {

    private static final String ARG_ID = "args_resource";
    private int mAnimailId;
    private TextView mTvEnglish;
    private TextView mTvVietNamese;
    private ImageView mImgImage;

    public static CardFragment newInstance(int id) {
        CardFragment cardFragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        cardFragment.setArguments(args);
        return cardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mAnimailId = args.getInt(ARG_ID,-1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        initViews(view);
        setData(mAnimailId);
        return view;
    }

    private void initViews(View view) {
        mTvEnglish = view.findViewById(R.id.tvEnglish);
        mTvVietNamese = view.findViewById(R.id.tvVietNamese);
        mImgImage = view.findViewById(R.id.imgImage);
    }

    private void setData(int mAnimailId) {
        mTvEnglish.setText(HomeFragment.mCardList.get(mAnimailId).getEnglishWord());
        mTvVietNamese.setText(HomeFragment.mCardList.get(mAnimailId).getVietnamWord());
        mImgImage.setImageResource(HomeFragment.mCardList.get(mAnimailId).getImage());
    }
}
