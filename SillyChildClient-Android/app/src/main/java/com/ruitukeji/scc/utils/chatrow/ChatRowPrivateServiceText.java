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
import com.ruitukeji.scc.entity.PrivateOrderPseudoAgreementBean;
import com.ruitukeji.scc.homepage.chartercustom.personaltailor.ScheduleDetailsActivity;

/**
 * 私人订制客服回复
 */
public class ChatRowPrivateServiceText extends ChatRow {

    private TextView contentView;
    private TextView tv_classification;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_adult1;
    private TextView tv_children;
    private TextView tv_destination;
    private TextView tv_viewDetails;
    private PrivateOrderPseudoAgreementBean privateOrderPseudoAgreementBean;

    public ChatRowPrivateServiceText(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.item_message_customerservice : com.hyphenate.helpdesk.R.layout.hd_row_sent_message, this);
    }

    @Override
    protected void onFindViewById() {
        contentView = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_chatcontent);
        tv_classification = (TextView) findViewById(R.id.tv_classification);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_adult1 = (TextView) findViewById(R.id.tv_adults);
        tv_children = (TextView) findViewById(R.id.tv_children);
        tv_destination = (TextView) findViewById(R.id.tv_destination);
        tv_viewDetails = (TextView) findViewById(R.id.tv_viewDetails);
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
            tv_viewDetails.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (privateOrderPseudoAgreementBean == null) {
                        return;
                    }
                    Intent intent = new Intent(context, ScheduleDetailsActivity.class);
                    // intent.putExtra("paymoney", tv_price.getText().toString());
                    intent.putExtra("air_id", privateOrderPseudoAgreementBean.getResult().getAir_id() + "");
                    context.startActivity(intent);
                }
            });
        }
    }

    private void initView() {
        String message1 = ((EMTextMessageBody) message.getBody()).getMessage();
        if (message1.startsWith(context.getString(R.string.privatePseudoAgreement))) {
            message1 = message1.substring(14);
            Log.d("tag1111", message1);
            privateOrderPseudoAgreementBean = (PrivateOrderPseudoAgreementBean) JsonUtil.getInstance().json2Obj(message1, PrivateOrderPseudoAgreementBean.class);
            if (privateOrderPseudoAgreementBean == null) {
                return;
            }
            tv_classification.setText(privateOrderPseudoAgreementBean.getResult().getOrder_type_str());
            tv_price.setText(privateOrderPseudoAgreementBean.getResult().getTotal_price());
            tv_adult1.setText(privateOrderPseudoAgreementBean.getResult().getUse_car_adult());
            tv_children.setText(privateOrderPseudoAgreementBean.getResult().getUse_car_children());
            tv_title.setText(privateOrderPseudoAgreementBean.getResult().getTitle());
            tv_destination.setText(privateOrderPseudoAgreementBean.getResult().getDest_address());
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