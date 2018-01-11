package com.pluu.webtoon.ui.settting;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.pluu.webtoon.R;

/**
 * Created by SCITMaster on 2018-01-11.
 */

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Preference login_title = (Preference) findPreference("login_title");
        Preference tag_select = (Preference) findPreference("tag_select");
        if(login_title.getTitle().equals("로그인")) {
            login_title.setTitle("로그아웃");
            tag_select.setEnabled(true);
        }else{
            login_title.setTitle("로그인");
            tag_select.setEnabled(false);
        }
        addPreferencesFromResource(R.xml.pref_settings);
    }
}
