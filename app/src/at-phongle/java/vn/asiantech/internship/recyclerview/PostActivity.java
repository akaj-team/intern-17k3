package vn.asiantech.internship.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Posts;

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
        mPostList.add(new Posts("Post 01", "This is PostOne'Description", 2));
        mPostList.add(new Posts("Post 02", "This is PostTwo'Description", -2));
        mPostList.add(new Posts("Post 03", "This is PostThree'Description", -4));
        mPostList.add(new Posts("Post 04", "This is PostFour'Description", 3));
        mPostList.add(new Posts("Post 05", "This is PostFive'Description", -5));
        mPostList.add(new Posts("Post 06", "This is PostSix'Description", 100));
        mPostList.add(new Posts("Post 07", "This is PostSeven'Description", 1));
        mPostList.add(new Posts("Post 08", "This is PostEight'Description", 0));
        mPostList.add(new Posts("Post 09", "This is PostNine'Description", 4));
        mPostList.add(new Posts("Post 10", "This is PostTen'Description", -1));
    }

    @Override
    public void onLikeClick(int position) {
        mPostList.get(position).setLikeNumber(mPostList.get(position).getLikeNumber() + 1);
        mPostAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDislikeClick(int position) {
        mPostList.get(position).setLikeNumber(mPostList.get(position).getLikeNumber() - 1);
        mPostAdapter.notifyItemChanged(position);
    }
}
