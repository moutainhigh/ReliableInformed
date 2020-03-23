package com.zjs.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李文
 * @create 2020-03-04 10:29
 **/
@Controller
public class NotificationController
{

    @Autowired
    private AbstractNotifier notifier;
    /**
     * 默认通知API地址
     *
             * @param data 字符串的数据
     * @return y =成功 ， n= 失败
     */
    @PostMapping("/api/notification")
    public String notification(@RequestBody String data) {
        //return "y";
        notifier.postForEntity("测试服务", "10.10.01", "处理消息有100个积压，请及时关注！ ");
        return "n";
    }

    @Autowired
    RestTemplate restTemplate;
    /**
     * 调用服务
     * @param name  服务名称
     * @param url   URL地址  数据库默认值为/api/notification   所以不会有空
     * @param data  要发送 字符串 数据
     * @return   接口返回 是否为 Y
     * @throws Exception   抛出调用异常
     */
    public boolean isSend(String name, String url, String data) throws Exception {

        return "y".equals(restTemplate.postForObject("http://" + name + "/" + url, data, String.class));
    }
}
