package com.example.im_huanxin;

import android.drm.DrmStore;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.im_huanxin.utils.AudioUtil;
import com.example.im_huanxin.utils.ToastUtil;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ChatActivity";
    private TextView mTvFriend;
    private String mName;
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
    private String mVoicePath = "";
    private long mDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mName = getIntent().getStringExtra(Constants.NAME);
        initView();
        initListener();
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
                    if (mName.equals(from)){
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

    private void initView() {
        mTvFriend = (TextView) findViewById(R.id.tv_friend);
        mTvFriend.setText("正在与:" + mName + "聊天");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
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

    private void sendAudioMsg() {
        if (TextUtils.isEmpty(mVoicePath)){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //filePath为语音文件路径，length为录音时间(秒)
                EMMessage message = EMMessage.createVoiceSendMessage(mVoicePath, (int) mDuration,
                        mName);
                //如果是群聊，设置chattype，默认是单聊
               /* if (chatType == CHATTYPE_GROUP)
                    message.setChatType(ChatType.GroupChat);*/
                EMClient.getInstance().chatManager().sendMessage(message);

                addMsg(message);
                mVoicePath="";
                mDuration=0;
            }
        }).start();
    }

    private void startRecord() {
        AudioUtil.startRecord(new AudioUtil.ResultCallBack() {
            @Override
            public void onSuccess(String path, long time) {
                mVoicePath = path;
                mDuration = time;
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
                final EMMessage message = EMMessage.createTxtSendMessage(content, mName);
                //如果是群聊，设置chattype，默认是单聊
                /*if (chatType == CHATTYPE_GROUP)
                    message.setChatType(ChatType.GroupChat);*/
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
}
