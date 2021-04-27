package cn.gaorongyi.split.controller;

import cn.gaorongyi.split.entity.Test;
import cn.gaorongyi.split.model.SnowFlake;
import cn.gaorongyi.split.service.ITestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author gaorongyi
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private ITestService testService;
    @Resource
    private SnowFlake snowFlake;

    @RequestMapping("/test")
    public Object test() {
        Test test = new Test();
        test.setCreateTime(new Date());
        String name = String.valueOf(new Random().nextInt(10000));
        test.setName(name);
        testService.insertOne(test);
        return test;
    }

    /**
     * 序列实现ID自增，高并发事务不适用
     *
     * @param count 模拟数据数量
     * @return
     */
    @RequestMapping("/testBatch")
    public Object testBatch(int count) {
        String name;
        List<Test> testList = new ArrayList<>();
        Test test;
        for (int i = 0; i < count; i++) {
            test = new Test();
            test.setId(testService.selectNextVal());
            test.setCreateTime(new Date());
            name = String.valueOf(new Random().nextInt(10000));
            test.setName(name);
            testList.add(test);
        }
        testService.batchInsert(testList);
        return testList;
    }

    /**
     * 雪花算法ID自增，注册为dubbo服务，高并发事务适用
     *
     * @param count 模拟数据数量
     * @return
     */
    @RequestMapping("/testBatchSnowFlake")
    public Object testBatchSnowFlake(int count) {
        String name;
        List<Test> testList = new ArrayList<>();
        Test test;
        for (int i = 0; i < count; i++) {
            test = new Test();
            test.setId(snowFlake.nextId());
            test.setCreateTime(new Date());
            name = String.valueOf(new Random().nextInt(10000));
            test.setName(name);
            testList.add(test);
        }
        if (testService.batchInsert(testList)) {
            return testList;
        }
        return false;
    }
}
