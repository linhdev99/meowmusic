package com.bkuhp2l.meowmusic.Service;

import com.bkuhp2l.meowmusic.Model.Album;
import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.Model.Category;
import com.bkuhp2l.meowmusic.Model.Song;
import com.bkuhp2l.meowmusic.Model.Playlist;
import com.bkuhp2l.meowmusic.Model.SongPlaylist;
import com.bkuhp2l.meowmusic.Model.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface DataService {
    @GET("banner.php")
    Call<List<Banner>> getDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> getDataPlaylist();

    @GET("all-playlist.php")
    Call<List<Playlist>> getDataMorePlaylist();

    @FormUrlEncoded
    @GET("song-playlist.php")
    Call<List<SongPlaylist>> getDataSongPlaylist(@Field("idPlaylist") String idPlaylist);

    @GET("topic.php")
    Call<List<Topic>> getDataTopic();

    @GET("category.php")
    Call<List<Category>> getDataCategory();

    @GET("album.php")
    Call<List<Album>> getDataAlbum();

    @GET("hotmusic.php")
    Call<List<Song>> getDataHotMusic();
}
