package vn.asiantech.internship.savedata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.User;

/*
 *Adapter to put data into recyclerView
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mUserList;
    private OnItemClickListener mOnItemClickListener;

    UserAdapter(List<User> userList, OnItemClickListener onItemClickListener) {
        mUserList = userList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        final UserViewHolder userViewHolder = new UserViewHolder(view);
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemUserClick(userViewHolder.getAdapterPosition());
                }
            }
        });
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.onBindData(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    // View Holder make view and bind data
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTvUserName;
        TextView mTvAge;

        UserViewHolder(View itemView) {
            super(itemView);
            mTvUserName = itemView.findViewById(R.id.tvUserName);
            mTvAge = itemView.findViewById(R.id.tvUserAge);
        }

        private void onBindData(User user) {
            mTvUserName.setText(user.getName());
            mTvAge.setText(String.valueOf(user.getAge()));
        }
    }

    // interface to set up event to click on item
    public interface OnItemClickListener {
        void onItemUserClick(int position);
    }
}
