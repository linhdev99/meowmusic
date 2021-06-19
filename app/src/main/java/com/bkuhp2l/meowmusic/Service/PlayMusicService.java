package com.bkuhp2l.meowmusic.Service;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.bkuhp2l.meowmusic.Model.Song;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PlayMusicService {
    public static PlayMusicService Mp3Service = new PlayMusicService();
//    public static ArrayList<Song> arraySong;
    public static MediaPlayer mediaPlayer = null;
    public static PlayMp3 playmusic_mp3;
    public static String linkSong;
    public static String idSong;
    public static int song_state_next;
//    public static boolean auto_next;

    public static PlayMusicService getInstance() {
        playmusic_mp3 = new PlayMp3();
//        arraySong = new ArrayList<>();
        song_state_next = 0;
//        auto_next = false;
        return Mp3Service;
    }

//    public static boolean getAutoNextSong() {
//        return auto_next;
//    }
//
//    public static void setAutoNextSong(boolean state) {
//        auto_next = state;
//    }
//
//    public static void setArraySong(ArrayList<Song> arrayList) {
//        arraySong = arrayList;
//    }

    public static int getSongStateNext() {
        return song_state_next;
    }

    public static void setSongStateNext(int state) {
        song_state_next = state;
    }

    public static void setLinkSong(String id, String link) {
        idSong = id;
        linkSong = link;
    }
    public static void PlayNewMusic() {
        playmusic_mp3.initialPlayMp3(linkSong);
        mediaPlayer = playmusic_mp3.getMediaPlayer();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (mediaPlayer != null) {
//                    auto_next = false;
//                    UpdateCheckAutoNext();
//                    UpdatePlayMusicAutoNextSong();
//                    Log.d("TAG", "auto update check auto next");
//                }
//                else {
//                    handler.postDelayed(this,100);
//                }
//            }
//        },300);
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

    public static int getIdSongInt() {
        int result = -1;
        try {
            result = Integer.parseInt(idSong);
        }
        catch (Exception err) {
            result = -1;
        }
        return result;
    }

//    private static void UpdatePlayMusicAutoNextSong() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (auto_next)
//                {
//                    Log.d("TAG","Next Song");
//                    return;
//                }
//                else
//                {
//                    handler.postDelayed(this, 1000);
//                }
//            }
//        }, 500);
//    }

//    private static void UpdateCheckAutoNext() {
//        Log.d("TAG", "UpdateCheckAutoNext 2");
//        Handler handler_UpdateCheckAutoNext = new Handler();
//        handler_UpdateCheckAutoNext.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (mediaPlayer != null) {
//                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mediaPlayer) {
//                            auto_next = true;
//                            Log.d("TAG", "UpdateCheckAutoNext");
//                            return;
//                        }
//                    });
//                }
//                handler_UpdateCheckAutoNext.postDelayed(this, 300);
//            }
//        }, 300);
//    }

    public static String getIdSongString() {
        return idSong;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    static class PlayMp3 extends AsyncTask<String, Void, String> {
        MediaPlayer media;
        public void initialPlayMp3(String urlSong) {
            if (media != null) {
                if (media.isPlaying()) {
                    media.stop();
                }
            }
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

