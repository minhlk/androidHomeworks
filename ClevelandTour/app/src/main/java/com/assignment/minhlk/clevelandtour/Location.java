package com.assignment.minhlk.clevelandtour;

/**
 * Created by minhlk on 5/11/17.
 */

public class Location {

    String title,lat,log,url;

    public Location(String title, String lat, String log, String url) {
        this.title = title;
        this.lat = lat;
        this.log = log;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
