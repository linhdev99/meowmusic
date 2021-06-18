package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bkuhp2l.meowmusic.Adapter.PlayMusicPagerAdapter;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_DiscMusic;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_Information;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_Playlist;
import com.bkuhp2l.meowmusic.Model.Song;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.PlayMusicService;
import com.squareup.picasso.Picasso;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import me.relex.circleindicator.CircleIndicator3;

public class PlayMusicActivity extends AppCompatActivity {
    TextView txtSong, txtSinger;
    ImageView btnHidePlayMusic;
    ImageView blurBackground;
    BlurView blurView;
    Song song;
    ActionBar actionBar;
    ViewPager2 viewPager2;
    PlayMusicPagerAdapter playMusicPagerAdapter;
    CircleIndicator3 circleIndicatorPlaymusic;
    Fragment_PlayMusic_Playlist fragment_playMusic_playlist;
    Fragment_PlayMusic_DiscMusic fragment_playMusic_discMusic;
    Fragment_PlayMusic_Information fragment_playMusic_information;
//    MediaPlayer mediaPlayer;
    TextView txt_time_current, txt_time_total;
    SeekBar seekbar_song;
    ImageView btn_shuffle, btn_previous, btn_play, btn_next, btn_repeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        actionBar = getSupportActionBar();
        actionBar.hide();
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
//        PlayMusicService.stopMusic();
    }


    private void EventClick() {
        btnHidePlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBackHome();
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 500);
    }

    private void InitialMedia() {
//        PlayMp3 playmusic_mp3 = new PlayMp3();
//        playmusic_mp3.execute(hotMusic.getLinkSong());
        if (Integer.parseInt(song.getIdSong()) != PlayMusicService.getIdSongInt()) {
//            Toast.makeText(this, hotMusic.getIdSong() + " = " + PlayMusicService.getIdSongString(), Toast.LENGTH_SHORT).show();
            PlayMusicService.setLinkSong(song.getIdSong(), song.getLinkSong());
            PlayMusicService.PlayNewMusic();
        }
        txt_time_current.setText(PlayMusicService.getTimeSongCurrent());
        txt_time_total.setText(PlayMusicService.getTimeSongTotal());
        seekbar_song.setMax(PlayMusicService.getTimeSongDuration());
        int test = PlayMusicService.getTimeSongDuration();
        btn_play.setImageResource(R.drawable.playmusicpause);
        UpdateTimeSong();
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
            if (intent.hasExtra("hotmusic")) {
                song = (Song) intent.getSerializableExtra("hotmusic");
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