package com.singh.harsukh.wtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ArticleActivity extends AppCompatActivity {
    TextView titleView;
    TextView dateView;
    WebView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        titleView = (TextView) findViewById(R.id.titleView);
        dateView = (TextView) findViewById(R.id.dateView);
        contentView = (WebView) findViewById(R.id.contentView);


        ArticlePost post = getIntent().getParcelableExtra("post");

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = dateformat.format(post.postDate);

        titleView.setText(post.postTitle);
        dateView.setText(datetime);
        contentView.getSettings().setJavaScriptEnabled(true);
        contentView.loadDataWithBaseURL("", post.postContent, "text/html", "UTF-8", "");
    }
}
