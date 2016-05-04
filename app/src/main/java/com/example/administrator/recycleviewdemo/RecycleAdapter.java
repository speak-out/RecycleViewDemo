package com.example.administrator.recycleviewdemo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.myHolderView> {
    private Activity activity;
    private ArrayList<String> arrayList;

    public RecycleAdapter(Activity activity, ArrayList<String> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public myHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        myHolderView myHolderView =
                new myHolderView(LayoutInflater.from(activity).inflate(R.layout.recycle_item, parent, false));

        return myHolderView;
    }

    @Override
    public void onBindViewHolder(myHolderView holder, int position) {
        holder.recyclerViewTestItemPersonNameTv.setText("hahahahahah");
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class myHolderView extends RecyclerView.ViewHolder {
        TextView recyclerViewTestItemPersonNameTv;
        public myHolderView(View itemView) {
            super(itemView);
            recyclerViewTestItemPersonNameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
        }
    }
}
