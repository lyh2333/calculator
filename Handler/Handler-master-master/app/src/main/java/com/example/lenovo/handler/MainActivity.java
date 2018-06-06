package com.example.lenovo.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int UPADTE_TEXT=1;
    private TextView text;
    private EditText editText;
    private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        text=(TextView) findViewById(R.id.output);
        editText=(EditText) findViewById(R.id.input);
        Button changetext=(Button) findViewById(R.id.changetext);
        changetext.setOnClickListener(this);
    }
    private Handler handler=new Handler(){
      public void handleMessage(Message msg){
          switch (msg.what) {
              case UPADTE_TEXT:
                  text.setText(s);
                  break;
              default:
                  break;
          }
      }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.changetext:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        s=editText.getText().toString();
                        Message message=new Message();
                        message.what=UPADTE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
