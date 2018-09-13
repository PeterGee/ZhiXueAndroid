package com.risenb.studyknowledge.ui.post;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.utils.SPUtils;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

/**
 * 录音的 Service
 * <p>
 */

public class RecordingService extends Service {

    private static final String LOG_TAG = "RecordingService";

    private String mFileName = null;
    private String mFilePath = null;

    private MediaRecorder mRecorder = null;

    private long mStartingTimeMillis = 0;
    private long mElapsedMillis = 0;
    private TimerTask mIncrementTimerTask = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            stopRecording();
        }
        super.onDestroy();
    }

    public void startRecording() {
        setFileNameAndPath();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioChannels(1);
        mRecorder.setAudioSamplingRate(44100);
        mRecorder.setAudioEncodingBitRate(192000);

        try {
            mRecorder.prepare();
            mRecorder.start();
            mStartingTimeMillis = System.currentTimeMillis();

        } catch (IllegalStateException e) {
            System.out.print(e.getMessage());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public void setFileNameAndPath() {
        int count = 0;
        File f;

        do {
            count++;
            mFileName = getString(R.string.default_file_name)
                    + "_" + (System.currentTimeMillis()) + ".mp4";
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath += "/SoundRecorder/" + mFileName;
            f = new File(mFilePath);
        } while (f.exists() && !f.isDirectory());
    }

    public void stopRecording() {

        try {
            mRecorder.stop();
            mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
            mRecorder.release();

            getSharedPreferences("sp_name_audio", MODE_PRIVATE)
                    .edit()
                    .putString("audio_path", mFilePath)
                    .putLong("elpased", mElapsedMillis)
                    .apply();

            if (mIncrementTimerTask != null) {
                mIncrementTimerTask.cancel();
                mIncrementTimerTask = null;
            }
            mRecorder = null;

        } catch (IllegalStateException e) {
            // TODO 如果当前java状态和jni里面的状态不一致，
            e.printStackTrace();
        }
    }

}
