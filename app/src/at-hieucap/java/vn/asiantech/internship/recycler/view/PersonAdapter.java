package vn.asiantech.internship.recycler.view;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import vn.asiantech.internship.R;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> mPersonList;

    PersonAdapter(List<Person> personList) {
        mPersonList = personList;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
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

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvNamePerson;
        private ImageView mImgLike;
        private ImageView mImgDisLike;
        private TextView mTvValue;
        private TextView mTvStatus;

        PersonViewHolder(final View itemView) {
            super(itemView);
            mTvNamePerson = itemView.findViewById(R.id.tvNamePerson);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDisLike = itemView.findViewById(R.id.imgDisLike);
            mTvValue = itemView.findViewById(R.id.tvValue);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mImgLike.setOnClickListener(this);
            mImgDisLike.setOnClickListener(this);
        }

        void onBindData() {
            Person person = mPersonList.get(getAdapterPosition());
            mTvNamePerson.setText(person.getName());
            mImgLike.setSelected(mImgLike.isSelected());
            mImgDisLike.setSelected(mImgDisLike.isSelected());
            mTvValue.setText(person.getValue());
            mTvStatus.setText(person.getStatus());
        }

        @Override
        public void onClick(View v) {
            BigDecimal bdValue = new BigDecimal(mTvValue.getText().toString());
            Integer integerValue0 = Integer.parseInt(v.getContext().getString(R.string.value_0));
            switch (v.getId()) {
                case R.id.imgLike:
                    mImgLike.setSelected(true);
                    bdValue = bdValue.add(new BigDecimal(BigInteger.ONE));
                    mTvValue.setText(String.valueOf(bdValue));
                    if (bdValue.compareTo(new BigDecimal(BigInteger.ZERO)) == integerValue0) {
                        mTvValue.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorBlack));
                    } else if (bdValue.compareTo(new BigDecimal(BigInteger.ZERO)) > integerValue0) {
                        mTvValue.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorRed));
                    } else {
                        mTvValue.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorGreen));
                    }
                    break;
                case R.id.imgDisLike:
                    mImgDisLike.setSelected(true);
                    bdValue = bdValue.subtract(new BigDecimal(BigInteger.ONE));
                    mTvValue.setText(String.valueOf(bdValue));
                    if (bdValue.compareTo(new BigDecimal(BigInteger.ZERO)) == integerValue0) {
                        mTvValue.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorBlack));
                    } else if (bdValue.compareTo(new BigDecimal(BigInteger.ZERO)) > integerValue0) {
                        mTvValue.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorRed));
                    } else {
                        mTvValue.setTextColor(ActivityCompat.getColor(v.getContext(), R.color.colorGreen));
                    }
                    break;
            }
        }
    }
}
