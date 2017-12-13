package vn.asiantech.internship.ui.savedata.ex3.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex3.model.User;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> mUserArrayLists;
    Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public UserAdapter(ArrayList<User> UserArrayLists, Context context, OnItemClickListener OnItemClick) {
        this.mUserArrayLists = UserArrayLists;
        this.mContext = context;
        mOnItemClickListener = OnItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_user, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClickListener(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindData(mUserArrayLists);
    }

    @Override
    public int getItemCount() {
        return mUserArrayLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private TextView mTvAge;
        private TextView mTvID;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvID = itemView.findViewById(R.id.tvID);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvAge = itemView.findViewById(R.id.tvAge);

        }

        private void onBindData(List<User> userList) {
            User users = userList.get(getAdapterPosition());
            if (users != null) {
                mTvID.setText(String.valueOf(users.getId()));
                mTvName.setText(users.getName());
                mTvAge.setText(String.valueOf(users.getAge()));
            }
        }
    }

    /**
     * This is Javadoc, interface OnItemClickListener
     */
    public interface OnItemClickListener {
        void onItemClickListener(int potion);
    }

}