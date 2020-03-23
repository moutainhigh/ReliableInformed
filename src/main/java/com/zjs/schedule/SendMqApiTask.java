package com.zjs.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.zjs.entity.ZjsTransaction;
import com.zjs.news.NotificationController;
import com.zjs.service.IZjsTransactionService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author atliwen
 */
@Component("SendMqApiTask")
public class SendMqApiTask implements IScheduleTaskDealSingle<ZjsTransaction>
{
    private static final Logger LOG = LoggerFactory.getLogger(SendMqApiTask.class);

    @Override
    public Comparator<ZjsTransaction> getComparator() {
        return null;
    }
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    NotificationController notification;

    @Autowired
    DefaultMQProducer mq;

    @Autowired
    IZjsTransactionService service;
    @Value("${retrycount:10}")
    private int retrycount;

    @Override
    public List<ZjsTransaction> selectTasks(String taskParameter, String ownSign, int taskQueueNum,
                                            List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        LOG.info("IScheduleTaskDealSingleTest选择任务列表开始啦..........");
        BaseTask b = new BaseTask();
        b.setQueueNum(taskQueueNum);
        b.setFetchNum(eachFetchDataNum);
        b.setRetrycount(retrycount);
        b.setCondition(taskItemList);
        return service.queryTaskToProcess(b);

    }

    @Override
    public boolean execute(ZjsTransaction model, String ownSign) throws Exception {
        ZjsTransaction newModel = new ZjsTransaction();
        newModel.setId(model.getId());
        LOG.error(model.getCharacteristic());
        //  发送消息

        try {
            boolean bo = true;
            if (model.getType() == 1) {
                bo = notification.isSend(model.getTopic(), model.getTags(), model.getMessage());
            } else {
                SendResult result = mq.send(new Message(model.getTopic(), model.getTags(), model.getMessage().getBytes(RemotingHelper.DEFAULT_CHARSET)));
                LOG.info(" {},{},{} ", model.getId(), model.getCharacteristic(), result);
            }
            if (bo) {
                //发送成功  不在处理
                newModel.setStatus(2);
            }
        } catch (Exception e) {
            newModel.setExceptionmsg(e.toString());
        }
        newModel.setRetrycount(model.getRetrycount() + 1);
        if (retrycount == newModel.getRetrycount()) {
            // 超出处理次数 不在处理
            newModel.setStatus(2);
        }
        newModel.setUpdatetime(new Date());
        service.updateById(newModel);
        return true;

    }


}
