package vn.asiantech.internship.ui.drawerlayout;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;

/**
 * This class is an adapter used for a recyclerView in drawerActivity
 */
public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int USER = 0;
    private static final int OPTION = 1;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    MenuAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
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
        View view;
        if (viewType == USER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header, parent, false);
            final UserHolder userHolder = new UserHolder(view);
            userHolder.mImgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClickImage();
                }
            });
            return userHolder;
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option, parent, false);
            final OptionHolder optionHolder = new OptionHolder(view);
            optionHolder.mTvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    optionHolder.mTvOption.setTextColor(view.getResources().getColor(R.color.blue_500));
                    optionHolder.mImgIcon.setPressed(true);
                    mOnItemClickListener.onClickItem(optionHolder.getAdapterPosition());
                }
            });
            optionHolder.mImgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            return optionHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == USER) {
            ((UserHolder) holder).onBindData(mObjects.get(position));
        } else if (getItemViewType(position) == OPTION) {
            ((OptionHolder) holder).onBindData(mObjects.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    /**
     * This interface use listen event click in RecyclerView
     */
    public interface OnItemClickListener {
        void onClickItem(int position);

        void onClickImage();
    }

    /**
     * This class ues to display option of recyclerView
     */
    private static class OptionHolder extends RecyclerView.ViewHolder {
        private TextView mTvOption;
        private ImageView mImgIcon;

        private OptionHolder(View itemView) {
            super(itemView);
            mTvOption = itemView.findViewById(R.id.tvOption);
            mImgIcon = itemView.findViewById(R.id.imgIcon);
        }

        private void onBindData(Object object) {
            Option option = (Option) object;
            mTvOption.setText(option.getOptionName());
            mImgIcon.setImageResource(option.getIcon());
            mTvOption.setTextColor(itemView.getResources().getColor(R.color.black));
            mImgIcon.setPressed(false);
        }
    }

    /**
     * This class use to display the header of recyclerView
     */
    private static class UserHolder extends RecyclerView.ViewHolder {
        private ImageView mImgAvatar;
        private TextView mTvMail;

        private UserHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvMail = itemView.findViewById(R.id.tvEmail);
        }

        private void onBindData(Object object) {
            User user = (User) object;
            if (TextUtils.isEmpty(user.getUri())) {
                mImgAvatar.setImageResource(user.getImgAvatar());
            }
            else {
                mImgAvatar.setImageURI(Uri.parse(user.getUri()));
            }
            mTvMail.setText(user.getEmail());
        }
    }
}
