package com.bkuhp2l.meowmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotMusic implements Serializable {
    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("nameSong")
    @Expose
    private String nameSong;
    @SerializedName("linkSong")
    @Expose
    private String linkSong;
    @SerializedName("imgSong")
    @Expose
    private String imgSong;
    @SerializedName("reactSong")
    @Expose
    private String reactSong;
    @SerializedName("idSinger")
    @Expose
    private String idSinger;
    @SerializedName("idAdvertisement")
    @Expose
    private Object idAdvertisement;
    @SerializedName("nameSinger")
    @Expose
    private String nameSinger;

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getImgSong() {
        return imgSong;
    }

    public void setImgSong(String imgSong) {
        this.imgSong = imgSong;
    }

    public String getReactSong() {
        return reactSong;
    }

    public void setReactSong(String reactSong) {
        this.reactSong = reactSong;
    }

    public String getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(String idSinger) {
        this.idSinger = idSinger;
    }

    public Object getIdAdvertisement() {
        return idAdvertisement;
    }

    public void setIdAdvertisement(Object idAdvertisement) {
        this.idAdvertisement = idAdvertisement;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }
}
