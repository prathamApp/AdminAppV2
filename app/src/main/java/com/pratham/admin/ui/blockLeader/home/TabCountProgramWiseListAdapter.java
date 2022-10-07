package com.pratham.admin.ui.blockLeader.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.Model_TabletCountProgramwise;

import java.util.ArrayList;
import java.util.List;

public class TabCountProgramWiseListAdapter extends RecyclerView.Adapter<TabCountProgramWiseListAdapter.MyViewHolder> {
    List<Model_TabletCountProgramwise> tabletCountList;
    Context context;
    String tabType;

    public TabCountProgramWiseListAdapter(Context context, List tabletCountList, String tabType) {
        this.tabletCountList = tabletCountList;
        this.context = context;
        this.tabType = tabType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tabletcount_programwise, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //  if(tabletCountList.get(position).getPratham_ID()!=null)
        holder.tv_programName.setText(tabletCountList.get(position).getProgramName());
        switch (tabType) {
            case "lost" :
                holder.tv_tabletCount.setText(tabletCountList.get(position).getTotalLost());
                break;
            case "damage" :
                holder.tv_tabletCount.setText(tabletCountList.get(position).getTotalDamaged());
                break;
            case "working" :
                holder.tv_tabletCount.setText(tabletCountList.get(position).getTotalWorking());
                break;
            case "dead" :
                holder.tv_tabletCount.setText(tabletCountList.get(position).getTotalDead());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return tabletCountList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_programName;
        TextView tv_tabletCount;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_programName = itemView.findViewById(R.id.tv_programName);
            tv_tabletCount = itemView.findViewById(R.id.tv_tabletCount);

        }
    }
}
