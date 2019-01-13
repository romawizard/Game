package com.diachenko.pokergame.data;

public class ApplicationMode {

    private Mode mode;
    private String url;

    private ApplicationMode(Mode mode, String url) {
        this.mode = mode;
        this.url = url;
    }

    public static ApplicationMode appMode() {
        return new ApplicationMode(Mode.APPLICATION, null);
    }

    public static ApplicationMode linkMode(String url) {
        return new ApplicationMode(Mode.LINK, url);
    }


    public static ApplicationMode otherMode() {
        return new ApplicationMode(Mode.OTHER, null);
    }

    public Mode getMode() {
        return mode;
    }

    public String getUrl() {
        return url;
    }


    public enum Mode {APPLICATION, LINK, OTHER}
}
