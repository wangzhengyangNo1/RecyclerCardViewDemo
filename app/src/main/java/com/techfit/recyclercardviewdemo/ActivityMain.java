package com.techfit.recyclercardviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.techfit.recyclercardviewdemo.bean.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityMain extends AppCompatActivity {

    @BindView(R.id.rv_items)
    RecyclerView mRvItems;

    private static final int ITEM_COUNT = 20;
    private ArrayList<Item> mItems;
    private AdapterRecyclerCard mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();

        mAdapter = new AdapterRecyclerCard(this, mItems);
        mRvItems.setAdapter(mAdapter);
        mRvItems.setItemAnimator(new DefaultItemAnimator());
//        mRvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        mRvItems.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
        mRvItems.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        mAdapter.setOnItemClickListener(new AdapterRecyclerCard.OnItemClickListener() {
            @Override
            public void onItemClick(View pView, int pPosition) {
                Toast.makeText(ActivityMain.this, "Item" + pPosition + " is clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View pView, int pPosition) {
                Item _removedItem = mAdapter.removeItem(pPosition);
                Toast.makeText(ActivityMain.this.getApplicationContext(), _removedItem.getMainTitle() + " is removed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mItems = new ArrayList<Item>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            mItems.add(new Item("Item" + i, "Item Number " + i));
        }
    }
}
