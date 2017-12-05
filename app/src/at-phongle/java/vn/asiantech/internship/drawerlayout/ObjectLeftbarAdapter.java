package vn.asiantech.internship.drawerlayout;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;

public class ObjectLeftbarAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int USER = 0;
    public static final int OPTION = 1;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    ObjectLeftbarAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
        mObjects = objects;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mObjects.get(position) instanceof User) {
            return USER;
        } else {
            return OPTION;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == USER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header_leftbar, parent, false);
            return new UserHolder(view);
        } else if (viewType == OPTION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_leftbar, parent, false);
            return new OptionHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == USER) {
            ((UserHolder) holder).onBindData();
        } else if (getItemViewType(position) == OPTION) {
            ((OptionHolder) holder).onBindData();
        }
    }
    class UserHolder extends RecyclerView.ViewHolder{
        private ImageView mImgAvatar;
        private TextView mTvEmail;

        UserHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvEmail = itemView.findViewById(R.id.tvEmail);

            mImgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        private  void onBindData(){
            User user = (User)mObjects.get(getAdapterPosition());
            mImgAvatar.setImageResource(user.getAvatar());
            mTvEmail.setText(user.getMail());
        }
    }
    class OptionHolder extends RecyclerView.ViewHolder{
            private ImageView mImgIconOption;
            private TextView mTvOption;

            OptionHolder(View itemView) {
                super(itemView);
                mImgIconOption = itemView.findViewById(R.id.imgIconOption);
                mTvOption = itemView.findViewById(R.id.tvOption);

            }
        private  void onBindData(){
            Option option = (Option)mObjects.get(getAdapterPosition());
            mImgIconOption.setImageResource(option.getIcon());
            mTvOption.setText(option.getName());
        }
    }
    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public interface OnItemClickListener {
//        void onOfflineClick(int position);
    }
}