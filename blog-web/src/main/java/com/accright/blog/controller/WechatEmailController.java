package com.accright.blog.controller;

import com.accright.blog.business.entity.MailDetail;
import com.accright.blog.business.service.MailService;
import com.accright.blog.framework.config.FreeMarkerConfig;
import com.accright.blog.framework.object.ResponseVO;
import com.accright.blog.util.ResultUtil;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来进行微信小程序发送邮件的DEMO
 */
@Slf4j
@RestController
@RequestMapping("/mail")
public class WechatEmailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfiger;

    @GetMapping("/send")
    @ResponseBody
    public ResponseVO send(String mailAddress){
        //mailAddress = "my_romantic_story@163.com";
        if (mailAddress == null || "".equals(mailAddress)){
            return ResultUtil.error("输入的邮箱不能为空！");
        }
        String errMessage = "";
        //邮箱校验方式
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regEx1);
        Matcher matcher = pattern.matcher(mailAddress);
        if (!matcher.matches()){
            return ResultUtil.error("邮箱不符合规则！");
        }
        try{
            //发送Html格式的邮件
            Template template = freeMarkerConfiger.getConfiguration().getTemplate("wechat-mail.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, new HashMap<>());
            MailDetail mailDetail = new MailDetail("职前公社求职大礼包！",
                    mailAddress,mailAddress,
                    text);
            mailService.send(mailDetail);
        }catch (Exception e){
            log.error("发送邮件出现异常！", e);
            return ResultUtil.error("发送邮件出现异常！"+e.getMessage());
        }
        return ResultUtil.success("向地址："+mailAddress+"发送邮件成功！");
    }
}
