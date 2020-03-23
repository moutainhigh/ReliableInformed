## 说明文档
   
#### 功能
```           
    1.通过查询数据库，发送MQ消息与调用接口API
    2.处理积压发送钉钉/企业微信预警通知
```
#### 参数说明

| 参数 | 是否必填 | 说明 | 默认值|
| --- | --- | --- | ---|
|job.zkConfig.zkConnectString|是|zkd地址| 无|
|zk.rootPath|否|根节点地址| /tb-schedule1/服务名称|
|job.zkConfig.userName|否|tbs用户名|admin|
|job.zkConfig.password|否|tbs密码|admin|
|spring.application.name|是|服务名称|无|
|rocketmq.producer.namesrvAddr|是|MQ地址|无|
|spring.datasource.url|是|MySql连接URL|无|
|spring.datasource.username|是|MySql用户名|无|
|spring.datasource.password|是|MySql密码|无|
|retrycount|否|处理次数|10|
|spring.zjs.news.dingDingUrl|否|钉钉通知地址|无|
|spring.zjs.news.weChatUrl|否|企业微信通知地址|无|


#### 运行说明
```
1.  配置 tbs 任务 
    1.1  发送MQ/调用接口任务 Bean
           任务处理的SpringBean:SendMqApiTask 5秒
    1.2  预警通知服务 Bean
           任务处理的SpringBean:SendNewsTask
2.  Docker 启动
     docker  run  -e 参数:值 , -e 参数:值   镜像名称
``` 
        
#### 管理页面
    地址： IP:端口/index.html


