package com.accright.blog.plugin;

import com.accright.blog.framework.property.COSProperties;
import com.accright.blog.framework.property.RedisProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * COS文件操作类 --单例模式
 */
public class COSApi {
    private static final Object LOCK = new Object();//加锁

    @Autowired
    private COSProperties cosProperties; //-- 无法取得？？？

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 获取属性文件的值  --无法取得？？？
     */
    @Value("${cos.secretId}")
    private String secretId;
    @Value("${cos.secretKey}")
    private String secretKey;
    @Value("${cos.regionName}")
    private String regionName;

    @Value("${banner.charset}")
    private String charset;

    private COSCredentials cred;
    private ClientConfig clientConfig;
    private COSClient cosclient;
    private String bucketName = "";

    private static final String BUCKET_URL = "https://springboot-blog-1256194683.cosbj.myqcloud.com/";
    //构造函数
    private COSApi()
    {
        // 1 初始化用户身份信息(secretId, secretKey)
        /*this.cred = new BasicCOSCredentials(cosProperties.getSecretId(), cosProperties.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        this.clientConfig = new ClientConfig(new Region(cosProperties.getRegionName()));
        // 3 生成cos客户端
        this.cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        this.bucketName = cosProperties.getBucketName();*/

        this.cred = new BasicCOSCredentials("","");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        this.clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        this.cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        this.bucketName = "springboot-blog-1256194683";

    }

    //单例模式创建
    public static COSApi getInstance() {
        synchronized (LOCK) {
            return new COSApi();
        }
    }



    /**
     * 使用文件流上传文件至COS
     * @param file
     * @return
     */
    public String uploadFileToCOS(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();//获取上传文件的名称用于获取后缀
        String prex = fileName.substring(fileName.lastIndexOf("."));//获取文件的后缀
        String key = new Date().getTime() + prex;//设置文件的key

        InputStream inputStream = file.getInputStream();//获取文件输入流
        ObjectMetadata objectMetadata = new ObjectMetadata();//获取文件元信息

        // 设置输入流长度为 500
        objectMetadata.setContentLength(file.getSize());
        // 设置 Content type, 默认是 application/octet-stream
        objectMetadata.setContentType(file.getContentType());

        PutObjectResult putObjectResult = cosclient.putObject(bucketName, key, inputStream,objectMetadata);
        inputStream.close();//关闭输入流
        //设置过期时间   ---可以返回带有过期时间的URL
        //Date expiration = new Date(new Date().getTime() + 30L * 60L * 1000L);
        //URL urlx = cosclient.generatePresignedUrl(bucketName, key, expiration,HttpMethodName.GET);
        //return urlx.toString();

        //关闭客户端
        cosclient.shutdown();
        //返回URL
        return BUCKET_URL+key;
    }

    public boolean deleteCOSFile(String key)
    {
        try {
            cosclient.deleteObject(bucketName,key);
            return true;
        }catch (CosClientException cosClientException){
            return false;
        }
    }
}
