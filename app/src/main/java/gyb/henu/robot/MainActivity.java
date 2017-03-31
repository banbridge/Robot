package gyb.henu.robot;


import android.content.Context;
import android.net.*;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;

import java.util.*;

import gyb.henu.robot.ChatMessage.ChatMessage;
import gyb.henu.robot.tools.HttpUties;

import static gyb.henu.robot.tools.HttpUties.*;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String str;

    Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ChatMessage s= (ChatMessage) msg.obj;
            list_message.add(s);
            chatMessageAdapter.notifyDataSetChanged();
            recycleView.smoothScrollToPosition(list_message.size()-1);
        }
    };

    private ListView recycleView;
    private LayoutInflater mInflater;
    private EditText editText;
    private List<ChatMessage> list_message=new ArrayList<ChatMessage>();
    private BaseAdapter chatMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView = (TextView) findViewById(R.id.textview);
      initView();
        initDatas();
    }

    private void initDatas() {
        ChatMessage ch=new ChatMessage();
        ch.setMsg("你好，我是苦雨");
        ch.setType(ChatMessage.Type.INCOMING);
        ch.setDate(new Date());
        list_message.add(ch);
        chatMessageAdapter=new ChatMessageAdapter(this,list_message);
        recycleView.setAdapter(chatMessageAdapter);
    }

    private void initView() {
        recycleView= (ListView) findViewById(R.id.id_main_recyleListView);
        editText= (EditText) findViewById(R.id.id_input);
    }

    public void btn_click(View v) {

        String toms=editText.getText().toString();
        if(TextUtils.isEmpty(toms)){
            Toast.makeText(MainActivity.this,"消息不能为空!!",Toast.LENGTH_SHORT).show();
            return;
        }
       ChatMessage tochat=new ChatMessage();
        tochat.setMsg(toms);
        tochat.setDate(new Date());
        tochat.setType(ChatMessage.Type.OUTCOMING);
        list_message.add(tochat);
        chatMessageAdapter.notifyDataSetChanged();
        recycleView.smoothScrollToPosition(list_message.size()-1);
       new Thread(){
           @Override
           public void run() {
               ChatMessage cj=HttpUties.sendMessage(editText.getText().toString());
               Message ms=Message.obtain();

               ms.obj=cj;
               myhandler.sendMessage(ms);
           }
       }.start();
        editText.setText("");
    }
}
