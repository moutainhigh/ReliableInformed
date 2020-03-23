package com.zjs.schedule;

import com.taobao.pamirs.schedule.TaskItemDefine;
import lombok.Data;

import java.util.List;

/**
 * @author 李文
 * @create 2020-03-19 13:57
 **/
@Data
public class BaseTask
{
    /**
     * 每次查询的任务的数量
     **/
    private int fetchNum;

    /**
     * 任务队列数量
     **/
    private int queueNum;

    /**
     * 获得的任务项
     **/
    private String condition;

    /**
     * 重试次数
     */
    private int retrycount;


    public BaseTask setCondition(List<TaskItemDefine> queueList) {
        StringBuilder condition = new StringBuilder();
        for (int i = 0; i < queueList.size(); i++) {
            if (i > 0) {
                condition.append(",");
            }
            TaskItemDefine s = queueList.get(i);
            condition.append(s.getTaskItemId());
        }
        this.condition = condition.toString();
        return this;
    }
    public BaseTask setCondition(String t) {

        this.condition = t;
        return this;
    }
}
