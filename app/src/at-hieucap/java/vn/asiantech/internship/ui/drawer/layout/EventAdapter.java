package vn.asiantech.internship.ui.drawer.layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
}
