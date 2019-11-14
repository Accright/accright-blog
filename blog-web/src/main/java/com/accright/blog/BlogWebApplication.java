package com.accright.blog;

import com.accright.blog.business.consts.DateConst;
import com.accright.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

/**
 * 程序启动类
 *

 * @version 1.0
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class BlogWebApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogWebApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) {
		log.info("博客部署完成，当前时间：" + DateUtil.date2Str(new Date(), DateConst.YYYY_MM_DD_HH_MM_SS_EN));
	}
}
