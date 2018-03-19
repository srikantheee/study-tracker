package com.mak.studytracker.helpers;

import android.content.Context;



/**
 * Created by itdev-8 on 4/3/16.
 */
public class URLHelper {

    public  static String BLOG_ID="4097077608852035694"; //URCE CSE B
    public  static String MAIN_URL="https://www.googleapis.com/blogger/v3/blogs/"+BLOG_ID+"/";
    public URLHelper(Context context) {
        this.context = context;
         }

    public String browserkey;
    Context context;


    public String getPosts() {
        return MAIN_URL+"posts?key=" + browserkey;
    }
    public String getOnePost(String post_id) {
        return MAIN_URL+"posts/"+post_id+"?key=" + browserkey;
    }
    public String getOnePostUpdated(String post_id) {
        return MAIN_URL+"posts/"+post_id+"?fields=updated&key=" + browserkey;
    }
    public String getAllPosts(int count) {
        return MAIN_URL+"posts?maxResults="+count+"&key=" + browserkey;
    }
    public String getPostsCount() {
        return " https://www.googleapis.com/blogger/v3/blogs/"+BLOG_ID+"?fields=posts%2FtotalItems&key=" + browserkey;
    }

    public String getPosts(String pageToken) {
        return MAIN_URL+"posts?pageToken=" + pageToken + "&key=" + browserkey;
    }
}
