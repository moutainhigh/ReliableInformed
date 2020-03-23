package com.zjs.health.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康监控节点示例  TODO 需要添加业务自设的检查规则时候，不需要请删除
 *
 * @author 李文
 * @date 2019-03-14 14:14
 **/
@Component
public class TestHealthIndicator implements HealthIndicator
{
    @Override
    public Health health() {
        //  TODO  实现自己的健康检查项
        int errorCode = check();
        if (errorCode == 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }
    private int check() {
        return 1;
    }
}
