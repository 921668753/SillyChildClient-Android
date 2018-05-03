package com.ruitukeji.scc.utils.chatrow;

import android.content.Context;
import android.text.TextUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.StringConstants;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.helpdesk.easeui.widget.chatrow.ChatRow;
import com.hyphenate.helpdesk.model.MessageHelper;
import com.hyphenate.helpdesk.model.VisitorTrack;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.utils.GlideImageLoader;

/**
 * 按天包车游
 * Created by liyuzhao on 15/12/2016.
 */

public class ChatRowByTheDay extends ChatRow {

    private TextView tv_destination1;
    private TextView tv_adult;
    private TextView tv_children;
    private TextView tv_charterDate1;
    private TextView tv_specifyCompanyGuide1;
    private TextView tv_seating;
    private TextView tv_customerName;
    private TextView tv_contactWay2;
    private TextView tv_remark;
    private TextView tv_chatcontent;
    private TextView tv_acid;
    private ImageView iv_achead;
    private LinearLayout ll_bags;
    private TextView tv_tags;
    private TextView tv_tags1;
    private TextView tv_tags2;
    private TextView tv_tags3;
    private TextView tv_origin1;

    public ChatRowByTheDay(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.hd_row_received_message : R.layout.item_message_bytheday, this);
    }

    @Override
    protected void onFindViewById() {
        ininView();
    }

    private void ininView() {
        tv_origin1 = (TextView) findViewById(R.id.tv_origin1);
        tv_destination1 = (TextView) findViewById(R.id.tv_destination1);
        tv_adult = (TextView) findViewById(R.id.tv_adult);
        tv_children = (TextView) findViewById(R.id.tv_children);
        tv_charterDate1 = (TextView) findViewById(R.id.tv_charterDate1);
        tv_specifyCompanyGuide1 = (TextView) findViewById(R.id.tv_specifyCompanyGuide1);
        tv_seating = (TextView) findViewById(R.id.tv_seating);

        ll_bags = (LinearLayout) findViewById(R.id.ll_bags);
        tv_tags = (TextView) findViewById(R.id.tv_tags);
        tv_tags1 = (TextView) findViewById(R.id.tv_tags1);
        tv_tags2 = (TextView) findViewById(R.id.tv_tags2);
        tv_tags3 = (TextView) findViewById(R.id.tv_tags3);

        tv_customerName = (TextView) findViewById(R.id.tv_customerName);
        tv_contactWay2 = (TextView) findViewById(R.id.tv_contactWay2);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        tv_chatcontent = (TextView) findViewById(R.id.tv_chatcontent);
        tv_acid = (TextView) findViewById(R.id.tv_acid);
        iv_achead = (ImageView) findViewById(R.id.iv_achead);
    }


    @Override
    protected void onUpdateView() {

    }


    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        if (message.direct() == Message.Direct.RECEIVE) {
            tv_chatcontent.setText(txtBody.getMessage());
            return;
        }
        VisitorTrack visitorTrack = MessageHelper.getVisitorTrack(message);
//        if (visitorTrack == null) {
//            return;
//        }
        String mobile = PreferenceHelper.readString(context, StringConstants.FILENAME, "mobile", context.getString(R.string.visitors));
        String nickname = PreferenceHelper.readString(context, StringConstants.FILENAME, "nickname", mobile);
        tv_acid.setText(nickname);
        String head_pic = PreferenceHelper.readString(context, StringConstants.FILENAME, "head_pic");
        if (StringUtils.isEmpty(head_pic)) {
            //  iv_achead.setImageResource(R.drawable.hd_default_avatar);
            GlideImageLoader.glideLoader(context, R.drawable.hd_default_avatar, iv_achead, 0, R.drawable.hd_default_avatar);
        } else {
            GlideImageLoader.glideLoader(context, head_pic, iv_achead, 0, R.drawable.hd_default_avatar);
        }
        try {

            tv_origin1.setText(message.getStringAttribute("start_address"));
            tv_destination1.setText(message.getStringAttribute("end_address"));
            tv_adult.setText(message.getStringAttribute("adult_num"));
            tv_children.setText(message.getStringAttribute("child_num"));
            tv_charterDate1.setText(message.getStringAttribute("pack_time"));
            tv_specifyCompanyGuide1.setText(message.getStringAttribute("drv_code"));
            tv_seating.setText(message.getStringAttribute("selectModels"));

            String tags = message.getStringAttribute("twenty_four");
            String tags1 = message.getStringAttribute("twenty_six");
            String tags2 = message.getStringAttribute("twenty_eight");
            String tags3 = message.getStringAttribute("thirty");
            int totalNumber = StringUtils.toInt(tags, 0) + StringUtils.toInt(tags1, 0) + StringUtils.toInt(tags2, 0) + StringUtils.toInt(tags3, 0);
            if (totalNumber <= 0) {
                ll_bags.setVisibility(GONE);
            } else {
                ll_bags.setVisibility(VISIBLE);
                if (StringUtils.toInt(tags, 0) <= 0) {
                    tv_tags.setVisibility(GONE);
                } else {
                    tv_tags.setVisibility(VISIBLE);
                    tv_tags.setText(tags + "*" + context.getString(R.string.bags24));
                }
                if (StringUtils.toInt(tags1, 0) <= 0) {
                    tv_tags1.setVisibility(GONE);
                } else {
                    tv_tags1.setVisibility(VISIBLE);
                    tv_tags1.setText(tags1 + "*" + context.getString(R.string.bags26));
                }
                if (StringUtils.toInt(tags2, 0) <= 0) {
                    tv_tags2.setVisibility(GONE);
                } else {
                    tv_tags2.setVisibility(VISIBLE);
                    tv_tags2.setText(tags2 + "*" + context.getString(R.string.bags28));
                }
                if (StringUtils.toInt(tags3, 0) <= 0) {
                    tv_tags3.setVisibility(GONE);
                } else {
                    tv_tags3.setVisibility(VISIBLE);
                    tv_tags3.setText(tags3 + "*" + context.getString(R.string.bags30));
                }
            }

            tv_customerName.setText(message.getStringAttribute("user_name"));
            tv_contactWay2.setText(message.getStringAttribute("connect"));
            if (StringUtils.isEmpty(message.getStringAttribute("remark"))) {
                tv_remark.setText(getResources().getString(R.string.notAvailable));
            } else {
                tv_remark.setText(message.getStringAttribute("remark"));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        String imageUrl = visitorTrack.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            //    Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(com.hyphenate.helpdesk.R.drawable.hd_default_image).into(mImageView);
        }
    }

    @Override
    protected void onBubbleClick() {

    }
}
