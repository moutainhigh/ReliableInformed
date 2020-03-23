package com.zjs.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.zjs.entity.ZjsTransaction;
import com.zjs.news.AbstractNotifier;
import com.zjs.news.News;
import com.zjs.service.IZjsTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 李文
 * @create 2020-03-19 16:46
 **/
@Component("SendNewsTask")
public class SendNewsTask implements IScheduleTaskDealSingle<News>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SendNewsTask.class);
    @Value("${retrycount:10}")
    private int retrycount;

    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.cloud.client.ipAddress}")
    private String ip;

    @Value("${server.port}")
    private String port;


    @Autowired
    private AbstractNotifier notifier;

    @Autowired
    IZjsTransactionService service;


    @Override
    public boolean execute(News news, String s) throws Exception {
        try {
            notifier.postForEntity(news.getName(), news.getUrl(), news.getData());
        } catch (Exception e) {
            LOGGER.error(" 发送监控状态通知异常 ", e);
        }
        return true;
    }

    @Override
    public List<News> selectTasks(String s, String s1, int i, List<TaskItemDefine> list, int i1) throws Exception {
        int count = service.count(new QueryWrapper<ZjsTransaction>()
                .lt("retrycount", retrycount));
        String data = "当前发送失败的消息 已经有 " + count + " 条，请及时关注";
        News n = new News(name,"http://" + ip + ":" + port + "/index.html",data);
        List<News> newsList=new ArrayList<>();
        newsList.add(n);
        return newsList;
    }

    @Override
    public Comparator<News> getComparator() {
        return null;
    }
}
