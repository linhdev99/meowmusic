package com.bkuhp2l.meowmusic.Service;

import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.Model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("song-banner.php")
    Call<List<Banner>> getDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> getDataPlaylist();
}
