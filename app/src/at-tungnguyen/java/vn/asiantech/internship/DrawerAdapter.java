package vn.asiantech.internship;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static vn.asiantech.internship.DrawerEvent.EVENT_CONTENT;
import static vn.asiantech.internship.DrawerEvent.EVENT_HEADER;

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DrawerEvent> mList;

    public DrawerAdapter(List<DrawerEvent> list) {
        this.mList = list;
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
        DrawerEvent object = mList.get(position);
        if (object != null) {
            switch (position) {
                case 0:
                    ((HeaderViewHolder) holder).mHeader.setText(object.getmHeader());
                    break;
                case 1:
                    ((ContentViewHolder) holder).mContent.setText(object.getmContent());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            DrawerEvent object = mList.get(position);
            if (object != null) {
                return object.getmType();
            }
        }
        return 0;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mHeader =  itemView.findViewById(R.id.headerText);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.tvdrawer);
        }
    }
}