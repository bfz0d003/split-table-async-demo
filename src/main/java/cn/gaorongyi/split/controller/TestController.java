package cn.gaorongyi.split.controller;

import cn.gaorongyi.split.entity.Test;
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

    @RequestMapping("/test")
    public Object test() {
        Test test = new Test();
        test.setCreateTime(new Date());
        String name = String.valueOf(new Random().nextInt(10000));
        test.setName(name);
        testService.insertOne(test);
        return test;
    }

    @RequestMapping("/testBatch")
    public Object test(int count) {
        String name;
        List<Test> testList = new ArrayList<>();
        Test test;
        for (int i = 0; i< count; i++) {
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
}
