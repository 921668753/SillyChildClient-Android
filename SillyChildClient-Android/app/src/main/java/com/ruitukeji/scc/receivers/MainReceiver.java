package com.ruitukeji.scc.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ruitukeji.scc.custominterfaces.MainCallBack;

/**
 * Created by Administrator on 2017/11/29.
 */

public class MainReceiver extends BroadcastReceiver {
    private MainCallBack mainCallBack;

    public MainReceiver(MainCallBack mainCallBack){
        this.mainCallBack=mainCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent!=null){
            mainCallBack.msgStyle(intent.getBooleanExtra("havemsg",false));
        }

    }
}