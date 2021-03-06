package cn.gaorongyi.split.service;

import cn.gaorongyi.split.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gaorongyi
 */
public interface ITestService extends IService<Test> {

    List<Test> pageList(int pageSize);

    @Transactional
    boolean batchInsert(List<Test> testList);
}
