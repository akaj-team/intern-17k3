package vn.asiantech.internship.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Comment;

public class CommentActivity extends AppCompatActivity implements CommentAdapter.OnItemClickListener {
    private RecyclerView mRecycleViewComment;
    private List<Comment> mComments;
    private CommentAdapter mAdapter;

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
        mComments = new ArrayList<>();
        mComments.add(new Comment("Nguyen Van A", "very good very good very good very good very good very good very good very good very good very good very good very good very good very good very good very good", 5));
        mComments.add(new Comment("Nguyen Van B", "not good not good not good not good not good not good not good not good not good not good not good ", -4));
        mComments.add(new Comment("Nguyen Van C", "not good", 3));
        mComments.add(new Comment("Nguyen Van D", "very good", -2));
        mComments.add(new Comment("Nguyen Van E", "very good", 1));
        mComments.add(new Comment("Nguyen Van F", "very good", 0));
        mComments.add(new Comment("Nguyen Van G", "very good", -1));
        mComments.add(new Comment("Nguyen Van H", "very goodvery goodvery goodvery goodvery good", -3));
    }

    private void initAdapter() {
        mAdapter = new CommentAdapter(mComments, this);
        mRecycleViewComment.setLayoutManager(new LinearLayoutManager(this));
        mRecycleViewComment.setAdapter(mAdapter);
    }

    @Override
    public void onClickLike(int position) {
        mComments.get(position).clickLike();
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickDislike(int position) {
        mComments.get(position).clickDislike();
        mAdapter.notifyItemChanged(position);

    }
}
