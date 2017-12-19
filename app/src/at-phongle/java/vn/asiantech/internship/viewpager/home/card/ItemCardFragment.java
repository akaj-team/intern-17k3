package vn.asiantech.internship.viewpager.home.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Dictionary;
import vn.asiantech.internship.viewpager.home.HomeFragment;

/**
 * Created by phongle on 14/12/2560.
 * cardDictionaryFragment
 */
public class ItemCardFragment extends Fragment {
    private static final String KEY_POSITION = "position";
    private int fragmentPosition;

    public static ItemCardFragment newInstance(int position) {
        Bundle agrs = new Bundle();
        agrs.putInt(KEY_POSITION, position);
        ItemCardFragment itemCardFragment = new ItemCardFragment();
        itemCardFragment.setArguments(agrs);
        return itemCardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentPosition = getArguments().getInt(KEY_POSITION, -1);
        } else {
            fragmentPosition = 1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_image_dictionary, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        Dictionary dictionary = HomeFragment.mDictionaryList.get(fragmentPosition);
        TextView tvVietNamText = view.findViewById(R.id.tvVietNamText);
        TextView tvEnglishText = view.findViewById(R.id.tvEnglishText);
        ImageView imgDictionary = view.findViewById(R.id.imgDictionary);
        tvVietNamText.setText(dictionary.getVietnamText());
        tvEnglishText.setText(dictionary.getEnglishText());
        imgDictionary.setImageResource(dictionary.getImage());
    }
}
