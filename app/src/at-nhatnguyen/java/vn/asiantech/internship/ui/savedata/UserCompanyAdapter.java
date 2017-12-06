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
 */

public class UserCompanyAdapter extends RecyclerView.Adapter<UserCompanyAdapter.UserCompanyViewHolder>{
    private List<UserCompany> mUserCompanies;

    public UserCompanyAdapter(List<UserCompany> userCompanies){
        mUserCompanies=userCompanies;
    }

    @Override
    public UserCompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserCompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserCompanyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class UserCompanyViewHolder extends RecyclerView.ViewHolder{
        private TextView mIDUser;
        private TextView mNameUser;
        private TextView mAgeUser;

        UserCompanyViewHolder(View itemView) {
            super(itemView);
            mIDUser = itemView.findViewById(R.id.tvIDUser);
            mNameUser = itemView.findViewById(R.id.tvNameUse);
            mAgeUser = itemView.findViewById(R.id.tvAgeUser);
        }

        private void onBindData(UserCompany userCompany){
//            UserCompany userCompany = mUserCompanies.get(getAdapterPosition());
            mIDUser.setText(userCompany.getIDUser());
            mNameUser.setText(userCompany.getNameUser());
            mAgeUser.setText(userCompany.getAgeUser());
        }
    }
}
