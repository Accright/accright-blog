/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.accright.blog.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.accright.blog.business.entity.Comment;
import com.accright.blog.business.entity.Config;
import com.accright.blog.business.entity.Link;
import com.accright.blog.business.entity.MailDetail;
import com.accright.blog.business.entity.Template;
import com.accright.blog.business.enums.TemplateKeyEnum;
import com.accright.blog.business.service.MailService;
import com.accright.blog.business.service.SysConfigService;
import com.accright.blog.business.service.SysTemplateService;
import com.accright.blog.framework.property.MailProperties;
import com.accright.blog.util.FreeMarkerUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮件发送
 *
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private SysTemplateService templateService;
    @Autowired
    private SysConfigService configService;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private MailProperties mailProperties;

    /**
     * 普通的发送
     */
    @Override
    @Async
    public void send(MailDetail mailDetail) {
        sendMessage(mailDetail, from);
    }

    /**
     * 发送友情链接邮件通知
     */
    @Override
    @Async
    public void send(Link link, TemplateKeyEnum keyEnum) {
        if (!StringUtils.isEmpty(link.getEmail())) {
            Config config = configService.get();
            Template template = templateService.getTemplate(keyEnum);
            String temXml = template.getRefValue();
            Map<String, Object> map = new HashMap<>(2);
            map.put("link", link);
            map.put("config", config);
            String mailContext = FreeMarkerUtil.template2String(temXml, map, true);

            MailDetail mailDetail = new MailDetail("友情链接操作通知", link.getEmail(), link.getName(), mailContext);
            send(mailDetail);
        }
        this.sendToAdmin(link);
    }

    /**
     * 发送评论邮件通知
     */
    @Override
    @Async
    public void send(Comment comment, TemplateKeyEnum keyEnum, boolean audit) {
        if (comment == null || StringUtils.isEmpty(comment.getEmail())) {
            this.sendToAdmin(comment);
            return;
        }
        Config config = configService.get();
        Template template = templateService.getTemplate(keyEnum);
        String temXml = template.getRefValue();
        Map<String, Object> map = new HashMap<>(2);
        map.put("comment", comment);
        map.put("config", config);
        String mailContext = FreeMarkerUtil.template2String(temXml, map, true);
        String subject = "评论回复通知";
        if (audit) {
            subject = "评论审核结果通知";
        }
        MailDetail mailDetail = new MailDetail(subject, comment.getEmail(), comment.getNickname(), mailContext);
        send(mailDetail);
        if (!audit) {
            this.sendToAdmin(comment);
        }
    }

    /**
     * 发送到管理员的友链操作通知
     */
    @Override
    @Async
    public void sendToAdmin(Link link) {
        Config config = configService.get();
        Template template = templateService.getTemplate(TemplateKeyEnum.TM_LINKS_TO_ADMIN);
        String temXml = template.getRefValue();
        Map<String, Object> map = new HashMap<>(1);
        map.put("link", link);
        String mailContext = FreeMarkerUtil.template2String(temXml, map, true);
        String adminEmail = config.getAuthorEmail();
        adminEmail = StringUtils.isEmpty(adminEmail) ? "yadong.zhang0415@gmail.com"
                                                     : (adminEmail.contains("#") ? adminEmail.replace("#", "@")
                                                                                 : adminEmail);
        MailDetail mailDetail = new MailDetail("有新的友链消息", adminEmail, config.getAuthorName(), mailContext);
        send(mailDetail);
    }

    /**
     * 发送到管理员的评论通知
     */
    @Override
    @Async
    public void sendToAdmin(Comment comment) {
        Config config = configService.get();
        Template template = templateService.getTemplate(TemplateKeyEnum.TM_NEW_COMMENT);
        String temXml = template.getRefValue();
        Map<String, Object> map = new HashMap<>(2);
        map.put("comment", comment);
        map.put("config", config);
        String mailContext = FreeMarkerUtil.template2String(temXml, map, true);
        String subject = "有新的评论消息";
        String adminEmail = config.getAuthorEmail();
        adminEmail = StringUtils.isEmpty(adminEmail) ? "yadong.zhang0415@gmail.com"
                                                     : (adminEmail.contains("#") ? adminEmail.replace("#", "@")
                                                                                 : adminEmail);
        MailDetail mailDetail = new MailDetail(subject, adminEmail, config.getAuthorName(), mailContext);
        send(mailDetail);
    }


    private boolean sendMessage(MailDetail detail, String from) {
        log.info("Start to send html email for [{}({})]", detail.getToUsername(), detail.getToMailAddress());
        if (StringUtils.isEmpty(detail.getToMailAddress())) {
            log.warn("邮件接收者为空！");
            return false;
        }
        return false;
    }
}
