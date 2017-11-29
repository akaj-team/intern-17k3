package vn.asiantech.internship.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by tungnguyen on 29/11/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> mPersonList;

    public PersonAdapter(List<Person> mPersonList ) {
        this.mPersonList = mPersonList;

    }

    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false );
                return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonAdapter.PersonViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
    class PersonViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvName;
        private TextView mTvStatus;
        private TextView mTvCount;
        private ImageView mImgLike;
        private ImageView mImgDislike;
         PersonViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvStatus = itemView.findViewById(R.id.tvDescription);
            mTvCount = itemView.findViewById(R.id.tvCount);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDislike = itemView.findViewById(R.id.imgDislike);
        }
        private void onBindData() {
            Person person = mPersonList.get(getAdapterPosition());
            mTvName.setText(person.getName());
            mTvStatus.setText(person.getDescription());
            mTvCount.setText(person.getCount());

        }
    }


}
