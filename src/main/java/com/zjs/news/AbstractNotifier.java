package com.zjs.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李文
 * @create 2020-03-03 16:24
 **/
public abstract class AbstractNotifier
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DingDingNews.class);

    String WebhookUrl;

    //https://oapi.dingtalk.com/robot/send?access_token=ab4de3f91e1d62be5516f3f1aedb955898edbec21c19295600c16651e72991a0
    public AbstractNotifier(String url) {
        this.WebhookUrl = url;
    }

    /**
     * 获取发送的消息内容
     *
     * @param name 服务名称
     * @param url  服务URL
     * @param data 数据
     * @return 消息内容
     */
    public abstract String getNews(String name, String url, String data);


    private RestTemplate restTemplate = new RestTemplate();

    public void postForEntity(String name, String url, String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity<>(getNews(name, url, data), headers);
        ResponseEntity e = this.restTemplate.postForEntity(WebhookUrl, entity, String.class);
        LOGGER.info("响应内容：" + e.toString());
    }
}
