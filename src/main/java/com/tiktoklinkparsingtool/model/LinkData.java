package com.tiktoklinkparsingtool.model;

/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :21
 * @Description 解析链接实体
 */
public class LinkData {

    public Integer id;
    public String link;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
