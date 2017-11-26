package vn.asiantech.internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by jackty on 23/11/2017.
 */

public class CustomSpnPhone extends BaseAdapter {
    private Context mcontext;
    private int mflags[];
    private String[] mcountryNames;
    private LayoutInflater mLifinflter;

    public CustomSpnPhone(Context applicationContext, int[] mflags, String[] mcountryNames) {
        this.mcontext = applicationContext;
        this.mflags = mflags;
        this.mcountryNames = mcountryNames;
        mLifinflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mflags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mLifinflter.inflate(R.layout.item_spn_phonesignup, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        icon.setImageResource(mflags[i]);
        return view;
    }
}
