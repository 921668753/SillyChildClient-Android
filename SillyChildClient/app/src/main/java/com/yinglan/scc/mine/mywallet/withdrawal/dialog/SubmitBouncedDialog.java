package com.yinglan.scc.mine.mywallet.withdrawal.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scc.R;


/**
 * 货主认证---提交弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class SubmitBouncedDialog extends BaseDialog implements View.OnClickListener {

    private Context context;
    private TextView tv_cancel;
    private TextView tv_determine;
    private TextView tv_content;

    public SubmitBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.dialog_markedasreadbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_content = (TextView) findViewById(R.id.tv_content);
        //  tv_content.setText(context.getString(R.string.confirmSubmit1));
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                confirm();
                break;
        }
    }

    public abstract void confirm();
}
