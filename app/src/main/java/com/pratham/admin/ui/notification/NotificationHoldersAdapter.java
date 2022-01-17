package com.pratham.admin.ui.notification;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.pratham.admin.R;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.ui.blockLeader.tabHolders.TabHolderListItemListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationHoldersAdapter extends RecyclerView.Adapter<NotificationHoldersAdapter.MyViewHolder> {
    List<CRL> crlList;
    Context context;
    private final NotificationHolderListItemListener tabHolderListItemListener;

    public NotificationHoldersAdapter(Context context, List crlList, NotificationHolderListItemListener tabHolderListItemListener) {
        this.crlList = crlList;
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
        holder.tv_name.setText(crlList.get(position).getFirstName());
        holder.tv_detail.setText(crlList.get(position).getMobile());
        // if(deviseList.get(position).getQR_ID()!=null)
        if (crlList.get(position).isSelected())
            holder.iv_crlSelected.setVisibility(View.VISIBLE);
        else
            holder.iv_crlSelected.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return crlList.size();
    }

    public List<CRL> getCrlList() {
        return crlList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_detail;
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
            iv_call = itemView.findViewById(R.id.iv_call);
            iv_sms = itemView.findViewById(R.id.iv_message);
            btn_acknowledege = itemView.findViewById(R.id.btn_acknowledege);
            iv_crlSelected = itemView.findViewById(R.id.iv_crl_selected);
            iv_tabHolderImage = itemView.findViewById(R.id.iv_tabHolderImage);
            ll_tabHolderDetail = itemView.findViewById(R.id.ll_tabHolderDetail);

            iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel: " + crlList.get(getAdapterPosition()).getMobile()));
                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) { //todo check
                        //ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},1);
                    }
                    context.startActivity(callIntent);
                }
            });
            btn_acknowledege.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tabHolderListItemListener.tabHolderItemClicked(itemView, crlList.get(getAdapterPosition()), getAdapterPosition());
                }
            });


            ll_tabHolderDetail.setOnClickListener(v ->
                    tabHolderListItemListener.tabHolderItemClicked(itemView, crlList.get(getAdapterPosition()), getAdapterPosition())
            );

            iv_tabHolderImage.setOnClickListener(v ->
                    tabHolderListItemListener.tabHolderDetails(crlList.get(getAdapterPosition()))
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

    public void filterList(ArrayList<CRL> filterdNames) {
        this.crlList = filterdNames;
        notifyDataSetChanged();
    }
}
