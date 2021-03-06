package vn.asiantech.internship.ui.savedata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.User;

/**
 * CompanyAdapter
 */
public class SQLiteAdapter extends RecyclerView.Adapter<SQLiteAdapter.UserViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<User> mUserList;

    SQLiteAdapter(List<User> userList, OnItemClickListener onItemClickListener) {
        this.mUserList = userList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        final UserViewHolder userViewHolder = new UserViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
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

    /**
     * interface OnItemClickListener for click item in recyclerview
     */
    public interface OnItemClickListener {
        void onItemUserClick(int position);
    }

    /**
     * Class UserViewHolder: holder for item in recyclerview
     */
    static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private TextView mTvAge;

        UserViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mTvName = itemView.findViewById(R.id.tvName);
            mTvAge = itemView.findViewById(R.id.tvAge);
        }

        private void onBindData(User user) {
            if (!user.equals(null)) {
                mTvName.setText(user.getName());
                mTvAge.setText(String.valueOf(user.getAge()));
            }
        }
    }
}
