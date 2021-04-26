package cn.gaorongyi.split.service;

import cn.gaorongyi.split.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author gaorongyi
 */
public interface ITestService extends IService<Test> {
    long selectNextVal();
    void insertOne(Test test);
    void batchInsert(List<Test> testList);
}
