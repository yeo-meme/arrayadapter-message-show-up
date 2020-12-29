package com.nalazoocare.chattingex;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-06-16
 */
public class ChatMessageAdapter extends ArrayAdapter {

    private List msgs = new ArrayList();

    public ChatMessageAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        msgs.add(object);
    }


    @Override
    public int getCount() {
        return msgs.size();
    }

    @Nullable
    @Override
    public ChatMessage getItem(int position) {
        return (ChatMessage) msgs.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chatting_message, parent, false);
        }

        //어레이 리스트 채팅문자열을 일겅
        ChatMessage msg = (ChatMessage) msgs.get(position);

        TextView msgText = row.findViewById(R.id.chatmessage);
        msgText.setText(msg.getContent());
//        msgText.setTextColor(Color.parseColor("#00000"));
        return row;
    }
}
