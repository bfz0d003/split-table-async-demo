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
    /**
     * 雪花算法ID自增，注册为dubbo服务或分节点ID，高并发事务适用
     */
    @Resource
    private SnowFlake snowFlake;

    /**
     * 批量插入
     *
     * @param count 模拟数据数量
     * @return Object
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

    /**
     * 模拟分页
     *
     * @param pageSize 模拟数据数量
     * @return Object
     */
    @RequestMapping("/testPage")
    public Object testPage(int pageSize) {
        return testService.pageList(pageSize);
    }
}
