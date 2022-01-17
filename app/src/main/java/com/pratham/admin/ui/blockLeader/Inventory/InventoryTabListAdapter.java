package com.pratham.admin.ui.blockLeader.Inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pratham.admin.R;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabItemClick;

import java.util.ArrayList;
import java.util.List;

public class InventoryTabListAdapter extends RecyclerView.Adapter<InventoryTabListAdapter.MyViewHolder> {
    List<DeviseList> deviseList;
    Context context;
    private final InventoryTabItemClick inventoryTabItemClick;

    public InventoryTabListAdapter(Context context, List deviseList, InventoryTabItemClick inventoryTabItemClick) {
        this.deviseList = deviseList;
        this.context=context;
        this.inventoryTabItemClick = inventoryTabItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        if(deviseList.get(position).getPratham_ID()!=null)
        holder.serialID.setText(deviseList.get(position).getSerialno());

        if(deviseList.get(position).getStatus()!=null && deviseList.get(position).getStatus().contains("Pending"))
            holder.serialID.setTextColor(context.getResources().getColor(R.color.red));
        else holder.serialID.setTextColor(context.getResources().getColor(R.color.grey_800));

        if(deviseList.get(position).isSelected())
            holder.iv_tabSelected.setVisibility(View.VISIBLE);
        else
            holder.iv_tabSelected.setVisibility(View.GONE);

        holder.cv_tablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deviseList.get(position).getStatus()!=null && deviseList.get(position).getStatus().contains("Pending"))
                    Toast.makeText(context, "Request Already Sent.", Toast.LENGTH_SHORT).show();
                else
                inventoryTabItemClick.onTabItemClicked(position, deviseList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return deviseList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serialID;
        CardView cv_tablet;
        ImageView iv_tabSelected;

        public MyViewHolder(View itemView) {
            super(itemView);
            serialID = itemView.findViewById(R.id.tabSerialID);
            cv_tablet = itemView.findViewById(R.id.cv_tablet);
            iv_tabSelected = itemView.findViewById(R.id.iv_tab_selected);
        }
    }

    public void filterList(ArrayList<DeviseList> filterdNames) {
        this.deviseList=filterdNames;
        notifyDataSetChanged();
    }
}
