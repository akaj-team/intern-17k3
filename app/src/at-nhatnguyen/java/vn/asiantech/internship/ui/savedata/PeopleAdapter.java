package vn.asiantech.internship.ui.savedata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.People;

/**
 * Created by hoangnhat on 06/12/2017.
 * Create PeopleAdapter
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
        peopleViewHolder.mNamePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onClickItem((peopleViewHolder.getAdapterPosition()));
            }
        });
        return peopleViewHolder;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        if (mPeoples.get(position) instanceof People) {
            holder.onBindData(mPeoples.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    /**
     * This interface use to get position when click item
     */
    public interface OnItemClickListener {
        void onClickItem(int position);
    }

    /**
     * This class is used for PeopleAdapter
     */
    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        private TextView mIdPeople;
        private TextView mNamePeople;
        private TextView mAgePeople;

        PeopleViewHolder(View itemView) {
            super(itemView);
            mIdPeople = itemView.findViewById(R.id.tvIDPeople);
            mNamePeople = itemView.findViewById(R.id.tvNamePeople);
            mAgePeople = itemView.findViewById(R.id.tvAgePeople);
        }

        private void onBindData(People people) {
            mIdPeople.setText(String.valueOf(people.getiDPeople()));
            mNamePeople.setText(people.getNamePeople());
            mAgePeople.setText(String.valueOf(people.getAgePeople()));
        }
    }
}
