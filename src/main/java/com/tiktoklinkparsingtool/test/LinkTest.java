package com.tiktoklinkparsingtool.test;

import com.tiktoklinkparsingtool.common.HttpClientUtil;
import com.tiktoklinkparsingtool.constant.TikTokConstant;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :52
 * @Description 测试解析地址
 */
public class LinkTest {

    public static void main(String[] args) throws Exception {
        //组装入参数据
        Map<String,String> params=new HashMap<String,String>(16);
        params.put("url","https://www.tiktok.com/@joyce__asmr/live?sender_device=pc&sender_web_id=7005408239244920326&is_from_webapp=v1&is_copy_url=0");
        //调取接口
        String getResultValue = HttpClientUtil.doPost(TikTokConstant.TIK_TOK_URL,
                (HashMap<String, String>) params);
        System.out.println("getResultValue = " + getResultValue);
    }
}
