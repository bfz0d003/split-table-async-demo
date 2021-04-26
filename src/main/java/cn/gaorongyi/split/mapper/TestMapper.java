package cn.gaorongyi.split.mapper;

import cn.gaorongyi.split.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author gaorongyi
 */
public interface TestMapper extends BaseMapper<Test> {
    long selectNextVal();
    void insertOne(Test test);
    void batchInsert0(List<Test> list);
    void batchInsert1(List<Test> list);
    void batchInsert2(List<Test> list);
    void batchInsert3(List<Test> list);
    void batchInsert4(List<Test> list);
    void batchInsert5(List<Test> list);
    void batchInsert6(List<Test> list);
    void batchInsert7(List<Test> list);
    void batchInsert8(List<Test> list);
    void batchInsert9(List<Test> list);
}
