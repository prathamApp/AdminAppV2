package com.pratham.admin.ui.notification;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.pratham.admin.R;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.Model_Notification;
import com.pratham.admin.ui.blockLeader.tabHolders.TabHolderListItemListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationHoldersAdapter extends RecyclerView.Adapter<NotificationHoldersAdapter.MyViewHolder> {
    List<Model_Notification> notificationList;
    Context context;
    private final NotificationHolderListItemListener tabHolderListItemListener;

    public NotificationHoldersAdapter(Context context, List notificationList, NotificationHolderListItemListener tabHolderListItemListener) {
        this.notificationList = notificationList;
        this.context = context;
        this.tabHolderListItemListener = tabHolderListItemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_holders, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //  if(deviseList.get(position).getPratham_ID()!=null)

        Log.i("CRLid",FastSave.getInstance().getString("CRLid", "no_crl"));
        holder.tv_detail.setText(notificationList.get(position).getAssignDate());
        if(notificationList.get(position).getAssignById().equals(FastSave.getInstance().getString("CRLid", "no_crl")))
        {
            holder.tv_name.setText(notificationList.get(position).getAssignToName());
            holder.btn_acknowledege.setVisibility(View.GONE);
            if(notificationList.get(position).getStatus().contains("Replace") || notificationList.get(position).getStatus().contains("Damaged"))
            holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Replace Tablets "+(notificationList.get(position).getAckstatus().equals("Acknowledged")?"Received":"Sent"));
            else if(notificationList.get(position).getStatus().contains("Lost") || notificationList.get(position).getStatus().contains("Stolen"))
                holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Lost Tablets Reported");
            else
                holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Tablets "+(notificationList.get(position).getAckstatus().equals("Acknowledged")?"Received":"Sent"));
        }
        else if(notificationList.get(position).getAssignToId().equals(FastSave.getInstance().getString("CRLid", "no_crl")))
        {
            holder.tv_name.setText(notificationList.get(position).getAssignByName());
            //holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Tablets Received");
            if(notificationList.get(position).getStatus().contains("Replace") || notificationList.get(position).getStatus().contains("Damaged"))
                holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Replace Tablets "+(notificationList.get(position).getAckstatus().equals("Acknowledged")?"Sent":"Received"));
            else if(notificationList.get(position).getStatus().contains("Lost") || notificationList.get(position).getStatus().contains("Stolen"))
                holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Lost Tablets Reported");
            else
                holder.tv_Type.setText(notificationList.get(position).getLstackdevice().size() +" Tablets "+(notificationList.get(position).getAckstatus().equals("Acknowledged")?"Sent":"Received")) ;


            if(notificationList.get(position).getAckstatus().equals("Acknowledged"))
            holder.btn_acknowledege.setVisibility(View.GONE);
                else
            holder.btn_acknowledege.setVisibility(View.VISIBLE);
        }
        //holder.tv_name.setText(notificationList.get(position).getAssignByName());

        if(notificationList.get(position).getAckstatus().equals("Acknowledged"))
            holder.tv_Type.setTextColor(context.getResources().getColor(R.color.green_700));
        else
            holder.tv_Type.setTextColor(context.getResources().getColor(R.color.red_400));

            holder.iv_crlSelected.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public List<Model_Notification> getnotificationList() {
        return notificationList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_detail;
        TextView tv_Type;
        ImageView iv_call;
        MaterialButton btn_acknowledege;
        ImageView iv_sms;
        ImageView iv_crlSelected;
        ImageView iv_tabHolderImage;
        LinearLayout ll_tabHolderDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_tabHolderName);
            tv_detail = itemView.findViewById(R.id.tv_tabHolderDetail);
            tv_Type = itemView.findViewById(R.id.tv_Type);
            iv_call = itemView.findViewById(R.id.iv_call);
            iv_sms = itemView.findViewById(R.id.iv_message);
            btn_acknowledege = itemView.findViewById(R.id.btn_acknowledege);
            iv_crlSelected = itemView.findViewById(R.id.iv_crl_selected);
            iv_tabHolderImage = itemView.findViewById(R.id.iv_tabHolderImage);
            ll_tabHolderDetail = itemView.findViewById(R.id.ll_tabHolderDetail);

            btn_acknowledege.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tabHolderListItemListener.tabHolderItemClicked(itemView, notificationList.get(getAdapterPosition()), getAdapterPosition());
                }
            });


            ll_tabHolderDetail.setOnClickListener(v ->
                    tabHolderListItemListener.tabHolderDetails(notificationList.get(getAdapterPosition()))
            );

            tv_Type.setOnClickListener(v ->
                    tabHolderListItemListener.tabHolderDetails(notificationList.get(getAdapterPosition()))
            );

            tv_name.setOnClickListener(v ->
                    tabHolderListItemListener.tabHolderDetails(notificationList.get(getAdapterPosition()))
            );

            iv_tabHolderImage.setOnClickListener(v ->
                    tabHolderListItemListener.tabHolderDetails(notificationList.get(getAdapterPosition()))
            );

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("###### : ", deviseList.get(getAdapterPosition()).getPratham_ID());
                  String prathamID=deviseList.get(getAdapterPosition()).getPratham_ID();
                  String qrID=deviseList.get(getAdapterPosition()).getQR_ID();
                  String deviceID=deviseList.get(getAdapterPosition()).getDeviceid();
                  String serNo=deviseList.get(getAdapterPosition()).getSerialno();
                  String tabModel=deviseList.get(getAdapterPosition()).getBrand() +" "+ deviseList.get(getLayoutPosition()).getModel();
                  if(prathamID!=null && qrID!=null){
                      Log.e(":::",prathamID+" : "+qrID);
//                      devicePrathamIdLisner.setDeviceDetail(prathamID,qrID,deviceID,serNo,tabModel);
                  }else {
                      Toast.makeText(context, "Pratham id or Qr id is null", Toast.LENGTH_SHORT).show();
                  }
                }
            });
*/
        }
    }

    public void filterList(ArrayList<Model_Notification> filterdNames) {
        this.notificationList = filterdNames;
        notifyDataSetChanged();
    }


}
