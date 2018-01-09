package com.pluu.webtoon.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pluu.webtoon.R;

/**
 * Created by SCITMaster on 2018-01-08.
 */

public class RegistActivity extends AppCompatActivity {

    String id, pw, gender;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


    }

    public void connect(View view) {

        id = ((EditText) findViewById(R.id.registerId)).toString();
        pw = ((EditText) findViewById(R.id.registerpassword)).toString();
        RadioGroup rg = (RadioGroup) findViewById(R.id.radios);

        //예외처리
        if (rg.getCheckedRadioButtonId() == 0){
            Toast.makeText(RegistActivity.this, "Please check your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        gender = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        
        if (id.equals("")){
            Toast.makeText(RegistActivity.this, "Please checkout your ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pw.equals("")){
            Toast.makeText(RegistActivity.this, "Please checkout your Password", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
