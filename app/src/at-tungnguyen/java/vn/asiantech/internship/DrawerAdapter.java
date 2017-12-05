package vn.asiantech.internship;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static vn.asiantech.internship.DrawerEvent.EVENT_CONTENT;
import static vn.asiantech.internship.DrawerEvent.EVENT_HEADER;

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static OnItemClickListener mOnItemClickListener;
    private List<DrawerEvent> mList;


    public DrawerAdapter(List<DrawerEvent> list,OnItemClickListener mOnItemClickListener) {
        this.mList = list;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case EVENT_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                return new HeaderViewHolder(view);
            case EVENT_CONTENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
                return new ContentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            return;
        }
        DrawerEvent object = mList.get(position - 1);
        if (object != null) {
            if (holder instanceof HeaderViewHolder) {
                ((HeaderViewHolder) holder).mHeader.setText(object.getmHeader());
            } else if (holder instanceof ContentViewHolder) {
                ((ContentViewHolder) holder).mContent.setText(object.getmContent());
                ((ContentViewHolder) holder).mImageView.setImageResource(object.getImageResource());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return EVENT_HEADER;
        }
        return EVENT_CONTENT;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeader;
        private CircleImageView mImgHeader;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            mImgHeader = itemView.findViewById(R.id.imgCirle);
            mImgHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onclickHeaderitem(view,getAdapterPosition());
                }
            });
            mHeader = itemView.findViewById(R.id.headerText);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent;
        private ImageView mImageView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.tvItemMenu);
            mImageView = itemView.findViewById(R.id.imgItemMenu);
        }
    }
    public interface OnItemClickListener{
        public void onclickHeaderitem(View view , int position);

        public void onclickMenuitem(View view , int position);
    }
}