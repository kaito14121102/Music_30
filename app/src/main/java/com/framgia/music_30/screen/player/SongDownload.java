package com.framgia.music_30.screen.player;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class SongDownload extends AsyncTask<String, Integer, String> {
    private Context mContext;
    private ProgressDialog progressDialog;
    private String mSongName;

    public SongDownload(Context context, String songName) {
        mContext = context;
        mSongName = songName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Download in progress...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... aurl) {
        int file_length = 0;
        try {
            URL url = new URL(aurl[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            file_length = connection.getContentLength();
            File newFolder = new File("sdcard/mymusic");
            if (!newFolder.exists()) {
                newFolder.mkdir();
            }
            File inputFile = new File(newFolder, mSongName + ".mp3");
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            byte data[] = new byte[1024];
            int total = 0;
            int count = 0;
            OutputStream output = new FileOutputStream(inputFile);
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
                publishProgress((int) ((total * 100) / file_length));
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
        }
        return "Download Complete";
    }

    protected void onProgressUpdate(Integer... progress) {
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.hide();
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
    }
}
