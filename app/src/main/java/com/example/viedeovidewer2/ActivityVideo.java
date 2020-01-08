
package com.example.viedeovidewer2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ActivityVideo extends AppCompatActivity {
    private VideoView Vview;
    private gdata app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get global data
        app = (gdata) getApplication();

        // Set up the video view
        setContentView(R.layout.activity_video);
        Vview = findViewById(R.id.videoView);
        // Here the video is put in the raw folder and will be inside the APK.
        String uri = "android.resource://" + getPackageName() + "/" + app.getNextVideoID();
        Vview.setVideoURI(Uri.parse(uri));
        Vview.start();
        Vview.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startActivity(new Intent("myActivityRating"));
            }

         }
        );
    }
//        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/humanstudy/bb.mp4";
//        File video=new  File(path);
//        if (video.exists()){
//            Log.i("info","video found");
//        }
//        Vview.setVideoPath(path);
//
//        Log.i("info ", path);




    public void onRequestPermissionsResult(int requestCode,
                                           String[] permission,
                                           int[] grantResults) {
        //requestCode就是requestPermissions()的第三个参数
        //permission就是requestPermissions()的第二个参数
        //grantResults是结果，0调试通过，-1表示拒绝
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }



}