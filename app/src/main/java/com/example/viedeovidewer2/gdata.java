package com.example.viedeovidewer2;

import android.app.Application;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class gdata extends Application{

    private String username;
    private String session;
    final public Map<String,Integer> videos = new HashMap<>();
    private int currentVideo = 0;
    private String currentVideoName;
    private String videoListFileName;
    private File videoList;
    InputStream instream;
    InputStreamReader inputreader;
    BufferedReader buffreader;
    String TAG = "myIO";








    public void setVideoListFileName(String videoListFileName) {
        this.videoListFileName = videoListFileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public int getVideo(String v){return videos.get(v);}

    public int getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(int currentVideo) {
        this.currentVideo = currentVideo;
    }

    public void addCurrentVideo(){
        this.currentVideo = this.currentVideo+1;
    }

    public void setCurrentVideoName(String currentVideoName) {
        this.currentVideoName = currentVideoName;
    }

    public String getCurrentVideoName() {
        return currentVideoName;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub  
        super.onCreate();
        videos.put("bb",R.raw.bb);
        videos.put("cr",R.raw.cr);
        videos.put("netflixcrowdrun_1080_25fps",R.raw.netflixcrowdrun_1080_25fps);
        Log.d(TAG, "onCreate: ");

    }

    public void setupIO(){
        videoListFileName = getApplicationContext().getExternalFilesDir("").getPath() + "/list/videolist_" + this.username + "_" + this.session+".txt";
        Log.d(TAG, "setupIO: "+  getApplicationContext().getExternalFilesDir("").getPath() + "/list/videolist_" + this.username + "_" + this.session+".txt");
        this.videoList = new File(this.videoListFileName);
        try{
            this.instream = new FileInputStream(videoList);
            this.inputreader = new InputStreamReader(this.instream);
            this.buffreader = new BufferedReader(this.inputreader);
        }

        catch (java.io.FileNotFoundException e) {
            Log.d(TAG, "The File doesn't not exist.");
        }

    }

    public String getNextVideoName(){
        try{
            this.currentVideoName = this.buffreader.readLine();
            Log.d(TAG, "getNextVideoName: "+this.currentVideoName);
            return this.currentVideoName;
        }
        catch (IOException e){
            Log.d(TAG, "The File doesn't not exist.");
            return null;
        }
    }

    public int getNextVideoID(){
        return this.videos.get(this.getNextVideoName());
    }

    public void releaseFile(){
        try{
            instream.close();

        }
        catch (java.io.IOException e){
            Log.d("info", "releaseFile: failed");
        }

    }

} 
