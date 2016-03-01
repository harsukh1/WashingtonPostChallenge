package com.singh.harsukh.wtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by harsukh on 3/1/16.
 */
public class PostAdapter extends ArrayAdapter {
    Context context;
    ArrayList<ArticlePost> rows;

    public PostAdapter(Context context, ArrayList<ArticlePost> rows)
    {
        super(context, R.layout.post_row, rows);
        this.context = context;
        this.rows = rows;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row  = inflater.inflate(R.layout.post_row, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = dateformat.format(rows.get(position).postDate);
        holder.Title.setText(rows.get(position).postTitle);
        holder.Date.setText(datetime);
        return row;
    }

    private class ViewHolder {
        protected TextView Title;
        protected TextView Date;

        public ViewHolder(View v){
            Title = (TextView) v.findViewById(R.id.title);
            Date = (TextView) v.findViewById(R.id.date);
        }
    }
}
