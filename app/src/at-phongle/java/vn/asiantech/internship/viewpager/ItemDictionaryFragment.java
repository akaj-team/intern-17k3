package vn.asiantech.internship.viewpager;

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

/**
 * Created by phongle on 14/12/2560.
 * cardDictionaryFragment
 */
public class ItemDictionaryFragment extends Fragment {
    private static final String KEY_POSITION = "position";
    private int fragmentPosition;


    static ItemDictionaryFragment init(int position) {
        ItemDictionaryFragment itemDictionaryFragment = new ItemDictionaryFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        itemDictionaryFragment.setArguments(args);
        return itemDictionaryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentPosition = getArguments().getInt(KEY_POSITION);
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
