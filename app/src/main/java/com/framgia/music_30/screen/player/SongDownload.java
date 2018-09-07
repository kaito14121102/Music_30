package com.framgia.music_30.screen.player;

import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.framgia.music_30.R;
import com.framgia.music_30.ultil.Constant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class SongDownload extends AsyncTask<String, Integer, String> {
    private String mSongName;
    private NotificationCompat.Builder mNotificationCompat;
    private NotificationManager mNotificationManager;

    public SongDownload(Context context, String songName) {
        mSongName = songName;
        mNotificationCompat = new NotificationCompat.Builder(context, Constant.CHANEL_ID)
                .setContentTitle(mSongName)
                .setContentText(Constant.DOWNLOADING)
                .setSmallIcon(R.drawable.ic_arrow_downloaded_black_24dp);
        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... aurl) {
        try {
            int file_length = 0;
            URL url = new URL(aurl[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            file_length = connection.getContentLength();
            File newFolder = new File(Constant.FOLDER_OFFLINE_MUSIC);
            if (!newFolder.exists()) {
                newFolder.mkdir();
            }
            File inputFile = new File(newFolder, mSongName + Constant.FILE_EXTEND);
            updateProgress(url, inputFile, file_length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constant.DOWNLOAD_COMPLETE;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        mNotificationCompat.setProgress(Constant.ONEHUNDERED, progress[0], false);
        mNotificationManager.notify(Constant.NOTIFICATION_DOWNLOAD, mNotificationCompat.build());
    }

    @Override
    protected void onPostExecute(String result) {
        mNotificationCompat.setContentTitle(result);
        mNotificationManager.notify(Constant.NOTIFICATION_DOWNLOAD, mNotificationCompat.build());
    }

    public void updateProgress(URL url, File inputFile, int file_length) throws IOException {
        InputStream input = new BufferedInputStream(url.openStream(), Constant.INPUTSTREAM_SIZE);
        byte data[] = new byte[Constant.SIZE_BYTE];
        int total = 0;
        int count = 0;
        OutputStream output = new FileOutputStream(inputFile);
        while ((count = input.read(data)) != -1) {
            total += count;
            output.write(data, 0, count);
            publishProgress((int) ((total * Constant.ONEHUNDERED) / file_length));
        }
        output.flush();
        output.close();
        input.close();
    }
}
