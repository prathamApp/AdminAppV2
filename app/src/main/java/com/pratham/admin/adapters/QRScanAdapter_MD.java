package com.pratham.admin.adapters;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pratham.admin.R;
import com.pratham.admin.activities.CustomDialogQRScan_MD;
import com.pratham.admin.interfaces.QRRecyclerListener;
import com.pratham.admin.modalclasses.TabletManageDevice;

import java.util.List;

public class QRScanAdapter_MD extends RecyclerView.Adapter<QRScanAdapter_MD.ViewHolder> {
    private List<TabletManageDevice> tabletManageDevices;
    QRRecyclerListener qrRecyclerListener;
    Context context;


    public QRScanAdapter_MD(CustomDialogQRScan_MD context, List<TabletManageDevice> tabletManageDevices) {
        this.tabletManageDevices = tabletManageDevices;
        this.qrRecyclerListener = (QRRecyclerListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qr_scan_row_md, parent, false);
        return new QRScanAdapter_MD.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String fname = tabletManageDevices.get(holder.getAdapterPosition()).getLogged_CRL_ID();
        if (tabletManageDevices.get(holder.getAdapterPosition()).getOldFlag() == true) {
            holder.parent_recycler_row.setBackgroundColor(Color.parseColor("#fdeddf"));
        } else {
            holder.parent_recycler_row.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        holder.ad_status.setText(tabletManageDevices.get(holder.getAdapterPosition()).getStatus());
        holder.ad_assignedTo.setText(tabletManageDevices.get(position).getAssigned_CRL_Name() + "( ID :" + tabletManageDevices.get(position).getAssigned_CRL_ID() + ")");
        holder.ad_AssignedBy.setText(tabletManageDevices.get(position).getLogged_CRL_NAME() + "( ID :" + tabletManageDevices.get(position).getLogged_CRL_ID() + ")");
        holder.qrID.setText(tabletManageDevices.get(position).getQR_ID());
        holder.pratham_id.setText(tabletManageDevices.get(position).getPratham_ID());
        if (tabletManageDevices.get(position).getVillageID() != null) {
            holder.village.setText(tabletManageDevices.get(position).getVillageName() + " (" + (tabletManageDevices.get(position).getVillageID()) + ")");
        }
        if (tabletManageDevices.get(position).getCollectedTabPrathamID() != null && !tabletManageDevices.get(position).getCollectedTabPrathamID().equalsIgnoreCase("")) {
            holder.collected_PrathamID.setText(tabletManageDevices.get(position).getCollectedTabPrathamID());
            holder.collected_QrID.setText(tabletManageDevices.get(position).getCollectedTabQrID());
        } else {
            holder.collected_PrathamID.setText(tabletManageDevices.get(position).getCollectedTab_serial_ID());
            holder.collected_QrID.setText(tabletManageDevices.get(position).getCollectedTabQrID());
        }

        if (tabletManageDevices.get(position).getPratham_ID() != null && !tabletManageDevices.get(position).getPratham_ID().equalsIgnoreCase("")) {
            holder.pratham_id.setText(tabletManageDevices.get(position).getPratham_ID());
            holder.qrID.setText(tabletManageDevices.get(position).getQR_ID());
        } else {
            holder.pratham_id.setText(tabletManageDevices.get(position).getTabSerial_ID());
            holder.qrID.setText(tabletManageDevices.get(position).getQR_ID());
        }

        /*  holder.serialId.setText(tabletManageDevices.get(position).getTabSerial_ID());*/

        /* String replaceStaatus = "";
        if (tabletManageDevices.get(position).getIs_Damaged() != null) {
            replaceStaatus = tabletManageDevices.get(position).getIs_Damaged() + " ";
        }
        if (tabletManageDevices.get(position).getDamageType() != null) {
            replaceStaatus += tabletManageDevices.get(position).getDamageType() + "  ";
        }
        if (tabletManageDevices.get(position).getComment() != null) {
            replaceStaatus += tabletManageDevices.get(position).getComment();
        }*/

        /* holder.newSerialID.setText(tabletManageDevices.get(position).getCollectedTab_serial_ID());*/

      /*  holder.checkBox_student.setText(fname);
        holder.txt_crl_id.setText(tabletManageDevices.get(holder.getAdapterPosition()).getAssigned_CRL_ID());
        holder.txt_qr_id.setText(tabletManageDevices.get(holder.getAdapterPosition()).getQR_ID());
        holder.txt_date.setText(tabletManageDevices.get(holder.getAdapterPosition()).getDate());
        holder.txt_pratham_id.setText(tabletManageDevices.get(holder.getAdapterPosition()).getPratham_ID());
        *//*   holder.txt_loggedIn_crl.setText(tabTracks.get(holder.getAdapterPosition()).getLoggedIn_CRL());*//*
         *//* holder.txt_sr_no.setText(tabletManageDevices.get(holder.getAdapterPosition()).getSerial_NO());*/
        holder.ad_iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrRecyclerListener.delete(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return tabletManageDevices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // @BindView(R.id.checkBox_student)
        TextView ad_status, ad_assignedTo, ad_AssignedBy, qrID, pratham_id, collected_PrathamID, collected_QrID, village;
        ImageView ad_iv_delete;
        ConstraintLayout parent_recycler_row;

        public ViewHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(this,itemView);
            ad_status = itemView.findViewById(R.id.ad_status);
            ad_assignedTo = itemView.findViewById(R.id.ad_assignedTo);
            ad_AssignedBy = itemView.findViewById(R.id.ad_AssignedBy);
            qrID = itemView.findViewById(R.id.qrID);
            pratham_id = itemView.findViewById(R.id.pratham_id);
            collected_PrathamID = itemView.findViewById(R.id.collected_PrathamID);
            collected_QrID = itemView.findViewById(R.id.collected_QrID);
            village = itemView.findViewById(R.id.village);
            ad_iv_delete = itemView.findViewById(R.id.ad_iv_delete);
            parent_recycler_row = itemView.findViewById(R.id.parent_recycler_row);
            /* serialId = itemView.findViewById(R.id.serialId);*/
            /*    newQrID = itemView.findViewById(R.id.newqr_id);*/
        }
    }
}