package vn.asiantech.internship.ui.thread_handler_countdowntmer.count_down_timer;

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
    private RecyclerView mRecyclerView;
    private FootballTeamAdapter mAdapter;
    private List<FootballTeam> mFootballTeamList = new ArrayList<>();
    private int mTime;
    CountDownTimer countTimer = new CountDownTimer(180000, 1000) {
        @Override
        public void onTick(long l) {
            mTime = (int) (l / 1000);
        }

        @Override
        public void onFinish() {
            mTime = 0;
        }
    };
    CountDownTimer add = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            // No-oop
        }

        @Override
        public void onFinish() {
            if (mTime > 0) {
                mFootballTeamList.add(new FootballTeam(R.drawable.ic_manchester_united, MANCHESTER_UNITED, PREMIER_LEAGUE));
                mFootballTeamList.add(new FootballTeam(R.drawable.ic_real_madrid, REAL_MADRID, LA_LIGA));
                mAdapter.notifyDataSetChanged();
                add.start();
            }
        }
    }.start();
    CountDownTimer delete = new CountDownTimer(15000, 1000) {
        @Override
        public void onTick(long l) {
            // No-oop
        }

        @Override
        public void onFinish() {
            if (mTime > 0) {
                mFootballTeamList.remove(mFootballTeamList.size() / 2);
                mAdapter.notifyDataSetChanged();
                delete.start();
            }
        }
    }.start();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_team);
        initViews();
        initData();
        initAdapter();
        countTimer.start();
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
