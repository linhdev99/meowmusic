package com.bkuhp2l.meowmusic.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_PlayMusic_DiscMusic extends Fragment {
    View view;
    CircleImageView imgDiscMusic, imgDiscMusicCore;
    ObjectAnimator objAnimator;
    public String urlImgSong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playmusic_discmusic, container, false);
        Mapping();
        InitialData();
        DiscAnimation(imgDiscMusic);
        DiscAnimation(imgDiscMusicCore);
        return view;
    }

    private void InitialData() {
        Picasso.with(getActivity()).load(urlImgSong).into(imgDiscMusic);
    }

    private void DiscAnimation(CircleImageView imageCircle) {
        objAnimator = ObjectAnimator.ofFloat(imageCircle,"rotation",0f,360f);
        objAnimator.setDuration(15000);
        objAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objAnimator.setRepeatMode(ValueAnimator.RESTART);
        objAnimator.setInterpolator(new LinearInterpolator());
        objAnimator.start();
    }

    private void Mapping() {
        imgDiscMusic = (CircleImageView) view.findViewById(R.id.img_disc_music);
        imgDiscMusicCore = (CircleImageView) view.findViewById(R.id.img_disc_music_core);
    }
}
