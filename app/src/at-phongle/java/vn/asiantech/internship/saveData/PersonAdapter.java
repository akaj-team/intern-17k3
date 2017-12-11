package vn.asiantech.internship.saveData;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Person;

/**
 * Created by phongle on 11/12/2560.
 * PersonAdapter
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_person, parent, false);
        return new PersonViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(PersonAdapter.PersonViewHolder holder, int position) {
        holder.onBindData(mPersonList, position);
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RelativeLayout mRlItemPerson;
        private TextView mTvPersonName;
        private TextView mTvAge;
        private OnItemClickListener mOnItemClickListener;

        PersonViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mOnItemClickListener = onItemClickListener;
            initViews();
            initListener();
        }

        private void initViews() {
            mRlItemPerson = itemView.findViewById(R.id.rlItemPerson);
            mTvPersonName = itemView.findViewById(R.id.tvPersonName);
            mTvAge = itemView.findViewById(R.id.tvAge);
        }

        private void initListener() {
            mRlItemPerson.setOnClickListener(this);
        }

        private void onBindData(List<Person> personList, int position) {
            Person person = personList.get(position);
            if (person != null) {
                mTvPersonName.setText(person.getName());
                mTvAge.setText(String.valueOf(person.getAge()));
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
