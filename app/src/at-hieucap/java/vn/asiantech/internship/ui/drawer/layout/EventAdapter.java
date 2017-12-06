package vn.asiantech.internship.ui.drawer.layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 06/12/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private static final int USER = 0;
    private static final int OPTION = 1;
    private Context mContext;
    private List<Event> mEventList;


    EventAdapter(Context context, List<Event> eventList, OnItemClickListener onItemClickListener) {
        mContext = context;
        mEventList = eventList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEventList.get(position) instanceof Event) {
            return USER;
        } else {
            return OPTION;
        }
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == USER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header_left_bar, parent, false);
            return new UserHolder(view, mOnItemClickListener);
        } else if (viewType == OPTION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option_left_bar, parent, false);
            return new OptionHolder(view, mOnItemClickListener);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }


    public interface OnItemClickListener {
        void onOfflineClick(int position);
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgItemOption;
        private TextView mTvItemOption;

        public EventViewHolder(View itemView) {
            super(itemView);
            mImgItemOption = itemView.findViewById(R.id.imgItemOption);
            mTvItemOption = itemView.findViewById(R.id.tvItemOption);
        }

        private void onBindData() {
            Event event = mEventList.get(getAdapterPosition());
            mTvItemOption.setText(event.getName());
            mImgItemOption.setImageResource(event.getIcon());
        }
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImgAvatar;
        private TextView mTvEmail;
        private OnItemClickListener mOnItemClickListener;
        UserHolder(View itemView, Object p1) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class OptionHolder extends EventViewHolder {
        public OptionHolder(View view, Object p1) {
            super();
        }
    }
}
