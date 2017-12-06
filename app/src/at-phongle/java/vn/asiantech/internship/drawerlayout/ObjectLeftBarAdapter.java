package vn.asiantech.internship.drawerlayout;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;

public class ObjectLeftBarAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int USER = 0;
    private static final int OPTION = 1;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    ObjectLeftBarAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
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
            return new UserHolder(view, mOnItemClickListener);
        } else if (viewType == OPTION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_leftbar, parent, false);
            return new OptionHolder(view, mOnItemClickListener);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == USER) {
            ((UserHolder) holder).onBindData(mObjects.get(position));
        } else if (getItemViewType(position) == OPTION) {
            ((OptionHolder) holder).onBindData(mObjects.get(position));
        }
    }

    static class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImgAvatar;
        private TextView mTvEmail;
        private OnItemClickListener mOnItemClickListener;

        UserHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
            mImgAvatar.setOnClickListener(this);
            mOnItemClickListener = onItemClickListener;
        }

        private void onBindData(Object object) {
            User user = (User) object;
            mImgAvatar.setImageDrawable(user.getAvatar());
            mTvEmail.setText(user.getMail());
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onClickChangeAvatar();
        }
    }

    static class OptionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout mLlItemMenuLeftBar;
        private ImageView mImgIconOption;
        private TextView mTvOption;
        private OnItemClickListener mOnItemClickListener;

        OptionHolder(final View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mImgIconOption = itemView.findViewById(R.id.imgIconOption);
            mTvOption = itemView.findViewById(R.id.tvOption);
            mLlItemMenuLeftBar = itemView.findViewById(R.id.llItemMenuLeftBar);
            mLlItemMenuLeftBar.setOnClickListener(this);
            mOnItemClickListener = onItemClickListener;
        }

        private void onBindData(Object object) {
            Option option = (Option) object;
            mImgIconOption.setImageResource(option.getIcon());
            mTvOption.setText(option.getName());
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onClickOption(getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public interface OnItemClickListener {
        void onClickChangeAvatar();

        void onClickOption(int position);
    }

}