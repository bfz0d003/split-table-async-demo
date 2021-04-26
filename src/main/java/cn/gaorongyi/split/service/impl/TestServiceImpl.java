package cn.gaorongyi.split.service.impl;

import cn.gaorongyi.split.entity.Test;
import cn.gaorongyi.split.mapper.TestMapper;
import cn.gaorongyi.split.model.TestModel;
import cn.gaorongyi.split.service.ITestService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author gaorongyi
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Resource
    private TestModel testModel;
    @Resource
    private TestMapper testMapper;

    @Override
    public long selectNextVal() {
        return testMapper.selectNextVal();
    }

    @Override
    public void insertOne(Test test) {
        testMapper.insertOne(test);
    }

    @Override
    public void batchInsert(List<Test> testList) {
        Map<Integer, List<Test>> map = new HashMap<>(15);
        for (Test test : testList) {
            switch (test.getName().substring(test.getName().length()-1)) {
                case "0":
                    if (!map.containsKey(0)) {
                        map.put(0, new ArrayList<>());
                    }
                    map.get(0).add(test);
                    break;
                case "1":
                    if (!map.containsKey(1)) {
                        map.put(1, new ArrayList<>());
                    }
                    map.get(1).add(test);
                    break;
                case "2":
                    if (!map.containsKey(2)) {
                        map.put(2, new ArrayList<>());
                    }
                    map.get(2).add(test);
                    break;
                case "3":
                    if (!map.containsKey(3)) {
                        map.put(3, new ArrayList<>());
                    }
                    map.get(3).add(test);
                    break;
                case "4":
                    if (!map.containsKey(4)) {
                        map.put(4, new ArrayList<>());
                    }
                    map.get(4).add(test);
                    break;
                case "5":
                    if (!map.containsKey(5)) {
                        map.put(5, new ArrayList<>());
                    }
                    map.get(5).add(test);
                    break;
                case "6":
                    if (!map.containsKey(6)) {
                        map.put(6, new ArrayList<>());
                    }
                    map.get(6).add(test);
                    break;
                case "7":
                    if (!map.containsKey(7)) {
                        map.put(7, new ArrayList<>());
                    }
                    map.get(7).add(test);
                    break;
                case "8":
                    if (!map.containsKey(8)) {
                        map.put(8, new ArrayList<>());
                    }
                    map.get(8).add(test);
                    break;
                case "9":
                    if (!map.containsKey(9)) {
                        map.put(9, new ArrayList<>());
                    }
                    map.get(9).add(test);
                    break;
                default:
                    break;
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(map.size());
        for (Map.Entry<Integer, List<Test>> entry : map.entrySet()) {
            testModel.batchInsertAsync(entry.getValue(), entry.getKey(), countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("# ROLLBACK !!!");
            e.printStackTrace();
        }
        System.out.println("# BATCH INSERT SUCCESS");
    }
}
