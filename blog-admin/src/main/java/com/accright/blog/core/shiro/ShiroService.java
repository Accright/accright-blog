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
package com.accright.blog.core.shiro;

import com.accright.blog.business.entity.Resources;
import com.accright.blog.business.entity.User;
import com.accright.blog.business.service.SysResourcesService;
import com.accright.blog.business.service.SysUserService;
import com.accright.blog.core.shiro.realm.ShiroRealm;
import com.accright.blog.framework.holder.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro-权限相关的业务处理
 *

 * @date 2018/4/25 14:37
 * @since 1.0
 */
@Slf4j
@Service
public class ShiroService {

    @Autowired
    private SysResourcesService resourcesService;
    @Autowired
    private SysUserService userService;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        /*
            配置访问权限
            - anon:所有url都都可以匿名访问
            - authc: 需要认证才能进行访问
            - user:配置记住我或认证通过可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/passport/logout", "logout");
        filterChainDefinitionMap.put("/passport/login", "anon");
        filterChainDefinitionMap.put("/passport/signin", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/plugin/**", "anon");
        filterChainDefinitionMap.put("/vendors/**", "anon");
        filterChainDefinitionMap.put("/getKaptcha", "anon");
        // 加载数据库中配置的资源权限列表
        List<Resources> resourcesList = resourcesService.listUrlAndPermission();
        for (Resources resources : resourcesList) {
            if (!StringUtils.isEmpty(resources.getUrl()) && !StringUtils.isEmpty(resources.getPermission())) {
                String permission = "perms[" + resources.getPermission() + "]";
                filterChainDefinitionMap.put(resources.getUrl(), permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {
        ShiroFilterFactoryBean shirFilter = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shirFilter) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shirFilter.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shirFilter.getFilterChainDefinitionMap().clear();
            shirFilter.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shirFilter.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }

    /**
     * 重新加载用户权限
     *
     * @param user
     */
    public void reloadAuthorizingByUserId(User user) {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user.getId(), realmName);
        subject.runAs(principals);
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();

        log.info("用户[{}]的权限更新成功！！", user.getUsername());

    }

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId
     */
    public void reloadAuthorizingByRoleId(Long roleId) {
        List<User> userList = userService.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        for (User user : userList) {
            reloadAuthorizingByUserId(user);
        }
    }

}
