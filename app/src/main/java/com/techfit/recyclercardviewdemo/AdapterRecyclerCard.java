package com.techfit.recyclercardviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techfit.recyclercardviewdemo.bean.Item;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangzhengyang on 2016/12/13.
 */

public class AdapterRecyclerCard extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Item> mItems;
    private RecyclerViewHolder mHolder;
    private LayoutInflater mInflater;

    public static final int IN_ACTIVE = 121300;
    public static final int ACTIVE = 121301;


    public AdapterRecyclerCard(Context pContext, ArrayList<Item> pItems) {
        this.mItems = pItems;
        this.mContext = pContext;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < mItems.size(); i++) {
            Random _random = new Random();
//            int _curDimen = _random.nextInt(160) + 360;
            int _curDimen = _random.nextInt(160) + 240;
            mItems.get(i).dimen = _curDimen;

        }
        mItems.get(0).setActive(true);
        mItems.get(mItems.size()-1).setActive(true);
    }

    @Override
    public int getItemViewType(int position) {
        Item _item = mItems.get(position);
        return _item.isActive()?ACTIVE:IN_ACTIVE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _root = mInflater.inflate(viewType == IN_ACTIVE ? R.layout.layout_item_1 : R.layout.layout_item_2, parent, false);
        mHolder = new RecyclerViewHolder(_root);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Item _item = mItems.get(position);
//        _item.setSubTitle(position);
//        _item.setMainTitle(position);
        final RecyclerViewHolder _holder = (RecyclerViewHolder) holder;
        _holder.mMainTitle.setText(_item.getMainTitle());
        _holder.mSubtTitle.setText(_item.getSubTitle());

        ViewGroup.LayoutParams _layoutParams = _holder.itemView.getLayoutParams();
        _layoutParams.height = _item.dimen;

        _holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener == null) return;
                int _position = _holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(_holder.itemView,_position);
            }
        });
        _holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null){
                    int _position = _holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(_holder.itemView,_position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.maintitle)
        TextView mMainTitle;
        @BindView(R.id.subtitle)
        TextView mSubtTitle;
        @BindView(R.id.ll_item)
        LinearLayout mItem;

        public RecyclerViewHolder(View pRoot) {
            super(pRoot);
            ButterKnife.bind(this, pRoot);
        }
    }



    public interface OnItemClickListener{
        void onItemClick(View pView, int pPosition);
        void onItemLongClick(View pView, int pPosition);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener pListener){
        mOnItemClickListener = pListener;
    }

    public Item removeItem(int pPosition){
        Item _removedItem = mItems.remove(pPosition);
        notifyItemRemoved(pPosition);
//        notifyDataSetChanged();
//        notifyItemRangeChanged(pPosition,getItemCount());
        return _removedItem;
    }
}
