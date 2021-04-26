package cn.gaorongyi.split;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gaorongyi
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@ServletComponentScan
@EnableAsync
@EnableTransactionManagement
@MapperScan("cn.gaorongyi.split.**.mapper*")
public class SplitTableDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SplitTableDemoApplication.class, args);
	}
}
