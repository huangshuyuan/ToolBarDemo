package com.hsy.toolbar.toolbardemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 作者：huangshuyuan on 2017/5/12 15:06
 * 邮箱：hshuyuan@foxmail.com
 */

public class MyMainItemAdaper extends RecyclerView.Adapter<MyMainItemAdaper.ItemViewHolder> {
    Context context;
    String[] itemArr;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //点击事件
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new ItemViewHolder(v);

    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.button.setText(itemArr[position]);
        holder.position = position;

    }

    @Override
    public int getItemCount() {
        return itemArr.length;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        public int position;

        public ItemViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.itemButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*绑定点击事件*/
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    /*构造函数*/
    public MyMainItemAdaper(Context context, String[] itemArr) {
        this.context = context;
        this.itemArr = itemArr;
    }
}
