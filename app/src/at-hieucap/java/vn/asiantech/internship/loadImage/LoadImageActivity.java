package vn.asiantech.internship.loadImage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class LoadImageActivity extends AppCompatActivity {
    private ImageAdapter mAdapter;
    private List<ItemImage> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ImageView mImgItem;
    private Context mContext;

    public LoadImageActivity(ImageView mImgItem, Context mContext) {
        this.mContext = mContext;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.rlImage);
        mAdapter = new ImageAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mImgItem = findViewById(R.id.imgItem);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mList.add(new ItemImage(Picasso.with(this).load("http://i.imgur.com/DvpvklR.png")));
    }
}
