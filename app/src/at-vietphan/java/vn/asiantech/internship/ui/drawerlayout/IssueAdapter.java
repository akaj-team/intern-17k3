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

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;
import vn.asiantech.internship.models.User;

/**
 * Class IssueAdapter
 */
public class IssueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    IssueAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
        this.mObjects = objects;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        }
        return ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case HEADER:
                view = layoutInflater.inflate(R.layout.row_header, parent, false);
                return new HeaderHolder(view, mOnItemClickListener);
            default:
                view = layoutInflater.inflate(R.layout.row_item, parent, false);
                return new ItemHolder(view, mOnItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.onBindData(mObjects.get(position));
                break;
            default:
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.onBindData(mObjects.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    /**
     * Interface onItemClickListener handle click change avatar and items issue
     */
    public interface OnItemClickListener {
        void onClickItemIssue(int position);

        void onClickImgAvatar();
    }

    /**
     * Holder for header in drawer layout
     */
    private static class HeaderHolder extends RecyclerView.ViewHolder {
        private CircleImageView mCircleImgAvatar;
        private TextView mTvEmail;
        private OnItemClickListener mOnItemClickListener;

        HeaderHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            mCircleImgAvatar = itemView.findViewById(R.id.circleImgAvater);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
            mOnItemClickListener = onItemClickListener;
            mCircleImgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickImgAvatar();
                    }
                }
            });
        }

        private void onBindData(Object object) {
            User user = (User) object;
            if (TextUtils.isEmpty(user.getUri())) {
                mCircleImgAvatar.setImageResource(user.getImg());
            } else {
                mCircleImgAvatar.setImageURI(Uri.parse(user.getUri()));
            }
            mTvEmail.setText(user.getEmail());
        }
    }

    /**
     * Holder for item in drawer layout
     */
    private static class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView mImgIssue;
        private TextView mTvNameIssue;
        private OnItemClickListener mOnItemClickListener;

        ItemHolder(final View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            mImgIssue = itemView.findViewById(R.id.imgIssue);
            mTvNameIssue = itemView.findViewById(R.id.tvNameIssue);
            mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickItemIssue(getAdapterPosition());
                    }
                }
            });
        }

        private void onBindData(Object object) {
            Issue issue = (Issue) object;
            mImgIssue.setImageResource(issue.getIcon());
            mTvNameIssue.setText(issue.getName());
        }
    }
}
