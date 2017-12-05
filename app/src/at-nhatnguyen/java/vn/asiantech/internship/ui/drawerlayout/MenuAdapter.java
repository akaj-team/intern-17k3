package vn.asiantech.internship.ui.drawerlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int USER = 0;
    public static final int OPTION = 1;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    public MenuAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == USER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header, parent, false);
            return new UserHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option, parent, false);
            return new OptionHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == USER) {
            ((UserHolder) holder).onBindData();
        } else if (getItemViewType(position) == OPTION) {
            ((OptionHolder) holder).onBindData();
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    //this method use listen event click in RecyclerView
    public interface OnItemClickListener {
        void onClickItem(int position);

        void onClickImage(View view);
    }

    class OptionHolder extends RecyclerView.ViewHolder {
        private TextView mTvOption;
        private ImageView mImgIcon;

        public OptionHolder(View itemView) {
            super(itemView);
            mTvOption = itemView.findViewById(R.id.tvOption);
            mImgIcon = itemView.findViewById(R.id.imgIcon);
        }

        private void onBindData() {
            Option option = (Option) mObjects.get(getAdapterPosition());
            mTvOption.setText(option.getOptionName());
            mImgIcon.setImageResource(option.getIcon());
            mTvOption.setTextColor(itemView.getResources().getColor(R.color.black));
            mImgIcon.setPressed(false);
            mTvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTvOption.setTextColor(itemView.getResources().getColor(R.color.blue_500));
                    mImgIcon.setPressed(true);
                    mOnItemClickListener.onClickItem(getAdapterPosition());
                }
            });
            mImgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClickItem(getAdapterPosition());
                }
            });
        }
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private ImageView mImgAvatar;
        private TextView mTvMail;

        public UserHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvMail = itemView.findViewById(R.id.tvMail);
        }

        private void onBindData() {
            User user = (User) mObjects.get(getAdapterPosition());
            mImgAvatar.setImageResource(user.getImgAvatar());
            mTvMail.setText(user.getMail());
            mImgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClickImage(itemView);
                }
            });
        }
    }
}
