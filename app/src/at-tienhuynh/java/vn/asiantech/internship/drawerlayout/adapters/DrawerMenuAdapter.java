package vn.asiantech.internship.drawerlayout.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.drawerlayout.models.DrawerMenu;

/**
 * Created at 2017
 * Created by jackty on 01/12/2017.
 */

public class DrawerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_TYPE = 0;
    private static final int ITEM_TYPE = 1;
    public static List<DrawerMenu> mListDrawerMenu;

    public DrawerMenuAdapter(List<DrawerMenu> mListDrawerMenus) {
        this.mListDrawerMenu = mListDrawerMenus;
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
        switch (position) {
            case HEADER_TYPE:
                ((HeaderViewHoder) holder).onBindDataHeader(position);
                break;
            default:
                ((ItemMenuViewHoder) holder).onBindDataListMenu(position);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mListDrawerMenu.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        }
        return ITEM_TYPE;

    }

    public static class HeaderViewHoder extends RecyclerView.ViewHolder {
        private CircleImageView mImgHeader;
        private TextView mTvEmail;

        HeaderViewHoder(View itemView) {
            super(itemView);
            mImgHeader = itemView.findViewById(R.id.imgHeader);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
        }

        void onBindDataHeader(int position) {
            DrawerMenu drawerMenu = mListDrawerMenu.get(position);
            mImgHeader.setImageResource(drawerMenu.getNumberImage());
            mTvEmail.setText(drawerMenu.getNameMenu());
        }

    }

    public static class ItemMenuViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImgItemMenu;
        private TextView mYvItemMenu;

        ItemMenuViewHoder(View itemView) {
            super(itemView);
            mImgItemMenu = itemView.findViewById(R.id.imgItemMenu);
            mYvItemMenu = itemView.findViewById(R.id.tvItemMenuDrawer);
        }

        void onBindDataListMenu(int position) {
            DrawerMenu drawerMenu = mListDrawerMenu.get(position);
            mImgItemMenu.setImageResource(drawerMenu.getNumberImage());
            mYvItemMenu.setText(drawerMenu.getNameMenu());
        }


    }

}
