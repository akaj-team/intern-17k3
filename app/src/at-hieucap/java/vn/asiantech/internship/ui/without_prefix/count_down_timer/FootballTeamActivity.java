package vn.asiantech.internship.ui.without_prefix.count_down_timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.FootballTeam;

/**
 * Created by tiboo on 20/12/2017.
 * Create FootballTeamActivity
 */
public class FootballTeamActivity extends AppCompatActivity {
    private static final String MANCHESTER_UNITED = "Manchester united";
    private static final String MANCHESTER_CITY = "Manchester city";
    private static final String LIVERPOOL = "Liverpool";
    private static final String REAL_MADRID = "Real madrid";
    private static final String BARCELONA = "Barcelona";
    private static final String PREMIER_LEAGUE = "Premier league";
    private static final String LA_LIGA = "La liga";
    private static final int LIMIT_TIME = 180000;
    private static final int ONE_SECOND = 1000;
    private List<FootballTeam> mFootballTeamList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FootballTeamAdapter mAdapter;
    private int mTime;

    CountDownTimer mCountTimer = new CountDownTimer(LIMIT_TIME, ONE_SECOND) {
        @Override
        public void onTick(long l) {
            mTime = (LIMIT_TIME / ONE_SECOND) - (int) (l / ONE_SECOND);
            if (mTime % 10 == 0) {
                mFootballTeamList.add(new FootballTeam(R.drawable.ic_manchester_united, MANCHESTER_UNITED, PREMIER_LEAGUE));
                mFootballTeamList.add(new FootballTeam(R.drawable.ic_real_madrid, REAL_MADRID, LA_LIGA));
                mAdapter.notifyDataSetChanged();
            }
            if (mTime % 15 == 0) {
                mFootballTeamList.remove(mFootballTeamList.size() / 2);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFinish() {
            mTime = 0;
            mCountTimer.cancel();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_team);
        initViews();
        initData();
        initAdapter();
        mCountTimer.start();
    }

    @Override
    protected void onDestroy() {
        mCountTimer.cancel();
        super.onDestroy();
    }

    private void initAdapter() {
        mAdapter = new FootballTeamAdapter(mFootballTeamList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mFootballTeamList.add(new FootballTeam(R.drawable.ic_manchester_united, MANCHESTER_UNITED, PREMIER_LEAGUE));
        mFootballTeamList.add(new FootballTeam(R.drawable.ic_liverpool, LIVERPOOL, PREMIER_LEAGUE));
        mFootballTeamList.add(new FootballTeam(R.drawable.ic_manchester_city, MANCHESTER_CITY, PREMIER_LEAGUE));
        mFootballTeamList.add(new FootballTeam(R.drawable.ic_real_madrid, REAL_MADRID, LA_LIGA));
        mFootballTeamList.add(new FootballTeam(R.drawable.ic_barcelona, BARCELONA, LA_LIGA));
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerViewFootballTeam);
    }
}
