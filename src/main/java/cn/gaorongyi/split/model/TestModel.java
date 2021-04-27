package cn.gaorongyi.split.model;

import cn.gaorongyi.split.entity.Test;
import cn.gaorongyi.split.mapper.TestMapper;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author gaorongyi
 */
@Log4j2
@Component
public class TestModel {
    @Resource
    private TestMapper testMapper;

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertAsync(List<Test> testList, int index, CountDownLatch countDownLatch, CountDownLatch mainLatch, RollBack rollBack, Map<Integer, Boolean> flagMap) {
        try {
            testMapper.batchInsert(testList, index);
            log.info("# " + index + ": " + JSON.toJSON(testList));
            // TODO: 2021/4/27 子线程异常测试代码
            if (index == 9) {
                throw new RuntimeException("TEST ROLLBACK");
            }
            flagMap.put(index, true);
        } catch (Exception e) {
            flagMap.put(index, false);
            e.printStackTrace();
            throw e;
        } finally {
            countDownLatch.countDown();
        }
        try {
            mainLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (rollBack.isRollBack()) {
            log.error("MAIN THREAD ROLLBACK FLAG IS TRUE");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
