package org.uplus.papper.entities;

import com.google.gson.JsonElement;

import java.io.Serializable;

/**
 * Created by youjia.zyj on 2014/5/29.
 */
public class Api implements Serializable {
    int totalNum;
    int startIndex;
    int returnNumber;
    JsonElement imgs;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(int returnNumber) {
        this.returnNumber = returnNumber;
    }

    public JsonElement getImgs() {
        return imgs;
    }

    public void setImgs(JsonElement imgs) {
        this.imgs = imgs;
    }
}
