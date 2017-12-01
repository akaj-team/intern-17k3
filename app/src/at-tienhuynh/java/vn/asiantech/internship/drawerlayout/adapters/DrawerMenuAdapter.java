package vn.asiantech.internship.drawerlayout.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.drawerlayout.models.DrawerMenu;

/**
 * Created at 2017
 * Created by jackty on 01/12/2017.
 */

public class DrawerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER_TYPE = 0;
    public static final int ITEM_TYPE = 1;
    private List<DrawerMenu> mListDrawerMenus;

    public DrawerMenuAdapter(List<DrawerMenu> mListDrawerMenus) {
        this.mListDrawerMenus = mListDrawerMenus;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_drawer_layout, parent, false);
//                return new HeaderViewHoder(view);
            case ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_menu_drawer, parent, false);
//                return new ItemMenuViewHoder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DrawerMenu drawerMenu = mListDrawerMenus.get(position);
        switch (position) {
            case HEADER_TYPE:
//                    ((HeaderViewHoder)holder).mEmail.setText(drawerMenu.getEmailHeader());
                break;
            case ITEM_TYPE:
//                    ((ItemMenuViewHoder)holder).mImgItemMenu.setImageResource(drawerMenu.getImgMenu());
//                    ((ItemMenuViewHoder)holder).mTvNameMenu.setText(drawerMenu.getNameMenuItem());
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mListDrawerMenus.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER_TYPE;
        }
        return ITEM_TYPE;
    }
}