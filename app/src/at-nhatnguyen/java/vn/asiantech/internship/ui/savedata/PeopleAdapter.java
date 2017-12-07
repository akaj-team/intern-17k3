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
        holder.onBindData(mPeoples.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    public interface OnItemClickListener {
        void onClickItem(int position);
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        private TextView mIDPeople;
        private TextView mNamePeople;
        private TextView mAgePeople;

        PeopleViewHolder(View itemView) {
            super(itemView);
            mIDPeople = itemView.findViewById(R.id.tvIDPeople);
            mNamePeople = itemView.findViewById(R.id.tvNamePeople);
            mAgePeople = itemView.findViewById(R.id.tvAgePeople);
        }

        private void onBindData(People people) {
            mIDPeople.setText(String.valueOf(people.getiDPeople()));
            mNamePeople.setText(people.getNamePeople());
            mAgePeople.setText(String.valueOf(people.getAgePeople()));
        }
    }
}
