package com.zjs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author atliwen
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zjs_transaction")
public class ZjsTransaction extends Model<ZjsTransaction>
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 特征标识
     */
    private String characteristic;

    /**
     * MQ消息内容/http实体对象json
     */
    private String message;

    /**
     * 创建时间
     */
    private Date creationtime;

    /**
     * 处理状态，1 未处理，2 已处理
     */
    private Integer status;

    /**
     * 要推送的tag/URL地址
     */
    private String tags;

    /**
     * 要推送的topic/服务名称
     */
    private String topic;

    /**
     * 最后一次重推更新时间
     */
    private Date updatetime;

    /**
     * 当重推失败时，存储失败信息
     */
    private String exceptionmsg;

    /**
     * 数据类型 0 MQ，1 接口
     */
    private Integer type;

    /**
     * 数据唯一标识（MD5/其他唯一数据）
     */
    private String onlydata;

    /**
     * 处理次数
     */
    private Integer retrycount;

    /**
     * 指定的主题队列下标（用于有序队列）
     */
    private Integer queueid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
