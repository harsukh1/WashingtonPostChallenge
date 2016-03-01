package com.singh.harsukh.wtest;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harsukh on 3/1/16.
 */
public class ArticleTask extends AsyncTask<String, Void, ArrayList> {

    MainActivity activty;

    public ArticleTask(MainActivity activity)
    {
        this.activty = activity;
    }

    @Override
    protected ArrayList doInBackground(String... params)
    {
        String string_url = params[0];
        ArrayList<ArticlePost> posts = new ArrayList<>();
        try {
            URL url = new URL(string_url);
            BufferedReader reader =new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
            String json;
            StringBuffer buffer = new StringBuffer();
            while((json =reader.readLine()) != null)
            {
                buffer.append(json);
            }
            JSONObject jsonObject = new JSONObject(buffer.toString());

            JSONArray jArray = jsonObject.getJSONArray("posts");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject oneObject = jArray.getJSONObject(i);
                String title = oneObject.getString("title");
                String date = oneObject.getString("date");
                String content = oneObject.getString("content");
                String REGEX = "(&#[0-9a-z]*;)|(\n)"; //hardcoded regex
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(title);
                title = matcher.replaceAll(" ");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dated = format.parse(date);
                ArticlePost post = new ArticlePost(title, dated, content);
                posts.add(post);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        activty.setListView(arrayList);
    }
}
