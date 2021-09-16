package com.tiktoklinkparsingtool.controller;

import com.tiktoklinkparsingtool.common.HttpClientUtil;
import com.tiktoklinkparsingtool.constant.TikTokConstant;
import com.tiktoklinkparsingtool.service.LinkParsingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zgp
 * @Since 2021 -09 -11 16 :15
 * @Description 解析直播链接
 */
@Controller
@RequestMapping("linkparsing")
public class LinkParsingController {

    @Autowired
    LinkParsingService linkParsingService;

    /**
     * 通过url解析直播链接
     * @param response
     * @param url
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getLinkParsing")
    public Map<String,String> getLinkParsing(ServletResponse response,String url) throws Exception {
        Map<String,String> stringMap=new HashMap<String, String>(16);
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        //判断参数是否为空
        if (StringUtils.isEmpty(url)){
            stringMap.put("result_code","9999");
            stringMap.put("result_msg","接口参数不能为空!");
            return stringMap;
        }
        //调用接口
        stringMap=linkParsingService.getLinkParsing(url);
        return stringMap;
    }

    /**
     * 通过url解析直播链接测试
     * @param response
     * @param url
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getLinkParsingTest")
    public Map<String,String> getLinkParsingTest(ServletResponse response,String url) throws Exception {
        Map<String,String> stringMap=new HashMap<String, String>(16);
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        //判断参数是否为空
        if (StringUtils.isEmpty(url)){
            stringMap.put("result_code","9999");
            stringMap.put("result_msg","接口参数不能为空!");
            return stringMap;
        }
        //组装入参数据
        Map<String,String> params=new HashMap<String,String>(16);
        params.put("url",url);
        //调取接口
        String getResultValue = HttpClientUtil.doPost(TikTokConstant.TIK_TOK_URL,
                (HashMap<String, String>) params);
        stringMap.put("result",getResultValue);
        return stringMap;
    }
}
