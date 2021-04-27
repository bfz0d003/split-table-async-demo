package cn.gaorongyi.split.mapper;

import cn.gaorongyi.split.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gaorongyi
 */
public interface TestMapper extends BaseMapper<Test> {
    List<String> selectSplitTableNames();

    List<Test> list(@Param("index") int index, @Param("pageSize") int pageSize);

    void batchInsert(@Param("list") List<Test> list, @Param("index") int index);
}
