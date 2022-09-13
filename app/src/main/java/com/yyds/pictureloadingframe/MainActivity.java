package com.yyds.pictureloadingframe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.yyds.imageloadingframe.ImageLoaderUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.image);
        ImageLoaderUtil.getInstance().with(this).disPlay(imageView,"https://pics3.baidu.com/feed/4a36acaf2edda3cc71280847f5e4cb0b203f92a5.jpeg?token=18ceabd3bb478991499dcb025ef1702c");
    }
}