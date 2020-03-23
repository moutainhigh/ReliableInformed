package com.zjs.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjs.entity.ZjsTransaction;
import com.zjs.schedule.BaseTask;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author atliwen
 * @since 2020-03-11
 */
public interface ZjsTransactionMapper extends BaseMapper<ZjsTransaction>
{

    List<ZjsTransaction> queryTaskToProcess(BaseTask b) throws Exception;



    List<ZjsTransaction> queryTaskToProcessTest(BaseTask b) throws Exception;
}
