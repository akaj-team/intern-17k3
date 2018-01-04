package vn.asiantech.internship.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Person;

/**
 * Create Person Adapter
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> mPersonList;
    private OnItemClickListener mOnItemClickListener;

    PersonAdapter(List<Person> personList, OnItemClickListener onItemClickListener) {
        mPersonList = personList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    public interface OnItemClickListener {
        void onLikeClick(int position);

        void onDislikeClick(int position);
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvNamePerson;
        private ImageView mImgLike;
        private ImageView mImgDislike;
        private TextView mTvCountLike;
        private TextView mTvStatus;

        PersonViewHolder(final View itemView) {
            super(itemView);
            initViews();
            initListener();
        }

        private void initViews() {
            mTvNamePerson = itemView.findViewById(R.id.tvNamePerson);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDislike = itemView.findViewById(R.id.imgDisLike);
            mTvCountLike = itemView.findViewById(R.id.tvCountLike);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
        }

        private void initListener() {
            mImgLike.setOnClickListener(this);
            mImgDislike.setOnClickListener(this);
        }

        private void onBindData() {
            Person person = mPersonList.get(getAdapterPosition());
            mTvNamePerson.setText(person.getName());
            mTvCountLike.setText(String.valueOf(person.getCountLike()));
            mTvStatus.setText(person.getStatus());
            displayLikeNumber(person.getCountLike(), itemView.getContext());
        }

        private void displayLikeNumber(int likeNumber, Context context) {
            if (likeNumber > 0) {
                mTvCountLike.setTextColor(context.getResources().getColor(R.color.colorGreen));
                mImgLike.setSelected(true);
                mImgDislike.setSelected(false);
            } else if (likeNumber < 0) {
                mTvCountLike.setTextColor(context.getResources().getColor(R.color.colorRed));
                mImgDislike.setSelected(true);
                mImgLike.setSelected(false);
            } else {
                mTvCountLike.setTextColor(context.getResources().getColor(R.color.black));
                mImgDislike.setSelected(false);
                mImgLike.setSelected(false);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgLike:
                    mOnItemClickListener.onLikeClick(getAdapterPosition());
                    break;
                case R.id.imgDisLike:
                    mOnItemClickListener.onDislikeClick(getAdapterPosition());
            }
        }
    }
}
