package com.pratham.admin.interfaces;

import com.pratham.admin.modalclasses.DeviseList;

public interface DevicePrathamIdLisner {
   public void getPrathamId(String prathamId,String QrId);
   public void setDeviceDetail(String prathamId,String qrId, String deviceId, String serNo, String tabDetail);
}
