package vn.asiantech.internship.ui.drawerlayout;

import android.net.Uri;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;
import vn.asiantech.internship.models.Person;

/**
 * Class IssueAdapter
 */
public class IssueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    IssueAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
        this.mObjects = objects;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewType.HEADER;
        }
        return ViewType.ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case ViewType.HEADER:
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
            case ViewType.HEADER:
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

    @IntDef({ViewType.HEADER, ViewType.ITEM})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
        int HEADER = 0;
        int ITEM = 1;
    }

    /**
     * Interface OnItemClickListener handle click change avatar and items issue
     */
    public interface OnItemClickListener {
        void onClickItemIssue(int position);

        void onClickImgAvatar();
    }

    /**
     * Holder for header in drawer layout
     */
    private static class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircleImageView mCircleImgAvatar;
        private TextView mTvEmail;
        private OnItemClickListener mOnItemClickListener;

        HeaderHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            mCircleImgAvatar = itemView.findViewById(R.id.circleImgAvatar);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
            mOnItemClickListener = onItemClickListener;
            mCircleImgAvatar.setOnClickListener(this);
        }

        private void onBindData(Object object) {
            Person person = (Person) object;
            if (TextUtils.isEmpty(person.getUri())) {
                mCircleImgAvatar.setImageResource(person.getImage());
            } else {
                mCircleImgAvatar.setImageURI(Uri.parse(person.getUri()));
            }
            mTvEmail.setText(person.getEmail());
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClickImgAvatar();
            }
        }
    }

    /**
     * Holder for item in drawer layout
     */
    private static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImgIssue;
        private TextView mTvNameIssue;
        private OnItemClickListener mOnItemClickListener;

        ItemHolder(final View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            mImgIssue = itemView.findViewById(R.id.imgIssue);
            mTvNameIssue = itemView.findViewById(R.id.tvNameIssue);
            mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        private void onBindData(Object object) {
            Issue issue = (Issue) object;
            mImgIssue.setImageResource(issue.getIcon());
            mTvNameIssue.setText(issue.getName());
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClickItemIssue(getAdapterPosition());
            }
        }
    }
}
