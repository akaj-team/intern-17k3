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
    private int mCountTime;
    CountDownTimer countDownTimer = new CountDownTimer(180000, 1000) {
        @Override
        public void onTick(long l) {
            mCountTime = (int) (l / 1000);
        }

        @Override
        public void onFinish() {
        }
    };

    CountDownTimer addItem = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            if (mCountTime > 0) {
                mFootballTeamList.add(new FootballTeam(R.drawable.ic_manchester_united, MANCHESTER_UNITED, PREMIER_LEAGUE));
                mFootballTeamList.add(new FootballTeam(R.drawable.ic_real_madrid, REAL_MADRID, LA_LIGA));
                mAdapter.notifyDataSetChanged();
                addItem.start();
            }
        }
    }.start();

    CountDownTimer deleteItem = new CountDownTimer(15000, 1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            if (mCountTime > 0) {
                mFootballTeamList.remove(mFootballTeamList.size() / 2);
                mAdapter.notifyDataSetChanged();
                deleteItem.start();
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
        countDownTimer.start();
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
