package com.zjs.news;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author 李文
 * @create 2020-03-06 11:14
 **/
@Configuration
@ConfigurationProperties("spring.zjs.news")
@Data
public class NewsConfigBean
{
    private String dingDingUrl = "";
    private String weChatUrl = "";
    @Bean
    public AbstractNotifier notifier() throws Exception {
        if (!StringUtils.isEmpty(weChatUrl))
        {
             return  new WeChatNews(weChatUrl);
        }
        if (!StringUtils.isEmpty(dingDingUrl))
        {
            return  new DingDingNews(dingDingUrl);
        }
        throw  new Exception("未配置通知地址");
    }
}
