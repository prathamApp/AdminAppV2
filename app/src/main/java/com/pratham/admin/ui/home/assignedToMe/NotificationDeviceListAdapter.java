package com.pratham.admin.ui.home.assignedToMe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.modalclasses.DeviseList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationDeviceListAdapter extends RecyclerView.Adapter<NotificationDeviceListAdapter.MyViewHolder> {
    List<DeviseList> deviseList;
    Context context;


    public NotificationDeviceListAdapter(Context context, List deviseList) {
        this.deviseList = deviseList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //  if(deviseList.get(position).getPratham_ID()!=null)
        holder.serialID.setText(deviseList.get(position).getSerialno());
    }

    @Override
    public int getItemCount() {
        return deviseList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serialID;


        public MyViewHolder(View itemView) {
            super(itemView);
            serialID = itemView.findViewById(R.id.tabSerialID);


        }
    }

    public void filterList(ArrayList<DeviseList> filterdNames) {
        this.deviseList = filterdNames;
        notifyDataSetChanged();
    }
}
