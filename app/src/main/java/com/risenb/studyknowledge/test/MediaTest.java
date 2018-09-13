package com.risenb.studyknowledge.test;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lidroid.mutils.media.MediaPlayerUtils;
import com.lidroid.mutils.utils.UIManager;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.ui.BaseUI;

/**
 * 播放mp3
 */
@ContentView(R.layout.include_music)
public class MediaTest extends BaseUI {
    /**
     * 进度
     */
    @ViewInject(R.id.tv_music_position)
    private TextView tv_music_position;

    /**
     * 进度
     */
    @ViewInject(R.id.sb_music_position1)
    private SeekBar sb_music_position1;

    /**
     * 进度
     */
    @ViewInject(R.id.sb_music_position2)
    private SeekBar sb_music_position2;

    /**
     * 歌曲时长
     */
    @ViewInject(R.id.tv_music_end)
    private TextView tv_music_end;

    @Override
    protected void back() {
        UIManager.getInstance().popActivity(getClass());
    }

    @Override
    protected void setControlBasis() {
        setTitle("");
    }

    @Override
    protected void prepareData() {
        MediaPlayerUtils.getMediaPlayerUtils().setOnPosition(new MediaPlayerUtils.OnPosition() {
            @Override
            public void onPosition(int position) {
                tv_music_position.setText(MediaPlayerUtils.getMediaPlayerUtils().getCurrentPositionStr());
                sb_music_position1.setProgress(MediaPlayerUtils.getMediaPlayerUtils().getCurrentPosition());
                sb_music_position2.setProgress(MediaPlayerUtils.getMediaPlayerUtils().getCurrentPosition());
            }

            @Override
            public void onPlayerIdx(int idx) {
                try {
                    tv_music_end.setText(MediaPlayerUtils.getMediaPlayerUtils().getDurationStr());
                    sb_music_position1.setMax(MediaPlayerUtils.getMediaPlayerUtils().getDuration());
                    sb_music_position2.setMax(MediaPlayerUtils.getMediaPlayerUtils().getDuration());
                } catch (Exception e) {
                }
            }
        });
        sb_music_position2.setOnSeekBarChangeListener(seekbarChangeListener);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    private SeekBar.OnSeekBarChangeListener seekbarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        private int progress;

        // 停止拖动时执行
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            MediaPlayerUtils.getMediaPlayerUtils().seekTo(progress);
            MediaPlayerUtils.getMediaPlayerUtils().start();
        }

        // 在进度开始改变时执行
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            MediaPlayerUtils.getMediaPlayerUtils().pause();
        }

        // 当进度发生改变时执行
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            this.progress = progress;
            sb_music_position1.setProgress(progress);
        }
    };


    /**
     * 上一曲
     *
     * @param view
     */
    @OnClick(R.id.back)
    private void up(View view) {
        MediaPlayerUtils.getMediaPlayerUtils().up();
    }

    /**
     * 下一曲
     *
     * @param view
     */
    @OnClick(R.id.back)
    private void next(View view) {
        MediaPlayerUtils.getMediaPlayerUtils().next();
    }

    /**
     * 播放
     *
     * @param view
     */
    @OnClick(R.id.back)
    private void player(View view) {
        if (MediaPlayerUtils.getMediaPlayerUtils().isPlaying()) {
            MediaPlayerUtils.getMediaPlayerUtils().pause();
        } else {
            MediaPlayerUtils.getMediaPlayerUtils().start();
        }
    }

//    @Override
//    public void setList(List<SoundInfoBySingleIdBean> list) {
//        MediaPlayerUtils.getMediaPlayerUtils().clear();
//        MediaPlayerUtils.getMediaPlayerUtils().addAll(list);
//        MediaPlayerUtils.getMediaPlayerUtils().player();
//    }
}
