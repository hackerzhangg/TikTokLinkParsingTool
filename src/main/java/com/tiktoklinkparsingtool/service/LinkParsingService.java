package com.tiktoklinkparsingtool.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tiktoklinkparsingtool.common.HttpClientUtil;
import com.tiktoklinkparsingtool.common.TimeUtil;
import com.tiktoklinkparsingtool.constant.TikTokConstant;
import com.tiktoklinkparsingtool.mapper.LinkParsingMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zgp
 * @Since 2021 -09 -11 16 :16
 * @Description 解析直播链接业务逻辑层
 */
@Service
public class LinkParsingService {

    @Autowired
    LinkParsingMapper linkParsingMapper;

    /**
     * 获取直播解析地址并持久化
     * @param url
     * @return
     */
    public Map<String, String> getLinkParsing(String url) {
        Map<String,String> stringMap=new HashMap<String, String>();
        try{
            //组装入参数据
            Map<String,String> params=new HashMap<String,String>(16);
            params.put("url",url);
            //调取接口
            String getResultValue = HttpClientUtil.doPost(TikTokConstant.TIK_TOK_URL,
                    (HashMap<String, String>) params);
            String nowDay = TimeUtil.getNowDay();
            stringMap.put("nowTime",nowDay);
            if (StringUtils.isEmpty(getResultValue)){
                stringMap.put("result_code","9999");
                stringMap.put("result_msg","解析链接参数异常，请联系管理员!");
                return stringMap;
            }
            if (StringUtils.equals(getResultValue,"{}")){
                stringMap.put("result_code","9999");
                stringMap.put("result_msg","解析链接参数异常为{}，请联系管理员!");
                return stringMap;
            }
            //解析返回参数信息
            JSONObject repJson = JSON.parseObject(getResultValue);
            //String retMsg= (String) repJson.get("msg");
            String codeValue = repJson.getString("code");
            String failCode="10001";
            if (StringUtils.equals(codeValue,failCode)){
                stringMap.put("result_code","9999");
                stringMap.put("result_msg","当前直播链接暂不可解析!");
                return stringMap;
            }
            Map<String,String> data = (Map<String, String>) repJson.get("data");
            String retUrl = data.get("url");
            if (StringUtils.isEmpty(retUrl)){
                stringMap.put("result_code","9999");
                stringMap.put("result_msg","解析链接url为空,请更换尝试！");
                return stringMap;
            }else {
                stringMap.put("result_code","0000");
                stringMap.put("result_msg","操作成功!");
                stringMap.put("result_data",retUrl);

                //将数据保存到link表中
                linkParsingMapper.sageLinkParsing(nowDay,retUrl);
                return stringMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringMap;
    }
}
