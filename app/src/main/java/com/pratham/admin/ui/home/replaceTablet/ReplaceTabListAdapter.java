package com.pratham.admin.ui.home.replaceTablet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.OnCheckBoxSelectedItem;
import com.pratham.admin.modalclasses.DeviseList;

import java.util.ArrayList;
import java.util.List;

public class ReplaceTabListAdapter extends RecyclerView.Adapter<ReplaceTabListAdapter.MyViewHolder> {
    List<DeviseList> deviseList;
    Context context;
    private final ReplaceTabItemClick replaceTabItemClick;

    public ReplaceTabListAdapter(Context context, List deviseList, ReplaceTabItemClick replaceTabItemClick) {
        this.deviseList = deviseList;
        this.context=context;
        this.replaceTabItemClick = replaceTabItemClick;
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

/*        if(deviseList.get(position).getStatus()!=null) {
            String individaulTabStatus = deviseList.get(position).getStatus().toUpperCase();
            switch (individaulTabStatus) {
                case "PENDING":
                    holder.serialID.setTextColor(context.getResources().getColor(R.color.red));
                    break;
                case "STOLEN":
                    holder.serialID.setTextColor(context.getResources().getColor(R.color.yellow));
                    break;
                case "DAMAGED":
                    holder.serialID.setTextColor(context.getResources().getColor(R.color.blue_700));
                    break;
                default:
                    holder.serialID.setTextColor(context.getResources().getColor(R.color.grey_800));
            }
        }*/

        if(deviseList.get(position).getStatus()!=null){
            if (deviseList.get(position).getStatus().contains("Pending"))
                holder.serialID.setTextColor(context.getResources().getColor(R.color.red));
            else if(deviseList.get(position).getStatus().contains("Stolen"))
                holder.serialID.setTextColor(context.getResources().getColor(R.color.yellow));
            else if(deviseList.get(position).getStatus().contains("Damaged"))
                holder.serialID.setTextColor(context.getResources().getColor(R.color.blue_700));
            else holder.serialID.setTextColor(context.getResources().getColor(R.color.grey_800));
        }


        holder.cv_tablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deviseList.get(position).getStatus()!=null) {
/*                    String individaulTabStatus = deviseList.get(position).getStatus().toUpperCase();
                    switch (individaulTabStatus) {
                        case "PENDING":
                            Toast.makeText(context, "Request Already Sent.", Toast.LENGTH_SHORT).show();
                            break;
                        case "STOLEN":
                            Toast.makeText(context, "Tablet is Lost.", Toast.LENGTH_SHORT).show();
                            break;
                        case "DAMAGED":
                            Toast.makeText(context, "Tablet is Damaged.", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            replaceTabItemClick.onTabItemClicked(position, deviseList.get(position));
                    }*/
                    if (deviseList.get(position).getStatus().contains("Pending"))
                        Toast.makeText(context, "Request Already Sent.", Toast.LENGTH_SHORT).show();
                    else if(deviseList.get(position).getStatus().contains("Stolen"))
                        Toast.makeText(context, "Tablet is Lost.", Toast.LENGTH_SHORT).show();
                    else if(deviseList.get(position).getStatus().contains("Damaged"))
                        Toast.makeText(context, "Tablet is Damaged.", Toast.LENGTH_SHORT).show();
                    else replaceTabItemClick.onTabItemClicked(position, deviseList.get(position));
                }

/*                if(deviseList.get(position).getStatus()!=null && deviseList.get(position).getStatus().contains("Pending"))
                    Toast.makeText(context, "Request Already Sent.", Toast.LENGTH_SHORT).show();
                else replaceTabItemClick.onTabItemClicked(position, deviseList.get(position));*/
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

        public MyViewHolder(View itemView) {
            super(itemView);
            serialID = itemView.findViewById(R.id.tabSerialID);
            cv_tablet = itemView.findViewById(R.id.cv_tablet);
        }
    }

    public void filterList(ArrayList<DeviseList> filterdNames) {
        this.deviseList=filterdNames;
        notifyDataSetChanged();
    }
}
