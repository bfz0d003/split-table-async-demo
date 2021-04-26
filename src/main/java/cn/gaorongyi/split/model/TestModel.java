package cn.gaorongyi.split.model;

import cn.gaorongyi.split.entity.Test;
import cn.gaorongyi.split.mapper.TestMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author gaorongyi
 */
@Component
public class TestModel {
    @Resource
    private TestMapper testMapper;

    @Async
    public void batchInsertAsync(List<Test> testList, int index, CountDownLatch countDownLatch) {
        switch (index) {
            case 0:
                testMapper.batchInsert0(testList);
                break;
            case 1:
                testMapper.batchInsert1(testList);
                break;
            case 2:
                testMapper.batchInsert2(testList);
                break;
            case 3:
                testMapper.batchInsert3(testList);
                break;
            case 4:
                testMapper.batchInsert4(testList);
                break;
            case 5:
                testMapper.batchInsert5(testList);
                break;
            case 6:
                testMapper.batchInsert6(testList);
                break;
            case 7:
                testMapper.batchInsert7(testList);
                break;
            case 8:
                testMapper.batchInsert8(testList);
                break;
            case 9:
                testMapper.batchInsert9(testList);
                break;
            default:
                throw new RuntimeException("BATCH INSERT INDEX ERROR");
        }
        System.out.println("# " + index + ": " + JSON.toJSON(testList));
        countDownLatch.countDown();
    }
}
