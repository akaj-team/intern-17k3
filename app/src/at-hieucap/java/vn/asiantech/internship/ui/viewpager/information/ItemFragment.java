package vn.asiantech.internship.ui.viewpager.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.CardEnglish;

/**
 * Created by tiboo on 19/12/2017.
 * Create Item StepLoginFragment
 */
public class ItemFragment extends Fragment {
    private int mPosition;

    public static ItemFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("Position", position);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt("Position");
        } else {
            mPosition = 1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_fragment_home, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        TextView tvEnglish = view.findViewById(R.id.tvNameEnglish);
        TextView tvVietnamese = view.findViewById(R.id.tvNameVietnamese);
        ImageView imgCard = view.findViewById(R.id.imgCardEnglish);
        CardEnglish cardEnglish = MenuFragment.mListCardEnglish.get(mPosition);
        tvEnglish.setText(cardEnglish.getNameEnglish());
        imgCard.setImageResource(cardEnglish.getImage());
        tvVietnamese.setText(cardEnglish.getNameVietNam());
    }
}
