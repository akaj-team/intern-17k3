package vn.asiantech.internship.drawer;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.model.DrawerItem;

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_HEADER = 1;
    private static final int ITEM_CONTENT = 2;
    private OnItemClickListener mOnItemClickListener;
    private List<DrawerItem> mDrawerItems = new ArrayList<>();

    DrawerAdapter(OnItemClickListener onItemClickListener, List<DrawerItem> drawerItems) {
        this.mOnItemClickListener = onItemClickListener;
        this.mDrawerItems = drawerItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                return new HeaderViewHolder(view, mOnItemClickListener);
            case ITEM_CONTENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
                return new ContentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = ((HeaderViewHolder) holder);
            headerViewHolder.mTvHeaderTitle.setText(mDrawerItems.get(position).getName());
            if (TextUtils.isEmpty(mDrawerItems.get(position).getImageUri())) {
                headerViewHolder.mImgHeader.setImageResource(mDrawerItems.get(position).getImageResource());
            } else {
                headerViewHolder.mImgHeader.setImageURI(Uri.parse(mDrawerItems.get(position).getImageUri()));
            }
        } else if (holder instanceof ContentViewHolder) {
            ContentViewHolder contentViewHolder = ((ContentViewHolder) holder);
            contentViewHolder.mContent.setText(mDrawerItems.get(position).getName());
            contentViewHolder.mImageView.setImageResource(mDrawerItems.get(position).getImageResource());
        }
    }

    @Override
    public int getItemCount() {
        return mDrawerItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDrawerItems.get(position).getType();
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvHeaderTitle;
        private CircleImageView mImgHeader;

        HeaderViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            mTvHeaderTitle = itemView.findViewById(R.id.tvHeaderTitle);
            mImgHeader = itemView.findViewById(R.id.imgCirle);
            mImgHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onclickHeaderitem(view, getAdapterPosition());
                }
            });
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent;
        private ImageView mImageView;

        ContentViewHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.tvItemMenu);
            mImageView = itemView.findViewById(R.id.imgItemMenu);
            mContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onclickContentitem(view, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        /**
         * This is Javadoc
         **/
        void onclickHeaderitem(View view, int position);

        void onclickContentitem(View view, int position);
    }
}
