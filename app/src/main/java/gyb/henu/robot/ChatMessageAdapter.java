package gyb.henu.robot;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

import gyb.henu.robot.ChatMessage.ChatMessage;

/**
 * Created by 高逸博 on 2017/3/19.
 */

public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mlayoutinflater;
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
        this.mlayoutinflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }


    @Override
    public int getItemViewType(int position) {
        if(mDatas.get(position).getType()== ChatMessage.Type.INCOMING){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage=mDatas.get(position);
        ViewHolder viewHolder=null;

        if(convertView==null){
            if(getItemViewType(position)==0){
                convertView=mlayoutinflater.inflate(R.layout.com_message,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mDate= (TextView) convertView.findViewById(R.id.id_textView_in_time);
                viewHolder.mMessage= (TextView) convertView.findViewById(R.id.id_textView_inmessage);
            }else{
                convertView=mlayoutinflater.inflate(R.layout.out_message,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mDate= (TextView) convertView.findViewById(R.id.id_textView_out_time);
                viewHolder.mMessage= (TextView) convertView.findViewById(R.id.id_textView_out_message);
            }
           convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.mMessage.setText(mDatas.get(position).getMsg());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd HH:mm:ss");
        viewHolder.mDate.setText(simpleDateFormat.format(mDatas.get(position).getDate()) );
        return convertView;
    }

    private final class ViewHolder{
        TextView mDate;
        TextView mMessage;
    }

}
