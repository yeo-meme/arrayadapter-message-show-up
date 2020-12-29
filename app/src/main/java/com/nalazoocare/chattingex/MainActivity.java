package com.nalazoocare.chattingex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nalazoocare.chattingex.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding  binding;
    private boolean isMine = true;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;

    private static String TAG = "mm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        chatMessages = new ArrayList<>();



        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"hi");
                if (binding.msgType.toString().trim().equals("")) {
                    Log.i(TAG,"btn");
                    Toast.makeText(MainActivity.this, "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
//                    ChatMessage chatMessage = new ChatMessage(binding.msgType.getText().toString());
                    Log.i(TAG,"chat : " + binding.msgType.getText().toString());

                    send();
                    if (isMine) {
                        isMine = false;
                    } else {
                        isMine = true;
                    }
                }
            }
        });

    }

    private void send() {
        setAdapter();
    }

    private void setAdapter() {
        adapter = new ChatMessageAdapter(this, R.layout.chatting_message);
        adapter.add(new ChatMessage(binding.msgType.getText().toString()));
        binding.listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        binding.listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                binding.listView.setSelection(adapter.getCount()-1);
            }
        });
        binding.msgType.setText("");

    }
}
