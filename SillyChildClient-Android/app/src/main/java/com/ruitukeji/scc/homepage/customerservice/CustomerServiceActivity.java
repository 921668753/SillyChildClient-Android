package com.ruitukeji.scc.homepage.customerservice;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.common.cklibrary.common.KJActivityStack;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.recorder.MediaManager;
import com.hyphenate.helpdesk.easeui.ui.ChatFragment;
import com.hyphenate.helpdesk.easeui.util.CommonUtils;
import com.hyphenate.helpdesk.easeui.util.Config;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.hyphenate.helpdesk.model.OrderInfo;
import com.hyphenate.helpdesk.model.VisitorInfo;
import com.hyphenate.helpdesk.model.VisitorTrack;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.constant.URLConstants;
import com.ruitukeji.scc.homepage.chartercustom.bytheday.ByTheDayActivity;
import com.ruitukeji.scc.homepage.chartercustom.personaltailor.PersonalTailorActivity;
import com.ruitukeji.scc.homepage.chartercustom.singletransport.SingleTransportActivity;
import com.ruitukeji.scc.homepage.chartercustom.transfer.TransferActivity;
import com.ruitukeji.scc.main.MainActivity;

/**
 * 客服客服聊天
 * Created by Admin on 2017/9/13.
 */

public class CustomerServiceActivity extends CustomChatBaseActivity {

    public static CustomerServiceActivity instance = null;

    private ChatFragment chatFragment;

    String toChatUsername;
    private Intent intent;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.hd_activity_chat);
        instance = this;
        intent = getIntent();
        //IM服务号
        toChatUsername = getIntent().getExtras().getString(Config.EXTRA_SERVICE_IM_NUMBER);
        //  Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername);
        //指定会话消息未读数清零
        if (conversation != null) {
            conversation.markAllMessagesAsRead();
        }

////获取此会话的所有消息
//        List<EMMessage> messages = conversation.getAllMessages();
////sdk初始化加载的聊天记录为20条，到顶时需要去db里获取更多
////获取startMsgId之前的pagesize条消息，此方法获取的messages sdk会自动存入到此会话中，app中无需再次把获取到的messages添加到会话中
//        List<EMMessage> messages1 = conversation.loadMoreMsgFromDB(conversation.getLastMessage().getMsgId(), 20);
        //可以直接new ChatFragment使用
        String chatFragmentTAG = "chatFragment";
//        chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentByTag(chatFragmentTAG);
        if (chatFragment == null) {
            chatFragment = new CustomChatFragment();
            //传入参数
            chatFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment, chatFragmentTAG).commit();
            sendOrderOrTrack();
        }

    }


    /**
     * 发送订单或轨迹消息
     */
    private void sendOrderOrTrack() {
        Bundle bundle = getIntent().getBundleExtra(Config.EXTRA_BUNDLE);
        if (bundle != null) {
            //检查是否是从某个商品详情进来
            int selectedIndex = bundle.getInt("img_selected", 0);
            switch (selectedIndex) {
                case 1:
                    sendTrackMessage(selectedIndex);
                    break;
                case 2:
                    sendOrderMessage(selectedIndex);
                    break;
                case 3:
                    break;
                case 4:
                    //    sendTrackMessage(selectedIndex);
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 发送订单消息
     * <p>
     * 不发送则是saveMessage
     *
     * @param selectedIndex
     */
    private void sendOrderMessage(int selectedIndex) {
        Message message = Message.createTxtSendMessage(getMessageContent(selectedIndex), toChatUsername);
        message.addContent(createOrderInfo(this, selectedIndex));
        ChatClient.getInstance().chatManager().saveMessage(message);
    }

    /**
     * 发送轨迹消息
     *
     * @param selectedIndex
     */
    private void sendTrackMessage(int selectedIndex) {
        Message message = Message.createTxtSendMessage(getMessageContent(selectedIndex), toChatUsername);
        VisitorInfo visitorInfo = (VisitorInfo) getIntent().getParcelableExtra(Config.EXTRA_VISITOR_INFO);
        if (visitorInfo != null) {
            message.addContent(visitorInfo);
        }
        message.addContent(createVisitorTrack(this, intent.getStringExtra("id"), intent.getStringExtra("chatMessage")));
        if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("0")) {
            KJActivityStack.create().finishActivity(TransferActivity.class);
            message.setAttribute("remark", intent.getStringExtra("remark"));
            message.setAttribute("chatMessage", intent.getStringExtra("chatMessage"));
            putMessageAirport(message);
        } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("1")) {
            KJActivityStack.create().finishActivity(TransferActivity.class);
            message.setAttribute("remark", intent.getStringExtra("remark"));
            message.setAttribute("chatMessage", intent.getStringExtra("chatMessage"));
            putMessageAirport(message);
        } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("2")) {
            KJActivityStack.create().finishActivity(SingleTransportActivity.class);
            message.setAttribute("remark", intent.getStringExtra("remark"));
            message.setAttribute("chatMessage", intent.getStringExtra("chatMessage"));
            putMessageSingleTransport(message);
        } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("3")) {
            KJActivityStack.create().finishActivity(PersonalTailorActivity.class);
            //  message.setAttribute("remark", intent.getStringExtra("remark"));
            message.setAttribute("chatMessage", intent.getStringExtra("chatMessage"));
            putMessagePersonalTailor(message);
        } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("4")) {
            KJActivityStack.create().finishActivity(ByTheDayActivity.class);
            message.setAttribute("remark", intent.getStringExtra("remark"));
            message.setAttribute("chatMessage", intent.getStringExtra("chatMessage"));
            putMessageByTheDay(message);
        }
        ChatClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 接送机
     */
    private void putMessageAirport(Message message) {
        message.setAttribute("flt_no", intent.getStringExtra("flightNumber"));
        message.setAttribute("airport_name", intent.getStringExtra("pickUpAtAirport"));
        message.setAttribute("start_address", intent.getStringExtra("deliveredSite"));
        message.setAttribute("selectModels", intent.getStringExtra("selectModels"));
        message.setAttribute("drv_code", intent.getStringExtra("workNumber"));
        message.setAttribute("user_name", intent.getStringExtra("name"));
        message.setAttribute("twenty_four", intent.getStringExtra("twenty_four"));
        message.setAttribute("twenty_six", intent.getStringExtra("twenty_six"));
        message.setAttribute("twenty_eight", intent.getStringExtra("twenty_eight"));
        message.setAttribute("thirty", intent.getStringExtra("thirty"));
        message.setAttribute("connect", intent.getStringExtra("contactWay"));
        message.setAttribute("adult_num", intent.getStringExtra("fewAdults"));
        message.setAttribute("child_num", intent.getStringExtra("severalChildren"));

//        message.setAttribute("number", intent.getStringExtra("number"));
        message.setAttribute("start_time", intent.getStringExtra("departureTime"));
    }

    /**
     * 单次接送
     */
    private void putMessageSingleTransport(Message message) {

        message.setAttribute("user_car_time", intent.getStringExtra("vehicleTime"));
        message.setAttribute("adult_num", intent.getStringExtra("fewAdults"));
        message.setAttribute("child_num", intent.getStringExtra("severalChildren"));
        message.setAttribute("selectModels", intent.getStringExtra("selectModels"));
        message.setAttribute("start_address", intent.getStringExtra("whereDoYouStart"));
        message.setAttribute("end_address", intent.getStringExtra("whereAreGoing"));
        message.setAttribute("drv_code", intent.getStringExtra("workNumber"));

        message.setAttribute("twenty_four", intent.getStringExtra("twenty_four"));
        message.setAttribute("twenty_six", intent.getStringExtra("twenty_six"));
        message.setAttribute("twenty_eight", intent.getStringExtra("twenty_eight"));
        message.setAttribute("thirty", intent.getStringExtra("thirty"));

        message.setAttribute("user_name", intent.getStringExtra("name"));
        message.setAttribute("connect", intent.getStringExtra("contactWay"));
    }

    /**
     * 私人订制
     */
    private void putMessagePersonalTailor(Message message) {
        message.setAttribute("adult_num", intent.getStringExtra("fewAdults"));
        message.setAttribute("child_num", intent.getStringExtra("severalChildren"));
        message.setAttribute("tour_time", intent.getStringExtra("travelTime"));
        message.setAttribute("start_address", intent.getStringExtra("origin"));
        message.setAttribute("end_address", intent.getStringExtra("destination"));
        message.setAttribute("tour_days", intent.getStringExtra("playNumberDays"));
        message.setAttribute("tour_favorite", intent.getStringExtra("travelPreferences"));
        message.setAttribute("recommend_diner", intent.getStringExtra("recommendRestaurant"));
        message.setAttribute("recommend_sleep", intent.getStringExtra("recommendedAccommodation"));
//        message.setAttribute("drv_code", intent.getStringExtra("workNumber"));
//        message.setAttribute("twenty_four", intent.getStringExtra("twenty_four"));
//        message.setAttribute("twenty_six", intent.getStringExtra("twenty_six"));
//        message.setAttribute("twenty_eight", intent.getStringExtra("twenty_eight"));
//        message.setAttribute("thirty", intent.getStringExtra("thirty"));
//        message.setAttribute("user_name", intent.getStringExtra("name"));
//        message.setAttribute("connect", intent.getStringExtra("contactWay"));
    }

    /**
     * 按天包车游
     */
    private void putMessageByTheDay(Message message) {

        message.setAttribute("start_address", intent.getStringExtra("origin"));
        message.setAttribute("end_address", intent.getStringExtra("destination"));
        message.setAttribute("pack_time", intent.getStringExtra("selectStartEndDateCar"));
        message.setAttribute("selectModels", intent.getStringExtra("selectModels"));
        message.setAttribute("drv_code", intent.getStringExtra("workNumber"));
        message.setAttribute("adult_num", intent.getStringExtra("adultNum"));
        message.setAttribute("child_num", intent.getStringExtra("childrenNum"));

        message.setAttribute("twenty_four", intent.getStringExtra("twenty_four"));
        message.setAttribute("twenty_six", intent.getStringExtra("twenty_six"));
        message.setAttribute("twenty_eight", intent.getStringExtra("twenty_eight"));
        message.setAttribute("thirty", intent.getStringExtra("thirty"));

        message.setAttribute("user_name", intent.getStringExtra("name"));
        message.setAttribute("connect", intent.getStringExtra("contactWay"));
    }


    public static VisitorTrack createVisitorTrack(Context context, String id, String index) {
        VisitorTrack track = ContentFactory.createVisitorTrack(null);
        switch (index) {
            case "0":
                track.title(context.getString(R.string.pickUpInformation))
                        .price(context.getString(R.string.toQuote))
                        .desc(context.getString(R.string.clickSeeDetails))
                        .imageUrl(URLConstants.IMAGE_RECEIVEORDER_URL)
                        .itemUrl(URLConstants.RECEIVEORDER + id);
                break;
            case "1":
                track.title(context.getString(R.string.sendMachineInformation))
                        .price(context.getString(R.string.toQuote))
                        .desc(context.getString(R.string.clickSeeDetails))
                        .imageUrl(URLConstants.IMAGE_SENDORDERINFO_URL)
                        .itemUrl(URLConstants.SENDORDERINFO + id);
                break;
            case "2":
                track.title(context.getString(R.string.singleTransport1))
                        .price(context.getString(R.string.toQuote))
                        .desc(context.getString(R.string.clickSeeDetails))
                        .imageUrl(URLConstants.IMAGE_ONCEORDER_URL)
                        .itemUrl(URLConstants.ONCEORDER + id);
                break;
            case "3":
                track.title(context.getString(R.string.privateCustomizedInformation))
                        .price(context.getString(R.string.toQuote))
                        .desc(context.getString(R.string.clickSeeDetails))
                        .imageUrl(URLConstants.IMAGE_PRIVATEORDER_URL)
                        .itemUrl(URLConstants.PRIVATEORDER + id + "/status/-3");
                break;
            case "4":
                track.title(context.getString(R.string.byTheDay1))
                        .price(context.getString(R.string.toQuote))
                        .desc(context.getString(R.string.clickSeeDetails))
                        .imageUrl(URLConstants.IMAGE_BYDAYORDER_URL)
                        .itemUrl(URLConstants.BYDAYORDER + id);
                break;
            default:
                break;
        }
        return track;
    }


    public static OrderInfo createOrderInfo(Context context, int index) {
        OrderInfo info = ContentFactory.createOrderInfo(null);
        switch (index) {
            case 1:
                info.title(context.getString(R.string.test_order_title1))
                        .orderTitle(String.format("%s：7890", context.getString(R.string.order_number)))
                        .price("￥8000")
                        .desc(context.getString(R.string.em_example1_text))
                        //     .imageUrl(IMAGE_URL_1)
                        .itemUrl("http://www.baidu.com");
                break;
            case 2:
                info.title(context.getString(R.string.test_order_title2))
                        .orderTitle(String.format("%s：7890", context.getString(R.string.order_number)))
                        .price("￥158000")
                        .desc(context.getString(R.string.em_example2_text))
                        //    .imageUrl(IMAGE_URL_2)
                        .itemUrl("http://www.baidu.com");
                break;
            default:
                break;
        }
        return info;

    }


    private String getMessageContent(int selectedIndex) {
        switch (selectedIndex) {
            case 1:
                return getResources().getString(R.string.em_example1_text);
            case 2:
                return getResources().getString(R.string.em_example2_text);
        }
        // 内容自己随意定义。
        return "";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
        instance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra(Config.EXTRA_SERVICE_IM_NUMBER);
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (CommonUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
