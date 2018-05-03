package com.ruitukeji.scc.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.constant.StringNewConstants;
import com.ruitukeji.scc.entity.HxUserListBean;
import com.ruitukeji.scc.entity.SystemMessageBean;
import com.ruitukeji.scc.retrofit.RequestClient;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/29.
 */

public class MainService extends Service {

    private Intent intentcast;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getSystemMessage();
        return super.onStartCommand(intent, flags, startId);
    }

    public void getSystemMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getSystemMessageWithContext(this,httpParams, 1, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(response, SystemMessageBean.class);
                if (systemMessageBean.getResult().getList() == null || systemMessageBean.getResult().getList().size() == 0) {
//                    mView.errorMsg(response, 0);
                    sendCast(false);
                    return;
                }
                if (systemMessageBean.getResult().getUnread() > 0) {
//                    mView.getSuccess("", 0);
                    sendCast(true);
                } else {
                    boolean isRefreshingHomePageFragment = PreferenceHelper.readBoolean(MainService.this, StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
                    if(!isRefreshingHomePageFragment){
                        getGuideMessage();
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                boolean isRefreshingHomePageFragment = PreferenceHelper.readBoolean(MainService.this, StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
                if(!isRefreshingHomePageFragment){
                    getGuideMessage();
                }
            }
        });
    }

    public void getGuideMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", "driver");
        httpParams.put("page", 1);
        httpParams.put("pageSize", 1);
        RequestClient.getHxUserListWithContext(this,httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                if (loadConversationList(response)) {
//                    mView.getSuccess(response, 0);
                    sendCast(true);
                } else {
//                    mView.errorMsg(response, 0);
                    sendCast(false);
                }
            }

            @Override
            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
                sendCast(false);
            }
        });
    }

    protected boolean loadConversationList(String response) {
        try{
            HxUserListBean hxUserListBean = (HxUserListBean) JsonUtil.getInstance().json2Obj(response, HxUserListBean.class);
            if (hxUserListBean == null || hxUserListBean.getResult() == null || hxUserListBean.getResult().size() == 0) {
                return false;
            }
            List<HxUserListBean.ResultBean> hxUserList = hxUserListBean.getResult();
            Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
            synchronized (conversations) {
                for (EMConversation conversation : conversations.values()) {
                    if (conversation.getAllMessages().size() != 0) {
                        try {
                            for (int i = 0; i < hxUserList.size(); i++) {
                                if (hxUserList.get(i).getHxName().equals(conversation.conversationId())) {
                                    Log.d("tag1111", conversation.conversationId());
                                    if (conversation.getUnreadMsgCount() > 0) {
                                        return true;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }

    private void sendCast(boolean havemsg){
        if (intentcast==null) intentcast=new Intent(StringNewConstants.MainServiceAction);
        intentcast.putExtra("havemsg",havemsg);
        sendBroadcast(intentcast);
        stopSelf();
    }
}
