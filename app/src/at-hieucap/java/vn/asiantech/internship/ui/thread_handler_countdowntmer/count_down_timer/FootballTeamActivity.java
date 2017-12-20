package vn.asiantech.internship.ui.thread_handler_countdowntmer.count_down_timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.FootballTeam;

/**
 * Created by tiboo on 20/12/2017.
 */

public class FootballTeamActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FootballTeamAdapter mAdapter;
    private List<FootballTeam> mFootballTeamList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_team);
        initViews();
        initData();
    }

    private void initData() {
//        mFootballTeamList.add(new FootballTeam(R.))
    }

    private void initViews() {
        mRecyclerView= findViewById(R.id.recyclerViewFootballTeam);
    }
}
