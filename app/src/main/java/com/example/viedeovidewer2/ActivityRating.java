
package com.example.viedeovidewer2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ActivityRating extends AppCompatActivity {

    private SeekBar skbar;
    private Button NextButton;
    private gdata app;


    File scoreLocation ;
//
    File scoreFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (gdata) getApplication();
        setContentView(R.layout.activity_rating);
        scoreLocation = new File(getApplicationContext().getExternalFilesDir("").getPath()+ "scores");
        if (!scoreLocation.exists())
            scoreLocation.mkdirs();
        NextButton = findViewById(R.id.NextButton);
        final gdata app = (gdata) getApplication();
        getApplicationContext().getExternalFilesDir("");

        scoreFile =  new File(getApplicationContext().getExternalFilesDir("").getPath()+"/filescore_id" +app.getUsername() + "_session" +app.getSession() + ".txt");

//        File scoreLocation = new File(getApplicationContext().getExternalFilesDir("").getPath());
//        scoreLocation.mkdirs();

        // String fname = Context.getExternalFilesDir();
        skbar = findViewById(R.id.seekBar);
        int random = new Random().nextInt(101);
        skbar.setProgress(random);
        skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                NextButton.setClickable(true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

        NextButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                try {
                    scoreFile.createNewFile();
                    FileOutputStream outStream = new FileOutputStream(scoreFile,true);
                    outStream.write("hello world\nhello hell".getBytes());
                    outStream.write(("\n" + skbar.getProgress()).getBytes());
                    outStream.close();
                    app.addCurrentVideo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent("myActivityVideo"));
            }

        });
        NextButton.setClickable(false);



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }



}