package vn.asiantech.internship.ui.drawerlayout;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.drawerlayout.Option;
import vn.asiantech.internship.models.drawerlayout.User;

public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int USER = 0;
    private static final int OPTION = 1;
    private List<Object> mInformation;
    private OnItemClickListener mOnItemClickListener;

    InformationAdapter(List<Object> information, OnItemClickListener onItemClickListener) {
        mInformation = information;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mInformation.get(position) instanceof User) {
            return USER;
        } else {
            return OPTION;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == USER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header_left_bar, parent, false);
            return new UserHolder(view);
        } else if (viewType == OPTION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option_left_bar, parent, false);
            return new OptionViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == USER) {
            ((UserHolder) holder).onBindData(mInformation.get(position));
        } else if (getItemViewType(position) == OPTION) {
            ((OptionViewHolder) holder).onBindData(mInformation.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mInformation.size();
    }

    public interface OnItemClickListener {
        void onClickChangeAvatar();

        void onClickOption(int position);
    }

    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImgAvatar;
        private TextView mTvEmail;

        UserHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
            mImgAvatar.setOnClickListener(this);
        }

        private void onBindData(Object information) {
            User user = (User) information;
            mImgAvatar.setImageDrawable(user.getAvatar());
            mTvEmail.setText(user.getEmail());
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onClickChangeAvatar();
        }
    }

    class OptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout mLlItemMenuLeftBar;
        private ImageView mImgIconOption;
        private TextView mTvOption;

        OptionViewHolder(final View itemView) {
            super(itemView);
            mImgIconOption = itemView.findViewById(R.id.imgItemOption);
            mTvOption = itemView.findViewById(R.id.tvItemOption);
            mLlItemMenuLeftBar = itemView.findViewById(R.id.llItemMenuLeftBar);
            mLlItemMenuLeftBar.setOnClickListener(this);
        }

        private void onBindData(Object information) {
            Option option = (Option) information;
            mImgIconOption.setImageResource(option.getIcon());
            mTvOption.setText(option.getName());
            if (option.isClicked()) {
                mTvOption.setTextColor(Color.BLUE);
            } else {
                mTvOption.setTextColor(Color.BLACK);
            }
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onClickOption(getAdapterPosition());
        }
    }
}
