package com.bkuhp2l.meowmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SongPlaylist implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("idSong")
    @Expose
    private String idSong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }
}
