package com.bkuhp2l.meowmusic.Service;

import com.bkuhp2l.meowmusic.Model.Album;
import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.Model.Category;
import com.bkuhp2l.meowmusic.Model.HotMusic;
import com.bkuhp2l.meowmusic.Model.Playlist;
import com.bkuhp2l.meowmusic.Model.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("banner.php")
    Call<List<Banner>> getDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> getDataPlaylist();

    @GET("topic.php")
    Call<List<Topic>> getDataTopic();

    @GET("category.php")
    Call<List<Category>> getDataCategory();

    @GET("album.php")
    Call<List<Album>> getDataAlbum();

    @GET("hotmusic.php")
    Call<List<HotMusic>> getDataHotMusic();
}
