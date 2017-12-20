package vn.asiantech.internship.savedata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.People;

/**
 * Create People Adapter
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    private List<People> mPeoples;
    private OnItemClickListener mOnItemClickListener;

    PeopleAdapter(List<People> peoples, OnItemClickListener onItemClickListener) {
        mPeoples = peoples;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        final PeopleViewHolder peopleViewHolder = new PeopleViewHolder(view);
        peopleViewHolder.mTvNamePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onClickItem(peopleViewHolder.getAdapterPosition());
            }
        });
        return peopleViewHolder;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        if (mPeoples.get(position) != null) {
            holder.onBindData(mPeoples.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    /**
     * Create interface On Item Click Listener
     */
    public interface OnItemClickListener {
        void onClickItem(int position);
    }

    /**
     * Create People View Holder
     */
    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvIdPeople;
        private TextView mTvNamePeople;
        private TextView mTvSlogan;

        PeopleViewHolder(View itemView) {
            super(itemView);
            mTvIdPeople = itemView.findViewById(R.id.tvIDPeople);
            mTvNamePeople = itemView.findViewById(R.id.tvNamePeople);
            mTvSlogan = itemView.findViewById(R.id.tvSlogan);
        }

        private void onBindData(People people) {
            mTvIdPeople.setText(String.valueOf(people.getIdPeople()));
            mTvNamePeople.setText(people.getNamePeople());
            mTvSlogan.setText(String.valueOf(people.getAgePeople()));
        }
    }
}
