package com.example.stelp;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

public class ListItem implements Parcelable {

    private String name, path, etag, contentType, publicUrl, mediaType;
    private boolean dir;
    private long contentLength, lastUpdated;

    public ListItem(Resources resources) {
        this.name = resources.getName();
        this.path = resources.getPath() != null ? resources.getPath().getPath() : null;
        this.etag = resources.getMd5();
        this.contentType = resources.getMimeType();
        this.publicUrl = resources.getPublicUrl();
        this.mediaType = resources.getMediaType();
        this.dir = resources.isDir();
        this.contentLength = resources.getSize();
        this.lastUpdated = resources.getModified() != null ? resources.getModified().getTime() : 0;
    }

    private String ListItem(String path, String name, long contentLength, long lastUpdated,
                            boolean dir, String etag, String contentType, String publicUrl, String mediaType) {
        return "ListItem{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", etag='" + etag + '\'' +
                ", contentType='" + contentType + '\'' +
                ", publicUrl='" + publicUrl + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", dir=" + dir +
                ", contentLength=" + contentLength +
                ", lastUpdate=" + lastUpdated +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(name);
        parcel.writeLong(contentLength);
        parcel.writeLong(lastUpdated);
        parcel.writeByte((byte) (dir ? 1 : 0));
        parcel.writeString(etag);
        parcel.writeString(contentType);
        parcel.writeString(publicUrl);
        parcel.writeString(mediaType);
    }

    protected ListItem(Parcel parcel) {
        name = parcel.readString();
        path = parcel.readString();
        etag = parcel.readString();
        contentType = parcel.readString();
        publicUrl = parcel.readString();
        mediaType = parcel.readString();
        dir = parcel.readByte() > 0;
        contentLength = parcel.readLong();
        lastUpdated = parcel.readLong();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel parcel) {
            return new ListItem(parcel);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public String getPath() { return path; }

    public String getName() { return name; }

    public long getContentLength() { return contentLength; }

    public long getLastUpdated() { return lastUpdated; }

    public boolean isDir() { return dir; }

    public String getContentType() { return  contentType; }

    public String getEtag() { return etag; }

    public  String getPublicUrl() { return  publicUrl; }

    public String getMimeType() { return mediaType; }
}




