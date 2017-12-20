package vn.asiantech.internship.ui.viewpager.fragment;

import android.os.Bundle;
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
public class HomeItemFragment extends Fragment {
    private Vocabulary mVocabulary;

    public HomeItemFragment() {
        //No-op
    }

    public static HomeItemFragment newInstance() {
        return new HomeItemFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_home_viewpager, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        TextView tvEnglich = view.findViewById(R.id.tvEnglish);
        TextView tvVietNam = view.findViewById(R.id.tvVietNam);
        ImageView imgAnimal = view.findViewById(R.id.imgAnimal);
        tvEnglich.setText(mVocabulary.getEnglishLanguage());
        tvVietNam.setText(mVocabulary.getVietnamese());
        imgAnimal.setImageResource(mVocabulary.getImage());
    }

    public void setMVocabulary(Vocabulary vocabulary) {
        mVocabulary = vocabulary;
    }
}
