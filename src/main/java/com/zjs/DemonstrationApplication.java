package com.zjs;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 李文
 * @create 2019-04-22 14:10
 **/
@SpringCloudApplication
@EnableHystrix
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@MapperScan("com.zjs.mapper")
public class DemonstrationApplication
{
    public static void main(String[] args) {
        SpringApplication.run(DemonstrationApplication.class, args);
        //com.taobao.pamirs.schedule.taskmanager.TBScheduleManagerStatic
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${spring.application.name}")
    String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    String namesrvAdd;

    @Bean
    public DefaultMQProducer defaultMQProducerrFactory() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAdd);
        producer.start();
        return producer;
    }

}
