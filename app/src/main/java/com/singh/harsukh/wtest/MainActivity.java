package com.singh.harsukh.wtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private ArrayList<ArticlePost> posts;
    private PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.listView);
        if(savedInstanceState == null) {
            ArticleTask task = new ArticleTask(this);
            task.execute("http://www.washingtonpost.com/wp-srv/simulation/simulation_test.json");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("post", posts.get(position));
        Log.e("MainActivity", "item clicked at position: " + position + "with title: "+ posts.get(position).postTitle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sortAlphabetically) {
            Collections.sort(posts, new Comparator<ArticlePost>() {
                public int compare(ArticlePost p1, ArticlePost p2) {
                    return p1.postTitle.compareTo(p2.postTitle);
                }
            });
        } else {
            Collections.sort(posts, new Comparator<ArticlePost>() {
                public int compare(ArticlePost p1, ArticlePost p2) {
                    return p1.postDate.compareTo(p2.postDate);
                }
            });
        }
        adapter.notifyDataSetChanged();
        mListView.invalidateViews();
        mListView.refreshDrawableState();
        return true;
    }

    public void setListView(ArrayList posts){
        this.posts = posts;
        adapter = new PostAdapter(MainActivity.this, posts);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(MainActivity.this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mListView = (ListView)findViewById(R.id.listView);
        posts = savedInstanceState.getParcelableArrayList("SERIAL_KEY");
        mListView.setAdapter(new PostAdapter(this, posts));
        mListView.setOnItemClickListener(MainActivity.this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("SERIAL_KEY", posts);
    }
}
