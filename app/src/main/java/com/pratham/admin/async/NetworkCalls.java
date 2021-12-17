package com.pratham.admin.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.interfaces.NetworkCallListnerSelectProgram;
import com.pratham.admin.util.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class NetworkCalls {
    private static NetworkCalls networkCalls;
    private ProgressDialog dialog;
    //  private NetworkCallListener networkCallListner;
    static Context mContext;

    private NetworkCalls() {
    }

    public static NetworkCalls getNetworkCallsInstance(Context context) {
        mContext = context;
        if (networkCalls == null) {
            networkCalls = new NetworkCalls();
        }
        return networkCalls;
    }


    //push data(json) to url      used in Activity_QRScan,AssignTabletMD
    public void postRequest(final NetworkCallListener networkCallListener, String url, String msg, String json, final String header) {
/*        dialog = new ProgressDialog(mContext);
        dialog.setTitle(msg);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();*/
        AndroidNetworking.post(url).setContentType("application/json").addStringBody(json).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                networkCallListener.onResponse(response, header);
                //dialog.dismiss();
            }

            @Override
            public void onError(ANError anError) {
                networkCallListener.onError(anError, header);
                //dialog.dismiss();
            }
        });
    }

    public void postRequestAsJSONObject(final NetworkCallListener networkCallListener, String url, String msg, JSONObject jsonObject, final String header) {
/*        dialog = new ProgressDialog(mContext);
        dialog.setTitle(msg);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();*/
        AndroidNetworking.post(url).setContentType("application/json").addJSONObjectBody(jsonObject).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                networkCallListener.onResponse(response, header);
                //dialog.dismiss();
            }

            @Override
            public void onError(ANError anError) {
                networkCallListener.onError(anError, header);
                //dialog.dismiss();
            }
        });
    }

    public void getRequest(final NetworkCallListener networkCallListener, String url, String msg, final String header) {
        dialog = new ProgressDialog(mContext);
        dialog.setTitle(msg);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        AndroidNetworking.get(url).setPriority(Priority.MEDIUM).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                networkCallListener.onResponse(response.toString(), header);
                dialog.dismiss();
            }

            @Override
            public void onError(ANError anError) {
                networkCallListener.onError(anError, header);
                dialog.dismiss();
            }
        });
    }

    //for new requests with context to show loading dialog
    public void getRequestNew(final NetworkCallListener networkCallListener, String url, String msg, final String header, Context context) {
        Utility.showLoadingDialog(context);
        AndroidNetworking.get(url).setPriority(Priority.MEDIUM).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                Utility.dismissLoadingDialog();
                networkCallListener.onResponse(response.toString(), header);
            }

            @Override
            public void onError(ANError anError) {
                Utility.dismissLoadingDialog();
                networkCallListener.onError(anError, header);
            }
        });
    }

    //for new requests with context to show loading dialog
    public void getRequestJsonObject(final NetworkCallListener networkCallListener, String url, String msg, final String header, Context context) {
        //Utility.showLoadingDialog(context);
        AndroidNetworking.get(url).setPriority(Priority.MEDIUM).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Utility.dismissLoadingDialog();
                networkCallListener.onResponse(response.toString(), header);
            }

            @Override
            public void onError(ANError anError) {
                Utility.dismissLoadingDialog();
                networkCallListener.onError(anError, header);
            }
        });
    }


    /*SELECT PROGRAM Calls*/
    public void getRequestWithProgram(final NetworkCallListnerSelectProgram networkCallListnerSelectProgram, String url, final String header, final String type, final String program) {
        AndroidNetworking.get(url).setPriority(Priority.MEDIUM).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                networkCallListnerSelectProgram.onResponce(response.toString(), header, type, program);

            }

            @Override
            public void onError(ANError anError) {
                networkCallListnerSelectProgram.onError(anError, header, type, program);
            }
        });
    }

    public void getRequestWithautLoader(final NetworkCallListener networkCallListener, String url, final String header) {
        AndroidNetworking.get(url).setPriority(Priority.MEDIUM).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                networkCallListener.onResponse(response.toString(), header);

            }

            @Override
            public void onError(ANError anError) {
                networkCallListener.onError(anError, header);
            }
        });
    }

    public void pushImageToInternet(final NetworkCallListener networkCallListener, String url, File file, String header) {
        AndroidNetworking.upload(url)
                .addMultipartFile("image",file)
                .addMultipartParameter("key", "value")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                        Log.e("progress : ",String.valueOf(bytesUploaded));
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        networkCallListener.onResponse(response.toString(), header);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        networkCallListener.onError(error, header);
                    }
                });
    }

}
