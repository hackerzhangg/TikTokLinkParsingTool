package com.tiktoklinkparsingtool.controller;

import com.tiktoklinkparsingtool.service.DomesticParsingService;
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
 * @Since 2021 -09 -13 17 :47
 * @Description
 */
@Controller
@RequestMapping("interface/domestic")
public class DomesticParsingController {

    @Autowired
    DomesticParsingService domesticParsingService;

    /**
     * 解析国内抖音直播链接
     * @param response
     * @param url
     * @return
     * @throws Exception
     */
    @RequestMapping("getDomesticParsing")
    @ResponseBody
    public Map<String,Object> getDomesticParsing(ServletResponse response, String url,String type) throws Exception {
        Map<String,Object> stringMap=new HashMap<String, Object>(16);
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        //判断参数是否为空
        if (StringUtils.isEmpty(url)){
            stringMap.put("result_code","9999");
            stringMap.put("result_msg","接口参数不能为空!");
            return stringMap;
        }
        stringMap=domesticParsingService.getLinkParsing(url,type);
        return null;
    }
}
