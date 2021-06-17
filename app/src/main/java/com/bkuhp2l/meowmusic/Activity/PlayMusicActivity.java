package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
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
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_HandlerMusic;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_Playlist;
import com.bkuhp2l.meowmusic.Model.HotMusic;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.PlayMusicService;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import me.relex.circleindicator.CircleIndicator3;

public class PlayMusicActivity extends AppCompatActivity {
    TextView txtSong, txtSinger;
    ImageView btnHidePlayMusic;
    ImageView blurBackground;
    BlurView blurView;
    HotMusic hotMusic;
    ActionBar actionBar;
    ViewPager2 viewPager2;
    PlayMusicPagerAdapter playMusicPagerAdapter;
    CircleIndicator3 circleIndicatorPlaymusic;
    Fragment_PlayMusic_Playlist fragment_playMusic_playlist;
    Fragment_PlayMusic_DiscMusic fragment_playMusic_discMusic;
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
        if (Integer.parseInt(hotMusic.getIdSong()) != PlayMusicService.getIdSongInt()) {
//            Toast.makeText(this, hotMusic.getIdSong() + " = " + PlayMusicService.getIdSongString(), Toast.LENGTH_SHORT).show();
            PlayMusicService.setLinkSong(hotMusic.getIdSong(), hotMusic.getLinkSong());
            PlayMusicService.PlayNewMusic();
        }
        txt_time_current.setText(PlayMusicService.getTimeSongCurrent());
        txt_time_total.setText(PlayMusicService.getTimeSongTotal());
        seekbar_song.setMax(PlayMusicService.getTimeSongDuration());
        int test = PlayMusicService.getTimeSongDuration();
        btn_play.setImageResource(R.drawable.playmusicpause);
    }

    private void SetupViewPager2() {
        playMusicPagerAdapter = new PlayMusicPagerAdapter(this );
        playMusicPagerAdapter.addFragment(fragment_playMusic_playlist);
        playMusicPagerAdapter.addFragment(fragment_playMusic_discMusic);
        viewPager2.setAdapter(playMusicPagerAdapter);
        viewPager2.setCurrentItem(1);
        circleIndicatorPlaymusic.setViewPager(viewPager2);
        playMusicPagerAdapter.registerAdapterDataObserver(circleIndicatorPlaymusic.getAdapterDataObserver());
    }

    private void SetDataIntent() {
        txtSong.setText(hotMusic.getNameSong());
        txtSinger.setText(hotMusic.getNameSinger());
        Picasso.with(this).load(hotMusic.getImgSong()).into(blurBackground);
        fragment_playMusic_discMusic.urlImgSong = hotMusic.getImgSong();
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
                hotMusic = (HotMusic) intent.getSerializableExtra("hotmusic");
            }
        }
    }

    private void Mapping() {
        fragment_playMusic_playlist = new Fragment_PlayMusic_Playlist();
        fragment_playMusic_discMusic = new Fragment_PlayMusic_DiscMusic();
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

//    class PlayMp3 extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return strings[0];
//        }
//
//        @Override
//        protected void onPostExecute(String song) {
//            super.onPostExecute(song);
//            try {
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    mediaPlayer.stop();
//                    mediaPlayer.reset();
//                }
//            });
//                mediaPlayer.setDataSource(song);
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mediaPlayer.start();
//            TimeSong();
//        }
//    }
//
//    private void TimeSong() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//        txt_time_current.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
//        txt_time_total.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
//        seekbar_song.setMax(mediaPlayer.getDuration());
//    }
}