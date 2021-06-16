package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bkuhp2l.meowmusic.Adapter.PlayMusicPagerAdapter;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_DiscMusic;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic_Playlist;
import com.bkuhp2l.meowmusic.Model.HotMusic;
import com.bkuhp2l.meowmusic.R;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import me.relex.circleindicator.CircleIndicator3;

public class PlayMusicActivity extends AppCompatActivity {
    TextView txtSong, txtSinger;
//    CircleImageView imgSong;
    ImageView btnHidePlayMusic;
    ImageView blurBackground;
    BlurView blurView;
    HotMusic hotMusic;
    ActionBar actionBar;
    ViewPager2 viewPager2;
    PlayMusicPagerAdapter playMusicPagerAdapter;
    CircleIndicator3 circleIndicatorPlaymusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        actionBar = getSupportActionBar();
        actionBar.hide();

        Mapping();
        DataIntent();
        SetDataIntent();
        SetupViewPager2();

        btnHidePlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void SetupViewPager2() {
        playMusicPagerAdapter = new PlayMusicPagerAdapter(this );
        playMusicPagerAdapter.addFragment(new Fragment_PlayMusic_Playlist());
        playMusicPagerAdapter.addFragment(new Fragment_PlayMusic_DiscMusic());
        viewPager2.setAdapter(playMusicPagerAdapter);
        viewPager2.setCurrentItem(1);
        circleIndicatorPlaymusic.setViewPager(viewPager2);
        playMusicPagerAdapter.registerAdapterDataObserver(circleIndicatorPlaymusic.getAdapterDataObserver());
    }

    private void SetDataIntent() {
        txtSong.setText(hotMusic.getNameSong());
        txtSinger.setText(hotMusic.getNameSinger());
        Picasso.with(this).load(hotMusic.getImgSong()).into(blurBackground);
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
        txtSong = (TextView) this.findViewById(R.id.textview_playmusic_namesong);
        txtSinger = (TextView) this.findViewById(R.id.textview_playmusic_namesinger);
//        imgSong = (CircleImageView) this.findViewById(R.id.img_disc_music);
        btnHidePlayMusic = (ImageView) this.findViewById(R.id.btn_hide_playmusic);
        blurBackground = (ImageView) this.findViewById(R.id.img_act_playmusic_background);
        viewPager2 = (ViewPager2) this.findViewById(R.id.viewpager2_playmusic);
        blurView = (BlurView) this.findViewById(R.id.imgBlur_act_playmusic_background);
        circleIndicatorPlaymusic = (CircleIndicator3) this.findViewById(R.id.circleIndicator_playmusic_viewpager2);
    }
}