//package com.zjs.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zjs.DemonstrationApplication;
//import com.zjs.entity.ZjsTransaction;
//import com.zjs.mapper.ZjsTransactionMapper;
//import com.zjs.schedule.BaseTask;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
///**
// * @author 李文
// * @create 2019-04-23 13:45
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemonstrationApplication.class)
//public class MyTetst
//{
//
//
//    @Autowired
//    ZjsTransactionMapper mapper;
//
//    @Test
//    public void test_1() throws Exception {
//        BaseTask b = new BaseTask();
//        b.setFetchNum(500);
//        b.setRetrycount(10);
//        b.setQueueNum(1);
//        b.setCondition("0");
//        ObjectMapper o = new ObjectMapper();
//        List<ZjsTransaction> l = mapper.queryTaskToProcess(b);
//        System.out.println(o.writeValueAsString(l));
//        l = mapper.queryTaskToProcessTest(b);
//
//        System.out.println(o.writeValueAsString(l));
//
//        System.out.println(mapper.queryTaskToProcessTest(b));
//    }
//
//}
