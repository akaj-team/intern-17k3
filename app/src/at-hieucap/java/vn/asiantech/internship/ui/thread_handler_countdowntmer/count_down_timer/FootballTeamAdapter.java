package vn.asiantech.internship.ui.thread_handler_countdowntmer.count_down_timer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.FootballTeam;

/**
 * Created by tiboo on 20/12/2017.
 * Create adapter
 */

public class FootballTeamAdapter extends RecyclerView.Adapter<FootballTeamAdapter.FootballTeamHolder> {
    private List<FootballTeam> mFootballTeamList;

    FootballTeamAdapter(List<FootballTeam> footballTeamList) {
        mFootballTeamList = footballTeamList;
    }

    @Override
    public FootballTeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_football_team, parent, false);
        return new FootballTeamHolder(view);
    }

    @Override
    public void onBindViewHolder(FootballTeamHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mFootballTeamList.size();
    }

    public class FootballTeamHolder extends RecyclerView.ViewHolder {
        private ImageView mImgLogo;
        private TextView mTvName;
        private TextView mTvNation;

        public FootballTeamHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mImgLogo = itemView.findViewById(R.id.imgLogo);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvNation = itemView.findViewById(R.id.tvNation);
        }

        public void onBindData() {
            FootballTeam footballTeam = mFootballTeamList.get(getAdapterPosition());
            mImgLogo.setImageResource(footballTeam.getLogo());
            mTvName.setText(footballTeam.getNameTeam());
            mTvNation.setText(footballTeam.getNation());
        }
    }
}
