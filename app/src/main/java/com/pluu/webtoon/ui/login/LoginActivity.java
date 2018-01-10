package com.pluu.webtoon.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pluu.webtoon.R;
import com.pluu.webtoon.vo.User;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SCITMaster on 2018-01-08.
 */

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    CheckBox loginchk;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;
    Button regist;
    String u, p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.password);
        loginchk = (CheckBox) findViewById(R.id.loginchk);
        regist = (Button) findViewById(R.id.regist);

        loginchk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()   {
            @Override
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                if (isChecked) {
                    loginChecked = true;
                } else {
                    // if unChecked, removeAll
                    loginChecked = false;
                    editor.clear();
                    editor.commit();
                }
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);
            }
        });
    }//onCreate

    public void check(){
        // if autoLogin checked, get input
        if (pref.getBoolean("loginChecked", false)) {
            userId.setText(pref.getString("userId", ""));
            password.setText(pref.getString("password", ""));
            loginchk.setChecked(true);
            // goto mainActivity

        } else {
            // if autoLogin unChecked
            String id = userId.getText().toString();
            String pw = password.getText().toString();
            Boolean validation = loginValidation(id, pw);

            if (validation) {
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                // save id, password to Database

                if (loginChecked) {
                    // if autoLogin Checked, save values
                    editor.putString("id", id);
                    editor.putString("pw", pw);
                    editor.putBoolean("loginChecked", true);
                    editor.commit();
                }
                // goto mainActivity

            } else {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                // goto LoginActivity
            }
        }
    }

    private boolean loginValidation(String userId, String password) {
        if (pref.getString("id", "").equals(userId) && pref.getString("pw", "").equals(password)) {
            // login success
            check();
            return true;
        } else if (pref.getString("id", "").equals(null)) {
            // sign in first
            Toast.makeText(LoginActivity.this, "Please Sign in first", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // login failed
            Toast.makeText(LoginActivity.this, "Please checkout your ID and PW ", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void connect(View view){
        int id = view.getId();
        u = userId.getText().toString().trim();
        p = password.getText().toString().trim();

        if(p.length() == 0 || u.length() == 0 ){
            Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        new LoginThread().start();

    }

    class LoginThread extends Thread{
        String addr = "http://10.10.12.71:8889/spring/login?userId="+u+"&password="+p;

        public void run(){
            try {

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
            User user = (User)msg.obj;

            if(user != null){
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(LoginActivity.this,"로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
    };


}
