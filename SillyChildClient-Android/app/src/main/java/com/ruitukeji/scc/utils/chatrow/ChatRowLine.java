package com.ruitukeji.scc.utils.chatrow;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.utils.JsonUtil;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.easeui.adapter.MessageAdapter;
import com.hyphenate.helpdesk.easeui.widget.chatrow.ChatRow;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.LinePseudoAgreementBean;
import com.ruitukeji.scc.homepage.chartercustom.routes.RouteDetailsActivity;
import com.ruitukeji.scc.utils.GlideImageLoader;

/**
 * 路线线路
 * Created by liyuzhao on 15/12/2016.
 */

public class ChatRowLine extends ChatRow {


    private ImageView img_line;
    private TextView tv_title;
    private TextView tv_money;
    private TextView tv_viewDetails;
    private TextView contentView;
    private LinePseudoAgreementBean linePseudoAgreementBean;

    public ChatRowLine(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.item_message_line : com.hyphenate.helpdesk.R.layout.hd_row_sent_message, this);
    }

    @Override
    protected void onFindViewById() {
        ininView();
    }

    private void ininView() {
        contentView = (TextView) findViewById(com.hyphenate.helpdesk.R.id.tv_chatcontent);
        img_line = (ImageView) findViewById(R.id.img_line);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_money = (TextView) findViewById(R.id.tv_money);
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
                    if (linePseudoAgreementBean == null) {
                        return;
                    }
                    Intent intent = new Intent(context, RouteDetailsActivity.class);
                    intent.putExtra("line_id", linePseudoAgreementBean.getResult().getLine_id() + "");
                    context.startActivity(intent);
                }
            });
        }
    }

    private void initView() {
        String message1 = ((EMTextMessageBody) message.getBody()).getMessage();
        if (message1.startsWith(context.getString(R.string.linePseudoAgreement))) {
            message1 = message1.substring(5);
            Log.d("tag1111", message1);
            linePseudoAgreementBean = (LinePseudoAgreementBean) JsonUtil.getInstance().json2Obj(message1, LinePseudoAgreementBean.class);
            if (linePseudoAgreementBean == null) {
                return;
            }
            GlideImageLoader.glideOrdinaryLoader(context, linePseudoAgreementBean.getResult().getCover_img(), img_line, R.mipmap.placeholderfigure1);
            tv_title.setText(linePseudoAgreementBean.getResult().getLine_title());
            tv_money.setText(linePseudoAgreementBean.getResult().getLine_price());
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
