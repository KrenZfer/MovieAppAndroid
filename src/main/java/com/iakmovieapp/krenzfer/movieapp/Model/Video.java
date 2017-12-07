package com.iakmovieapp.krenzfer.movieapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by krenzfer on 06/09/17.
 */

public class Video implements Parcelable {

    private String idVideo;
    private String keyVideo;
    private String nameVideo;
    private String siteVideo;

    public Video(String idVideo, String keyVideo, String nameVideo, String siteVideo) {
        this.idVideo = idVideo;
        this.keyVideo = keyVideo;
        this.nameVideo = nameVideo;
        this.siteVideo = siteVideo;
    }

    protected Video(Parcel in) {
        idVideo = in.readString();
        keyVideo = in.readString();
        nameVideo = in.readString();
        siteVideo = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getKeyVideo() {
        return keyVideo;
    }

    public void setKeyVideo(String keyVideo) {
        this.keyVideo = keyVideo;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public String getSiteVideo() {
        return siteVideo;
    }

    public void setSiteVideo(String siteVideo) {
        this.siteVideo = siteVideo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idVideo);
        dest.writeString(keyVideo);
        dest.writeString(nameVideo);
        dest.writeString(siteVideo);
    }
}
