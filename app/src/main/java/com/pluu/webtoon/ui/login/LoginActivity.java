package com.pluu.webtoon.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pluu.webtoon.R;

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


}
