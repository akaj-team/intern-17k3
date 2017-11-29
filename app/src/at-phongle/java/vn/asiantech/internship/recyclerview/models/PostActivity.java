package vn.asiantech.internship.recyclerview.models;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class PostActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewPost;
    private PostAdapter mPostAdapter;
    private List<Posts> mPostList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        mRecyclerViewPost = findViewById(R.id.rvPosts);
    }

    private void initAdapter() {
        mPostAdapter = new PostAdapter(mPostList, this);
        mRecyclerViewPost.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPost.setAdapter(mPostAdapter);
    }

    public void initData() {
        mPostList.add(new Posts("Subject 01", "Description 1dsasfdasdfasfa sfasfasfasfasfafa sfasfasfsafasfasfasfasffbghdfbsfhdhs", 2));
        mPostList.add(new Posts("Subject 02", "Description 2", -2));
    }

    @Override
    public void onLikeClick(int position) {
        mPostList.get(position).setLikeNumber(mPostList.get(position).getLikeNumber() + 1);
    }

    @Override
    public void onDislikeClick(int position) {
        mPostList.get(position).setLikeNumber(mPostList.get(position).getLikeNumber() + 1);
    }
}
