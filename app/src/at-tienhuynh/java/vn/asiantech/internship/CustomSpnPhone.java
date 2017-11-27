package vn.asiantech.internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created at 2017
 * Created by jackty on 23/11/2017.
 */
public class CustomSpnPhone extends BaseAdapter {
    private int mFlags[];
    private LayoutInflater mLayoutIfinflter;

    public CustomSpnPhone(Context context, int[] flags) {
        this.mFlags = flags;
        mLayoutIfinflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return mFlags.length;
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
        view = mLayoutIfinflter.inflate(R.layout.item_spn_phonesignup,null);
        ImageView icon = view.findViewById(R.id.imgNext);
        icon.setImageResource(mFlags[i]);
        return view;
    }
}
