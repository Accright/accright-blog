/**
 * MIT License
 */
package com.accright.blog.framework.config;

import org.springframework.stereotype.Component;

import tk.mybatis.spring.annotation.MapperScan;

/**
 *
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
@MapperScan("com.accright.blog.persistence.mapper")
public class MybatisConfig {
}
