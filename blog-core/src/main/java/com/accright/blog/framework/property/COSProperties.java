package com.accright.blog.framework.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * COS的配置文件
 */

@Component
@ConfigurationProperties(prefix = "cos")
@Data
@Order(-1)
public class COSProperties {
    private String secretId;
    private String secretKey;
    private String regionName;
    private String bucketName;
}
