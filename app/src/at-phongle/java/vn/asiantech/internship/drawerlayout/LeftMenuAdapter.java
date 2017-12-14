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

/**
 * Created by phongle on 11/12/2560.
 * LeftBarAdapter
 */
public class LeftMenuAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int USER = 0;
    private static final int OPTION = 1;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    LeftMenuAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == USER) {
            View view = layoutInflater.inflate(R.layout.item_header_leftmenu, parent, false);
            UserHolder userHolder = new UserHolder(view);
            userHolder.mImgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClickChangeAvatar();
                }
            });
            return new UserHolder(view);
        } else if (viewType == OPTION) {
            View view = layoutInflater.inflate(R.layout.item_option_leftmenu, parent, false);
            final OptionHolder optionHolder = new OptionHolder(view);
            optionHolder.mLlItemMenuLeftBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClickOption(optionHolder.getAdapterPosition());
                }
            });
            return new OptionHolder(view);
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

    private static class UserHolder extends RecyclerView.ViewHolder {
        private ImageView mImgAvatar;
        private TextView mTvEmail;

        UserHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
        }

        private void onBindData(Object object) {
            User user = (User) object;
            mImgAvatar.setImageDrawable(user.getAvatar());
            mTvEmail.setText(user.getMail());
        }
    }

    private static class OptionHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlItemMenuLeftBar;
        private ImageView mImgIconOption;
        private TextView mTvOption;

        OptionHolder(final View itemView) {
            super(itemView);
            mImgIconOption = itemView.findViewById(R.id.imgIconOption);
            mTvOption = itemView.findViewById(R.id.tvOption);
            mLlItemMenuLeftBar = itemView.findViewById(R.id.llItemMenuLeftBar);
        }

        private void onBindData(Object object) {
            Option option = (Option) object;
            mImgIconOption.setImageResource(option.getIcon());
            mTvOption.setText(option.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    // Interface declare listener for item.
    public interface OnItemClickListener {
        void onClickChangeAvatar();

        void onClickOption(int position);
    }
}
