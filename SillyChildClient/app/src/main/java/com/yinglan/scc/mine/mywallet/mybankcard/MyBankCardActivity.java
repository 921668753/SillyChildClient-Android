package com.yinglan.scc.mine.mywallet.mybankcard;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.mine.mywallet.mybankcard.MyBankCardViewAdapter;
import com.yinglan.scc.entity.mine.mywallet.mybankcard.MyBankCardBean;


/**
 * 我的银行卡
 * Created by Administrator on 2017/11/30.
 */

public class MyBankCardActivity extends BaseActivity implements MyBankCardContract.View, AdapterView.OnItemClickListener {

    /**
     * 银行卡列表
     */
    @BindView(id = R.id.lv_bankCard)
    private ChildListView lv_bankCard;

    private MyBankCardViewAdapter myBankCardViewAdapter;

    /**
     * 添加银行卡
     */
    @BindView(id = R.id.ll_addBankCard, click = true)
    private LinearLayout ll_addBankCard;

    private int type = 0;
    private Handler handler = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mybankcard);
    }

    @Override
    public void initData() {
        super.initData();
        myBankCardViewAdapter = new MyBankCardViewAdapter(this);
        handler = new Handler();
        mPresenter = new MyBankCardPresenter(this);
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myBankCard), true, R.id.titlebar);
        lv_bankCard.setAdapter(myBankCardViewAdapter);
        lv_bankCard.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_addBankCard:
                showActivity(aty, AddBankCardActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type == 1) {
            MyBankCardBean.ResultBean resultBean = myBankCardViewAdapter.getItem(position);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("bankCardName", resultBean.getBank());
            intent.putExtra("bankCardNun", resultBean.getBank_card());
            intent.putExtra("bankCardId", resultBean.getId());
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
            return;
        }
    }

    @Override
    public void setPresenter(MyBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        MyBankCardBean myBankCardBean = (MyBankCardBean) JsonUtil.getInstance().json2Obj(success, MyBankCardBean.class);
        myBankCardViewAdapter.clear();
        if (myBankCardBean.getResult() != null && myBankCardBean.getResult().size() > 0) {
            myBankCardViewAdapter.addNewData(myBankCardBean.getResult());
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (msg.trim().equals("暂无信息")) {
            return;
        }
//        if (!toLigon1(msg)) {
//            finish();
//            return;
//        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusAddBankCardEvent")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
                }
            }, 800);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myBankCardViewAdapter.clear();
        myBankCardViewAdapter = null;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
