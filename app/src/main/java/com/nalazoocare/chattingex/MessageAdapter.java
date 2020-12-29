package com.nalazoocare.chattingex;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-06-16
 */
public class MessageAdapter extends ArrayAdapter<ChatMessage> {

    private Activity activity;
    private List<ChatMessage> message;
    private static String TAG = "meme";

    public MessageAdapter(@NonNull Activity context, int resource,List<ChatMessage> obj) {
        super(context, resource);
        this.activity = context;
        this.message = obj;

        for (int i=0; i<obj.size(); i++) {
            Log.d(TAG,"adpater :" + obj.get(i).getContent());
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
       int layoutResource = 0;
       ChatMessage chatMessage = getItem(position);
       int viewType = getItemViewType(position);

//       if (chatMessage.isMine()) {
//           layoutResource = R.layout.item_chat_left;
//       } else {
//           layoutResource = R.layout.item_chat_right;
//       }

       if (convertView !=null) {
           holder = (ViewHolder) convertView.getTag();
       } else {
           convertView = inflater.inflate(layoutResource,parent,false);
           holder = new ViewHolder(convertView);
           convertView.setTag(holder);
       }
        for (int i=0; i<message.size(); i++) {
            holder.msg.setText( message.get(i).getContent());
        }
//       holder.msg.setText(chatMessage.getContent());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    private class ViewHolder {
        private TextView msg;
        public ViewHolder(View v) {
            msg = v.findViewById(R.id.txt_msg);
        }
    }
}
