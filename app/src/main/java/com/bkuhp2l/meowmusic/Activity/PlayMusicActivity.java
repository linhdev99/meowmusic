package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bkuhp2l.meowmusic.Adapter.PlayMusicPagerAdapter;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_DiscMusic;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_Information;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_Playlist;
import com.bkuhp2l.meowmusic.Model.Song;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.PlayMusicService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import me.relex.circleindicator.CircleIndicator3;

public class PlayMusicActivity extends AppCompatActivity {
    TextView txtSong, txtSinger;
    ImageView btnHidePlayMusic;
    ImageView blurBackground;
    BlurView blurView;
    Song song;
    int id_cur_song, id_cur_on_playlist;
    ActionBar actionBar;
    ViewPager2 viewPager2;
    PlayMusicPagerAdapter playMusicPagerAdapter;
    CircleIndicator3 circleIndicatorPlaymusic;
    Fragment_PlayMusic_Playlist fragment_playMusic_playlist;
    Fragment_PlayMusic_DiscMusic fragment_playMusic_discMusic;
    Fragment_PlayMusic_Information fragment_playMusic_information;
    ArrayList<Song> arraySong;
    TextView txt_time_current, txt_time_total;
    SeekBar seekbar_song;
    ImageView btn_shuffle, btn_previous, btn_play, btn_next, btn_repeat;
    int song_state = 0; // 0: next = next + 1, 1: shuffle, 2: return_unlimit, 3: return_onetime
    boolean song_next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        actionBar = getSupportActionBar();
        actionBar.hide();
        arraySong = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Mapping();
        DataIntent();
        SetDataIntent();
        SetupViewPager2();
        InitialMedia();
        EventClick();
    }

    @Override
    public void onBackPressed() {
        EventBackHome();
    }

    private void EventBackHome() {
        finish();
    }


    private void EventClick() {
        PlayMusicCheckSongState();
        btnHidePlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBackHome();
            }
        });
        seekbar_song.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                PlayMusicService.getMediaPlayer().seekTo(seekBar.getProgress());
            }
        });
    }

    public void PlayMusicCheckSongState() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (song_next)
                {
                    song_state = PlayMusicService.getSongStateNext();
                    if (song_state == 0) {
//                        PlayMusicService.setAutoNextSong(false);
                        song_next = false;
                        NextClickButton();
                    }
                    else if (song_state == 1) {
//                        PlayMusicService.setAutoNextSong(false);
                        song_next = false;
                        int id_temp = id_cur_on_playlist;
                        while (id_temp == id_cur_on_playlist) {
                            id_temp = ThreadLocalRandom.current().nextInt(0, arraySong.size() - 1);
                        }
                        PlayMusicById(id_temp);
                    }
                    else if (song_state == 2) {
//                        PlayMusicService.setAutoNextSong(false);
                        song_next = false;
                        PlayMusicService.getMediaPlayer().seekTo(0);
                        PlayMusicService.getMediaPlayer().start();
                    }
                    handler.postDelayed(this, 1000);
                }
                else
                {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 500);
    }

    public  boolean isInternetConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else
            connected = false;
        return connected;
    }

    public void PlayClickButton() {
        if (PlayMusicService.isPlayingMusic()) {
            PlayMusicService.pauseMusic();
            btn_play.setImageResource(R.drawable.playmusicplay);
        }
        else
        {
            PlayMusicService.startMusic();
            btn_play.setImageResource(R.drawable.playmusicpause);
        }
    }

    public void NextClickButton() {
        int id_next = id_cur_on_playlist + 1;
        if (id_next < arraySong.size()) {
            PlayMusicById(id_next);
        }
        else {
            Toast.makeText(this, "This is the last music!", Toast.LENGTH_SHORT).show();
        }
    }

    public void PreviousClickButton() {
        int id_next = id_cur_on_playlist - 1;
        if (id_next >= 0) {
            PlayMusicById(id_next);
        }
        else {
            Toast.makeText(this, "This is the first music!", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShuffleClickButton() {
        song_state = PlayMusicService.getSongStateNext();
        if (song_state != 1) {
            song_state = 1;
            PlayMusicService.setSongStateNext(song_state);
            btn_shuffle.setColorFilter(ContextCompat.getColor(this, R.color.choose_btn_playmusic_handler));
            btn_repeat.setColorFilter(ContextCompat.getColor(this, R.color.notchoose_btn_playmusic_handler));
        }
        else {
            song_state = 0;
            PlayMusicService.setSongStateNext(song_state);
            btn_shuffle.setColorFilter(ContextCompat.getColor(this, R.color.notchoose_btn_playmusic_handler));
            btn_repeat.setColorFilter(ContextCompat.getColor(this, R.color.notchoose_btn_playmusic_handler));
        }
    }

    public void RepeatClickButton() {
        song_state = PlayMusicService.getSongStateNext();
        if (song_state != 2) {
            song_state = 2;
            PlayMusicService.setSongStateNext(song_state);
            btn_repeat.setColorFilter(ContextCompat.getColor(this, R.color.choose_btn_playmusic_handler));
            btn_shuffle.setColorFilter(ContextCompat.getColor(this, R.color.notchoose_btn_playmusic_handler));
        }
        else {
            song_state = 0;
            PlayMusicService.setSongStateNext(song_state);
            btn_repeat.setColorFilter(ContextCompat.getColor(this, R.color.notchoose_btn_playmusic_handler));
            btn_shuffle.setColorFilter(ContextCompat.getColor(this, R.color.notchoose_btn_playmusic_handler));
        }
    }

    private void InitialMedia() {
//        PlayMusicService.setArraySong(arraySong);
        if (Integer.parseInt(song.getIdSong()) != PlayMusicService.getIdSongInt()) {
            PlayMusicService.setLinkSong(song.getIdSong(), song.getLinkSong());
            PlayMusicService.PlayNewMusic();
        }
        id_cur_song = PlayMusicService.getIdSongInt();
        txt_time_current.setText(PlayMusicService.getTimeSongCurrent());
        txt_time_total.setText(PlayMusicService.getTimeSongTotal());
        seekbar_song.setMax(PlayMusicService.getTimeSongDuration());
        if (PlayMusicService.isPlayingMusic()) {
            btn_play.setImageResource(R.drawable.playmusicpause);
        }
        else
        {
            btn_play.setImageResource(R.drawable.playmusicplay);
        }
        if (PlayMusicService.getSongStateNext() == 1) {
            btn_shuffle.setColorFilter(ContextCompat.getColor(this, R.color.choose_btn_playmusic_handler));
        }
        else if (PlayMusicService.getSongStateNext() == 2) {
            btn_repeat.setColorFilter(ContextCompat.getColor(this, R.color.choose_btn_playmusic_handler));
        }
        UpdateTimeSong();
    }

    public void PlayMusicById(int id) {
        song = arraySong.get(id);
        int id_temp = Integer.parseInt(song.getIdSong());
        if (id_temp != id_cur_song) {
            txtSong.setText(song.getNameSong());
            txtSinger.setText(song.getNameSinger());
            Picasso.with(this).load(song.getImgSong()).into(blurBackground);
            fragment_playMusic_discMusic.urlImgSong = song.getImgSong();
            fragment_playMusic_discMusic.setImgDiscMusic();
            PlayMusicService.setLinkSong(song.getIdSong(), song.getLinkSong());
            PlayMusicService.PlayNewMusic();
            txt_time_current.setText(PlayMusicService.getTimeSongCurrent());
            txt_time_total.setText(PlayMusicService.getTimeSongTotal());
            seekbar_song.setMax(PlayMusicService.getTimeSongDuration());
            btn_play.setImageResource(R.drawable.playmusicpause);
            id_cur_song = PlayMusicService.getIdSongInt();
            id_cur_on_playlist = id;
            fragment_playMusic_playlist.SetBackgroundSongPlaylist(id_cur_on_playlist);
        }
    }

    private void SetupViewPager2() {
        playMusicPagerAdapter = new PlayMusicPagerAdapter(this );
        playMusicPagerAdapter.addFragment(fragment_playMusic_playlist);
        playMusicPagerAdapter.addFragment(fragment_playMusic_discMusic);
        playMusicPagerAdapter.addFragment(fragment_playMusic_information);
        viewPager2.setAdapter(playMusicPagerAdapter);
        viewPager2.setCurrentItem(1);
        circleIndicatorPlaymusic.setViewPager(viewPager2);
        playMusicPagerAdapter.registerAdapterDataObserver(circleIndicatorPlaymusic.getAdapterDataObserver());
    }

    private void SetDataIntent() {
        txtSong.setText(song.getNameSong());
        txtSinger.setText(song.getNameSinger());
        Picasso.with(this).load(song.getImgSong()).into(blurBackground);
        fragment_playMusic_discMusic.urlImgSong = song.getImgSong();
        BlurBackground();
    }

    private void BlurBackground() {
        float radius = 25f;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("music_playlist_array")) {
                id_cur_on_playlist = (int) intent.getSerializableExtra("music_playlist_id_current");
                arraySong = (ArrayList<Song>) intent.getSerializableExtra("music_playlist_array");
                song = arraySong.get(id_cur_on_playlist);
            }
        }
    }

    private void Mapping() {
        fragment_playMusic_playlist = new Fragment_PlayMusic_Playlist();
        fragment_playMusic_discMusic = new Fragment_PlayMusic_DiscMusic();
        fragment_playMusic_information = new Fragment_PlayMusic_Information();
        txtSong = (TextView) this.findViewById(R.id.textview_playmusic_namesong);
        txtSinger = (TextView) this.findViewById(R.id.textview_playmusic_namesinger);
        btnHidePlayMusic = (ImageView) this.findViewById(R.id.btn_hide_playmusic);
        blurBackground = (ImageView) this.findViewById(R.id.img_act_playmusic_background);
        viewPager2 = (ViewPager2) this.findViewById(R.id.viewpager2_playmusic);
        blurView = (BlurView) this.findViewById(R.id.imgBlur_act_playmusic_background);
        circleIndicatorPlaymusic = (CircleIndicator3) this.findViewById(R.id.circleIndicator_playmusic_viewpager2);
        txt_time_current = (TextView) this.findViewById(R.id.textview_playmusic_handler_timesong_current);
        txt_time_total = (TextView) this.findViewById(R.id.textview_playmusic_handler_timesong_total);
        seekbar_song = (SeekBar) this.findViewById(R.id.seekbar_playmusic_song);
        btn_shuffle = (ImageView) this.findViewById(R.id.btn_playmusic_handler_shuffle);
        btn_previous = (ImageView) this.findViewById(R.id.btn_playmusic_handler_previous);
        btn_play = (ImageView) this.findViewById(R.id.btn_playmusic_handler_play);
        btn_next = (ImageView) this.findViewById(R.id.btn_playmusic_handler_next);
        btn_repeat = (ImageView) this.findViewById(R.id.btn_playmusic_handler_repeat);
    }

    private void UpdateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PlayMusicService.getMediaPlayer() != null) {
                    seekbar_song.setProgress(PlayMusicService.getMediaPlayer().getCurrentPosition());
                    txt_time_current.setText(PlayMusicService.getTimeSongCurrent());
                    handler.postDelayed(this, 300);
                    PlayMusicService.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            song_next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                    });
                }
            }
        }, 300);
    }
}