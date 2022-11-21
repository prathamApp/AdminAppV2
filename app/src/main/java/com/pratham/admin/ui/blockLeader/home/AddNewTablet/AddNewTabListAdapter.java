package com.pratham.admin.ui.blockLeader.home.AddNewTablet;

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
import com.pratham.admin.ui.blockLeader.Inventory.InventoryTabItemClick;

import java.util.ArrayList;
import java.util.List;

public class AddNewTabListAdapter extends RecyclerView.Adapter<AddNewTabListAdapter.MyViewHolder> {
    List<String> scannedTabList;
    Context context;
    //private final InventoryTabItemClick inventoryTabItemClick;
    private AddNewTabListAdapter addNewTabListAdapter;

    public AddNewTabListAdapter(Context context, List scannedTabList) {
        this.scannedTabList = scannedTabList;
        this.context=context;
        //this.inventoryTabItemClick = inventoryTabItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_tab_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        if(deviseList.get(position).getPratham_ID()!=null)
        holder.serialID.setText(scannedTabList.get(position));

        holder.iv_clearTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addNewTabListAdapter.scannedTabList.remove(position);
                scannedTabList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return scannedTabList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serialID;
        CardView cv_tablet;
        ImageView iv_clearTab;

        public MyViewHolder(View itemView) {
            super(itemView);
            serialID = itemView.findViewById(R.id.tabSerialID);
            cv_tablet = itemView.findViewById(R.id.cv_tablet);
            iv_clearTab = itemView.findViewById(R.id.iv_clear_tab);
        }
    }

/*    public void filterList(ArrayList<DeviseList> filterdNames) {
        this.deviseList=filterdNames;
        notifyDataSetChanged();
    }*/
}
