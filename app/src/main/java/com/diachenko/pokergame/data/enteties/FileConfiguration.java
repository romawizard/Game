package com.diachenko.pokergame.data.enteties;

import com.google.gson.annotations.SerializedName;

public class FileConfiguration {


    @SerializedName("Open")
    private String open;

    @SerializedName("Openlink")
    private String openLink;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getOpenLink() {
        return openLink;
    }

    public void setOpenlink(String openLink) {
        openLink = openLink;
    }

    @Override
    public String toString() {
        return "FileConfiguration{" +
                "open='" + open + '\'' +
                ", openLink='" + openLink + '\'' +
                '}';
    }
}
