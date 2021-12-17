package com.pratham.admin.ui.home.assignedToMe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.modalclasses.DeviseList;

import java.util.ArrayList;
import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.MyViewHolder> {
    List<DeviseList> deviseList;
    Context context;
    private final DevicePrathamIdLisner devicePrathamIdLisner;

    public DeviceListAdapter(Context context, List deviseList, DevicePrathamIdLisner devicePrathamIdLisner) {
        this.deviseList = deviseList;
        this.context = context;
        this.devicePrathamIdLisner = devicePrathamIdLisner;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("###### : ", deviseList.get(getAdapterPosition()).getPratham_ID());
                    String prathamID = deviseList.get(getAdapterPosition()).getPratham_ID();
                    String qrID = deviseList.get(getAdapterPosition()).getQR_ID();
                    String deviceID = deviseList.get(getAdapterPosition()).getDeviceid();
                    String serNo = deviseList.get(getAdapterPosition()).getSerialno();
                    String tabModel = deviseList.get(getAdapterPosition()).getBrand() + " " + deviseList.get(getLayoutPosition()).getModel();
                    if (prathamID != null && qrID != null) {
                        Log.e(":::", prathamID + " : " + qrID);
                        devicePrathamIdLisner.setDeviceDetail(prathamID, qrID, deviceID, serNo, tabModel);
                    } else {
                        Toast.makeText(context, "Pratham id or Qr id is null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void filterList(ArrayList<DeviseList> filterdNames) {
        this.deviseList = filterdNames;
        notifyDataSetChanged();
    }
}
