package com.accright.blog.framework.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * COS的配置文件
 */

@Configuration
@ConfigurationProperties(prefix = "cos")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class COSProperties {
    private String secretId;
    private String secretKey;
    private String regionName;
    private String bucketName;
}
