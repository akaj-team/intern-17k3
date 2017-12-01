package vn.asiantech.internship.drawerlayout.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.drawerlayout.models.DrawerMenu;

import static vn.asiantech.internship.drawerlayout.models.DrawerMenu.HEADER_TYPE;
import static vn.asiantech.internship.drawerlayout.models.DrawerMenu.ITEM_TYPE;

/**
 * Created at 2017
 * Created by jackty on 01/12/2017.
 */

public class DrawerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
                return new HeaderViewHoder(view);
            case ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_menu_drawer, parent, false);
                return new ItemMenuViewHoder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DrawerMenu drawerMenu = mListDrawerMenus.get(position);
        if (drawerMenu != null) {
            switch (drawerMenu.getType()) {
                case HEADER_TYPE:
                    ((HeaderViewHoder)holder).mEmail.setText(drawerMenu.getEmailHeader());
                    break;
                case ITEM_TYPE:
                    ((ItemMenuViewHoder)holder).mImgItemMenu.setImageResource(drawerMenu.getImgMenu());
                    ((ItemMenuViewHoder)holder).mTvNameMenu.setText(drawerMenu.getNameMenuItem());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mListDrawerMenus.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mListDrawerMenus != null) {
            DrawerMenu drawerMenu = mListDrawerMenus.get(position);
            if (drawerMenu != null) {
                drawerMenu.getType();
            }
        }
        return 0;
    }

    public static class HeaderViewHoder extends RecyclerView.ViewHolder {
        private TextView mEmail;

        HeaderViewHoder(View itemView) {
            super(itemView);
            mEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

    public static class ItemMenuViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImgItemMenu;
        private TextView mTvNameMenu;

        ItemMenuViewHoder(View itemView) {
            super(itemView);
            mImgItemMenu = itemView.findViewById(R.id.imgItemMenu);
            mTvNameMenu = itemView.findViewById(R.id.tvItemMenuDrawer);
        }
    }
}

