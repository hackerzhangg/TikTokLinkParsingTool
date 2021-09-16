package com.tiktoklinkparsingtool.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :50
 * @Description
 */
public class HttpClientUtil {

    /**
     * Http Get方法
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (Map.Entry<String, String> entries : param.entrySet()) {
                    builder.addParameter(entries.getKey(), entries.getValue());
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            int status = 200;
            if (response.getStatusLine().getStatusCode() == status) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {

            }
        }
        return resultString;
    }

    /**
     * form-data 接口传参方式
     * Post方式
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String doPost(String url, HashMap<String, String> map) throws Exception {
        String result = "";
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(550000).setConnectTimeout(550000)
                .setConnectionRequestTimeout(550000).setStaleConnectionCheckEnabled(true).build();
        client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        URIBuilder uriBuilder = new URIBuilder(url);

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("Charset", "UTF8");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            params.add(pair);
        }

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        try {
            response = client.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF8");
                }
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException("创建连接失败" + e);
        } catch (IOException e) {
            throw new RuntimeException("创建连接失败" + e);
        }finally {
            try {
                client.close();
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
