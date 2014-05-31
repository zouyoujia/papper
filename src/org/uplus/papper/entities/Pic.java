package org.uplus.papper.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by youjia.zyj on 2014/5/28.
 */
public class Pic implements Serializable {
    private long id;
    @SerializedName("imageUrl")
    private String imageUrl;
    private String desc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
