package com.ruitukeji.scc.utils.chatrow;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.common.cklibrary.utils.JsonUtil;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.easeui.adapter.MessageAdapter;
import com.hyphenate.helpdesk.easeui.widget.chatrow.ChatRow;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.OnceOrderPseudoAgreementBean;
import com.ruitukeji.scc.mine.myorder.orderDetails.CharterOrderDetailsActivity;

/**
 * 单次接送客服回复
 */
public class ChatRowServiceText extends ChatRow {

    private TextView contentView;
    private TextView tv_orderNumberThreeWord;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_adult1;
    private TextView tv_children1;
    private TextView tv_dates;
    private TextView tv_goPayment1;
    private OnceOrderPseudoAgreementBean orderPseudoAgreementBean;

    public ChatRowServiceText(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? com.hyphenate.helpdesk.R.layout.item_message_customerservice1 : com.hyphenate.helpdesk.R.layout.hd_row_sent_message, this);
    }

    @Override
    protected void onFindViewById() {
        contentView = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_chatcontent);
        tv_orderNumberThreeWord = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_orderNumberThreeWord);
        tv_title = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_title);
        tv_price = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_price);
        tv_adult1 = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_adults);
        tv_children1 = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_children1);
        tv_dates = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_dates);
        tv_goPayment1 = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_goPayment1);
    }

    @Override
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        //   Spannable span = SmileUtils.getSmiledText(context, txtBody.getMessage());
        Log.d("tag1111", "11111");
        // 设置内容
        handleTextMessage();
    }

    protected void handleTextMessage() {

        boolean isShowProgress = UIProvider.getInstance().isShowProgress();
        if (message.direct() == Message.Direct.SEND) {
            contentView.setText(((EMTextMessageBody) message.getBody()).getMessage());
            setMessageSendCallback();
            switch (message.getStatus()) {
                case CREATE:
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.VISIBLE);
                    // 发送消息
                    break;
                case SUCCESS: // 发送成功
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.GONE);
                    break;
                case FAIL: // 发送失败
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.VISIBLE);
                    break;
                case INPROGRESS: // 发送中
                    if (isShowProgress)
                        progressBar.setVisibility(View.VISIBLE);
                    statusView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        } else {
            initView();
            tv_goPayment1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (orderPseudoAgreementBean == null) {
                        return;
                    }
                    Intent intent = new Intent(context, CharterOrderDetailsActivity.class);
                    // intent.putExtra("paymoney", tv_price.getText().toString());
                    intent.putExtra("airid", orderPseudoAgreementBean.getResult().getAir_id() + "");
                    context.startActivity(intent);
                }
            });
        }
    }

    private void initView() {
        String message1 = ((EMTextMessageBody) message.getBody()).getMessage();
        if (message1.startsWith(context.getString(R.string.orderPseudoAgreement))) {
            message1 = message1.substring(11);
            Log.d("tag1111", message1);
            orderPseudoAgreementBean = (OnceOrderPseudoAgreementBean) JsonUtil.getInstance().json2Obj(message1, OnceOrderPseudoAgreementBean.class);
            if (orderPseudoAgreementBean == null) {
                return;
            }
            tv_orderNumberThreeWord.setText(orderPseudoAgreementBean.getResult().getOrder_sn());
            tv_price.setText(orderPseudoAgreementBean.getResult().getTotal_price());
            tv_adult1.setText(orderPseudoAgreementBean.getResult().getUse_car_adult());
            tv_children1.setText(orderPseudoAgreementBean.getResult().getUse_car_children());
            tv_title.setText(orderPseudoAgreementBean.getResult().getTitle());
            tv_dates.setText(orderPseudoAgreementBean.getResult().getWork_at());
        }
    }


    @Override
    protected void onUpdateView() {
        if (adapter instanceof MessageAdapter) {
            ((MessageAdapter) adapter).refresh();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onBubbleClick() {
        // TODO Auto-generated method stub

    }


}