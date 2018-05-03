package com.ruitukeji.scc.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.hyphenate.helpdesk.easeui.ui.BaseActivity;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.utils.easeim.runtimepermissions.PermissionsManager;

/**
 * 聊天界面
 */
public class ChatMessageActivity extends BaseActivity {
    public static ChatMessageActivity activityInstance;
    private ChatMessageFragment chatFragment;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        toChatUsername = getIntent().getExtras().getString("userId");
        chatFragment = new ChatMessageFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        super.onNewIntent(intent);
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
        setIntent(intent);

    }
//
//    @Override
//    public void onBackPressed() {
//        // chatFragment.onBackPressed();
//
//    }

    public String getToChatUsername() {
        return toChatUsername;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


    /**
     * 返回键监听
     * 关闭activity
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
