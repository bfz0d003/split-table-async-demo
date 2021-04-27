package cn.gaorongyi.split.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaorongyi
 */
@Data
@EqualsAndHashCode
@TableName("test")
@ToString
public class Test implements Serializable, Comparable<Test> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Date createTime;

    @Override
    public int compareTo(Test o) {
        if (this.id.equals(o.id)) {
            return 0;
        } else if (this.id > o.id) {
            return 1;
        } else {
            return -1;
        }
    }
}
