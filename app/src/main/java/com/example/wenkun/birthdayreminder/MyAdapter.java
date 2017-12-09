package com.example.wenkun.birthdayreminder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 焜 on 2017/10/22.
 */

public abstract class MyAdapter<T> extends RecyclerView.Adapter<ViewHolder>{
    private OnItemClickListener mOnItemClickListener;
    private List<T> mdata;
    private Context mContext;
    private int mLayoutId;

    public MyAdapter(Context context, int layoutId, List<T> data) {
        mContext=context;
        mLayoutId=layoutId;
        mdata=data;
    }
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener=onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder=ViewHolder.get(mContext,parent,mLayoutId);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        convert(holder,mdata.get(position));
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }

    }
    @Override
    public int getItemCount() {return mdata.size();}

    public abstract void convert(ViewHolder holder, T t);
    /*
        public void removeData( int position) {
            mdata.remove(position);
            notifyItemInserted(position);
            notifyItemRangeChanged(position, mdata.size());
        }
    */
    public void removeData( int position) {
        mdata.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mdata.size());
    }

    public T getItemData(int position) {
        return mdata.get(position);
    }
}
