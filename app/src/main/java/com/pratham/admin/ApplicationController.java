package com.pratham.admin;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatDelegate;

import com.androidnetworking.AndroidNetworking;
//import com.facebook.stetho.Stetho;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.LocaleManager;

public class ApplicationController extends Application {
    private static ApplicationController INSTANCE;

    public ApplicationController() {
        INSTANCE = this;
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        FastSave.init(getApplicationContext());
        INSTANCE = this;
       /* if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }*/
    }

    public static synchronized ApplicationController getInstance() {
        return INSTANCE;
    }

    public void setConnectionListener(ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }



}
