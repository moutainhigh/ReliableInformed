package com.zjs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjs.entity.ZjsTransaction;
import com.zjs.mapper.ZjsTransactionMapper;
import com.zjs.schedule.BaseTask;
import com.zjs.service.IZjsTransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atliwen
 * @since 2020-03-11
 */
@Service
public class ZjsTransactionServiceImpl extends ServiceImpl<ZjsTransactionMapper, ZjsTransaction> implements IZjsTransactionService
{

    @Override
    public List<ZjsTransaction> queryTaskToProcess(BaseTask b) throws Exception {
        return baseMapper.queryTaskToProcess(b);

    }
}
