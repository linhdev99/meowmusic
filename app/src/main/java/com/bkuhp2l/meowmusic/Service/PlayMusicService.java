package com.bkuhp2l.meowmusic.Service;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PlayMusicService {
    public static PlayMusicService Mp3Service = new PlayMusicService();
    public static MediaPlayer mediaPlayer = null;
    public static PlayMp3 playmusic_mp3;
    public static String linkSong;

    public static PlayMusicService getInstance(String link) {
        linkSong = link;
        playmusic_mp3 = new PlayMp3();
        playmusic_mp3.initialPlayMp3(linkSong);
        mediaPlayer = playmusic_mp3.getMediaPlayer();
        Log.d("media", String.valueOf(mediaPlayer));
        return Mp3Service;
    }

    public static String getTimeSongTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(mediaPlayer.getDuration());
    }

    public static String getTimeSongCurrent() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(mediaPlayer.getCurrentPosition());
    }

    public static int getTimeSongDuration() {
        int result = 0;
        try {
            result = mediaPlayer.getDuration();
        }
        catch (Exception err) {
            Log.d("Err", String.valueOf(mediaPlayer));
        }
        return result;
    }

    public static void stopMusic() {
        mediaPlayer.stop();
    }
    public static void startMusic() {
        mediaPlayer.start();
    }
    public static void pauseMusic() {
        mediaPlayer.pause();
    }

    public static boolean isPlayingMusic() {
        return mediaPlayer.isPlaying();
    }


    static class PlayMp3 extends AsyncTask<String, Void, String> {
        MediaPlayer media;
        public void initialPlayMp3(String urlSong) {
            onPostExecute(urlSong);
        }
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                media = new MediaPlayer();
                media.setAudioStreamType(AudioManager.STREAM_MUSIC);
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer media) {
                        media.stop();
                        media.reset();
                    }
                });
                media.setDataSource(song);
                media.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            media.start();
        }
        public MediaPlayer getMediaPlayer() {
            Log.d("media", String.valueOf(media));
            return media;
        }
    }
}

