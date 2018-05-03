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
 * 包车详情----送机
 */
public class ChatRowSendMachineInformation extends ChatRow {


    private TextView tv_flightNumber1;
    private TextView tv_contactWay1;
    private TextView tv_adult;
    private TextView tv_children;
    private TextView tv_departureTime1;
    private TextView tv_specifyCompanyGuide1;
    private TextView tv_remark;
    private TextView tv_deliveredAirport1;
    private TextView tv_departureTime;
    private TextView tv_deliveredSite1;
    private TextView tv_placeDeparture1;
    private TextView tv_name1;
    private TextView tv_seating1;
    private TextView tv_chatcontent;
    private ImageView iv_userhead;
    private TextView tv_acid;
    private ImageView iv_achead;

    private LinearLayout ll_bags;
    private TextView tv_tags;
    private TextView tv_tags1;
    private TextView tv_tags2;
    private TextView tv_tags3;

    public ChatRowSendMachineInformation(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.item_message_customerservice1 : R.layout.item_message_sendmachineinformation, this);
    }

    @Override
    protected void onFindViewById() {
        initView();
    }

    /**
     * 送机
     */
    private void initView() {
        tv_flightNumber1 = (TextView) findViewById(R.id.tv_flightNumber1);
        tv_deliveredAirport1 = (TextView) findViewById(R.id.tv_deliveredAirport1);
        tv_departureTime = (TextView) findViewById(R.id.tv_departureTime);
        tv_deliveredSite1 = (TextView) findViewById(R.id.tv_deliveredSite1);
        tv_placeDeparture1 = (TextView) findViewById(R.id.tv_placeDeparture1);
        tv_name1 = (TextView) findViewById(R.id.tv_name1);
        tv_contactWay1 = (TextView) findViewById(R.id.tv_contactWay1);

        tv_adult = (TextView) findViewById(R.id.tv_adult);

        tv_children = (TextView) findViewById(R.id.tv_children);

        tv_departureTime1 = (TextView) findViewById(R.id.tv_departureTime1);
        tv_seating1 = (TextView) findViewById(R.id.tv_seating);
        tv_specifyCompanyGuide1 = (TextView) findViewById(R.id.tv_specifyCompanyGuide1);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        tv_chatcontent = (TextView) findViewById(R.id.tv_chatcontent);
        tv_acid = (TextView) findViewById(R.id.tv_acid);
        iv_achead = (ImageView) findViewById(R.id.iv_achead);
        ll_bags = (LinearLayout) findViewById(R.id.ll_bags);
        tv_tags = (TextView) findViewById(R.id.tv_tags);
        tv_tags1 = (TextView) findViewById(R.id.tv_tags1);
        tv_tags2 = (TextView) findViewById(R.id.tv_tags2);
        tv_tags3 = (TextView) findViewById(R.id.tv_tags3);
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        if (message.direct() == Message.Direct.RECEIVE) {
            //设置内容
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
            // iv_achead.setImageResource(R.drawable.hd_default_avatar);
            GlideImageLoader.glideLoader(context, R.drawable.hd_default_avatar, iv_achead, 0, R.drawable.hd_default_avatar);

        } else {
            GlideImageLoader.glideLoader(context, head_pic, iv_achead, 0, R.drawable.hd_default_avatar);
        }
        initDataInformation();
        String imageUrl = visitorTrack.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            //    Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(com.hyphenate.helpdesk.R.drawable.hd_default_image).into(mImageView);
        }
    }


    private void initDataInformation() {
        try {
            tv_flightNumber1.setText(message.getStringAttribute("flt_no"));
            tv_deliveredAirport1.setText(message.getStringAttribute("airport_name"));
            tv_placeDeparture1.setText(message.getStringAttribute("start_address"));

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

            tv_name1.setText(message.getStringAttribute("user_name"));
            tv_contactWay1.setText(message.getStringAttribute("connect"));

            tv_adult.setText(message.getStringAttribute("adult_num"));
            tv_children.setText(message.getStringAttribute("child_num"));

            tv_departureTime1.setText(message.getStringAttribute("start_time"));
            tv_seating1.setText(message.getStringAttribute("selectModels"));
            tv_specifyCompanyGuide1.setText(message.getStringAttribute("drv_code"));
            if (StringUtils.isEmpty(message.getStringAttribute("remark"))) {
                tv_remark.setText(getResources().getString(R.string.notAvailable));
            } else {
                tv_remark.setText(message.getStringAttribute("remark"));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onBubbleClick() {

    }
}
