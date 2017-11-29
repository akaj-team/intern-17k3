package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.models.Comment;

public class RecyclerviewActivity extends AppCompatActivity implements CommentAdapter.OnItemClickListener {
    private RecyclerView mRecycleViewComment;
    private List<Comment> mCommentList;
    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecycleViewComment = findViewById(R.id.recycleViewComment);
    }

    private void initData() {
        mCommentList = new ArrayList<>();
        mCommentList.add(new Comment("Nguyen Van A", "very good very good very good very good very good very good very good very good very good very good very good very good very good very good very good very good", 5));
        mCommentList.add(new Comment("Nguyen Van B", "not good not good not good not good not good not good not good not good not good not good not good ", -4));
        mCommentList.add(new Comment("Nguyen Van C", "not good", 3));
        mCommentList.add(new Comment("Nguyen Van D", "very good", -2));
        mCommentList.add(new Comment("Nguyen Van E", "very good", 1));
        mCommentList.add(new Comment("Nguyen Van F", "very good", 0));
        mCommentList.add(new Comment("Nguyen Van G", "very good", -1));
        mCommentList.add(new Comment("Nguyen Van H", "very goodvery goodvery goodvery goodvery good", -3));
    }

    private void initAdapter() {
        mCommentAdapter = new CommentAdapter(mCommentList, this);
        mRecycleViewComment.setLayoutManager(new LinearLayoutManager(this));
        mRecycleViewComment.setAdapter(mCommentAdapter);
    }

    @Override
    public void onClickLike(int position) {
        mCommentList.get(position).clickLike();
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickDislike(int position) {
        mCommentList.get(position).clickDislike();
        mCommentAdapter.notifyDataSetChanged();

    }
}
