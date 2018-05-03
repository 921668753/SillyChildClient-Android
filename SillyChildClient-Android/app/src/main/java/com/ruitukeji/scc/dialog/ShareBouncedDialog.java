package com.ruitukeji.scc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.ViewInject;
import com.ruitukeji.scc.R;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 分享--------分享弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class ShareBouncedDialog extends Dialog implements View.OnClickListener {


    private Context context;
    private LinearLayout ll_weChatFriends;
    private LinearLayout ll_circleFriends;
    private LinearLayout ll_QQFriends;
    private LinearLayout ll_sinaWeibo;
    private TextView tv_cancel;

    public ShareBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sharebounced);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        ll_weChatFriends = (LinearLayout) findViewById(R.id.ll_weChatFriends);
        ll_weChatFriends.setOnClickListener(this);
        ll_circleFriends = (LinearLayout) findViewById(R.id.ll_circleFriends);
        ll_circleFriends.setOnClickListener(this);
        ll_QQFriends = (LinearLayout) findViewById(R.id.ll_QQFriends);
        ll_QQFriends.setOnClickListener(this);
        ll_sinaWeibo = (LinearLayout) findViewById(R.id.ll_sinaWeibo);
        ll_sinaWeibo.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weChatFriends:
                dismiss();
                share(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.ll_circleFriends:
                dismiss();
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.ll_QQFriends:
                dismiss();
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.ll_sinaWeibo:
                dismiss();
                ViewInject.toast("暂未开发");
                //   share(SHARE_MEDIA.QZONE);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public abstract void share(SHARE_MEDIA platform);


}
