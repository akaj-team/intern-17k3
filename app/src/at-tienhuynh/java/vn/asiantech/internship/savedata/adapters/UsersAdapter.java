package vn.asiantech.internship.savedata.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.savedata.models.Users;

/**
 * Created at 2017
 * Created by jackty on 06/12/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHoder> {
    private List<Users> mUsersList;
    private onItemClick mOnItemClick;

    public UsersAdapter(List<Users> usersList, onItemClick onItemClick) {
        mUsersList = usersList;
        mOnItemClick = onItemClick;
    }

    @Override
    public UsersHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_user, parent, false);
        final UsersHoder usersHoder = new UsersHoder(view);
        usersHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClick.onItemClickListener(usersHoder.getAdapterPosition());
            }
        });
        return usersHoder;
    }

    @Override
    public void onBindViewHolder(UsersHoder holder, int position) {
        holder.onBindData(mUsersList);
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    static class UsersHoder extends RecyclerView.ViewHolder {
        private TextView mTvIdUser;
        private TextView mTvNameUser;
        private TextView mTvAgeUser;


        UsersHoder(View itemView) {
            super(itemView);
            mTvIdUser = itemView.findViewById(R.id.tvIdUser);
            mTvNameUser = itemView.findViewById(R.id.tvNameUser);
            mTvAgeUser = itemView.findViewById(R.id.tvAgeUser);
        }

        void onBindData(List<Users> usersList) {
            Users users = usersList.get(getAdapterPosition());
            mTvIdUser.setText(String.valueOf(users.getId()));
            mTvNameUser.setText(users.getName());
            mTvAgeUser.setText(String.valueOf(users.getAge()));
        }
    }

    /**
     * On Item RecyclerView Click
     */
    public interface onItemClick {
        void onItemClickListener(int potion);
    }

}