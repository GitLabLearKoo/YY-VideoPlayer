package com.ybj366533.videoplayer.player;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Message;
import android.view.Surface;

import com.ybj366533.videoplayer.model.VideoModel;
import com.ybj366533.videoplayer.model.VideoOptionModel;
import com.ybj366533.videoplayer.utils.Debuger;

import java.util.List;

import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 系统播放器，总觉得不好用
 */

public class SystemPlayerManager implements IPlayerManager {

    private AndroidMediaPlayer mediaPlayer;

    private Surface surface;

    private boolean release;

    @Override
    public IMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void initVideoPlayer(Context context, Message msg, List<VideoOptionModel> optionModelList) {
        mediaPlayer = new AndroidMediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        release = false;
        try {
            mediaPlayer.setDataSource(context, Uri.parse(((VideoModel) msg.obj).getUrl()), ((VideoModel) msg.obj).getMapHeadData());
            mediaPlayer.setLooping(((VideoModel) msg.obj).isLooping());
            if (((VideoModel) msg.obj).getSpeed() != 1 && ((VideoModel) msg.obj).getSpeed() > 0) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDisplay(Message msg) {
        if (msg.obj == null && mediaPlayer != null && !release) {
            mediaPlayer.setSurface(null);
            if (surface != null) {
                surface.release();
                surface = null;
            }
        } else if (msg.obj != null) {
            Surface holder = (Surface) msg.obj;
            surface = holder;
            if (mediaPlayer != null && holder.isValid() && !release) {
                mediaPlayer.setSurface(holder);
            }
        }
    }

    @Override
    public void setSpeed(float speed, boolean soundTouch) {
        Debuger.printfError(" not support setSpeed");
    }

    @Override
    public void setNeedMute(boolean needMute) {
        try {
            if (mediaPlayer != null && !release) {
                if (needMute) {
                    mediaPlayer.setVolume(0, 0);
                } else {
                    mediaPlayer.setVolume(1, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void releaseSurface() {

    }

    @Override
    public void release() {
        if (mediaPlayer != null) {
            release = true;
            mediaPlayer.release();
        }
    }
}
