package com.jt.web;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest {


    @Test
    public void test01() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "https://www.baidu.com";

        HttpPost httpPost = new HttpPost(url);
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        if (httpResponse.getStatusLine().getStatusCode() == 200){

            System.out.println("请求有效");
        }

        String result = EntityUtils.toString(httpResponse.getEntity());

        System.out.println(result);
    }
}
