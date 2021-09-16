package com.tiktoklinkparsingtool.service;

import com.tiktoklinkparsingtool.common.HttpClientUtil;
import com.tiktoklinkparsingtool.constant.DomesticParsingConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zgp
 * @Since 2021 -09 -13 17 :49
 * @Description
 */
@Service
public class DomesticParsingService {

    public Map<String, Object> getLinkParsing(String url,String type) {
        Map<String,Object> retMap=new HashMap<String, Object>(16);
        try{
            Map<String,String> params=new HashMap<String, String>(16);
            params.put("url",url);
            params.put("type",type);
            String retParams = HttpClientUtil.doGet(DomesticParsingConstant.DOMESTIC_URL, params);
            if (StringUtils.isEmpty(retParams)){
                retMap.put("result_code","9999");
                retMap.put("result_msg","请求接口为空!");
                return retMap;
            }
            if (StringUtils.equals(DomesticParsingConstant.IS_EMPTY_VALUE,retParams)){
                retMap.put("result_code","9999");
                retMap.put("result_msg","请求接口为{}!");
                return retMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
