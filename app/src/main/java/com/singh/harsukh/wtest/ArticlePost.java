package com.singh.harsukh.wtest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by harsukh on 3/1/16.
 */
public class ArticlePost implements Parcelable {
    String postTitle;
    Date postDate;
    String postContent;

    public ArticlePost(String postTitle, Date postDate, String postContent) {
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postContent = postContent;
    }


    protected ArticlePost(Parcel in) {
        postTitle = in.readString();
        long tmpPostDate = in.readLong();
        postDate = tmpPostDate != -1 ? new Date(tmpPostDate) : null;
        postContent = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postTitle);
        dest.writeLong(postDate != null ? postDate.getTime() : -1L);
        dest.writeString(postContent);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ArticlePost> CREATOR = new Parcelable.Creator<ArticlePost>() {
        @Override
        public ArticlePost createFromParcel(Parcel in) {
            return new ArticlePost(in);
        }

        @Override
        public ArticlePost[] newArray(int size) {
            return new ArticlePost[size];
        }
    };
}
