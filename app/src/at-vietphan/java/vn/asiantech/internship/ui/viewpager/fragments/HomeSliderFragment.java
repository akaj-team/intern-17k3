package vn.asiantech.internship.ui.viewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Vocabulary;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSliderFragment extends Fragment {
    private Vocabulary mVocabulary;

    public static HomeSliderFragment newInstance() {
        return new HomeSliderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_vocabulary, container, false);
        TextView tvEnglish = view.findViewById(R.id.tvCountryFirst);
        TextView tvVietnamese = view.findViewById(R.id.tvCountrySecond);
        ImageView imgAnimal = view.findViewById(R.id.imgAnimal);
        tvEnglish.setText(mVocabulary.getFirstLanguage());
        tvVietnamese.setText(mVocabulary.getSecondLanguage());
        imgAnimal.setImageResource(mVocabulary.getImageAnimal());
        return view;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        mVocabulary = vocabulary;
    }
}
