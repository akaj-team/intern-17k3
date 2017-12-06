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
    private final int HEADER_TYPE = 0;
    private final int ITEM_TYPE = 1;
    private List<DrawerMenu> mListDrawerMenu;
    private onItemClickListener mOnItemClickListener;

    public DrawerMenuAdapter(List<DrawerMenu> ListDrawerMenus, onItemClickListener onItemClickListener) {
        mListDrawerMenu = ListDrawerMenus;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_drawer_layout, parent, false);
                HeaderHoder headerHoder = new HeaderHoder(view);
                headerHoder.mImgHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onImgHeaderClick(view);
                    }
                });
                return headerHoder;
            case ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_menu_drawer, parent, false);
                final ItemHoder itemMenuViewHoder = new ItemHoder(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view, itemMenuViewHoder.getAdapterPosition());
                    }
                });
                return itemMenuViewHoder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case HEADER_TYPE:
                ((HeaderHoder) holder).onBindDataHeader(position, mListDrawerMenu);
                break;
            default:
                ((ItemHoder) holder).onBindDataListMenu(position, mListDrawerMenu);
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

    /**
     * Header Hoder
     **/

    static class HeaderHoder extends RecyclerView.ViewHolder {
        private CircleImageView mImgHeader;
        private TextView mTvEmail;

        HeaderHoder(View itemView) {
            super(itemView);
            mImgHeader = itemView.findViewById(R.id.imgHeader);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
        }

        void onBindDataHeader(int position, List<DrawerMenu> drawerMenuList) {
            DrawerMenu drawerMenu = drawerMenuList.get(position);
            mImgHeader.setImageResource(drawerMenu.getNumberImage());
            mTvEmail.setText(drawerMenu.getNameMenu());
        }
    }

    /**
     * Item Hoder
     **/

    static class ItemHoder extends RecyclerView.ViewHolder {
        private ImageView mImgItemMenu;
        private TextView mTvItemMenu;

        ItemHoder(View itemView) {
            super(itemView);
            mImgItemMenu = itemView.findViewById(R.id.imgItemMenu);
            mTvItemMenu = itemView.findViewById(R.id.tvItemMenuDrawer);
        }

        void onBindDataListMenu(int position, List<DrawerMenu> drawerMenuList) {
            DrawerMenu drawerMenu = drawerMenuList.get(position);
            mImgItemMenu.setImageResource(drawerMenu.getNumberImage());
            mTvItemMenu.setText(drawerMenu.getNameMenu());
        }
    }

    /**
     * OnClick Drawer Menu
     **/

    public interface onItemClickListener {
        void onItemClick(View view, int position);

        void onImgHeaderClick(View view);
    }
}
