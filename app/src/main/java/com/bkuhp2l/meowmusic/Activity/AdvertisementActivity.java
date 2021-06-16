package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class AdvertisementActivity extends AppCompatActivity {
    ActionBar actionBar;
    Banner banner;
    ImageView imgBackground, imgAvatar;
    TextView txtName, txtContent;
    RoundedImageView btnBack;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        actionBar = getSupportActionBar();
        actionBar.hide();
        DataIntent();
        Mapping();
        SetDataIntent();
        HandlerButton();
    }

    private void HandlerButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void SetDataIntent() {
        Picasso.with(this).load(banner.getImage()).into(imgBackground);
        Picasso.with(this).load(banner.getAvatar()).into(imgAvatar);
        txtName.setText(banner.getTitle());
        txtContent.setText(banner.getContent());
    }

    private void Mapping() {
        imgBackground = (ImageView) this.findViewById(R.id.img_background_act_adv);
        imgAvatar = (ImageView) this.findViewById(R.id.img_avt_act_adv);
        txtName = (TextView) this.findViewById(R.id.textView_name_act_adv);
        txtContent = (TextView) this.findViewById(R.id.textView_content_act_adv);
        btnBack = (RoundedImageView) this.findViewById(R.id.btn_back_act_adv);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")) {
                banner = (Banner) intent.getSerializableExtra("banner");
            }
        }
    }

}