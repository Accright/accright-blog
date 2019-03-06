package com.accright.blog.framework.filter;

import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Component
@WebFilter(filterName = "MailFilter",urlPatterns = "/mail/*")
public class MailFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String referer = request.getHeader("referer");
        if (!TextUtils.isEmpty(referer)){
            if (referer.startsWith("https://servicewechat.com/wx9648d5e82288f4d1")){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                wrapper.sendRedirect("/error/wechatError");
                return;
            }
        }else {
            wrapper.sendRedirect("/error/wechatError");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
