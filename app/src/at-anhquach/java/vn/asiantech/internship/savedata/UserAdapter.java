package vn.asiantech.internship.savedata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.User;

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
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.onBindData(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTvUserName;
        TextView mTvAge;

        UserViewHolder(View itemView) {
            super(itemView);
            mTvUserName = itemView.findViewById(R.id.tvUserName);
            mTvAge = itemView.findViewById(R.id.tvUserAge);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemUserClick(getAdapterPosition());
                    }
                }
            });
        }

        private void onBindData(User user) {
            mTvUserName.setText(user.getName());
            mTvAge.setText(String.valueOf(user.getAge()));
        }
    }
    public interface OnItemClickListener{
        void onItemUserClick(int position);
    }
}
