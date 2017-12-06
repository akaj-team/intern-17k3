package vn.asiantech.internship.ui.savedata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.UserCompany;

/**
 * Created by hoangnhat on 06/12/2017.
 * Create UserCompanyAdapter
 */
public class UserCompanyAdapter extends RecyclerView.Adapter<UserCompanyAdapter.UserCompanyViewHolder> {
    private List<UserCompany> mUserCompanies;
    private OnItemClickListener mOnItemClickListener;

    UserCompanyAdapter(List<UserCompany> userCompanies,OnItemClickListener onItemClickListener) {
        mUserCompanies = userCompanies;
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public UserCompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        final UserCompanyViewHolder userCompanyViewHolder = new UserCompanyViewHolder(view);
        userCompanyViewHolder.mNameUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onClickItem(1);
            }
        });
        return userCompanyViewHolder;
    }

    @Override
    public void onBindViewHolder(UserCompanyViewHolder holder, int position) {
        holder.onBindData(mUserCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserCompanies.size();
    }

    public interface OnItemClickListener {
        void onClickItem(int position);
    }

    static class UserCompanyViewHolder extends RecyclerView.ViewHolder {
        private TextView mIDUser;
        private TextView mNameUser;
        private TextView mAgeUser;

        UserCompanyViewHolder(View itemView) {
            super(itemView);
            mIDUser = itemView.findViewById(R.id.tvIDUser);
            mNameUser = itemView.findViewById(R.id.tvNameUse);
            mAgeUser = itemView.findViewById(R.id.tvAgeUser);
        }

        private void onBindData(UserCompany userCompany) {
            mIDUser.setText(String.valueOf(userCompany.getIDUser()));
            mNameUser.setText(userCompany.getNameUser());
            mAgeUser.setText(String.valueOf(userCompany.getAgeUser()));
        }
    }
}
