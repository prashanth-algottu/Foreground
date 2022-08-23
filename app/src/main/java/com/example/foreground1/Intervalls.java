package com.example.foreground1;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;

public class Intervalls extends AsyncTask<String, Void, String> {
    private Context mContext;

    public Intervalls (Context context){
        mContext = context;
    }
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,60000);
                MediaPlayer mediaPlayer = MediaPlayer.create(mContext,R.raw.pikachu);
                mediaPlayer.start();
            }
        },0000);
    }
}
