package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class CardFragment extends Fragment {

    private static final String ARG_ID = "args_resource";

    public static CardFragment newInstance(int id) {
        CardFragment cardFragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        cardFragment.setArguments(args);
        return cardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int mIdFragment = getArguments().getInt(ARG_ID);
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        TextView tvEnglish = view.findViewById(R.id.tvEnglish);
        TextView tvVietNamese = view.findViewById(R.id.tvVietNamese);
        ImageView imgImage = view.findViewById(R.id.imgImage);
        tvEnglish.setText(HomeFragment.mCardList.get(mIdFragment).getEnglishWord());
        tvVietNamese.setText(HomeFragment.mCardList.get(mIdFragment).getVietnamWord());
        imgImage.setImageResource(HomeFragment.mCardList.get(mIdFragment).getImage());
        return view;
    }
}
