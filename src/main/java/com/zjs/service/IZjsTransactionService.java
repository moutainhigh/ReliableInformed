package com.zjs.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zjs.entity.ZjsTransaction;
import com.zjs.schedule.BaseTask;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author atliwen
 * @since 2020-03-11
 */
public interface IZjsTransactionService extends IService<ZjsTransaction>
{
    List<ZjsTransaction> queryTaskToProcess(BaseTask b) throws Exception;
}
