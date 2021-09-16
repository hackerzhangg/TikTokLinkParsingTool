package com.tiktoklinkparsingtool.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author zgp
 * @Since 2021 -09 -11 16 :16
 * @Description 解析直播链接数据库层
 */
public interface LinkParsingMapper {

    /**
     * 添加直播解析记录
     * @param nowDay
     * @param retUrl
     */
    void sageLinkParsing(@Param("time") String nowDay, @Param("url") String retUrl);
}
