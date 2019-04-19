package com.example.im_huanxin;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.im_huanxin.utils.AudioUtil;
import com.example.im_huanxin.utils.ToastUtil;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.exceptions.HyphenateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ChatGroupActivity";
    /**
     * 请输入群组名称
     */
    private EditText mEtGroupName;
    /**
     * 创建群
     */
    private Button mBtnCreate;
    /**
     * 请输入群组id
     */
    private EditText mEtGroupId;
    /**
     * 加入群聊
     */
    private Button mBtnJoin;
    private RecyclerView mRlv;
    private EditText mEtContent;
    /**
     * 发送
     */
    private Button mBtnSend;
    /**
     * 开始录音
     */
    private Button mBtnRecord;
    /**
     * 发送语音
     */
    private Button mBtnSendVoice;
    private ChatXlvAdapter mAdapter;
    private String mGroupId;
    private String mVoicePath;
    private int mDuration;
    private PopupWindow popupWindow;

    //石明洋 1808B 群聊
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_group);
        initView();
        initListener();
    }

    private void joinGroup() {
        mGroupId = mEtGroupId.getText().toString().trim();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //如果群开群是自由加入的，即group.isMembersOnly()为false，直接join
                try {
                    EMClient.getInstance().groupManager().joinGroup(mGroupId);//需异步处理
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
                //需要申请和验证才能加入的，即group.isMembersOnly()为true，调用下面方法
                //EMClient.getInstance().groupManager().applyJoinToGroup(mGroupId, "求加入");//需异步处理
            }
        }).start();
    }

    private void createGroup() {
        final String groupName = mEtGroupName.getText().toString().trim();
        if (TextUtils.isEmpty(groupName)){
            ToastUtil.showShort("群组名称不能为空");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 创建群组
                 * @param groupName 群组名称
                 * @param desc 群组简介
                 * @param allMembers 群组初始成员，如果只有自己传空数组即可
                 * @param reason 邀请成员加入的reason
                 * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
                 *               option.inviteNeedConfirm表示邀请对方进群是否需要对方同意，默认是需要用户同意才能加群的。
                 *               option.extField创建群时可以为群组设定扩展字段，方便个性化订制。
                 * @return 创建好的group
                 * @throws HyphenateException
                 */
                EMGroupOptions option = new EMGroupOptions();
                option.maxUsers = 200;
                option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin ;

                try {
                    EMGroup group = EMClient.getInstance().groupManager().createGroup(groupName, "瞎聊",
                            new String[]{}, "", option);
                    mGroupId = group.getGroupId();
                    Log.d(TAG, "创建的群id: "+mGroupId);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initListener() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }


    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
            if (messages != null && messages.size()>0){
                final ArrayList<EMMessage> list = new ArrayList<>();
                for (EMMessage msg : messages) {
                    String to = msg.getTo();
                    String from = msg.getFrom();
                    EMMessageBody body = msg.getBody();
                    long msgTime = msg.getMsgTime();
                    Log.d(TAG, "from: "+from+",to:"+to+",body:"+body.toString());

                    //区分是否是聊天对象发送过来的消息
                    if (mGroupId.equals(to)){
                        list.add(msg);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addData(list);
                        //滑动到底部
                        mRlv.scrollToPosition(mAdapter.mlist.size()-1);
                    }
                });

            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }
        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };

    private void playAudio(String autioPath) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(autioPath);
            //异步加载
            //mediaPlayer.prepareAsync();
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendAudioMsg() {
        if (TextUtils.isEmpty(mVoicePath)){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //filePath为语音文件路径，length为录音时间(秒)
                EMMessage message = EMMessage.createVoiceSendMessage(mVoicePath, (int) mDuration,
                        mGroupId);
                //如果是群聊，设置chattype，默认是单聊
                message.setChatType(EMMessage.ChatType.GroupChat);
                EMClient.getInstance().chatManager().sendMessage(message);

                addMsg(message);
                //清空路径,避免多次点击可以发送同样的语音
                mVoicePath = "";
                mDuration = 0;
            }
        }).start();
    }

    private void startRecord() {
        AudioUtil.startRecord(new AudioUtil.ResultCallBack() {
            @Override
            public void onSuccess(String path, long time) {
                mVoicePath = path;
                mDuration = (int) time;
            }

            @Override
            public void onFail(String msg) {
                Log.d(TAG, "onFail: "+msg);
                showToast(" 录音失败");
            }
        });
    }

    public void showToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showShort(msg);
            }
        });
    }

    //发送文本消息
    private void sendTextMsg() {
        final String content = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            ToastUtil.showShort("发送的内容不能为空");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                final EMMessage message = EMMessage.createTxtSendMessage(content, mGroupId);
                //如果是群聊，设置chattype，默认是单聊

                message.setChatType(EMMessage.ChatType.GroupChat);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);

                addMsg(message);
            }
        }).start();
    }

    private void addMsg(final EMMessage message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.addSingleMsg(message);
                mEtContent.setText("");
                //滑动到底部
                mRlv.scrollToPosition(mAdapter.mlist.size()-1);
            }
        });
    }

    private void initView() {
        mEtGroupName = (EditText) findViewById(R.id.et_group_name);
        mBtnCreate = (Button) findViewById(R.id.btn_create);
        mBtnCreate.setOnClickListener(this);
        mEtGroupId = (EditText) findViewById(R.id.et_group_id);
        mBtnJoin = (Button) findViewById(R.id.btn_join);
        mBtnJoin.setOnClickListener(this);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mBtnRecord = (Button) findViewById(R.id.btn_record);
        mBtnRecord.setOnClickListener(this);
        mBtnSendVoice = (Button) findViewById(R.id.btn_send_voice);
        mBtnSendVoice.setOnClickListener(this);

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<EMMessage> list = new ArrayList<>();
        mAdapter = new ChatXlvAdapter(list);
        mRlv.setAdapter(mAdapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mAdapter.SetOnItemClickLisener(new ChatXlvAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position, String autopath) {
                playAudio(autopath);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 0, "创建群聊并选择加入");
        menu.add(1, 2, 0, "加入群聊");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:



            case 2:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_create:
                createGroup();
                break;
            case R.id.btn_join:
                joinGroup();
                break;
            case R.id.btn_send:
                sendTextMsg();
                break;
            case R.id.btn_record:
                //是否正在录音
                if (AudioUtil.isRecording){
                    AudioUtil.stopRecord();
                    mBtnRecord.setText("开始录音");
                }else {
                    mBtnRecord.setText("停止录音");
                    startRecord();
                }
                break;
            case R.id.btn_send_voice:
                sendAudioMsg();
                break;
        }
    }
}
