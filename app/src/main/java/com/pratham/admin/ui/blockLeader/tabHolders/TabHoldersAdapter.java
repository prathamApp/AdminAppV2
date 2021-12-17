package com.pratham.admin.ui.blockLeader.tabHolders;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.DeviseList;

import java.util.ArrayList;
import java.util.List;

public class TabHoldersAdapter extends RecyclerView.Adapter<TabHoldersAdapter.MyViewHolder> {
    List<CRL> crlList;
    Context context;
    private final TabHolderListItemListener tabHolderListItemListener;

    public TabHoldersAdapter(Context context, List crlList, TabHolderListItemListener tabHolderListItemListener) {
        this.crlList = crlList;
        this.context=context;
        this.tabHolderListItemListener = tabHolderListItemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tab_holders, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      //  if(deviseList.get(position).getPratham_ID()!=null)
        holder.tv_name.setText(crlList.get(position).getFirstName());
        holder.tv_mob.setText(crlList.get(position).getMobile());
       // if(deviseList.get(position).getQR_ID()!=null)
    }

    @Override
    public int getItemCount() {
        return crlList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_mob;
        ImageView iv_call;
        ImageView iv_sms;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_tabHolderName);
            tv_mob = itemView.findViewById(R.id.tv_tabHolderMobNo);
            iv_call = itemView.findViewById(R.id.iv_call);
            iv_sms = itemView.findViewById(R.id.iv_message);

            iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel: "+crlList.get(getAdapterPosition()).getMobile()));
                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) { //todo check
                        //ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},1);
                    }
                    context.startActivity(callIntent);
                }
            });

            iv_sms.setOnClickListener(v ->
                    tabHolderListItemListener.sendSms(crlList.get(getAdapterPosition()).getMobile())
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

/*    public void filterList(ArrayList<DeviseList> filterdNames) {
        this.deviseList=filterdNames;
        notifyDataSetChanged();
    }*/
}
