package com.pluu.webtoon.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pluu.webtoon.R;
import com.pluu.webtoon.vo.User;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SCITMaster on 2018-01-08.
 */

public class RegistActivity extends AppCompatActivity {

    String id, pw, gender;
    Button confrimBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        confrimBtn = (Button) findViewById(R.id.confirmBtn);

        confrimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = ((EditText) findViewById(R.id.registerId)).toString().trim();
                pw = ((EditText) findViewById(R.id.registerpassword)).toString().trim();
                RadioGroup rg = (RadioGroup) findViewById(R.id.radios);

                //예외처리
                if (rg.getCheckedRadioButtonId() == 0){
                    Toast.makeText(RegistActivity.this, "Please check your gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                gender = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString().trim();

                if (id.equals("")){
                    Toast.makeText(RegistActivity.this, "Please checkout your ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pw.equals("")){
                    Toast.makeText(RegistActivity.this, "Please checkout your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

//                new JoinThread().start();
                new Thread(){
                    @Override
                    public void run() {
                        String addr = "http://10.10.12.71:8889/webtoonpick/join?id="+id+"&pw="+pw+"&gender="+gender;

                            try {
                                Toast.makeText(RegistActivity.this,"제발2",Toast.LENGTH_SHORT).show();
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                                conn.setRequestMethod("GET");
                                conn.setConnectTimeout(10000);

                                StringBuilder sb = new StringBuilder();
                                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                                    int ch;

                                    while ((ch = in.read()) != -1){
                                        sb.append((char)ch);
                                    }
                                    in.close();
                                }

                                Log.v("Response Conde =>", conn.getResponseCode()+"");
                                Message message = handler.obtainMessage();
                                message.obj = sb.toString();
                                handler.sendMessage(message);
                                conn.disconnect();


                            }catch (Exception e){

                            }
                        }


                }.start();
                Toast.makeText(RegistActivity.this,"제발1",Toast.LENGTH_SHORT).show();
            }
        });
    }


    class JoinThread extends Thread{
        String addr = "http://10.10.12.71:8889/webtoonpick/join?id="+id+"&pw="+pw+"&gender="+gender;

        public void run(){
            try {
                Toast.makeText(RegistActivity.this,"제발2",Toast.LENGTH_SHORT).show();
                URL url = new URL(addr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);

                StringBuilder sb = new StringBuilder();
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    int ch;

                    while ((ch = in.read()) != -1){
                        sb.append((char)ch);
                    }
                    in.close();
                }

                Log.v("Response Conde =>", conn.getResponseCode()+"");
                Message message = handler.obtainMessage();
                message.obj = sb.toString();
                handler.sendMessage(message);
                conn.disconnect();


            }catch (Exception e){

            }
        }
    }
    // TextView에 데이터를 출력하는 역할 담당
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String message = (String)msg.obj;

            if(message.equals("SUCCESS")){
                Toast.makeText(RegistActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(RegistActivity.this,"회원가입 실패", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
