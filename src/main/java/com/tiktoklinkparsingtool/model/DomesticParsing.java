package com.tiktoklinkparsingtool.model;

/**
 * @Author zgp
 * @Since 2021 -09 -13 17 :50
 * @Description
 */
public class DomesticParsing {

    private Integer id;
    private String flvUrl;
    private String hlsUrl;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlvUrl() {
        return flvUrl;
    }

    public void setFlvUrl(String flvUrl) {
        this.flvUrl = flvUrl;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
