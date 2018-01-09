package com.pluu.webtoon.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pluu.webtoon.R;

import me.gujun.android.taggroup.TagGroup;

public class TagSearchActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_search);

        TagGroup platform_tag_group = (TagGroup) findViewById(R.id.platform_tag_group);
        platform_tag_group.setTags(new String[]{"네이버", "다음", "카카오페이지", "레진코믹스", "붐툰"});

        TagGroup daylist_tag_group = (TagGroup) findViewById(R.id.daylist_tag_group);
        daylist_tag_group.setTags(new String[]{"월", "화", "수", "목", "금", "토", "일"});

        TagGroup genre_tag_group = (TagGroup) findViewById(R.id.genre_tag_group);
        genre_tag_group.setTags(new String[]{"에피소드", "옴니버스", "스토리", "일상",
                "개그", "판타지", "액션", "드라마", "순정", "감성", "스릴러", "시대극", "스포츠"});
    }
}
