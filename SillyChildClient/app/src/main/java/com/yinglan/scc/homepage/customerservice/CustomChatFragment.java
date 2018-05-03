package com.yinglan.scc.homepage.customerservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.common.cklibrary.common.StringConstants;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.helpdesk.easeui.provider.CustomChatRowProvider;
import com.hyphenate.helpdesk.easeui.recorder.MediaManager;
import com.hyphenate.helpdesk.easeui.ui.ChatFragment;
import com.hyphenate.helpdesk.easeui.util.CommonUtils;
import com.hyphenate.helpdesk.easeui.widget.AlertDialogFragment;
import com.hyphenate.helpdesk.easeui.widget.chatrow.ChatRow;
import com.hyphenate.helpdesk.model.MessageHelper;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.main.MainActivity;
import com.yinglan.scc.utils.chatrow.ChatRowByTheDay;
import com.yinglan.scc.utils.chatrow.ChatRowEvaluation;
import com.yinglan.scc.utils.chatrow.ChatRowForm;
import com.yinglan.scc.utils.chatrow.ChatRowLine;
import com.yinglan.scc.utils.chatrow.ChatRowLocation;
import com.yinglan.scc.utils.chatrow.ChatRowOrder;
import com.yinglan.scc.utils.chatrow.ChatRowPickupInformation;
import com.yinglan.scc.utils.chatrow.ChatRowPrivateCustomizedInformation;
import com.yinglan.scc.utils.chatrow.ChatRowPrivateServiceText;
import com.yinglan.scc.utils.chatrow.ChatRowSendMachineInformation;
import com.yinglan.scc.utils.chatrow.ChatRowSingleTransportInformation;
import com.yinglan.scc.utils.chatrow.ChatRowServiceText;


/**
 * 客服客服聊天 消息展示页面
 */
public class CustomChatFragment extends ChatFragment implements ChatFragment.EaseChatFragmentListener {

    //避免和基类定义的常量可能发生冲突,常量从11开始定义

    //message type 需要从1开始
    public static final int MESSAGE_TYPE_SENT_MAP = 1;
    public static final int MESSAGE_TYPE_RECV_MAP = 2;
    public static final int MESSAGE_TYPE_SENT_ORDER = 3;
    public static final int MESSAGE_TYPE_RECV_ORDER = 4;
    public static final int MESSAGE_TYPE_SENT_EVAL = 5;
    public static final int MESSAGE_TYPE_RECV_EVAL = 6;
    public static final int MESSAGE_TYPE_SENT_TRACK = 7;
    public static final int MESSAGE_TYPE_RECV_TRACK = 8;
    public static final int MESSAGE_TYPE_SENT_FORM = 9;
    public static final int MESSAGE_TYPE_RECV_FORM = 10;

    public static final int MESSAGE_TYPE_RECV_TRACK1 = 12;
    public static final int MESSAGE_TYPE_SENT_TRACK1 = 11;

    public static final int MESSAGE_TYPE_SENT_TRACK2 = 13;
    public static final int MESSAGE_TYPE_RECV_TRACK2 = 14;

    public static final int MESSAGE_TYPE_SENT_TRACK3 = 15;
    public static final int MESSAGE_TYPE_RECV_TRACK3 = 16;

    public static final int MESSAGE_TYPE_SENT_TRACK4 = 17;
    public static final int MESSAGE_TYPE_RECV_TRACK4 = 18;

    public static final int MESSAGE_TYPE_SENT_TRACK5 = 19;
    public static final int MESSAGE_TYPE_RECV_TRACK5 = 20;

    public static final int MESSAGE_TYPE_SENT_TRACK6 = 21;
    public static final int MESSAGE_TYPE_RECV_TRACK6 = 22;

    public static final int MESSAGE_TYPE_SENT_TRACK7 = 23;
    public static final int MESSAGE_TYPE_RECV_TRACK7 = 24;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void setUpView() {
        //这是新添加的扩展点击事件
        setChatFragmentListener(this);
        super.setUpView();
        //可以在此处设置titleBar(标题栏)的属性
        titleBar.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        titleBar.setTitle(getString(R.string.sillyChildCustomerService));
        titleBar.setLeftImageResource(R.mipmap.back);
        titleBar.setRightLayoutVisibility(View.GONE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isSingleActivity(getActivity())) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                getActivity().finish();
            }
        });
        //  titleBar.setRightImageResource(R.drawable.hd_chat_delete_icon);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
//        messageList.setOnShowActivity(new OnShowActivity() {
//            @Override
//            public void showActivity() {
//                Intent intent = new Intent(getActivity(), CheckstandActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    private void showAlertDialog() {
        FragmentTransaction mFragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        String fragmentTag = "dialogFragment";
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment != null) {
            //为了不重复显示dialog，在显示对话框之前移除正在显示的对话框
            mFragTransaction.remove(fragment);
        }
        final AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.setTitleText(getString(R.string.prompt));
        dialogFragment.setContentText(getString(R.string.Whether_to_empty_all_chats));
        dialogFragment.setupLeftButton(null, null);
        dialogFragment.setupRightBtn(null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatClient.getInstance().chatManager().clearConversation(toChatUsername);
                messageList.refresh();
                dialogFragment.dismiss();
                MediaManager.release();
            }
        });
        dialogFragment.show(mFragTransaction, fragmentTag);
    }

    @Override
    public void onAvatarClick(String username) {
        //头像点击事情
//        startActivity(new Intent(getActivity(), ...class));
    }

    @Override
    public boolean onMessageBubbleClick(Message message) {
        //  ViewInject.toast("1");
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(Message message) {
        //  ViewInject.toast("1");
    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        // ViewInject.toast("1");
        return false;
    }


    @Override
    public CustomChatRowProvider onSetCustomChatRowProvider() {
        return new DemoCustomChatRowProvider();
    }

    @Override
    protected void registerExtendMenuItem() {
        //demo 这里不覆盖基类已经注册的item, item点击listener沿用基类的
        // super.registerExtendMenuItem();
        for (int i = 0; i < itemStrings.length; i++) {
            if (i == 0 || i == 1) {
                inputMenu.registerExtendMenuItem(itemStrings[i], itemdrawables[i], itemIds[i], extendMenuItemClickListener);
            }
        }
    }


    @Override
    public void onMessageSent() {
//        messageList.setOnShowActivity(new OnShowActivity() {
//            @Override
//            public void showActivity() {
//                Intent intent = new Intent(getActivity(), CheckstandActivity.class);
//                startActivity(intent);
//            }
//        });
        messageList.refreshSelectLast();
    }


    /**
     * chat row provider
     */
    private final class DemoCustomChatRowProvider implements CustomChatRowProvider {

        @Override
        public int getCustomChatRowTypeCount() {
            //地图 和 满意度 发送接收 共4种
            //订单 和 轨迹 发送接收共4种
            // form 发送接收2种
            return 25;
        }

        @Override
        public int getCustomChatRowType(Message message) {
            //此处内部有用到,必须写否则可能会出现错位
            try {
                if (message.getType() == Message.Type.LOCATION) {
                    return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_MAP : MESSAGE_TYPE_SENT_MAP;
                } else if (message.getType() == Message.Type.TXT) {
                    if (((EMTextMessageBody) message.getBody()).getMessage().startsWith(getString(R.string.privatePseudoAgreement))) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK7 : MESSAGE_TYPE_SENT_TRACK7;
                    } else if (((EMTextMessageBody) message.getBody()).getMessage().startsWith(getString(R.string.orderPseudoAgreement))) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_ORDER : MESSAGE_TYPE_SENT_ORDER;
                    } else if (((EMTextMessageBody) message.getBody()).getMessage().startsWith(getString(R.string.linePseudoAgreement))) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK6 : MESSAGE_TYPE_SENT_TRACK6;
                    } else if (MessageHelper.getEvalRequest(message) != null) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_EVAL : MESSAGE_TYPE_SENT_EVAL;
                    } else if (MessageHelper.getOrderInfo(message) != null) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK : MESSAGE_TYPE_SENT_TRACK;
                    } else if (message.getStringAttribute("chatMessage").equals("0")) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_FORM : MESSAGE_TYPE_SENT_FORM;
                    } else if (MessageHelper.isFormMessage(message)) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK1 : MESSAGE_TYPE_SENT_TRACK1;
                    } else if (message.getStringAttribute("chatMessage").equals("1")) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK2 : MESSAGE_TYPE_SENT_TRACK2;
                    } else if (message.getStringAttribute("chatMessage").equals("2")) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK3 : MESSAGE_TYPE_SENT_TRACK3;
                    } else if (message.getStringAttribute("chatMessage").equals("3")) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK4 : MESSAGE_TYPE_SENT_TRACK4;
                    } else if (message.getStringAttribute("chatMessage").equals("4")) {
                        return message.direct() == Message.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TRACK5 : MESSAGE_TYPE_SENT_TRACK5;
                    }
                }
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        public ChatRow getCustomChatRow(Message message, int position, BaseAdapter adapter) {
            try {
                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername);
                //指定会话消息未读数清零
                if (conversation != null) {
                    conversation.markAllMessagesAsRead();
                }
                if (message.getType() == Message.Type.LOCATION) {
                    return new ChatRowLocation(getActivity(), message, position, adapter);
                } else if (message.getType() == Message.Type.TXT) {
                    if (((EMTextMessageBody) message.getBody()).getMessage().startsWith(getString(R.string.privatePseudoAgreement))) {
                        return new ChatRowPrivateServiceText(getActivity(), message, position, adapter);
                    } else if (((EMTextMessageBody) message.getBody()).getMessage().startsWith(getString(R.string.orderPseudoAgreement))) {
                        return new ChatRowServiceText(getActivity(), message, position, adapter);
                    } else if (((EMTextMessageBody) message.getBody()).getMessage().startsWith(getString(R.string.linePseudoAgreement))) {
                        return new ChatRowLine(getActivity(), message, position, adapter);
                    } else if (MessageHelper.getEvalRequest(message) != null) {
                        return new ChatRowEvaluation(getActivity(), message, position, adapter);
                    } else if (MessageHelper.getOrderInfo(message) != null) {
                        return new ChatRowOrder(getActivity(), message, position, adapter);
                    } else if (message.getStringAttribute("chatMessage").equals("0")) {
                        return new ChatRowPickupInformation(getActivity(), message, position, adapter);
                    } else if (MessageHelper.isFormMessage(message)) {
                        return new ChatRowForm(getActivity(), message, position, adapter);
                    } else if (message.getStringAttribute("chatMessage").equals("1")) {
                        return new ChatRowSendMachineInformation(getActivity(), message, position, adapter);
                    } else if (message.getStringAttribute("chatMessage").equals("2")) {
                        return new ChatRowSingleTransportInformation(getActivity(), message, position, adapter);
                    } else if (message.getStringAttribute("chatMessage").equals("3")) {
                        return new ChatRowPrivateCustomizedInformation(getActivity(), message, position, adapter);
                    } else if (message.getStringAttribute("chatMessage").equals("4")) {
                        return new ChatRowByTheDay(getActivity(), message, position, adapter);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * @param message  放入昵称  图片
     */
    @Override
    public void attachMessageAttrs(Message message) {
        String mobile = PreferenceHelper.readString(getContext(), StringConstants.FILENAME, "mobile", "");
        String nickname = PreferenceHelper.readString(getContext(), StringConstants.FILENAME, "nickname", "");
        String head_pic = PreferenceHelper.readString(getContext(), StringConstants.FILENAME, "head_pic", "");
        if (StringUtils.isEmpty(nickname)) {
            nickname = mobile;
        }
        message.setAttribute("nickname", nickname);
        message.setAttribute("avatar", head_pic);
        super.attachMessageAttrs(message);
    }


}
