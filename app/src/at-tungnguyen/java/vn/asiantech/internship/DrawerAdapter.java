package vn.asiantech.internship;

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

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<DrawerItem> mList = new ArrayList<>();

    DrawerAdapter(OnItemClickListener mOnItemClickListener, List<DrawerItem> mList) {
        this.mOnItemClickListener = mOnItemClickListener;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                return new HeaderViewHolder(view, mOnItemClickListener);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
                return new ContentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = ((HeaderViewHolder) holder);
            headerViewHolder.tvHeaderTitle.setText(mList.get(position).getName());
            if (TextUtils.isEmpty(mList.get(position).getImageuri())) {
                headerViewHolder.mImgHeader.setImageResource(mList.get(position).getImageresource());
            } else {
                headerViewHolder.mImgHeader.setImageURI(Uri.parse(mList.get(position).getImageuri()));
            }
        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).mContent.setText(mList.get(position).getName());
            ((ContentViewHolder) holder).mImageView.setImageResource(mList.get(position).getImageresource());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHeaderTitle;
        private CircleImageView mImgHeader;

         HeaderViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            mImgHeader = itemView.findViewById(R.id.imgCirle);
            mImgHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onclickHeaderitem(view, getAdapterPosition());
                }
            });
            tvHeaderTitle = itemView.findViewById(R.id.headerText);
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent;
        private ImageView mImageView;

         ContentViewHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.tvItemMenu);
            mImageView = itemView.findViewById(R.id.imgItemMenu);
        }
    }

    public interface OnItemClickListener {
         void onclickHeaderitem(View view, int position);
        /**
         This is Javadoc
         **/
    }
}
